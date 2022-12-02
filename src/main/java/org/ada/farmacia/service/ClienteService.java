package org.ada.farmacia.service;

import org.ada.farmacia.dto.ClienteDTO;
import org.ada.farmacia.entity.Cliente;
import org.ada.farmacia.exceptions.ExistingResourceException;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.ClienteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final FacturaService facturaService;

    public ClienteService(ClienteRepository clienteRepository, FacturaService facturaService) {
        this.clienteRepository = clienteRepository;
        this.facturaService = facturaService;
    }

    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);
        checkForExistingCliente(cliente.getId());
        cliente = clienteRepository.save(cliente);

        return clienteDTO;
    }

    public List<ClienteDTO> retrieveAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente))
                .collect(Collectors.toList());
    }

    public ClienteDTO retrieveById(String clienteId){
        Optional<Cliente> cliente= clienteRepository.findById(clienteId);
        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("El cliente que está intentando consultar no existe.");
        }
        return mapToDTO(cliente.get());
    }

    public void delete(String clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("El cliente que está intentando eliminar no existe.");
        }
    }

    public void replace(String clienteId, ClienteDTO clienteDTO) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("EL cliente que está intentando modificar no existe.");
        }
        Cliente clienteToReplace = cliente.get();
        clienteToReplace.setNombre(clienteDTO.getNombre());
        clienteToReplace.setApellido(clienteDTO.getApellido());
        clienteToReplace.setContacto(clienteDTO.getContacto());
        clienteToReplace.setDireccion(clienteDTO.getDireccion());

        clienteRepository.save(clienteToReplace);
    }

    public void modify(String clienteId, Map<String, Object> fieldsToModify) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("EL cliente que está intentando modificar no existe.");
        }
        Cliente clienteToModify = cliente.get();

        fieldsToModify.forEach((key, value) ->
                clienteToModify.modifyAttributeValue(key, value));
        clienteRepository.save(clienteToModify);
    }

    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getId(), clienteDTO.getNombre(),
                clienteDTO.getApellido(),clienteDTO.getContacto(),
                clienteDTO.getDireccion());

        return cliente;
    }

    private ClienteDTO mapToDTO (Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNombre(),
                cliente.getApellido(), cliente.getContacto(), cliente.getDireccion(),
                facturaService.mapToDTOS(cliente.getFacturas()));

        return clienteDTO;
    }

    private void checkForExistingCliente(String clienteId){
        if (clienteRepository.existsById(clienteId)) {
            throw new ExistingResourceException("El cliente que se está intentando crear, ya existe.");
        }
    }
}
