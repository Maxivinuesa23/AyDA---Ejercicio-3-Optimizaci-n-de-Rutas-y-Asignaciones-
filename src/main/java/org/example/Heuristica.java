package org.example;

import java.util.ArrayList;

public class Heuristica {
    public void buscarcamino(int matrizciudades[][], int min){
        boolean[] visitadas = new boolean[min];
        int ciudadActual = 0;
        int longitud= 0;
        visitadas[ciudadActual] = true;
        ArrayList<Integer> camino = new ArrayList<>();
        camino.add(ciudadActual);

        for(int k = 0; k < (min - 1); k++){
            int siguiente = -1;
            int costoMin = Integer.MAX_VALUE;
            for(int j = 0; j < min; j++){
                if(!visitadas[j] && matrizciudades[ciudadActual][j] < costoMin){
                    costoMin = matrizciudades[ciudadActual][j];
                    siguiente = j;
                }
            }
            longitud = longitud + costoMin;
            ciudadActual = siguiente;
            visitadas[ciudadActual] = true;
            camino.add(ciudadActual);
        }
        longitud = longitud + matrizciudades[ciudadActual][0];
        // Volver al inicio
        camino.add(0);

        System.out.print("Resultado: ");
        for (int i=0; i<camino.size(); i++){
            System.out.print(""+camino.get(i)+" ");
        }

        System.out.println("\nLongitud: " +longitud);
    }
}
