/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package jframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;

import javax.swing.JTextField;

import javax.swing.JFrame;


public class Cliente extends JFrame implements ActionListener{
        JTextField txtmensaje;
	JButton btnEnviar;
	
        public Cliente() {
	txtmensaje=new JTextField();
	txtmensaje.setBounds(10,10,200,20);//ubica el texto dentro del formulario y pasar coordenadas
	add(txtmensaje);//agrega el mensaje al formulario
	
	btnEnviar=new JButton();//instancia del boton
	btnEnviar.setText("Enviar");
	
	btnEnviar.setBounds(10,40,150,20);
	btnEnviar.addActionListener(this);
	add(btnEnviar);	
	//propiedades del formulario
	setLayout(null);
	setSize(400,400);
	setVisible(true);

        }
    public static void main(String[] args) {
       new Cliente();
    }
    
	@Override

    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnEnviar){
	try {
	Socket cli=new Socket("localhost",8080);
	DataOutputStream flujo=new DataOutputStream(cli.getOutputStream());
				
	flujo.writeUTF(txtmensaje.getText());
	cli.close();
				
	}catch(Exception ex){
	System.out.println("Error en Cliente" + ex.getMessage());
				
	}
			
		}
	}
}
