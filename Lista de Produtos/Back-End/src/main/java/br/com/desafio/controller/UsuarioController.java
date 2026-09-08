package br.com.desafio.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.model.Usuario;
import br.com.desafio.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<String> verificarUsuario(@Valid @RequestBody Usuario usuario) {
		
		boolean resultado = usuarioService.verificarUsuario(usuario);
		
		if(resultado) {
			
			return ResponseEntity.ok().body("Login realizado com sucesso!");
		}
		
		return ResponseEntity.status(401).body("Login ou senha invalidos!");
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<String> cadastrarUsuario(@Valid @RequestBody Usuario usuario){
		
		boolean resultado = usuarioService.cadastrarUsuario(usuario);
		
		if(resultado) {
			
			return ResponseEntity.created(null).body("Usuario cadastrado com sucesso!");
		}
		
		return ResponseEntity.status(409).body("Login já cadastrado por outro usuario!");
	}
	
}
