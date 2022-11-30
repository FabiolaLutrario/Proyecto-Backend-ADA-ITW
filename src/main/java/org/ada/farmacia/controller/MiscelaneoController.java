package org.ada.farmacia.controller;

import org.ada.farmacia.dto.MiscelaneoDTO;
import org.ada.farmacia.service.MiscelaneoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/miscelaneos")
public class MiscelaneoController {

    private final MiscelaneoService miscelaneoService;

    public MiscelaneoController(MiscelaneoService miscelaneoService) {
        this.miscelaneoService = miscelaneoService;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody MiscelaneoDTO miscelaneoDTO){
        MiscelaneoDTO createdMiscelaneoDTO = miscelaneoService.create(miscelaneoDTO);

        return new ResponseEntity(miscelaneoDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(miscelaneoService.retrieveAll(), HttpStatus.OK);

    }

    @GetMapping("/miscelaneoId/{miscelaneoId}")
    public ResponseEntity retrieveById(@PathVariable String miscelaneoId){
        MiscelaneoDTO miscelaneoDTO = miscelaneoService.retrieveById(miscelaneoId);

        return new ResponseEntity(miscelaneoDTO, HttpStatus.OK);
    }

    @GetMapping("/miscelaneoNombre/{miscelaneoNombre}")
    public ResponseEntity retrieveByNombre(@PathVariable String miscelaneoNombre){

        return new ResponseEntity(miscelaneoService.retrieveByNombre(miscelaneoNombre), HttpStatus.OK);
    }

    @DeleteMapping("/{miscelaneoId}")
    public ResponseEntity delete(@PathVariable String miscelaneoId){
        miscelaneoService.delete(miscelaneoId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{miscelaneoId}")
    public ResponseEntity replace(@PathVariable String miscelaneoId,
                                  @RequestBody MiscelaneoDTO miscelaneoDTO) {
        miscelaneoService.replace(miscelaneoId, miscelaneoDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{miscelaneoId}")
    public ResponseEntity modify(@PathVariable String miscelaneoId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        miscelaneoService.modify(miscelaneoId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
