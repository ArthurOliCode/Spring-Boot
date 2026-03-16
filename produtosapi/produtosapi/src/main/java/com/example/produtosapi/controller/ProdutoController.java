package com.example.produtosapi.controller;

import com.example.produtosapi.model.Produto;
import com.example.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido: " + produto);

        var id = UUID.randomUUID().toString(); // Gerando IDs de forma automática com a classe UUID
        produto.setId(id); // Atribuindo esse Id à classe Produto, para enfim adicioná-lo a tabela.

        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id){
    /*    Optional<Produto> produto = produtoRepository.findById(id);
        return produto.isPresent() ? produto.get() : null; outra opção:
     */
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id,
                            @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }
}
