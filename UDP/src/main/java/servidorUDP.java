/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.net.*;

public class servidorUDP {
     public static void main(String[] args) throws SocketException, IOException {
        
        DatagramSocket socket = new DatagramSocket(9999);
        
        byte[] mensaje = new byte[256];
        DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length);
        
        socket.receive(paquete);
        
        String mensajeRecibido = new String(paquete.getData(), 0, paquete.getLength());
        
        String respuesta = "Mensaje recibido en el servidor con el siguiente contenido: " + mensajeRecibido;
        byte[] respuestaBytes = respuesta.getBytes();
        
        InetAddress direccion = paquete.getAddress();
        paquete = new DatagramPacket(respuestaBytes, respuestaBytes.length, direccion, paquete.getPort());
        socket.send(paquete);
        System.out.println("El Servidor ha respondido!!!");
        
    }

}
