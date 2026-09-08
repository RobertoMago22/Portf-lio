package br.com.desafio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Produto;
import br.com.desafio.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;

	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto cadastraProduto(Produto produto) {
		
		return produtoRepository.save(produto);
		
	}
	
	public boolean atualizarProduto(Long id, Produto produto) {
		
		Optional<Produto> produtoBanco = buscarPorId(id);
		
		if(produtoBanco.isEmpty()) {
			
			return false;
		}
		
		Produto produtoAtual = produtoBanco.get();
		
		produtoAtual.setNome(produto.getNome());
		produtoAtual.setPreco(produto.getPreco());
		produtoAtual.setQuantidade(produto.getQuantidade());
		
		produtoRepository.save(produtoAtual);
		
		return true;
		
	}
	
	public boolean deletarProduto(Long id) {
		
		Optional<Produto> produtoBanco = buscarPorId(id);
		
		if(produtoBanco.isEmpty()) {
			
			return false;
		}
		
		produtoRepository.deleteById(id);
		
		return true;
		
	}
	
	public List<Produto> buscarPorNome(String nome){
		
		return produtoRepository.findByNomeContainingIgnoreCase(nome);
		
	}
	
	public Optional<Produto> buscarPorId(Long id) {
		
		return produtoRepository.findById(id);
	}
	
	public List<Produto> buscarAllProdutos(){
		
		return produtoRepository.findAll();
		
	}
	
	

}
