package com.compras.sistema_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.compras.sistema_compras.model.Produtos;
import com.compras.sistema_compras.repository.CategoriaRepository;
import com.compras.sistema_compras.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTAR PRODUTOS
    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("produto", new Produtos());
        return "produto"; // nome do HTML
    }

    // SALVAR NOVO PRODUTO
    @PostMapping("/produtos/salvar")
    public String salvarProduto(@ModelAttribute Produtos produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    // EDITAR PRODUTO
    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produtos produto = produtoRepository.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaRepository.findAll()); // Adiciona a lista de categorias
        return "editar-produto";

    }

    @PostMapping("/produtos/atualizar")
    public String atualizarProduto(@ModelAttribute Produtos produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    // EXCLUIR PRODUTO
    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
     //CARREGAR PAGINA INICIAL
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "index";
    }

}
