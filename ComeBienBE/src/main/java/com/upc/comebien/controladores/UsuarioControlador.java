package com.upc.comebien.controladores;

import com.upc.comebien.dtos.UsuarioDTO;
import com.upc.comebien.entidades.Usuario;
import com.upc.comebien.negocios.UsuarioNegocio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioControlador {
    @Autowired
    public UsuarioNegocio usuarioNegocio;
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        List<Usuario> list = usuarioNegocio.listado();
        List<UsuarioDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<UsuarioDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario;
        try {
            usuario = convertToEntity(usuarioDTO);
            usuarioDTO = convertToDto(usuarioNegocio.registrar(usuario));
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }
    @PutMapping("/usuario")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDetalle){
        UsuarioDTO usuarioDTO;
        Usuario usuario;
        try {
            usuario = convertToEntity(usuarioDetalle);
            usuario = usuarioNegocio.actualizarUsuario(usuario);
            usuarioDTO = convertToDto(usuario);
            return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    @DeleteMapping("/usuario/{codigo}")
    public ResponseEntity<UsuarioDTO> borrarUsuario(@PathVariable(value = "codigo") String codigo){
        Usuario usuario;
        UsuarioDTO usuarioDTO;
        try {
            usuario = usuarioNegocio.borrarUsuario(codigo);
            usuarioDTO = convertToDto(usuario);
            return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }
    @GetMapping("/usuario/{codigo}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable(value = "codigo") String codigo){
        Usuario usuario;
        UsuarioDTO usuarioDTO;
        try {
            usuario = usuarioNegocio.buscar(codigo);
            usuarioDTO = convertToDto(usuario);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo obtener, sorry");
        }
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }
    private UsuarioDTO convertToDto(Usuario usuario){
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }
    private Usuario convertToEntity(UsuarioDTO usuarioDTO){
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return usuario;
    }
    private List<UsuarioDTO> convertToLisDto(List<Usuario> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
