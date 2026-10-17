/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class servidor extends JFrame implements Runnable  {
    JTextArea txtmensajes;
	
	public servidor() {
		txtmensajes=new JTextArea();
		txtmensajes.setBounds(10,10,400,400);
		add(txtmensajes);
		
		setLayout(null);
		setSize(500,500);
		setVisible(true);
				
Thread hilo=new Thread(this);
		hilo.start();
		
	}
public static void main(String[] args) {
		new servidor();

}
@Override
	public void run() {
		try {
		
			ServerSocket servidor=new ServerSocket(8080);
			
			Socket cli;
			
			while(true){
				cli=servidor.accept();
				
DataInputStream flujo=new DataInputStream(cli.getInputStream());
				
	String msg=flujo.readUTF();
				
				txtmensajes.append("\n" + msg);
				
				cli.close();
				
			if(msg.equalsIgnoreCase("CERRAR CHAT")){
				servidor.close();
				break;
			}
			}
			
		} catch(IOException e){
			
			e.printStackTrace();
		}
		
	}
}
