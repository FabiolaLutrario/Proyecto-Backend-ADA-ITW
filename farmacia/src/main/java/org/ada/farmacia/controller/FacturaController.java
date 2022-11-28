package org.ada.farmacia.controller;

import org.ada.farmacia.dto.FacturaDTO;
import org.ada.farmacia.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes/{clienteId}/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping()
    public ResponseEntity create(@PathVariable String clienteId,
                                 @RequestBody FacturaDTO facturaDTO){
        facturaService.create(facturaDTO, clienteId);

        return new ResponseEntity(facturaDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(facturaService.retrieveAll(), HttpStatus.OK);

    }

    @GetMapping("/facturaId/{facturaId}")
    public ResponseEntity retrieveById(@PathVariable Integer facturaId){
        FacturaDTO facturaDTO = facturaService.retrieveById(facturaId);

        return new ResponseEntity(facturaDTO, HttpStatus.OK);
    }
}
