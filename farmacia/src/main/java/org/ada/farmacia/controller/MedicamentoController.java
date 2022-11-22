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

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;


    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody List<MedicamentoDTO> medicamentoDTOS, Laboratorio laboratorio){
        List<MedicamentoDTO> createdMedicamentoDTOS = medicamentoService.create(medicamentoDTOS, laboratorio);

        List <String> medicamentosById = new ArrayList<>();

        for (MedicamentoDTO medicamentoDTO: medicamentoDTOS) {
            medicamentosById.add(medicamentoDTO.getId());
        }

        return new ResponseEntity(medicamentosById, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(medicamentoService.retrieveAll(), HttpStatus.OK);

    }

    @GetMapping("/medicamentoId/{id}")
    public ResponseEntity retrieveById(@PathVariable String id){
        MedicamentoDTO medicamentoDTO = medicamentoService.retrieveById(id);

        return new ResponseEntity(medicamentoDTO, HttpStatus.OK);
    }

    @GetMapping("/medicamentoNombreComercial/{nombreComercial}")
    public ResponseEntity retrieveByNombreComercial(@PathVariable String nombreComercial){

        return new ResponseEntity(medicamentoService.retrieveByNombreComercial(nombreComercial), HttpStatus.OK);
    }

    @GetMapping("/medicamentoNombreGenerico/{nombreGenerico}")
    public ResponseEntity retrieveByNombreGenerico(@PathVariable String nombreGenerico){

        return new ResponseEntity(medicamentoService.retrieveByNombreGenerico(nombreGenerico), HttpStatus.OK);
    }
}
