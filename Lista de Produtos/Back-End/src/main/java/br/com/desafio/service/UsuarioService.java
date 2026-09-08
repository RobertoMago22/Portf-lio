package br.com.desafio.service;

import org.springframework.stereotype.Service;

import br.com.desafio.model.Usuario;
import br.com.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public boolean verificarUsuario(Usuario usuario) {
		
		Usuario usuarioEncontrado = usuarioRepository.findByLogin(usuario.getLogin());
		
		if(usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(usuario.getSenha())) {
			
			return true;
		}
		
		return false;
		
	}
	
	public boolean cadastrarUsuario(Usuario usuario) {
		
		Usuario usuarioEncontrado = usuarioRepository.findByLogin(usuario.getLogin());
		
		if(usuarioEncontrado == null) {
			
			usuarioRepository.save(usuario);
			
			return true;
		}
		
		return false;
		
	}
	
	

}
