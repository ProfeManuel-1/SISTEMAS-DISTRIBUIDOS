/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mongodbcrud;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import java.net.UnknownHostException;


public class basedatos {
     public DBCollection coleccion() throws UnknownHostException{
        MongoClient mongoDB = new MongoClient();
        DB baseDatos = mongoDB.getDB("Universidad");
        DBCollection coleccion = baseDatos.getCollection("Alumnos");
        return coleccion;    
    }
    
    public void insertar1(Alumnos alumno) throws UnknownHostException{
        BasicDBObject documento = new BasicDBObject();
        documento.put("nombre", alumno.nombre);
        documento.put("apellidos", alumno.apellidos);
        documento.put("estudios", alumno.estudios);
        coleccion().insert(documento);
    }
    
     public void insertar2(Alumnos alumno) throws UnknownHostException{
        BasicDBObject documento = new BasicDBObject();
        documento.put("nombre", alumno.nombre);
        documento.put("apellidos", alumno.apellidos);
        coleccion().insert(documento);
    }
     
    public void modificar(String nombre, String apellidos, String estudios) throws UnknownHostException{
        DBObject query = new BasicDBObject("nombre", nombre);
        DBObject update = new BasicDBObject();
        update.put("nombre", nombre);
        update.put("apellidos", apellidos);
        update.put("estudios", estudios);
        WriteResult resultado = coleccion().update(query, update);
    }
    
    public void eliminar(String nombre) throws UnknownHostException{
        DBObject documento = new BasicDBObject("nombre", nombre);
        WriteResult resultado = coleccion().remove(documento);
    }
    
    public void buscarTodos() throws UnknownHostException{
        DBCursor cursorTodos = coleccion().find();
        while(cursorTodos.hasNext()){
            System.out.println(cursorTodos.next());
        }
    }
    
    public void buscarNombre(String nombre) throws UnknownHostException{
        DBObject nombres = new BasicDBObject("nombre", nombre);
        DBCursor cursorNombres = coleccion().find(nombres);
        while(cursorNombres.hasNext()){
            System.out.println(cursorNombres.next());
        }
    }
    
    public void ordenar(String atributo) throws UnknownHostException{
        DBCursor cursorOrdenar = coleccion().find();
        cursorOrdenar.sort(new BasicDBObject(atributo, 1));
        while(cursorOrdenar.hasNext()){
            System.out.println(cursorOrdenar.next());
        }
    }

}
