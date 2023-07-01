package com.upc.comebien.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecetaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer valoracion;
    private Integer calorias;
    private String link;
}
