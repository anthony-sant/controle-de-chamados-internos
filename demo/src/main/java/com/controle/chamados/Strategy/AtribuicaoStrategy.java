package com.controle.chamados.Strategy;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.controle.chamados.DTO.ChamadoDTO;
import com.controle.chamados.Entity.Responsavel;


public interface AtribuicaoStrategy {
    public Optional<Responsavel> definirResponsavel(ChamadoDTO chamado);
}
