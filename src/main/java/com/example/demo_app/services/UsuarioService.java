package com.example.demo_app.services;

import java.util.List;
import java.util.Optional;
import com.example.demo_app.dto.Usuario;

public interface UsuarioService {

	List<Usuario> getAllUsuarios();
	Optional<Usuario> getUsuarioById(Long id);
	Optional<Usuario> createUsuario(Usuario usuario);
	Usuario updateUsuario(Long id, Usuario usuario);
	void deleteUsuarioById(Long id);
	
}
