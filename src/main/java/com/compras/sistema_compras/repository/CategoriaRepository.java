package com.compras.sistema_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.sistema_compras.model.Categoria;
 
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}