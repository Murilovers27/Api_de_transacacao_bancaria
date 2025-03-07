package desafio.itau.Controller.Dto;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(Double valor, OffsetDateTime dataHora) {

}
