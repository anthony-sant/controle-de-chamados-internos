package com.controle.chamados.Strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.controle.chamados.Enum.AtribuicaoEnum;

@Component
public class AtribuicaoStrategyFactory {
    

    private final Map<String, AtribuicaoStrategy> strategies;


    public AtribuicaoStrategyFactory(Map<String, AtribuicaoStrategy> strategies) {
        this.strategies = strategies;
    }

    public AtribuicaoStrategy obterEstrategia(AtribuicaoEnum tipo) {
        
        AtribuicaoStrategy strategy = strategies.get(tipo.name());
        
        if (strategy == null) {
            throw new IllegalArgumentException("A estratégia de atribuição não foi implementada: " + tipo);
        }
        
        return strategy;
    }
}

