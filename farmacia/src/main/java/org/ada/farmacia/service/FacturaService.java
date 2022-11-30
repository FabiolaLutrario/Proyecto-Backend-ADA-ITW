package org.ada.farmacia.service;

import org.ada.farmacia.dto.FacturaDTO;
import org.ada.farmacia.entity.*;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public FacturaDTO create(FacturaDTO facturaDTO){
        String clienteId= facturaDTO.getIdCliente();
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("El cliente no existe.");
        }
        Factura factura = mapToEntity(facturaDTO, cliente.get());
        factura = facturaRepository.save(factura);
        facturaDTO.setId(factura.getId());

        //Sacar esto a otros métodos para que sea más legible el código
        //por recomendación del prof

        Double precioTotalDetalleCompraMedicamentos = 0.00;
        Double precioTotalDetalleCompraMiscelaneos =0.00;

        if(!CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMedicamentoDTOS())){

            List <DetalleCompraMedicamento> detalleCompraMedicamentos =
            detalleCompraMedicamentoService.create(facturaDTO.getDetalleCompraMedicamentoDTOS(),
                    factura);

            for (DetalleCompraMedicamento detalleCompraMedicamento: detalleCompraMedicamentos) {
                precioTotalDetalleCompraMedicamentos = precioTotalDetalleCompraMedicamentos +
                        detalleCompraMedicamento.getPrecioTotal();
            }
        }

        if(!CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMiscelaneoDTOS())){

            List <DetalleCompraMiscelaneo> detalleCompraMiscelaneos =
            detalleCompraMiscelaneoService.create(facturaDTO.getDetalleCompraMiscelaneoDTOS(),
                    factura);

            for (DetalleCompraMiscelaneo detalleCompraMiscelaneo: detalleCompraMiscelaneos) {
                precioTotalDetalleCompraMiscelaneos = precioTotalDetalleCompraMiscelaneos +
                        detalleCompraMiscelaneo.getPrecioTotal();
            }
        }

        if(CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMedicamentoDTOS())
        && CollectionUtils.isEmpty(facturaDTO.getDetalleCompraMiscelaneoDTOS())){
            throw new RuntimeException("No se puede generar una factura vacía");
        }

        factura.setTotalVenta(precioTotalDetalleCompraMedicamentos+precioTotalDetalleCompraMiscelaneos);

        factura.setImpuesto(factura.getTotalVenta()-(factura.getTotalVenta()/1.12));

        facturaRepository.save(factura);

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
                cliente);

        return factura;
    }

    private FacturaDTO mapToDTO (Factura factura){
        FacturaDTO facturaDTO = new FacturaDTO(factura.getFecha().toString(),
                factura.getCliente().getId(),
                detalleCompraMedicamentoService.mapToDTOS(factura.getDetalleCompraMedicamentos()),
                detalleCompraMiscelaneoService.mapToDTOS(factura.getDetalleCompraMiscelaneos()));
        facturaDTO.setId(factura.getId());
        facturaDTO.setImpuesto(factura.getImpuesto());
        facturaDTO.setTotalVenta(factura.getTotalVenta());

        return facturaDTO;
    }
}
