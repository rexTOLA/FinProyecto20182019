package rutas;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author rexTOLA
*/
public class Programa {
   private String nombre;
   private String path;

   public Programa(String nombre, String path) {
       this.nombre = nombre;
       this.path = path;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getPath() {
       return path;
   }

   public void setPath(String path) {
       this.path = path;
   }
   
 //toString

   @Override
   public String toString() {
       return nombre;
   }
   

   
   
}

