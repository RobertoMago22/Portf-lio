package br.com.desafio.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatorio!")
	@Size(min = 2, max = 50, message = "O nome teve ter entre 2 à 50 caracteres!")
	@Column(nullable = false, length = 50)
	private String nome;
	
	@NotNull(message = "O preço é obriatorio!")
	@DecimalMin(value = "0.00", message = "O preço não pode ter o valor menor que R$.: 0,00")
	@DecimalMax(value = "999999999999999999.99", message = "O valor máximo é R$.: 999.999.999.999.999.999,99")
	@Digits(integer = 18, fraction = 2, message = "O preço deve ter até 2 números como fração e até 18 como inteiros!")
	@Column(nullable = false, precision = 20, scale = 2)
	private BigDecimal preco;
	
	@NotNull(message = "A quantidade é obrigatoria")
	@Column(nullable = false)
	private Long quantidade;
	
	
	public Produto() {
		
	}

	public Produto(String nome, BigDecimal preco, Long quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
	

}
