package org.example;

import java.util.Random;

public class GeneradorMatriz {
    Random rand = new Random();
    public int[][] generarmatriz(int min, int max){
        int[][] matrizciudades = new int[min][max];

        for(int i=0; i<min; i++){
            for (int j=0; j<max; j++){
                matrizciudades[i][j]= -1;
            }
        }

        for (int i=0; i<min; i++){
            for (int j=0; j<max; j++){
                if(i==j){
                    matrizciudades[i][j]=0;
                }
                else{
                    if(matrizciudades[i][j]==-1){
                        matrizciudades[i][j]= rand.nextInt(1000)+1;
                        matrizciudades[j][i]= matrizciudades[i][j];
                    }
                }
            }
        }

        return matrizciudades;
    }
}
