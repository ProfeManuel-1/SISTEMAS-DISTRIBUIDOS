/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.cereales;
import java.util.Scanner;
public class Cereales {

    public static void main(String[] args) {
        Scanner leer=new Scanner(System.in);
        
    int inferior = 0; 
    int superior = 0; 
    int totAnual = 0; 
    
    int toneladas[]=new int[12];

    for (int i=0;i<12;i++){
    System.out.println("Ingresa las toneladas del mes:"+(i+1));
    toneladas[i]=leer.nextInt();
 	totAnual = totAnual + toneladas[i]; 

}
  
    int promedioAnual=totAnual/12; 

    for (int i=0;i<12;i++) { 
    if (toneladas[i]>promedioAnual) {
    superior++;
}   else { 
}
    if (toneladas[i]<promedioAnual) { 
    inferior++; 
    } 
} 
System.out.println("Promedio anual de toneladas: " +promedioAnual); 
System.out.println("Meses que no superaron: " +inferior); 
System.out.println("Meses que superaron: " +superior); 
    } 
}

