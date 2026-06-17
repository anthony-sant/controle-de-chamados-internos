package com.controle.chamados.DTO;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.controle.chamados.Enum.AtribuicaoEnum;
import com.controle.chamados.Enum.PrioridadeEnum;
import com.controle.chamados.Enum.StatusChamadoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChamadoDTO {

    
    private String titulo;
    private String descricao;
    private ResponsavelDTO responsavel;
    private PrioridadeEnum prioridade;
    private AtribuicaoEnum tipoAtribuicao;
    private String status;
}
