
package com.gomensoro.ventasgomensoro.entidades;


public class Cliente {
    
    private Integer rut;
    private String nombre;

    public Cliente() {
    }

    public Cliente(Integer rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }
    
    

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
