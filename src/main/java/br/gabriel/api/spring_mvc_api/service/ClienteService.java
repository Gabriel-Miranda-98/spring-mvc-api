package br.gabriel.api.spring_mvc_api.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gabriel.api.spring_mvc_api.dto.ClienteDto;
import br.gabriel.api.spring_mvc_api.model.Cliente;
import br.gabriel.api.spring_mvc_api.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDto> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDto> buscarPorId(UUID id) {
        return clienteRepository.findById(id)
                .map(this::convertToDto);
    }

    public ClienteDto salvar(ClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return convertToDto(clienteSalvo);
    }

    public ClienteDto atualizar(UUID id, ClienteDto clienteDto) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }

        clienteDto.setId(id);
        Cliente cliente = convertToEntity(clienteDto);
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return convertToDto(clienteAtualizado);
    }

    public void deletar(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    private ClienteDto convertToDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getName());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getPhone());
        return dto;
    }

    private Cliente convertToEntity(ClienteDto dto) {

       Cliente cliente= Cliente.builder().id(dto.getId())
                .name(dto.getNome())
                .email(dto.getEmail())
                .phone(dto.getTelefone())
                .address(dto.getEndereco())
                .build();

        return cliente;
    }
}
