package com.example.demo_app.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_app.dao.UsuarioRepository;
import com.example.demo_app.dto.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	public UsuarioRepository usuarioRepository;
	
    Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuario = usuarioRepository.findAll();
		return usuario;
	}

	@Override
	public Optional<Usuario> getUsuarioById(Long id) {
		Optional<Usuario> searchUser = usuarioRepository.findById(id);
		if(searchUser.isPresent()) {
			logger.info("Usuario encontrado.");
			return searchUser;
		} else {
			logger.error("No se encontró el usuario" + id + "buscado");
			throw new NoSuchElementException("No se encontró el usuario" + id + "buscado");
		}
	}

	@Override
	public Optional<Usuario> createUsuario(Usuario usuario) {
		Optional<Usuario> optUsuario = Optional.of(usuario);
		if(optUsuario.isPresent()) {
			Usuario newUser = new Usuario();
			newUser.setNombre(usuario.getNombre());
			newUser.setApellidos(usuario.getApellidos());
			newUser.setDni(usuario.getDni());
			newUser.setSueldo(usuario.getSueldo());
			usuarioRepository.save(newUser);
			logger.info("Se ha creado el usuario correctamente.");
			Optional<Usuario> rturndOptUser = Optional.of(newUser);
			return rturndOptUser;
		} else {
			logger.error("No se ha recibido un usuario para crear.");
			throw new NoSuchElementException("No se ha recibido un usuario para crear.");
		}
	}

	@Override
	public Usuario updateUsuario(Long id, Usuario usuario) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if(optUsuario.isPresent()) {
			Usuario usuarioUpdate = optUsuario.get();
			usuarioUpdate.setNombre(usuario.getNombre());
			usuarioUpdate.setApellidos(usuario.getApellidos());
			usuarioUpdate.setDni(usuario.getDni());
			usuarioUpdate.setSueldo(usuario.getSueldo());
			usuarioRepository.save(usuarioUpdate);
			logger.info("Usuario con id: " + id + " actualizado correctamente.");
			return usuarioUpdate;
		} else {
			logger.error("El usuario a modificar no existe");
			throw new NoSuchElementException("No se encontró el usuario" + id + "buscado");
		}
	}

	@Override
	public void deleteUsuarioById(Long id) {
	    Optional<Usuario> optUsuario = usuarioRepository.findById(id);
	    if(optUsuario.isPresent()) {
	    	Usuario usuarioTrust = optUsuario.get();
	    	usuarioRepository.deleteById(usuarioTrust.getId());
			logger.info("Usuario con id: " + id + " de forma efectiva.");
	    } else {
	    	logger.error("El usuario a eliminar no existe.");
	    }
	}

}
