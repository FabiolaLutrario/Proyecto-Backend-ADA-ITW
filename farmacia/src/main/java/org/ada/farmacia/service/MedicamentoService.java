package org.ada.farmacia.service;

import org.ada.farmacia.dto.MedicamentoDTO;
import org.ada.farmacia.entity.Laboratorio;
import org.ada.farmacia.entity.Medicamento;
import org.ada.farmacia.exceptions.ExistingResourceException;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.LaboratorioRepository;
import org.ada.farmacia.repository.MedicamentoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final LaboratorioRepository laboratorioRepository;


    public MedicamentoService(MedicamentoRepository medicamentoRepository, LaboratorioRepository laboratorioRepository) {
        this.medicamentoRepository = medicamentoRepository;
        this.laboratorioRepository = laboratorioRepository;
    }

    public void create(List<MedicamentoDTO> medicamentoDTOS, Laboratorio laboratorio){
        List<Medicamento> medicamentos = medicamentoDTOS.stream()
                .map(medicamentoDTO -> mapToEntity(medicamentoDTO, laboratorio))
                .collect(Collectors.toList());
        for (Medicamento medicamento: medicamentos) {
            checkForExistingMedicamento(medicamento.getId());
        }
        medicamentoRepository.saveAll(medicamentos);
    }

    public MedicamentoDTO create(MedicamentoDTO medicamentoDTO, Integer laboratorioId){
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(laboratorioId);
        if(laboratorio.isEmpty()){
            throw new ResourceNotFoundException("El laboratorio al que está intentando asociar no existe.");
        }
        Medicamento medicamento = mapToEntity(medicamentoDTO, laboratorio.get());
        checkForExistingMedicamento(medicamento.getId());
        medicamento = medicamentoRepository.save(medicamento);

        return medicamentoDTO;
    }

    public List<MedicamentoDTO> retrieveAll(){
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    public MedicamentoDTO retrieveById(String medicamentoId){
        Optional<Medicamento> medicamento= medicamentoRepository.findById(medicamentoId);
        if(medicamento.isEmpty()){
            throw new ResourceNotFoundException("El medicamento que está intentando consultar no existe.");
        }
        return mapToDTO(medicamento.get());
    }

    public List<MedicamentoDTO> retrieveByNombreComercial(String medicamentoNombreComercial){
        List<Medicamento> medicamentos = medicamentoRepository.findByNombreComercial(medicamentoNombreComercial);
        if(medicamentos.isEmpty()){
            throw new ResourceNotFoundException("El medicamento que está intentando consultar no existe.");
        }
        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    public List<MedicamentoDTO> retrieveByNombreGenerico(String medicamentoNombreGenerico){
        List<Medicamento> medicamentos = medicamentoRepository.findByNombreGenerico(medicamentoNombreGenerico);
        if(medicamentos.isEmpty()){
            throw new ResourceNotFoundException("El medicamento que está intentando consultar no existe.");
        }
        return medicamentos.stream()
                .map(medicamento -> mapToDTO(medicamento))
                .collect(Collectors.toList());
    }

    public void delete(String medicamentoId) {
        try {
            medicamentoRepository.deleteById(medicamentoId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("El medicamento que está intendo eliminar no existe.");
        }
    }

    public void replace(String medicamentoId, MedicamentoDTO medicamentoDTO) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(medicamentoId);
        if (medicamento.isEmpty()) {
            throw new ResourceNotFoundException("EL medicamento que está intentando modificar no existe.");
        }
        Medicamento medicamentoToReplace = medicamento.get();
        medicamentoToReplace.setNombreComercial(medicamentoDTO.getNombreComercial());
        medicamentoToReplace.setNombreGenerico(medicamentoDTO.getNombreGenerico());
        medicamentoToReplace.setPresentacion(medicamentoDTO.getPresentacion());
        medicamentoToReplace.setPrincipioActivo(medicamentoDTO.getPrincipioActivo());
        medicamentoToReplace.setDosis(medicamentoDTO.getDosis());
        medicamentoToReplace.setPrecioCompra(medicamentoDTO.getPrecioCompra());
        medicamentoToReplace.setStock(medicamentoDTO.getStock());
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(medicamentoDTO.getLaboratorioId());
        if (laboratorio.isEmpty()) {
            throw new ResourceNotFoundException("EL laboratorio que está intentando asociar no existe.");
        }
        Laboratorio laboratorioToReplace = laboratorio.get();
        medicamentoToReplace.setLaboratorio(laboratorioToReplace);
        medicamentoRepository.save(medicamentoToReplace);
    }

    public void modify(String medicamentoId, Map<String, Object> fieldsToModify) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(medicamentoId);
        if (medicamento.isEmpty()) {
            throw new ResourceNotFoundException("El medicamento que está intentando modificar no existe");
        }
        Medicamento medicamentoToModify = medicamento.get();

        if (fieldsToModify.containsKey("laboratorio_id")){
            Optional<Laboratorio> laboratorio = laboratorioRepository.findById((Integer) fieldsToModify.get("laboratorio_id"));
            if (laboratorio.isEmpty()) {
                throw new ResourceNotFoundException("EL laboratorio que está intentando asociar no existe.");
            }
            Laboratorio laboratorioToReplace = laboratorio.get();
            fieldsToModify.put("laboratorio_id", laboratorioToReplace);
        }
        fieldsToModify.forEach((key, value) ->
                medicamentoToModify.modifyAttributeValue(key, value));
        medicamentoRepository.save(medicamentoToModify);
    }

    private void checkForExistingMedicamento(String medicamentoId){
        if (medicamentoRepository.existsById(medicamentoId)) {
            throw new ExistingResourceException("El medicamento que se está intentando crear, ya existe.");
        }
    }

    private Medicamento mapToEntity(MedicamentoDTO medicamentoDTO, Laboratorio laboratorio) {
        Medicamento medicamento = new Medicamento(medicamentoDTO.getId(), medicamentoDTO.getNombreComercial(),
                medicamentoDTO.getNombreGenerico(), medicamentoDTO.getPresentacion(),
                medicamentoDTO.getPrincipioActivo(), medicamentoDTO.getDosis(), medicamentoDTO.getPrecioCompra(),
                medicamentoDTO.getStock(), laboratorio);
        medicamento.setPrecioVenta(medicamentoDTO.getPrecioCompra()*1.30);

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
