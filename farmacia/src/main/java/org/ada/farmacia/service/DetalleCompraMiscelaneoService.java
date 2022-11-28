package org.ada.farmacia.service;

import org.ada.farmacia.dto.DetalleCompraMedicamentoDTO;
import org.ada.farmacia.dto.DetalleCompraMiscelaneoDTO;
import org.ada.farmacia.entity.*;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.DetalleCompraMiscelaneoRepository;
import org.ada.farmacia.repository.MiscelaneoRepository;
import org.springframework.stereotype.Service;

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

    public void create(List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS, Factura factura) {

//Código que agregué de forma rudimentaria para obtener el Objeto medicamento por Id (Se puede mejorar)

        for (DetalleCompraMiscelaneoDTO detalleCompraMiscelaneoDTO1 : detalleCompraMiscelaneoDTOS) {

            Optional<Miscelaneo> miscelaneo = miscelaneoRepository.findById(detalleCompraMiscelaneoDTO1.getIdMiscelaneo());
            if (miscelaneo.isEmpty()) {
                throw new ResourceNotFoundException("EL producto que está intentando facturar no existe.");
            }
            Miscelaneo miscelaneoGetter = miscelaneo.get();
            DetalleCompraMiscelaneo detalleCompraMiscelaneo = mapToEntity(detalleCompraMiscelaneoDTO1, factura, miscelaneoGetter);


            //Hasta acá el código que mencioné arriba que agregué

            List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos = detalleCompraMiscelaneoDTOS.stream()
                    .map(detalleCompraMiscelaneoDTO -> mapToEntity(detalleCompraMiscelaneoDTO, factura, miscelaneoGetter))
                    .collect(Collectors.toList());
            detalleCompraMiscelaneoRepository.saveAll(detalleCompraMiscelaneos);
        }
    }

    private DetalleCompraMiscelaneo mapToEntity(DetalleCompraMiscelaneoDTO detalleCompraMiscelaneoDTO, Factura factura, Miscelaneo miscelaneo) {
        DetalleCompraMiscelaneo detalleCompraMiscelaneo = new DetalleCompraMiscelaneo(detalleCompraMiscelaneoDTO.getCantidad(),
                miscelaneo,factura);
        detalleCompraMiscelaneo.setPrecioUnitario(miscelaneo.getPrecioVenta());
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
        detalleCompraMiscelaneoDTO.setPrecioUnitario(detalleCompraMiscelaneo.getPrecioUnitario());
        detalleCompraMiscelaneoDTO.setPrecioTotal(detalleCompraMiscelaneo.getPrecioTotal());

        return detalleCompraMiscelaneoDTO;
    }
}
