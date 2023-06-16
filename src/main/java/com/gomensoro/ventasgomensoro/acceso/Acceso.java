
package com.gomensoro.ventasgomensoro.acceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author GIGABYTE
 */
public class Acceso {
    
    public static List<String> cargarArchivo(String ruta){
        List<String> lista = null;
        FileReader fi;
        BufferedReader bu;
        String linea;
        File carpeta = new File("Archivos");
        File archivo = new File("Archivos" + ruta);
                
        try{
            if(!carpeta.exists()){
                FileUtils.forceMkdir(carpeta); //en caso de no existir crea esa carpeta
            }
            if(!archivo.exists()){
                archivo.createNewFile();
            }
            
            lista = new ArrayList<>(); //Instancio la lista
            fi = new FileReader("Archivos" + ruta); //leo el archivo
            bu = new BufferedReader(fi); //leo el archivo de texto que esta en el FileReader
            
            while((linea = bu.readLine()) != null){ //mientras la lectura de la linea sea distinto a nulo, leo el archivo
                lista.add(linea);
            }
            bu.close();
            
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo: " + archivo.getName() + " ->" + e.getMessage());
        }
        return lista;
    }
    
}
