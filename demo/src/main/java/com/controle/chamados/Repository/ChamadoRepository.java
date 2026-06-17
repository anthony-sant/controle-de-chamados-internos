package com.controle.chamados.Repository;
import com.controle.chamados.Entity.*;
import com.controle.chamados.Enum.StatusChamado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
    Long countByResponsavelAndStatusIn(Responsavel responsavel, List<StatusChamado> status);
}
