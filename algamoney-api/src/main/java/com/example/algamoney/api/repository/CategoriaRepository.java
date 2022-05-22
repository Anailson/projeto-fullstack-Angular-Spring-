package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.algamoney.api.model.Categoria;

/*
 *  Repository implementação de vários métodos como Salvar, Consulta etc 
 */

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long > {

		
}
