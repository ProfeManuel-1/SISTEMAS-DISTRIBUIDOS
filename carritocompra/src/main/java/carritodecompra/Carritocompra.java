/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package carritodecompra;

import java.util.Scanner;
import java.util.ArrayList;

public class Carritocompra {

    public static void main(String[] args) {
        String[]producto={"leche","jamon","pollo","cereal","mantequilla"};
        double[]precios={23.90,55.50,49.90,20.50,8.50};
        int opcion=0;
        double total=0;
        //un array list para simular el carrito de compra
        ArrayList<String> carrito= new ArrayList();
        Scanner lector= new Scanner(System.in);
        System.out.println("Producto en la Tienda");
        
       for(int i=0;i<5;i++){
            System.out.println(i +" "+ producto[i]+":"+precios[i]);
       }
       System.out.println("Bienvenidos a mi Tienda La economica");
        System.out.println("1:agregar un producto");
       System.out.println("Elija los productos");
       System.out.println("Para ver los productos 6");
       System.out.println("Presione 7 para salir");
       
       do{
           opcion=lector.nextInt();
           switch(opcion){
               case 1:
                   carrito.add(producto[0]);
                   total=total+ precios[0];
                   break;
                   
               case 2:
                   carrito.add(producto[1]);
                   total=total+ precios[1];
                   break;
                   
               case 3:
                   carrito.add(producto[2]);
                   total=total+ precios[2];
                   break;
                   
               case 4:
                   carrito.add(producto[3]);
                   total=total+ precios[3];
                   break;
                   
               case 5 :
                   carrito.add(producto[4]);
                   total=total+ precios[4];
                   break;
                   
               case 6: 
                   for(int i=0;i<=carrito.size();i++){
                         System.out.println(carrito.get(i));
                   }
           break;
                }
       
    } while(opcion!=7);
         System.out.println("Su pago total es"+ total);
    }
}