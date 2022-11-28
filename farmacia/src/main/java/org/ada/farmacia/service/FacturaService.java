package org.ada.farmacia.service;

import net.bytebuddy.asm.Advice;
import org.ada.farmacia.dto.FacturaDTO;
import org.ada.farmacia.entity.Cliente;
import org.ada.farmacia.entity.DetalleCompraMedicamento;
import org.ada.farmacia.entity.DetalleCompraMiscelaneo;
import org.ada.farmacia.entity.Factura;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.ClienteRepository;
import org.ada.farmacia.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final DetalleCompraMedicamentoService detalleCompraMedicamentoService;
    private final DetalleCompraMiscelaneoService detalleCompraMiscelaneoService;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public FacturaService(FacturaRepository facturaRepository, ClienteRepository clienteRepository, DetalleCompraMedicamentoService detalleCompraMedicamentoService, DetalleCompraMiscelaneoService detalleCompraMiscelaneoService) {
        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
        this.detalleCompraMedicamentoService = detalleCompraMedicamentoService;
        this.detalleCompraMiscelaneoService = detalleCompraMiscelaneoService;
    }

    public FacturaDTO create(FacturaDTO facturaDTO, String clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("El cliente no existe.");
        }
        Factura factura = mapToEntity(facturaDTO, cliente.get());
        factura = facturaRepository.save(factura);
        facturaDTO.setId(factura.getId());
        if(!CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMedicamentoDTOS())){
            detalleCompraMedicamentoService.create(facturaDTO.getDetalleCompraMedicamentoDTOS(),
                    factura);
        }
        if(!CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMiscelaneoDTOS())){
            detalleCompraMiscelaneoService.create(facturaDTO.getDetalleCompraMiscelaneoDTOS(),
                    factura);
        }
        if(CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMedicamentoDTOS())
        && CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMiscelaneoDTOS())){
            throw new RuntimeException("No se puede generar una factura vacía");
        }

        return facturaDTO;
    }

    public List<FacturaDTO> retrieveAll(){
        List<Factura> facturas = facturaRepository.findAll();
        return facturas.stream()
                .map(factura -> mapToDTO(factura))
                .collect(Collectors.toList());
    }

    public FacturaDTO retrieveById(Integer facturaId){
        Optional<Factura> factura= facturaRepository.findById(facturaId);
        if(factura.isEmpty()){
            throw new ResourceNotFoundException("La factura que está intentando consultar no existe.");
        }
        return mapToDTO(factura.get());
    }

    public List<FacturaDTO> mapToDTOS(List<Factura> facturas) {

        return facturas.stream()
                .map(factura -> mapToDTO(factura))
                .collect(Collectors.toList());
    }

    private Factura mapToEntity(FacturaDTO facturaDTO, Cliente cliente) {

        Factura factura = new Factura(LocalDate.parse(facturaDTO.getFecha(), DATE_TIME_FORMATTER),
                facturaDTO.getEstado(), cliente);
        
        return factura;
    }

    private FacturaDTO mapToDTO (Factura factura){
        FacturaDTO facturaDTO = new FacturaDTO(factura.getFecha().toString(),
                factura.getCliente().getId(), factura.getEstado(),
                detalleCompraMedicamentoService.mapToDTOS(factura.getDetalleCompraMedicamentos()),
                detalleCompraMiscelaneoService.mapToDTOS(factura.getDetalleCompraMiscelaneos()));
        facturaDTO.setId(factura.getId());
        facturaDTO.setImpuesto(factura.getImpuesto());
        facturaDTO.setTotalVenta(factura.getTotalVenta());

        return facturaDTO;
    }
}
