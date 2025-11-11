package com.compras.sistema_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.sistema_compras.model.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {
    
}
