/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1_jesusmelendezoteros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nocturno
 */
public class Ejercicio1_JesusMelendezOteros {

    public static void main(String[] args) {
        
        try {
            Scanner sc = new Scanner(System.in);
            File fichero = new File("src/main/java/com/mycompany/ejercicio1_jesusmelendezoteros/entrada.bin");
            FileWriter filewriter = new FileWriter(fichero);
            


                    
            String respuesta = "";
            while (!respuesta.equals("0")) {            

                System.out.println("Introduce un numero: ");
                respuesta = sc.nextLine();
                
                if (respuesta.equals("0")) {
                    break;
                }
                
                filewriter.write(respuesta + System.lineSeparator());                

            }
            filewriter.close();
            System.out.println("Fin de escritura de datos.");
            
            FileReader fr = new FileReader(fichero);
            List<Integer> numerosLeidos = new ArrayList<Integer>();
            int caracter;
            String numero = "";
            
            while ((caracter = fr.read()) != -1) {
                if (caracter == '\n') {
                    // Al encontrar salto de línea, guarda la provincia acumulada y reinicia
                    numerosLeidos.add(Integer.parseInt(numero));
                    numero = "";
                } else if (caracter != '\r') {
                    // Ignora el retorno de carro (\r en Windows) y concatena el carácter
                    numero += (char) caracter;
                }
            }
            
            fr.close();
            
            if (!numerosLeidos.isEmpty()) {
                int mayor = numerosLeidos.get(0);
                int menor = numerosLeidos.get(0);
                int suma = 0;

                // Muestra el contenido recuperado en el array provinciasLeidas
                System.out.println("Numeros leídos correctamente del fichero:");

                for (int i = 0; i < numerosLeidos.size(); i++) {
                    suma = suma + numerosLeidos.get(i);
                    
                    System.out.println((i + 1) + ". " + numerosLeidos.get(i));
                    
                    if (numerosLeidos.get(i) > mayor) {
                        mayor = numerosLeidos.get(i);
                    }
                    
                    if (numerosLeidos.get(i) < menor){
                        menor = numerosLeidos.get(i);
                    }
                }
                
                File fichero2 = new File("src/main/java/com/mycompany/ejercicio1_jesusmelendezoteros/salida.txt");
                FileWriter filewriter2 = new FileWriter(fichero2);
                         
                filewriter2.write("Mayor: " + mayor + System.lineSeparator());
                filewriter2.write("Menor: " + menor + System.lineSeparator());
                filewriter2.write("Suma: " + suma + System.lineSeparator());
                
                filewriter2.close();
                
                System.out.println("Mayor: " + mayor
                        + "\nMenor: " + menor
                        + "\nSuma: " + suma);
            }else{
                System.out.println("Fichero no tiene numeros");
            }
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
                
        
    }
}
