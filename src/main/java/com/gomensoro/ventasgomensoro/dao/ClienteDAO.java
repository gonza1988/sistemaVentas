package com.gomensoro.ventasgomensoro.dao;

import com.gomensoro.ventasgomensoro.acceso.Acceso;
import com.gomensoro.ventasgomensoro.dao.interfaces.metodosDao;
import com.gomensoro.ventasgomensoro.entidades.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ClienteDAO implements metodosDao<Cliente> {

    private final List<Cliente> lista;
    private Metodos<Cliente> metodos;
    private final String ruta = "cliente.txt";
    private boolean resp; //creado para saber si se inserto con exito o no un metodo
    private Cliente cliente;

    public ClienteDAO() {
        lista = new ArrayList<>();
        metodos = new Metodos<>(lista);
        cargarLista(); //cada vez que se inicialice se va a cargar la lista con la lectura del archivo txt
    }

    private void cargarLista() {
        Cliente cliente;
        for (String dato : Acceso.cargarArchivo(ruta)) {
            StringTokenizer st = new StringTokenizer(dato, ","); //sirve para leer los datos separados en este caso por una ","
            cliente = new Cliente(Integer.parseInt(st.nextToken()), st.nextToken());
            metodos.agregarRegistro(cliente);
        }
    }

    @Override
    public List listar() {
        List<Cliente> registros = new ArrayList<>();
        try {
            for (String dato : Acceso.cargarArchivo(ruta)) {
                StringTokenizer st = new StringTokenizer(dato, ","); //sirve para leer los datos separados en este caso por una ","
                cliente = new Cliente(Integer.parseInt(st.nextToken()), st.nextToken());
                registros.add(cliente);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Clientes: " + e.getMessage());
        }

        return registros;
    }

    @Override
    public boolean insertar(Cliente obj) {
        resp = false;
        PrintWriter pw;
        FileWriter fw;

        try {
            fw = new FileWriter("Archivos/" + ruta); //donde vamos a guardar la informacion
            pw = new PrintWriter(fw); //escribe en el archivo segun la ruta que le pasemos
            obj = new Cliente(obj.getRut(), obj.getNombre());
            int codigo = buscarCodigo(obj.getRut());

            if (codigo == -1) {
                metodos.agregarRegistro(obj);
            } else {
                metodos.modificar(codigo, obj);
            }

            for (int i = 0; i < metodos.cantidadRegistro(); i++) {
                cliente = metodos.obtenerRegistro(i);
                pw.println(String.valueOf(cliente.getRut() + "," + cliente.getNombre()));
            }
            pw.close();
            resp = true;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar Clientes: " + e.getMessage());
        }

        return resp;
    }

    @Override
    public boolean actualizar(Cliente obj) {
        resp = false;
        PrintWriter pw;
        FileWriter fw;

        try {
            fw = new FileWriter("Archivos/" + ruta); //donde vamos a guardar la informacion
            pw = new PrintWriter(fw); //escribe en el archivo segun la ruta que le pasemos
            obj = new Cliente(obj.getRut(), obj.getNombre());
            metodos.agregarRegistro(obj);

            for (int i = 0; i < metodos.cantidadRegistro(); i++) {
                cliente = metodos.obtenerRegistro(i);
                pw.println(String.valueOf(cliente.getRut() + "," + cliente.getNombre()));
            }
            pw.close();
            resp = true;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar Clientes: " + e.getMessage());
        }

        return resp;
    }

    @Override
    public int buscarCodigo(int codigo) {
        for (int i = 0; i < metodos.cantidadRegistro(); i++) {
            if (codigo == metodos.obtenerRegistro(i).getRut()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Cliente getObjeto(int codigo) {
        Cliente cliente = null;

        for (int i = 0; i < metodos.cantidadRegistro(); i++) {
            cliente = metodos.obtenerRegistro(i);
            if (cliente.getRut() == codigo) {
                return cliente;
            }
        }
        return cliente;
    }

}
