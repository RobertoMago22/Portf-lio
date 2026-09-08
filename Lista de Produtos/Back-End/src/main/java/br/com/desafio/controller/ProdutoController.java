package br.com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.model.Produto;
import br.com.desafio.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<List<Produto>> cadastrarProduto(@Valid @RequestBody Produto produto){
		
		produtoService.cadastraProduto(produto);
		
		return listaProdutos();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<Produto>> atualizarProduto(@PathVariable Long id, 
			@Valid @RequestBody Produto produto){
		
		boolean produtoAtualizado = produtoService.atualizarProduto(id, produto);
		
		if(!produtoAtualizado) {
			
			return ResponseEntity.notFound().build();
		}
		
		return listaProdutos();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<Produto>> deletarProduto(@PathVariable Long id){
		
		boolean produtoAtualizado = produtoService.deletarProduto(id);
		
		if(!produtoAtualizado) {
			
			return ResponseEntity.notFound().build();
		}
		
		return listaProdutos();
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome){
		
		return ResponseEntity.ok(produtoService.buscarPorNome(nome));
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Produto>> listaProdutos(){
		
		return ResponseEntity.ok(produtoService.buscarAllProdutos());
	}

}
