package desafio.itau.Business.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import desafio.itau.Controller.Dto.TransacaoRequestDTO;
import desafio.itau.Infra.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class TranscacaoService {

    private final List<TransacaoRequestDTO> listaTranscao = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto){

        log.info("Adicionando transação: {}", dto);

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            throw new UnprocessableEntity("Data da transação não pode ser futura");
        }

        if(dto.valor() < 0){

            log.error("o valor não pode ser negativo");

            throw new UnprocessableEntity("o valor não pode ser negativo");
        }

        listaTranscao.add(dto);
    }

    public void limparTransacacoes(){
        listaTranscao.clear();
    }


    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloDeBusca){
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(60); 

        return listaTranscao.stream().filter(transacoes -> transacoes.dataHora().isAfter(dataHoraIntervalo)).toList();
    }    
}

