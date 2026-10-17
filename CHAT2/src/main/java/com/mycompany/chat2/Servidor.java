/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat2;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Servidor extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;

    private JTextArea txtMensajes;
    private ServerSocket servidor;
    private volatile boolean running = true;

    // Lista de clientes conectados
    private final List<Socket> clientes = Collections.synchronizedList(new ArrayList<>());

    public Servidor() {
        super("Servidor de Chat");

        txtMensajes = new JTextArea();
        txtMensajes.setEditable(false);
        add(new JScrollPane(txtMensajes));

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Thread(this).start();
    }

    public static void main(String[] args) {
        new Servidor();
    }

    @Override
    public void run() {
        try {
            servidor = new ServerSocket(8080);
            append("Servidor escuchando en puerto 8080...");

            while (running) {
                Socket cli = servidor.accept();
                clientes.add(cli);
                new Thread(() -> manejarCliente(cli)).start();
            }
        } catch (IOException e) {
            append("Error servidor: " + e.getMessage());
        }
    }

    private void manejarCliente(Socket cli) {
        try (DataInputStream entrada = new DataInputStream(cli.getInputStream())) {
            while (running) {
                String msg = entrada.readUTF();
                append(msg);
                enviarATodos(msg);
            }
        } catch (IOException e) {
            append("Cliente desconectado.");
            clientes.remove(cli);
        }
    }

    private void enviarATodos(String mensaje) {
        synchronized (clientes) {
            for (Socket s : clientes) {
                try {
                    DataOutputStream salida = new DataOutputStream(s.getOutputStream());
                    salida.writeUTF(mensaje);
                } catch (IOException ignored) {}
            }
        }
    }

    private void append(String text) {
        SwingUtilities.invokeLater(() -> txtMensajes.append(text + "\n"));
    }
 
}
