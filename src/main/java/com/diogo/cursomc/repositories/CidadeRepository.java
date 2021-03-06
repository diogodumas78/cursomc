package com.diogo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diogo.cursomc.domain.Categoria;
import com.diogo.cursomc.domain.Cidade;
import com.diogo.cursomc.domain.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
