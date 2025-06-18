package br.gabriel.api.spring_mvc_api.dto;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "DTO para transferência de dados do cliente")
public class ClienteDto {

    @Schema(description = "ID único do cliente", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Schema(description = "Nome completo do cliente", example = "João da Silva", required = true)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    @Schema(description = "Endereço de email do cliente", example = "joao.silva@email.com", required = true)
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (00) 00000-0000")
    @Schema(description = "Número de telefone do cliente", example = "(11) 99999-9999", required = true)
    private String telefone;

    @Size(max = 255, message = "Endereço não pode ter mais de 255 caracteres")
    @Schema(description = "Endereço completo do cliente", example = "Rua das Flores, 123 - São Paulo, SP")
    private String endereco;
}
