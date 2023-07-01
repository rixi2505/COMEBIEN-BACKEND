package com.upc.comebien.negocios;

import com.upc.comebien.entidades.Usuario;
import com.upc.comebien.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioNegocio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Transactional
    public Usuario registrar(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }
    public Usuario buscar(String codigo) throws Exception {
        return usuarioRepositorio.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<Usuario> listado(){
        return usuarioRepositorio.findAll();
    }
    public Usuario actualizarUsuario(Usuario usuario) throws Exception {
        usuarioRepositorio.findById(usuario.getUsuario()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return usuarioRepositorio.save(usuario);
    }
    public Usuario borrarUsuario(String codigo) throws Exception{
        Usuario usuario = usuarioRepositorio.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        usuarioRepositorio.delete(usuario);
        return usuario;
    }

}
