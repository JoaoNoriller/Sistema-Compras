package com.compras.sistema_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compras.sistema_compras.model.Categoria;
import com.compras.sistema_compras.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // LISTAR E CADASTRAR CATEGORIAS
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.exebirTodos());
        model.addAttribute("categoria", new Categoria());
        return "categoria";
    }

    @PostMapping("/salvar")
    public String salvarCategoria(@ModelAttribute Categoria categoria, Model model) {
        try {
            categoriaService.salvar(categoria);
            return "redirect:/categorias";

        } catch (IllegalArgumentException e) {
            // ERRO CAPTURADO → MANDA DE VOLTA PARA A TELA
            model.addAttribute("erro", e.getMessage());

            // NECESSÁRIO PARA RECARREGAR A LISTA
            model.addAttribute("categorias", categoriaService.exebirTodos());
            model.addAttribute("categoria", new Categoria());

            return "categoria"; // volta para a mesma página exibindo o pop-up
        }
    }

    // EXCLUIR CATEGORIA
    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirPorid(id);
        return "redirect:/categorias";
    }
}
