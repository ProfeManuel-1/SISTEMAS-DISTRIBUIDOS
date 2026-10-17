/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat2;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.*;

public class Cliente extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;

    private JTextPane areaChat;
    private StyledDocument doc;
    private JTextField txtMensaje;
    private JButton btnEnviar;

    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private String nombre;
    private String rutaAvatar;

    public Cliente(String host, int puerto, String nombre, String rutaAvatar) {
        super("Chat de " + nombre);
        this.nombre = nombre;
        this.rutaAvatar = rutaAvatar;

        // Interfaz
        areaChat = new JTextPane();
        areaChat.setEditable(false);
        doc = areaChat.getStyledDocument();
        add(new JScrollPane(areaChat), "Center");

        txtMensaje = new JTextField();
        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(this);

        JPanel panel = new JPanel(new java.awt.BorderLayout());
        panel.add(txtMensaje, "Center");
        panel.add(btnEnviar, "East");
        add(panel, "South");

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Conexión
        try {
            socket = new Socket(host, puerto);
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
            new Thread(this).start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar: " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEnviar) {
            String mensaje = txtMensaje.getText().trim();
            if (!mensaje.isEmpty()) {
                try {
                    // Enviar solo el texto al servidor
                    salida.writeUTF(nombre + ": " + mensaje);
                    // Mostrar mensaje local con avatar
                    agregarMensaje(nombre, mensaje, rutaAvatar);
                    txtMensaje.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al enviar mensaje");
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = entrada.readUTF();
                // Mostrar mensaje recibido sin avatar (solo texto)
                agregarMensajeRecibido(msg);
            }
        } catch (IOException e) {
            try {
                doc.insertString(doc.getLength(), "Desconectado del servidor.\n", null);
            } catch (BadLocationException ex) {}
        }
    }

    // Muestra mensaje enviado por mí con avatar
    private void agregarMensaje(String nombre, String mensaje, String rutaAvatar) {
        try {
            doc.insertString(doc.getLength(), nombre + ": ", null);

            // Insertar imagen
            if (rutaAvatar != null) {
                ImageIcon icon = new ImageIcon(rutaAvatar);
                icon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
                areaChat.setCaretPosition(doc.getLength());
                areaChat.insertIcon(icon);
            }

            doc.insertString(doc.getLength(), " " + mensaje + "\n", null);
        } catch (BadLocationException e) {}
    }

    // Muestra mensajes recibidos (sin avatar)
    private void agregarMensajeRecibido(String mensaje) {
        try {
            doc.insertString(doc.getLength(), mensaje + "\n", null);
        } catch (BadLocationException e) {}
    }

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Escribe tu nombre:");
        if (nombre != null && !nombre.isBlank()) {

            // Seleccionar avatar
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Selecciona tu avatar");
            String rutaAvatar = null;
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                rutaAvatar = chooser.getSelectedFile().getAbsolutePath();
            }

            // ⚠️ Cambia la IP por la del servidor o Hamachi
            new Cliente("10.254.40.161", 8080, nombre.trim(), rutaAvatar);
        }
    }

    
}
