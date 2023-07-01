package com.upc.comebien.negocios;

import com.upc.comebien.entidades.Receta;
import com.upc.comebien.repositorios.RecetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecetaNegocio {
    @Autowired
    private RecetaRepositorio recetaRepositorio;
    @Transactional
    public Receta registrar(Receta receta){
        return recetaRepositorio.save(receta);
    }
    public Receta buscar(Long codigo) throws Exception {
        return recetaRepositorio.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public Receta buscarPorNombre(String nombre) {
        return recetaRepositorio.findByNombre(nombre);
    }
    public List<Receta> buscarPorValoracion(Integer valoracion) {
        return recetaRepositorio.findByValoracion(valoracion);
    }
    public List<Receta> buscarPorCalorias(Integer calorias) {
        return recetaRepositorio.findByCalorias(calorias);
    }
    public List<Receta> listado(){
        return recetaRepositorio.findAll();
    }
    public Receta actualizarReceta(Receta receta) throws Exception {
        recetaRepositorio.findById(receta.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return recetaRepositorio.save(receta);
    }
    public Receta borrarReceta(Long codigo) throws Exception{
        Receta receta = recetaRepositorio.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        recetaRepositorio.delete(receta);
        return receta;
    }
}
