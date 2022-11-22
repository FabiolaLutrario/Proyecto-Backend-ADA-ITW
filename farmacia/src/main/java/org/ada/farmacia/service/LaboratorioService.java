package org.ada.farmacia.service;

import com.sun.jdi.IntegerValue;
import org.ada.farmacia.dto.LaboratorioDTO;
import org.ada.farmacia.dto.MedicamentoDTO;
import org.ada.farmacia.entity.Laboratorio;
import org.ada.farmacia.entity.Medicamento;
import org.ada.farmacia.exceptions.ExistingResourceException;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;
    private final MedicamentoService medicamentoService;

    public LaboratorioService(LaboratorioRepository laboratorioRepository, MedicamentoService medicamentoService) {
        this.laboratorioRepository = laboratorioRepository;
        this.medicamentoService = medicamentoService;
    }

    public LaboratorioDTO create(LaboratorioDTO laboratorioDTO) {
        Laboratorio laboratorio = mapToEntity(laboratorioDTO);
        checkForExistingLaboratorio(laboratorio.getNombre());
        laboratorio= laboratorioRepository.save(laboratorio);
        if(!CollectionUtils.isEmpty(laboratorioDTO.getMedicamentoDTOS())){
            medicamentoService.create(laboratorioDTO.getMedicamentoDTOS(), laboratorio);
            //Crea un laboratorio desde 0 con una lista de medicamentos asociados.
        }

        return laboratorioDTO;
    }

    public List<LaboratorioDTO> retrieveAll(){
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return laboratorios.stream()
                .map(laboratorio -> mapToDTO(laboratorio))
                .collect(Collectors.toList());
    }

    public LaboratorioDTO retrieveById(Integer id){
        Optional<Laboratorio> laboratorio= laboratorioRepository.findById(id);
        if(laboratorio.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return mapToDTO(laboratorio.get());
    }

    public LaboratorioDTO retrieveByNombre(String nombre){
        Optional<Laboratorio> laboratorio= laboratorioRepository.findByNombre(nombre);
        if(laboratorio.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return mapToDTO(laboratorio.get());
    }

    private void checkForExistingLaboratorio(String nombre){
        if (laboratorioRepository.findByNombre(nombre).isPresent()) {
            throw new ExistingResourceException();
        }
    }

    private Laboratorio mapToEntity(LaboratorioDTO laboratorioDTO) {
        Laboratorio laboratorio = new Laboratorio(laboratorioDTO.getNombre());

        return laboratorio;
    }

    private LaboratorioDTO mapToDTO (Laboratorio laboratorio){
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO(laboratorio.getNombre(),
                medicamentoService.mapToDTOS(laboratorio.getMedicamentos()));
        laboratorioDTO.setId(laboratorio.getId());

        return laboratorioDTO;

    }
}
