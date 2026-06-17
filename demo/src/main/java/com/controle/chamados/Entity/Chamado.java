package com.controle.chamados.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.controle.chamados.DTO.ChamadoDTO;
import com.controle.chamados.Enum.PrioridadeEnum;
import com.controle.chamados.Enum.StatusChamadoEnum;

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
    PrioridadeEnum prioridade;
    StatusChamadoEnum statusChamado;
    LocalDateTime dataAbertura;
    @ManyToOne
    Responsavel responsavel;

    public Chamado(ChamadoDTO dto){
        this.titulo = dto.getTitulo();
        this.descricao = dto.getDescricao();
        this.prioridade = dto.getPrioridade();
    }
}
