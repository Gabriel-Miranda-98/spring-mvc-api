package br.gabriel.api.spring_mvc_api.dto;

import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resposta de erro de validação")
public class ErrorResponseDto {

    @Schema(description = "Mapa de erros de validação", example = "{\"nome\": \"Nome é obrigatório\", \"email\": \"Email deve ter um formato válido\"}")
    private Map<String, String> errors;

    @Schema(description = "Mensagem de erro geral", example = "Cliente não encontrado")
    private String message;

    public ErrorResponseDto(Map<String, String> errors) {
        this.errors = errors;
    }

    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
