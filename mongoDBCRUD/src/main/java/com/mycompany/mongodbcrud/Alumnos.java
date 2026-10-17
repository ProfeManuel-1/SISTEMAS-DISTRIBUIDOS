/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mongodbcrud;


public class Alumnos {
    String nombre;
    String apellidos;
    String estudios;

    public Alumnos(String nombre, String apellidos, String estudios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estudios = estudios;
    }

    public Alumnos(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

}
