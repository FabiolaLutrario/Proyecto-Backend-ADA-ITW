package org.ada.farmacia.service;

import org.ada.farmacia.dto.MedicamentoDTO;
import org.ada.farmacia.entity.Laboratorio;
import org.ada.farmacia.entity.Medicamento;
import org.ada.farmacia.exceptions.ExistingResourceException;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;


    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public List<MedicamentoDTO> create (List<MedicamentoDTO> medicamentoDTOS, Laboratorio laboratorio){
        List<Medicamento> medicamentos = medicamentoDTOS.stream()
                .map(medicamentoDTO -> mapToEntity(medicamentoDTO, laboratorio))
                .collect(Collectors.toList());
        for (Medicamento medicamento: medicamentos) {
            checkForExistingMedicamento(medicamento.getId());
        }
        medicamentoRepository.saveAll(medicamentos);

        return medicamentoDTOS;
    }

    public List<MedicamentoDTO> retrieveAll(){
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    public MedicamentoDTO retrieveById(String id){
        Optional<Medicamento> medicamento= medicamentoRepository.findById(id);
        if(medicamento.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return mapToDTO(medicamento.get());
    }

    public List<MedicamentoDTO> retrieveByNombreComercial(String nombreComercial){
        List<Medicamento> medicamentos = medicamentoRepository.findByNombreComercial(nombreComercial);
        if(medicamentos.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    public List<MedicamentoDTO> retrieveByNombreGenerico(String nombreGenerico){
        List<Medicamento> medicamentos = medicamentoRepository.findByNombreGenerico(nombreGenerico);
        if(medicamentos.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    private void checkForExistingMedicamento(String medicamentoId){
        if (medicamentoRepository.existsById(medicamentoId)) {
            throw new ExistingResourceException();
        }
    }

    private Medicamento mapToEntity(MedicamentoDTO medicamentoDTO, Laboratorio laboratorio) {
        Medicamento medicamento = new Medicamento(medicamentoDTO.getId(), medicamentoDTO.getNombreComercial(),
                medicamentoDTO.getNombreGenerico(), medicamentoDTO.getPresentacion(),
                medicamentoDTO.getPrincipioActivo(), medicamentoDTO.getDosis(), medicamentoDTO.getPrecioCompra(),
                medicamentoDTO.getStock(), laboratorio);

        return medicamento;
    }

    public List<MedicamentoDTO> mapToDTOS(List<Medicamento> medicamentos) {

        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    private MedicamentoDTO mapToDTO (Medicamento medicamento){
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(medicamento.getId(),medicamento.getNombreComercial(),
                medicamento.getNombreGenerico(),medicamento.getPresentacion(), medicamento.getPrincipioActivo(),
                medicamento.getDosis(),medicamento.getPrecioCompra(), medicamento.getStock(), medicamento.getLaboratorio().getNombre());

        return medicamentoDTO;

    }
}
