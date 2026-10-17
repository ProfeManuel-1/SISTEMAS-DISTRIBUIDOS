/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mongodbcrud;

import java.net.UnknownHostException;

public class main {
    public static void main(String[] args) throws UnknownHostException {
        
        Alumnos alumno1 = new Alumnos ("Claudia", "García", "Informática");
        Alumnos alumno2 = new Alumnos ("Lucia", "Valverde", "Biología");
        Alumnos alumno3 = new Alumnos ("Tomas", "Mañas", "Psicología");
        Alumnos alumno4 = new Alumnos ("Eduardo", "García");
        
        BaseDatos bd = new BaseDatos();
                    
              
    }

}
