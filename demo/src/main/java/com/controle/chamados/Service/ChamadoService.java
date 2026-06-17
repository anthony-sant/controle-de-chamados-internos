package com.controle.chamados.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.DocFlavor.READER;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.controle.chamados.DTO.ChamadoDTO;
import com.controle.chamados.Entity.Chamado;
import com.controle.chamados.Entity.Responsavel;
import com.controle.chamados.Enum.AtribuicaoEnum;
import com.controle.chamados.Enum.PrioridadeEnum;
import com.controle.chamados.Enum.StatusChamadoEnum;
import com.controle.chamados.Repository.ChamadoRepository;
import com.controle.chamados.Strategy.AtribuicaoStrategy;
import com.controle.chamados.Strategy.AtribuicaoStrategyFactory;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ChamadoService {

    @Value("${code.strategy.fallback}")
    private String tipoFallbackConfig;

    ChamadoRepository chamadoRepository;
    private final AtribuicaoStrategyFactory atribuicaoStrategyFactory;
    
    public Chamado salvarChamado(ChamadoDTO chamadoDTO){
        Chamado chamado = new Chamado(chamadoDTO);

        AtribuicaoStrategy atribuicaoEscolhida = atribuicaoStrategyFactory.obterEstrategia(chamadoDTO.getTipoAtribuicao());
        AtribuicaoEnum tipoAtribuicaoFallback = AtribuicaoEnum.valueOf(tipoFallbackConfig);
        AtribuicaoStrategy atribuicaoFallback = atribuicaoStrategyFactory.obterEstrategia(tipoAtribuicaoFallback);

        Responsavel responsavelFinal = atribuicaoEscolhida.definirResponsavel(chamadoDTO)
                                        .or(() -> atribuicaoFallback.definirResponsavel(chamadoDTO))
                                        .orElseThrow(() -> new RuntimeException("Nenhum funcionáio disponível no Momento"));

        chamado.setResponsavel(responsavelFinal);
        return chamadoRepository.save(chamado);
    }

    private Chamado buscarOuFalhar(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado com o ID: " + id));
    }

    public List<Chamado> listarTodos(){
        return chamadoRepository.findAll();
    }

    public Chamado buscarPorId(Long id) {
        return buscarOuFalhar(id);
    }

    public Chamado atualizarChamado(Long id, ChamadoDTO chamadoDTO) {
        Chamado chamadoExistente = buscarOuFalhar(id);

        chamadoExistente.setTitulo(chamadoDTO.getTitulo());
        chamadoExistente.setDescricao(chamadoDTO.getDescricao());
        chamadoExistente.setPrioridade(chamadoDTO.getPrioridade());
        chamadoExistente.setStatusChamado(StatusChamadoEnum.valueOf(chamadoDTO.getStatus()));
        

        return chamadoRepository.save(chamadoExistente);
    }

    public void deletarChamado(Long id) {
        Chamado chamado = buscarOuFalhar(id);
        chamadoRepository.delete(chamado);
    }
}
