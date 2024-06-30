package com.example.demo_app.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_app.dto.Usuario;
import com.example.demo_app.services.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	public UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping("/all")
	public List<Usuario> getAllUsuarios(){
		return usuarioServiceImpl.getAllUsuarios();
	}
	
	@GetMapping("/{id}")
	public Usuario getUsuarioById(@PathVariable(name="id") Long id) {
		Optional<Usuario> usuario = usuarioServiceImpl.getUsuarioById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@PostMapping("/create")
	public Optional<Usuario> createUsuario(@RequestBody Usuario usuario){
		Optional<Usuario> newUsuario = usuarioServiceImpl.createUsuario(usuario);
		if(newUsuario.isPresent()) {
			return newUsuario;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@PutMapping("/update/{id}")
	public Usuario updateUsuario(@PathVariable(name="id") Long id, @RequestBody Usuario usuario){
		Usuario updateUsuario = usuarioServiceImpl.updateUsuario(id, usuario);
		return updateUsuario;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUsuario(@PathVariable(name="id") Long id) {
		usuarioServiceImpl.deleteUsuarioById(id);
	}
}
