package com.compras.sistema_compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.sistema_compras.model.Produtos;
import com.compras.sistema_compras.repository.ProdutoRepository;

@Service
public class ProdutoService {

     @Autowired
    private ProdutoRepository produtoRepository;


    public List<Produtos> exibirTodos() {
        return produtoRepository.findAll();
    }
}
