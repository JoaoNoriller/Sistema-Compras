package com.compras.sistema_compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compras.sistema_compras.model.Categoria;
import com.compras.sistema_compras.repository.CategoriaRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTAR TODAS AS CATEGORIAS
    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("novaCategoria", new Categoria());
        return "categorias"; // Template categorias.html
    }

    // CADASTRAR NOVA CATEGORIA
    @PostMapping("/adicionar")
    public String adicionarCategoria(@ModelAttribute Categoria novaCategoria) {
        categoriaRepository.save(novaCategoria);
        return "redirect:/categorias";
    }

    // EXCLUIR (opcional)
    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }
}
