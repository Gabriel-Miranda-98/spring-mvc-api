package br.gabriel.api.spring_mvc_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.gabriel.api.spring_mvc_api.dto.ClienteDto;
import br.gabriel.api.spring_mvc_api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class)))
    })
    public ResponseEntity<List<ClienteDto>> listarTodos() {
        List<ClienteDto> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class))),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = br.gabriel.api.spring_mvc_api.dto.ErrorResponseDto.class)))
    })
    public ResponseEntity<ClienteDto> buscarPorId(
            @Parameter(description = "ID do cliente", required = true) @PathVariable UUID id) {
        Optional<ClienteDto> cliente = clienteService.buscarPorId(id);
        return cliente.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar clientes por nome", description = "Retorna uma lista de clientes que contêm o nome informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class))),
        @ApiResponse(responseCode = "400", description = "Parâmetro de busca inválido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = br.gabriel.api.spring_mvc_api.dto.ErrorResponseDto.class)))
    })
    public ResponseEntity<List<ClienteDto>> buscarPorNome(
            @Parameter(description = "Nome ou parte do nome do cliente para busca", required = true, example = "João")
            @RequestParam String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ClienteDto> clientes = clienteService.buscarPorNome(nome.trim());
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    @Operation(summary = "Criar novo cliente", description = "Cria um novo cliente com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = br.gabriel.api.spring_mvc_api.dto.ErrorResponseDto.class)))
    })
    public ResponseEntity<ClienteDto> criar(
            @Parameter(description = "Dados do cliente a ser criado", required = true)
            @Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteCriado = clienteService.salvar(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class))),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = br.gabriel.api.spring_mvc_api.dto.ErrorResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = br.gabriel.api.spring_mvc_api.dto.ErrorResponseDto.class)))
    })
    public ResponseEntity<ClienteDto> atualizar(
            @Parameter(description = "ID do cliente", required = true) @PathVariable UUID id,
            @Parameter(description = "Novos dados do cliente", required = true)
            @Valid @RequestBody ClienteDto clienteDto) {
        try {
            ClienteDto clienteAtualizado = clienteService.atualizar(id, clienteDto);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente", description = "Remove um cliente do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = br.gabriel.api.spring_mvc_api.dto.ErrorResponseDto.class)))
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do cliente", required = true) @PathVariable UUID id) {
        try {
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    @Operation(summary = "Contar clientes", description = "Retorna o número total de clientes cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Número de clientes retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "integer")))
    })
    public ResponseEntity<Long> contarClientes() {
        long count = clienteService.contar();
        return ResponseEntity.ok(count);
    }
}
