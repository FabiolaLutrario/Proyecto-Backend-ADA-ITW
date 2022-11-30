package org.ada.farmacia.controller;

import org.ada.farmacia.dto.LaboratorioDTO;
import org.ada.farmacia.service.LaboratorioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody LaboratorioDTO laboratorioDTO){
        LaboratorioDTO createdLaboratorioDTO = laboratorioService.create(laboratorioDTO);

        return new ResponseEntity(laboratorioDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(laboratorioService.retrieveAll(), HttpStatus.OK);

    }

    @GetMapping("/laboratorioId/{id}")
    public ResponseEntity retrieveById(@PathVariable Integer id){
        LaboratorioDTO laboratorioDTO = laboratorioService.retrieveById(id);

        return new ResponseEntity(laboratorioDTO, HttpStatus.OK);
    }

    @GetMapping("/laboratorioNombre/{nombre}")
    public ResponseEntity retrieveByNombre(@PathVariable String nombre){

        return new ResponseEntity(laboratorioService.retrieveByNombre(nombre), HttpStatus.OK);
    }

    @DeleteMapping("/{laboratorioId}")
    public ResponseEntity delete(@PathVariable Integer laboratorioId){
        laboratorioService.delete(laboratorioId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{laboratorioId}")
    public ResponseEntity replace(@PathVariable Integer laboratorioId,
                                  @RequestBody LaboratorioDTO laboratorioDTO) {
        laboratorioService.replace(laboratorioId, laboratorioDTO);

        return new ResponseEntity(HttpStatus.OK);
    }
}
