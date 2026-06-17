package com.controle.chamados.Strategy;

import org.springframework.stereotype.Component;

import com.controle.chamados.Entity.Responsavel;


public interface AtribuicaoStrategy {
    public Responsavel definirResponsavel();
}
