package com.compras.sistema_compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.compras.sistema_compras.model.Categoria;
import com.compras.sistema_compras.model.Produtos;
import com.compras.sistema_compras.repository.CategoriaRepository;
import com.compras.sistema_compras.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/")
    public String Inicio(Model model) {
        model.addAttribute("paginaCategoria", categoriaRepository.findAll());
        model.addAttribute("paginaProduto", produtoRepository.findAll());
        return "index";

    }
     // FORMULÁRIO PARA CADASTRAR NOVO PRODUTO
    @GetMapping("/novo")
    public String mostrarFormularioNovoProduto(Model model) {
        model.addAttribute("produto", new Produtos());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "produto"; // Template novo-produto.html
    }

    // CADASTRAR NOVO PRODUTO
    @PostMapping("/adicionar")
    public String adicionarProduto(@ModelAttribute Produtos novoProduto) {
        produtoRepository.save(novoProduto);
        return "redirect:/Produtos";
    }

    // EDITAR PRODUTO (carregar formulário)
    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produtos produto = produtoRepository.findById(id).orElse(null);
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categorias);
        return "editar-produto"; // Template editar-produto.html
    }

    // ATUALIZAR PRODUTO
    @PostMapping("/atualizar")
    public String atualizarProduto(@ModelAttribute Produtos produtoAtualizado) {
        produtoRepository.save(produtoAtualizado);
        return "redirect:/Produtos";
    }

    // EXCLUIR PRODUTO
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/Produtos";
    }
}

