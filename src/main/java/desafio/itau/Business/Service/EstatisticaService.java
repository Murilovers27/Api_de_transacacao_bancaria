package desafio.itau.Business.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import desafio.itau.Controller.Dto.EstatisticaDto;
import desafio.itau.Controller.Dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstatisticaService {
    public final TranscacaoService transacoesService;

    public EstatisticaDto calcularEstatisticas(Integer intervaloDeBusca){

        List<TransacaoRequestDTO> transacoes = transacoesService.buscarTransacoes(intervaloDeBusca);

        
        DoubleSummaryStatistics estatisticas = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        return new EstatisticaDto(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getMax(), estatisticas.getMin(), estatisticas.getAverage()); 
    }
}
