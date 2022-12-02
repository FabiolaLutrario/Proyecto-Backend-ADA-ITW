package org.ada.farmacia.controller;


import org.ada.farmacia.dto.ClienteDTO;
import org.ada.farmacia.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO createdClienteDTO = clienteService.create(clienteDTO);

        return new ResponseEntity(clienteDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(clienteService.retrieveAll(), HttpStatus.OK);

    }

    @GetMapping("/clienteId/{clienteId}")
    public ResponseEntity retrieveById(@PathVariable String clienteId){
        ClienteDTO clienteDTO = clienteService.retrieveById(clienteId);

        return new ResponseEntity(clienteDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity delete(@PathVariable String clienteId){
        clienteService.delete(clienteId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity replace(@PathVariable String clienteId,
                                  @RequestBody ClienteDTO clienteDTO) {
        clienteService.replace(clienteId, clienteDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity modify(@PathVariable String clienteId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        clienteService.modify(clienteId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
