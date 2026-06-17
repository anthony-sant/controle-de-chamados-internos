package com.controle.chamados.Repository;
import com.controle.chamados.Entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{
    
}
