package br.com.desafio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O login é obrigatorio.")
	@Size(min = 4, max = 30, message = "O login deve possuir entre 4 à 30 caracteres.")
	@Column(nullable = false, length = 30, unique = true)
	private String login;
	
	@NotBlank(message = "A senha é obrigatorio")
	@Size(min = 4, max = 20, message = "A senha deve possuir entre 4 à 20 caracteres.")
	@Column(nullable = false, length = 20)
	private String senha;
	
	
	public Usuario() {
		
	}

	public Usuario(
			@NotBlank(message = "O login é obrigatorio.")
			@Size(min = 4, max = 30, message = "O login deve possuir entre 4 à 30 caracteres.")
			String login, 
			@NotBlank(message = "O login é obrigatorio.")
			@Size(min = 4, max = 30, message = "O login deve possuir entre 4 à 30 caracteres.")
			String senha) {
		
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	

}
