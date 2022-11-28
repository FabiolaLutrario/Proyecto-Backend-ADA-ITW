package org.ada.farmacia.service;

import org.ada.farmacia.dto.MiscelaneoDTO;
import org.ada.farmacia.entity.Miscelaneo;
import org.ada.farmacia.exceptions.ExistingResourceException;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.MiscelaneoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MiscelaneoService {

    private final MiscelaneoRepository miscelaneoRepository;


    public MiscelaneoService(MiscelaneoRepository miscelaneoRepository) {
        this.miscelaneoRepository = miscelaneoRepository;
    }

    public MiscelaneoDTO create(MiscelaneoDTO miscelaneoDTO) {
        Miscelaneo miscelaneo = mapToEntity(miscelaneoDTO);
        checkForExistingMiscelaneo(miscelaneo.getId());
        miscelaneo = miscelaneoRepository.save(miscelaneo);

        return miscelaneoDTO;
    }

    public List<MiscelaneoDTO> retrieveAll(){
        List<Miscelaneo> miscelaneos = miscelaneoRepository.findAll();
        return miscelaneos.stream()
                .map(miscelaneo -> mapToDTO(miscelaneo))
                .collect(Collectors.toList());
    }

    public MiscelaneoDTO retrieveById(String miscelaneoId){
        Optional<Miscelaneo> miscelaneo= miscelaneoRepository.findById(miscelaneoId);
        if(miscelaneo.isEmpty()){
            throw new ResourceNotFoundException("El producto que está intentando consultar no existe.");
        }
        return mapToDTO(miscelaneo.get());
    }

    public List<MiscelaneoDTO> retrieveByNombre(String miscelaneoNombre){
        List<Miscelaneo> miscelaneos = miscelaneoRepository.findByNombre(miscelaneoNombre);
        if(miscelaneos.isEmpty()){
            throw new ResourceNotFoundException("El producto que está intentando consultar no existe.");
        }
        return miscelaneos.stream()
                .map(miscelaneo -> mapToDTO(miscelaneo))
                .collect(Collectors.toList());
    }

    public void delete(String miscelaneoId) {
        try {
            miscelaneoRepository.deleteById(miscelaneoId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("El producto que está intentando eliminar no existe.");
        }
    }

    public void replace(String miscelaneoId, MiscelaneoDTO miscelaneoDTO) {
        Optional<Miscelaneo> miscelaneo = miscelaneoRepository.findById(miscelaneoId);
        if (miscelaneo.isEmpty()) {
            throw new ResourceNotFoundException("EL producto que está intentando modificar no existe.");
        }
        Miscelaneo miscelaneoToReplace = miscelaneo.get();
        miscelaneoToReplace.setNombre(miscelaneoDTO.getNombre());
        miscelaneoToReplace.setDescripcion(miscelaneoDTO.getDescripcion());
        miscelaneoToReplace.setPrecioCompra(miscelaneoDTO.getPrecioCompra());
        miscelaneoToReplace.setStock(miscelaneoDTO.getStock());

        miscelaneoRepository.save(miscelaneoToReplace);
    }

    public void modify(String miscelaneoId, Map<String, Object> fieldsToModify) {
        Optional<Miscelaneo> miscelaneo = miscelaneoRepository.findById(miscelaneoId);
        if (miscelaneo.isEmpty()) {
            throw new ResourceNotFoundException("EL producto que está intentando modificar no existe.");
        }
        Miscelaneo miscelaneoToModify = miscelaneo.get();

        fieldsToModify.forEach((key, value) ->
                miscelaneoToModify.modifyAttributeValue(key, value));
        miscelaneoRepository.save(miscelaneoToModify);
    }

    private Miscelaneo mapToEntity(MiscelaneoDTO miscelaneoDTO) {
        Miscelaneo miscelaneo = new Miscelaneo(miscelaneoDTO.getId(), miscelaneoDTO.getNombre(),
                miscelaneoDTO.getDescripcion(),miscelaneoDTO.getPrecioCompra(),
                miscelaneoDTO.getStock());
        miscelaneo.setPrecioVenta(miscelaneoDTO.getPrecioCompra()*1.30);

        return miscelaneo;
    }

    private MiscelaneoDTO mapToDTO (Miscelaneo miscelaneo){
        MiscelaneoDTO miscelaneoDTO = new MiscelaneoDTO(miscelaneo.getId(), miscelaneo.getNombre(),
                miscelaneo.getDescripcion(),miscelaneo.getPrecioCompra(),
                miscelaneo.getStock());

        return miscelaneoDTO;

    }

    private void checkForExistingMiscelaneo(String miscelaneoId){
        if (miscelaneoRepository.existsById(miscelaneoId)) {
            throw new ExistingResourceException("El producto que se está intentando crear, ya existe.");
        }
    }
}
