
package com.gomensoro.ventasgomensoro.dao;

//Clase creada solamente para manipular el arraylist

import java.util.List;

public class Metodos<T> {
    
    private final List<T> a;

    public Metodos(List<T> a) {
        this.a = a;
    }
    
    public void agregarRegistro(T p){
        this.a.add(p); //recive parametro generico y lo agrega a la lista
    }
    
    public void modificar(int i, T p){ //le paso el indice y el parametro
        this.a.set(i, p);
    }
    
    public void eliminarRegistro(int i){
        this.a.remove(i);
    }
    
    public T obtenerRegistro(int i){
        return (T)this.a.get(i);
    }
    
    public int cantidadRegistro(){
        return this.a.size();
    }
}
