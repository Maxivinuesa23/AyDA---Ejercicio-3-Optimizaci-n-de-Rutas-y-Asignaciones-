package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Scanner scc = new Scanner(System.in);
        int opc = 0;
        long inicio= 0;
        long memoriaAntes = 0;
        long memoriaDespues = 0;
        long fin = 0;
        long memoriaUsada = 0;
        long duracion = 0;


        GeneradorMatriz generarMatriz = new GeneradorMatriz();

        int matrizciudades[][]= generarMatriz.generarmatriz();

        Heuristica heuristica = new Heuristica();
        FuerzaBruta fuerzaBruta = new FuerzaBruta();
        DivideYVencerasBanchAndBound divide = new DivideYVencerasBanchAndBound();


        do{
            System.out.println("*************************");
            System.out.println("*  Encontrar Caminos  *");
            System.out.println("* 1. Re-Generar Matriz 15x15 *");
            System.out.println("* 2. Utilizar Algoritmo Heuristica   *");
            System.out.println("* 3. Utilizar Fuerza Bruta   *");
            System.out.println("* 4. Utilizar Divide Y Venceras Con Branch And Bound  *");
            System.out.println("*************************");
            System.out.println("* 0. Salir*");
            opc = scc.nextInt();

            switch(opc){
                case 1:
                    try {
                        matrizciudades= generarMatriz.generarmatriz();
                        System.out.println("Matriz generada!");
                        System.out.println("\nPresiona Enter para salir...");
                        System.in.read(); // Espera una tecla antes de terminar el programa
                    } catch (Exception e) {
                        System.out.println("Error al crear la matriz");
                    }
                    break;

                case 2:
                    inicio = System.nanoTime();
                    memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
                    //*************************

                    heuristica.buscarcamino(matrizciudades);

                    //*************************
                    memoriaDespues = runtime.totalMemory() - runtime.freeMemory();
                    fin = System.nanoTime();
                    memoriaUsada = memoriaDespues - memoriaAntes;
                    duracion = fin - inicio;
                    System.out.println("Tiempo de ejecución para encontrar el camino: " + duracion / 1_000_000 + " ms");
                    System.out.println("Memoria usada aproximada: " + (memoriaUsada / (1024.0 * 1024.0)) + " MB");

                    System.out.println("\nPresiona Enter para salir...");
                    System.in.read(); // Espera una tecla antes de terminar el programa
                    break;

                case 3:
                    inicio = System.nanoTime();
                    memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
                    //*************************

                    fuerzaBruta.encontrarTSP(matrizciudades);

                    //*************************
                    memoriaDespues = runtime.totalMemory() - runtime.freeMemory();
                    fin = System.nanoTime();
                    memoriaUsada = memoriaDespues - memoriaAntes;
                    duracion = fin - inicio;
                    System.out.println("Tiempo de ejecución para encontrar el camino: " + duracion / 1_000_000 + " ms");
                    System.out.println("Memoria usada aproximada: " + (memoriaUsada / (1024.0 * 1024.0)) + " MB");

                    System.out.println("\nPresiona Enter para salir...");
                    System.in.read(); // Espera una tecla antes de terminar el programa
                    break;

                case 4:
                    inicio = System.nanoTime();
                    memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
                    //*************************

                    divide.solveTSP(matrizciudades);

                    //*************************
                    memoriaDespues = runtime.totalMemory() - runtime.freeMemory();
                    fin = System.nanoTime();
                    memoriaUsada = memoriaDespues - memoriaAntes;
                    duracion = fin - inicio;
                    System.out.println("Tiempo de ejecución para encontrar el camino: " + duracion / 1_000_000 + " ms");
                    System.out.println("Memoria usada aproximada: " + (memoriaUsada / (1024.0 * 1024.0)) + " MB");

                    System.out.println("\nPresiona Enter para salir...");
                    System.in.read(); // Espera una tecla antes de terminar el programa
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion No Valida.");
                    break;
            }

        }while (opc != 0);

    }
}