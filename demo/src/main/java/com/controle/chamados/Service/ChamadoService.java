package com.controle.chamados.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.DocFlavor.READER;

import org.springframework.stereotype.Service;

import com.controle.chamados.DTO.ChamadoDTO;
import com.controle.chamados.Entity.Chamado;
import com.controle.chamados.Entity.Responsavel;
import com.controle.chamados.Enum.StatusChamado;
import com.controle.chamados.Repository.ChamadoRepository;
import com.controle.chamados.Strategy.AtribuicaoStrategy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ChamadoService {
    ChamadoRepository chamadoRepository;
    private final AtribuicaoStrategy AtribuicaoStrategy;
    
    public Chamado salvarChamado(ChamadoDTO chamadoDTO){
        Chamado chamado = new Chamado();

        

        if(chamado.getResponsavel() == null){
            Responsavel responsavelIdeal = AtribuicaoStrategy.definirResponsavel();
            chamado.setResponsavel(responsavelIdeal);
        }
        
        return chamadoRepository.save(chamado);
    }

    public List<Chamado> listarTodos(){
        return chamadoRepository.findAll();
    }

    public Chamado buscarPorId(Long id){
        return chamadoRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Chamado não encontrado!"));
    }

    public Chamado atualizar(Long id, Chamado chamadoAtualizado){
        Chamado chamadoExistente = buscarPorId(id);

        chamadoExistente.setTitulo(chamadoAtualizado.getTitulo());
    }
}
