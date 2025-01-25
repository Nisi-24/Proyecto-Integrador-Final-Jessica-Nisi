package ar.edu.centro8.proyecto_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.proyecto_final.entities.Usuario;
import ar.edu.centro8.proyecto_final.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
     @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/traer")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

   @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario updatedUsuario) {
        Usuario updated = usuarioRepository.save(updatedUsuario);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(existingUsuario);
        
        return ResponseEntity.ok("El usuario ha sido actualizado correctamente");
    }
}
