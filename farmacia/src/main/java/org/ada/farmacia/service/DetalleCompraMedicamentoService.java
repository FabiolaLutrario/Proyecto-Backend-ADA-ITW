package org.ada.farmacia.service;

import org.ada.farmacia.dto.DetalleCompraMedicamentoDTO;
import org.ada.farmacia.entity.*;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.DetalleCompraMedicamentoRepository;
import org.ada.farmacia.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleCompraMedicamentoService {

    private final DetalleCompraMedicamentoRepository detalleCompraMedicamentoRepository;
    private final MedicamentoRepository medicamentoRepository;

    public DetalleCompraMedicamentoService(DetalleCompraMedicamentoRepository detalleCompraMedicamentoRepository, MedicamentoRepository medicamentoRepository) {
        this.detalleCompraMedicamentoRepository = detalleCompraMedicamentoRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public void create(List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS, Factura factura) {

        //Código que agregué de forma rudimentaria para obtener el Objeto medicamento por Id (Se puede mejorar)

        for (DetalleCompraMedicamentoDTO detalleCompraMedicamentoDTO1 : detalleCompraMedicamentoDTOS) {

            Optional<Medicamento> medicamento = medicamentoRepository.findById(detalleCompraMedicamentoDTO1.getIdMedicamento());
            if (medicamento.isEmpty()) {
                throw new ResourceNotFoundException("EL medicamento que está intentando facturar no existe.");
            }
            Medicamento medicamentoGetter=medicamento.get();
            DetalleCompraMedicamento detalleCompraMedicamento = mapToEntity(detalleCompraMedicamentoDTO1, factura, medicamentoGetter);


        //Hasta acá el código que mencioné arriba que agregué

        List<DetalleCompraMedicamento> detalleCompraMedicamentos = detalleCompraMedicamentoDTOS.stream()
                .map(detalleCompraMedicamentoDTO -> mapToEntity(detalleCompraMedicamentoDTO, factura, medicamentoGetter))
                .collect(Collectors.toList());
        detalleCompraMedicamentoRepository.saveAll(detalleCompraMedicamentos);
        }
    }

    private DetalleCompraMedicamento mapToEntity(DetalleCompraMedicamentoDTO detalleCompraMedicamentoDTO, Factura factura, Medicamento medicamento) {
        DetalleCompraMedicamento detalleCompraMedicamento = new DetalleCompraMedicamento(detalleCompraMedicamentoDTO.getCantidad(),
                medicamento,factura);
        detalleCompraMedicamento.setPrecioUnitario(medicamento.getPrecioVenta());
        detalleCompraMedicamento.setPrecioTotal(medicamento.getPrecioVenta()
        * detalleCompraMedicamentoDTO.getCantidad());

        return detalleCompraMedicamento;
    }

    public List<DetalleCompraMedicamentoDTO> mapToDTOS(List<DetalleCompraMedicamento> detalleCompraMedicamentos) {

        return detalleCompraMedicamentos.stream()
                .map(detalleCompraMedicamento -> mapToDTO(detalleCompraMedicamento))
                .collect(Collectors.toList());
    }

    private DetalleCompraMedicamentoDTO mapToDTO (DetalleCompraMedicamento detalleCompraMedicamento){
        DetalleCompraMedicamentoDTO detalleCompraMedicamentoDTO = new DetalleCompraMedicamentoDTO(detalleCompraMedicamento.getMedicamento().getId(),
                detalleCompraMedicamento.getCantidad());
        detalleCompraMedicamentoDTO.setPrecioUnitario(detalleCompraMedicamento.getPrecioUnitario());
        detalleCompraMedicamentoDTO.setPrecioTotal(detalleCompraMedicamento.getPrecioTotal());

        return detalleCompraMedicamentoDTO;
    }
}
