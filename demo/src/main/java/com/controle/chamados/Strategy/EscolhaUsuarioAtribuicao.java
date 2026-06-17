package com.controle.chamados.Strategy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.controle.chamados.DTO.ChamadoDTO;
import com.controle.chamados.Entity.Responsavel;
import com.controle.chamados.Repository.ChamadoRepository;
import com.controle.chamados.Repository.ResponsavelRepository;

import lombok.AllArgsConstructor;

@Component("ESCOLHA")
@AllArgsConstructor
public class EscolhaUsuarioAtribuicao implements AtribuicaoStrategy{
    
    private final ResponsavelRepository responsavelRepository;
    private final ChamadoRepository chamadoRepository;

    @Override
    public Optional<Responsavel> definirResponsavel(ChamadoDTO chamado){
        if(chamado.getResponsavel() == null){
            return Optional.empty();
        }

        Responsavel responsavel = new Responsavel(chamado.getResponsavel());

        return Optional.ofNullable(responsavel);
    }
}
