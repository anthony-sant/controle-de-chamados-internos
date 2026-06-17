package com.controle.chamados.Strategy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.controle.chamados.DTO.ChamadoDTO;
import com.controle.chamados.Entity.Responsavel;
import com.controle.chamados.Enum.StatusChamadoEnum;
import com.controle.chamados.Repository.ChamadoRepository;
import com.controle.chamados.Repository.ResponsavelRepository;

import lombok.AllArgsConstructor;

@Component("MENOR_FILA")
@AllArgsConstructor
public class MenorFIlaAtribuicaoStrategy implements AtribuicaoStrategy{
    private final ResponsavelRepository responsavelRepository;
    private final ChamadoRepository chamadoRepository;

    @Override
    public Optional<Responsavel> definirResponsavel(ChamadoDTO chamado){
        List<Responsavel> todosResponsaveis = responsavelRepository.findAll();

        if(todosResponsaveis.isEmpty()){
            return Optional.empty();
        }

        List<StatusChamadoEnum> statusAtivos = List.of(StatusChamadoEnum.ABERTO, StatusChamadoEnum.EM_ANDAMENTO);

        return todosResponsaveis.stream().
                                    min(Comparator.comparing(responsavel -> 
                                        chamadoRepository.countByResponsavelAndStatusIn(responsavel, statusAtivos)
                                    ));

    }
}
