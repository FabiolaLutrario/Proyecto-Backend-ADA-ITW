package org.ada.farmacia.service;

import org.ada.farmacia.dto.DetalleCompraMiscelaneoDTO;
import org.ada.farmacia.entity.*;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.DetalleCompraMiscelaneoRepository;
import org.ada.farmacia.repository.MiscelaneoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleCompraMiscelaneoService {

    private final DetalleCompraMiscelaneoRepository detalleCompraMiscelaneoRepository;
    private final MiscelaneoRepository miscelaneoRepository;

    public DetalleCompraMiscelaneoService(DetalleCompraMiscelaneoRepository detalleCompraMiscelaneoRepository, MiscelaneoRepository miscelaneoRepository) {
        this.detalleCompraMiscelaneoRepository = detalleCompraMiscelaneoRepository;
        this.miscelaneoRepository = miscelaneoRepository;
    }

    public List<DetalleCompraMiscelaneo> create(List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS, Factura factura) {

        List <DetalleCompraMiscelaneo> detalleCompraMiscelaneos = new ArrayList<>();
        for (DetalleCompraMiscelaneoDTO detalleCompraMiscelaneoDTO : detalleCompraMiscelaneoDTOS) {

            Optional<Miscelaneo> miscelaneo = miscelaneoRepository.findById(detalleCompraMiscelaneoDTO.getIdMiscelaneo());
            if (miscelaneo.isEmpty()) {
                throw new ResourceNotFoundException("EL producto que está intentando facturar no existe.");
            }
            DetalleCompraMiscelaneo detalleCompraMiscelaneo =mapToEntity(detalleCompraMiscelaneoDTO, factura,miscelaneo.get());
            detalleCompraMiscelaneoRepository.save(detalleCompraMiscelaneo);
            detalleCompraMiscelaneos.add(detalleCompraMiscelaneo);
        }

        return detalleCompraMiscelaneos;
    }

    private DetalleCompraMiscelaneo mapToEntity(DetalleCompraMiscelaneoDTO detalleCompraMiscelaneoDTO, Factura factura, Miscelaneo miscelaneo) {
        DetalleCompraMiscelaneo detalleCompraMiscelaneo = new DetalleCompraMiscelaneo(detalleCompraMiscelaneoDTO.getCantidad(),
                miscelaneo,factura);
        detalleCompraMiscelaneo.setPrecioTotal(miscelaneo.getPrecioVenta()
                * detalleCompraMiscelaneoDTO.getCantidad());

        return detalleCompraMiscelaneo;
    }

    public List<DetalleCompraMiscelaneoDTO> mapToDTOS(List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos) {

        return detalleCompraMiscelaneos.stream()
                .map(detalleCompraMiscelaneo -> mapToDTO(detalleCompraMiscelaneo))
                .collect(Collectors.toList());
    }

    private DetalleCompraMiscelaneoDTO mapToDTO (DetalleCompraMiscelaneo detalleCompraMiscelaneo){
        DetalleCompraMiscelaneoDTO detalleCompraMiscelaneoDTO = new DetalleCompraMiscelaneoDTO(detalleCompraMiscelaneo.getMiscelaneo().getId(),
                detalleCompraMiscelaneo.getCantidad());
        detalleCompraMiscelaneoDTO.setPrecioUnitario(detalleCompraMiscelaneo.getPrecioTotal()/detalleCompraMiscelaneo.getCantidad());
        detalleCompraMiscelaneoDTO.setPrecioTotal(detalleCompraMiscelaneo.getPrecioTotal());
        detalleCompraMiscelaneoDTO.setId(detalleCompraMiscelaneo.getId());

        return detalleCompraMiscelaneoDTO;
    }
}
