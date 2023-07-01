package com.upc.comebien.repositorios;

import com.upc.comebien.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaRepositorio extends JpaRepository<Receta, Long> {
    Receta findByNombre(String nombre);
    List<Receta> findByValoracion(Integer valoracion);
    List<Receta> findByCalorias(Integer calorias);
}
