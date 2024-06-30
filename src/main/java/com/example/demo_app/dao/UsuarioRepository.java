package com.example.demo_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo_app.dto.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
