/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.net.*;
import java.io.*;
public class servidorTCP {
    public static void main(String[] args) throws IOException {
        
        ObjectInputStream objectIS = null;
        ObjectOutputStream objectOS = null;
        Socket socket = null;
        
        ServerSocket serverSocket = new ServerSocket(9999);
        
        try{
            socket = serverSocket.accept();
            objectIS = new ObjectInputStream (socket.getInputStream());
            objectOS = new ObjectOutputStream (socket.getOutputStream());
            
            String mensaje = (String)objectIS.readObject();
            
            String respuesta = "Su mensaje " + mensaje + " ha sido recibido en el servidor";
            
            objectOS.writeObject(respuesta);
            
            System.out.println("El mensaje enviado por el cliente ha sido respondido en el cmd de windows");
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
