package com.compras.sistema_compras.service;

import java.util.List;
import java.util.Optional;

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

    // SALVAR OU ATUALIZAR PRODUTO
    public Produtos save(Produtos produto) {

        // VALIDAÇÃO DO NOME DO PRODUTO
        if (produto.getTitulo() == null || produto.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }

        return produtoRepository.save(produto);
    }

    // BUSCAR POR ID
    public Optional<Produtos> findById(Long id) {
        return produtoRepository.findById(id);
    }

    // EXCLUIR
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    // SE PRECISAR DE OUTRO FIND ALL
    public List<Produtos> findAll() {
        return produtoRepository.findAll();
    }
}
