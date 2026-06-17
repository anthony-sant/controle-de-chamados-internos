package com.controle.chamados.Strategy;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.controle.chamados.Entity.Responsavel;
import com.controle.chamados.Enum.StatusChamado;
import com.controle.chamados.Repository.ChamadoRepository;
import com.controle.chamados.Repository.ResponsavelRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MenorFIlaAtribuicaoStrategy implements AtribuicaoStrategy{
    private final ResponsavelRepository responsavelRepository;
    private final ChamadoRepository chamadoRepository;

    @Override
    public Responsavel definirResponsavel(){
        List<Responsavel> todosResponsaveis = responsavelRepository.findAll();

        List<StatusChamado> statusAtivos = List.of(StatusChamado.ABERTO, StatusChamado.EM_ANDAMENTO);
        Responsavel menosOcupado = todosResponsaveis.stream().
                                    min(Comparator.comparing(responsavel -> 
                                        chamadoRepository.countByResponsavelAndStatusIn(responsavel, statusAtivos)
                                    ))
                                    .orElseThrow(() -> new RuntimeException("Nenhum responsável cadastrado no sistema!"));

        return menosOcupado;
    }
}
