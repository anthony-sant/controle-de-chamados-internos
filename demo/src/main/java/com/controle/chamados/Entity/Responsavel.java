package com.controle.chamados.Entity;

import org.springframework.data.annotation.Id;

import com.controle.chamados.DTO.ResponsavelDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Responsavel {
    @Id
    Long id;
    String nome;

    public Responsavel(ResponsavelDTO dto){
        this.nome = dto.getNome();
    }

}
