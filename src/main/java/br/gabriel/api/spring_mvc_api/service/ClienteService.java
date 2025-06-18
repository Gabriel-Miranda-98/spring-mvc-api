package br.gabriel.api.spring_mvc_api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gabriel.api.spring_mvc_api.model.Cliente;
import br.gabriel.api.spring_mvc_api.repository.ClienteRepository;

@Service
public class ClienteService {

  @Autowired
  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public Optional<Cliente> findById(UUID id) {
    return clienteRepository.findById(id);
  }

  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  public Cliente update(UUID id, Cliente cliente) {
    if (clienteRepository.existsById(id)) {
      cliente.setId(id);
      return clienteRepository.save(cliente);
    }
    throw new RuntimeException("Cliente não encontrado");
  }

  public void deleteById(UUID id) {
    clienteRepository.deleteById(id);
  }


  public Long count() {
    return clienteRepository.count();
  }

  public Optional<Cliente> findByName(String name) {
    return clienteRepository.findByName(name);
  }

}
