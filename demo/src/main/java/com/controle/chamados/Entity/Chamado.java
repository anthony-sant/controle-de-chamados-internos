package com.controle.chamados.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.controle.chamados.Enum.Prioridade;
import com.controle.chamados.Enum.StatusChamado;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Chamado {
    @Id
    Long id;
    String titulo;
    String descricao;
    Prioridade prioridade;
    StatusChamado statusChamado;
    LocalDateTime dataAbertura;
    @ManyToOne
    Responsavel responsavel;
}
