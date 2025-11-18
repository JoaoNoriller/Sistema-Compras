package com.compras.sistema_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.compras.sistema_compras.model.Produtos;
import com.compras.sistema_compras.service.CategoriaService;
import com.compras.sistema_compras.service.ProdutoService;


@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    // LISTAR PRODUTOS
    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.exibirTodos());
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("produto", new Produtos());
        return "produto"; // nome do HTML
    }

    // SALVAR NOVO PRODUTO
    @PostMapping("/produtos/salvar")
    public String salvarProduto(@ModelAttribute Produtos produto) {
        produtoService.save(produto);
        return "redirect:/produtos";
    }

    // EDITAR PRODUTO
    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produtos produto = produtoService.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.findAll()); // Adiciona a lista de categorias
        return "editar-produto";

    }

    @PostMapping("/produtos/atualizar")
    public String atualizarProduto(@ModelAttribute Produtos produto) {
        produtoService.save(produto);
        return "redirect:/produtos";
    }

    // EXCLUIR PRODUTO
    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
        return "redirect:/produtos";
    }
     //CARREGAR PAGINA INICIAL
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        return "index";
    }

}
