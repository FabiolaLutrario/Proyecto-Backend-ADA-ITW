package org.ada.farmacia.service;

import org.ada.farmacia.dto.LaboratorioDTO;
import org.ada.farmacia.entity.Laboratorio;
import org.ada.farmacia.exceptions.ExistingResourceException;
import org.ada.farmacia.exceptions.ResourceNotDeletedException;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.LaboratorioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
        laboratorioDTO.setId(laboratorio.getId());
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
            throw new ResourceNotFoundException("El laboratorio consultado no existe.");
        }
        return mapToDTO(laboratorio.get());
    }

    public LaboratorioDTO retrieveByNombre(String nombre){
        Optional<Laboratorio> laboratorio= laboratorioRepository.findByNombre(nombre);
        if(laboratorio.isEmpty()){
            throw new ResourceNotFoundException("El laboratorio consultado no existe.");
        }
        return mapToDTO(laboratorio.get());
    }

    public void delete(Integer laboratorioId) {
        try {
            laboratorioRepository.deleteById(laboratorioId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("El laboratorio que está intentando eliminar no existe.");
        } catch (DataIntegrityViolationException e){
            throw  new ResourceNotDeletedException("No se puede eliminar el laboratorio porque tiene medicamentos asociados.");
        }
    }

    public void replace(Integer laboratorioId, LaboratorioDTO laboratorioDTO) {
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(laboratorioId);
        if (laboratorio.isEmpty()) {
            throw new ResourceNotFoundException("El laboratorio que se está intentando modificar, no existe.");
        }
        Laboratorio laboratorioToReplace = laboratorio.get();
        laboratorioToReplace.setNombre (laboratorioDTO.getNombre());
        laboratorioRepository.save(laboratorioToReplace);
    }

    private void checkForExistingLaboratorio(String nombre){
        if (laboratorioRepository.findByNombre(nombre).isPresent()) {
            throw new ExistingResourceException("El laboratorio que se está intentando crear, ya existe.");
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
