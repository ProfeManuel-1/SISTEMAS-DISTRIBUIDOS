
package com.mycompany.tcp;

import java.io.*;
import java.net.*;

public class programacliente {
      public static void main(String[] args) throws IOException {
        
        ObjectInputStream objectIS = null;
        ObjectOutputStream objectOS = null;
        Socket socket = null;
        
        try{
            socket = new Socket("192.168.56.1", 9999);
            objectOS = new ObjectOutputStream(socket.getOutputStream());
            objectIS = new ObjectInputStream(socket.getInputStream());
            
            objectOS.writeObject("Hola Java desde el ordenador del cliente");
            String mensaje = (String) objectIS.readObject();
            System.out.println(mensaje);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
            if (objectOS != null || objectIS != null || socket != null){
                objectOS.close();
                objectIS.close();
                socket.close();
            }
        }
    }

}
