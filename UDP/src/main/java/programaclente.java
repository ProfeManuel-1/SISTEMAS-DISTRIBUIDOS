/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.net.*;


public class programaclente {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException{
        
        DatagramSocket socket = new DatagramSocket();
        byte[] ip = {127,0,0,1};
        InetAddress direccion = InetAddress.getByAddress(ip);
        
        byte[] mensaje = "Mensaje de Proyecto Java al servidor desde el lado del cliente".getBytes();
        DatagramPacket paquete = new DatagramPacket (mensaje, mensaje.length, direccion, 9999);
        
        socket.send(paquete);
        
        byte[] respuesta = new byte[256];
        paquete = new DatagramPacket(respuesta, respuesta.length, direccion, 9999);
        
        socket.receive(paquete);
        
        String confirmacionServidor = new String(paquete.getData(), 0, paquete.getLength());
        System.out.println(confirmacionServidor);
        
        socket.close();
    }

}
