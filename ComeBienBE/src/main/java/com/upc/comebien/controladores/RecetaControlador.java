package com.upc.comebien.controladores;

import com.upc.comebien.dtos.RecetaDTO;
import com.upc.comebien.entidades.Receta;
import com.upc.comebien.negocios.RecetaNegocio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:5000"})
@RestController
@RequestMapping("/api")
public class RecetaControlador {
    @Autowired
    public RecetaNegocio recetaNegocio;
    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetas(){
        List<Receta> list = recetaNegocio.listado();
        List<RecetaDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<RecetaDTO>>(listDto, HttpStatus.OK);
    }
    @GetMapping("/recetas/valoracion/{valoracion}")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorValoracion(@PathVariable(value = "valoracion") Integer valoracion){
        List<Receta> list = recetaNegocio.buscarPorValoracion(valoracion);
        List<RecetaDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<RecetaDTO>>(listDto, HttpStatus.OK);
    }
    @GetMapping("/recetas/calorias/{caloria}")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorCaloria(@PathVariable(value = "caloria") Integer caloria){
        List<Receta> list = recetaNegocio.buscarPorCalorias(caloria);
        List<RecetaDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<RecetaDTO>>(listDto, HttpStatus.OK);
    }
    @PostMapping("/receta")
    public ResponseEntity<RecetaDTO> crearReceta(@RequestBody RecetaDTO recetaDTO){
        Receta receta;
        try {
            receta = convertToEntity(recetaDTO);
            recetaDTO = convertToDto(recetaNegocio.registrar(receta));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
    }
    @PutMapping("/receta")
    public ResponseEntity<RecetaDTO> actualizarReceta(@RequestBody RecetaDTO recetaDetalle){
        RecetaDTO recetaDTO;
        Receta receta;
        try {
            receta = convertToEntity(recetaDetalle);
            receta = recetaNegocio.actualizarReceta(receta);
            recetaDTO = convertToDto(receta);
            return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    @DeleteMapping("/receta/{codigo}")
    public ResponseEntity<RecetaDTO> borrarReceta(@PathVariable(value = "codigo") Long codigo){
        Receta receta;
        RecetaDTO recetaDTO;
        try {
            receta = recetaNegocio.borrarReceta(codigo);
            recetaDTO = convertToDto(receta);
            return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }
    @GetMapping("/receta/{codigo}")
    public ResponseEntity<RecetaDTO> obtenerReceta(@PathVariable(value = "codigo") Long codigo){
        Receta receta;
        RecetaDTO recetaDTO;
        try {
            receta = recetaNegocio.buscar(codigo);
            recetaDTO = convertToDto(receta);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo obtener, sorry");
        }
        return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
    }
    @GetMapping("/receta/{nombre}")
    public ResponseEntity<RecetaDTO> obtenerRecetaPorNombre(@PathVariable(value = "nombre") String nombre){
        Receta receta;
        RecetaDTO recetaDTO;
        try {
            receta = recetaNegocio.buscarPorNombre(nombre);
            recetaDTO = convertToDto(receta);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo obtener, sorry");
        }
        return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
    }

    private RecetaDTO convertToDto(Receta receta){
        ModelMapper modelMapper = new ModelMapper();
        RecetaDTO recetaDTO = modelMapper.map(receta, RecetaDTO.class);
        return recetaDTO;
    }
    private Receta convertToEntity(RecetaDTO recetaDTO){
        ModelMapper modelMapper = new ModelMapper();
        Receta receta = modelMapper.map(recetaDTO, Receta.class);
        return receta;
    }
    private List<RecetaDTO> convertToLisDto(List<Receta> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
