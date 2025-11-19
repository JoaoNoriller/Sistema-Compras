package com.compras.sistema_compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.sistema_compras.model.Categoria;
import com.compras.sistema_compras.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> exebirTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria salvar(Categoria categoria) {
        validarCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    public void excluirPorid(long id){
        categoriaRepository.deleteById(id);
    }

    private void validarCategoria(Categoria categoria) {

        // Verifica se já existe uma categoria com o mesmo nome
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new IllegalArgumentException("O nome da categoria já existe!!");
        }
    }
}
