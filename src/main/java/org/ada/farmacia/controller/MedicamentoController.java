package org.ada.farmacia.controller;

import org.ada.farmacia.dto.LaboratorioDTO;
import org.ada.farmacia.dto.MedicamentoDTO;
import org.ada.farmacia.entity.Laboratorio;
import org.ada.farmacia.entity.Medicamento;
import org.ada.farmacia.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/laboratorios/{laboratorioId}/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;


    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping()
    public ResponseEntity create(@PathVariable Integer laboratorioId,
                                 @RequestBody MedicamentoDTO medicamentoDTO){
        medicamentoService.create(medicamentoDTO, laboratorioId);

        return new ResponseEntity(medicamentoDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(medicamentoService.retrieveAll(), HttpStatus.OK);

    }

    @GetMapping("/medicamentoId/{medicamentoId}")
    public ResponseEntity retrieveById(@PathVariable String medicamentoId){
        MedicamentoDTO medicamentoDTO = medicamentoService.retrieveById(medicamentoId);

        return new ResponseEntity(medicamentoDTO, HttpStatus.OK);
    }

    @GetMapping("/medicamentoNombreComercial/{medicamentoNombreComercial}")
    public ResponseEntity retrieveByNombreComercial(@PathVariable String medicamentoNombreComercial){

        return new ResponseEntity(medicamentoService.retrieveByNombreComercial(medicamentoNombreComercial), HttpStatus.OK);
    }

    @GetMapping("/medicamentoNombreGenerico/{medicamentoNombreGenerico}")
    public ResponseEntity retrieveByNombreGenerico(@PathVariable String medicamentoNombreGenerico){

        return new ResponseEntity(medicamentoService.retrieveByNombreGenerico(medicamentoNombreGenerico), HttpStatus.OK);
    }

    @DeleteMapping("/{medicamentoId}")
    public ResponseEntity delete(@PathVariable String medicamentoId){
        medicamentoService.delete(medicamentoId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{medicamentoId}")
    public ResponseEntity replace(@PathVariable String medicamentoId,
                                  @RequestBody MedicamentoDTO medicamentoDTO) {
        medicamentoService.replace(medicamentoId, medicamentoDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{medicamentoId}")
    public ResponseEntity modify(@PathVariable String medicamentoId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        medicamentoService.modify(medicamentoId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
