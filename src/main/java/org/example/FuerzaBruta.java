package org.example;

import java.util.ArrayList;

public class FuerzaBruta {
    private static double costoMinimo = Double.MAX_VALUE;
    private static ArrayList<Integer> mejorRuta = null;
    private static int[][] distancias;
    private static int numCiudades;

    public static void encontrarTSP(int[][] matrizDistancias) {
        distancias = matrizDistancias;
        numCiudades = matrizDistancias.length;

        // Lista de ciudades a visitar (excluyendo la de inicio)
        ArrayList<Integer> ciudadesAVisitar = new ArrayList<>();
        for (int i = 1; i < numCiudades; i++) {
            ciudadesAVisitar.add(i);
        }

        // Llamar a la función recursiva para generar las permutaciones
        // Empezamos desde la ciudad 0 con un costo inicial de 0
        generarPermutaciones(0, ciudadesAVisitar, new ArrayList<>(), 0);

        System.out.println("Costo mínimo: " + costoMinimo);
        System.out.println("Mejor ruta: " + mejorRuta);
    }

    private static void generarPermutaciones(int ciudadActual, ArrayList<Integer> ciudadesPendientes, ArrayList<Integer> rutaActual, double costoActual) {
        // Agrega la ciudad actual a la ruta
        rutaActual.add(ciudadActual);

        // Caso base: si ya visitamos todas las ciudades
        if (ciudadesPendientes.isEmpty()) {
            // Suma el costo de volver al inicio (ciudad 0)
            costoActual += distancias[ciudadActual][0];

            // Si esta ruta es mejor, la guardamos
            if (costoActual < costoMinimo) {
                costoMinimo = costoActual;
                mejorRuta = new ArrayList<>(rutaActual);
                mejorRuta.add(0); // Añade el regreso al inicio
            }
            return;
        }

        // Caso recursivo: genera las permutaciones para el resto de ciudades
        for (int i = 0; i < ciudadesPendientes.size(); i++) {
            // Elige la siguiente ciudad a visitar
            int siguienteCiudad = ciudadesPendientes.get(i);

            // Crea nuevas listas para la recursión y remueve la ciudad elegida
            ArrayList<Integer> nuevasCiudadesPendientes = new ArrayList<>(ciudadesPendientes);
            nuevasCiudadesPendientes.remove(i);

            // Costo de ir de la ciudad actual a la siguiente
            double costoASumar = distancias[ciudadActual][siguienteCiudad];

            // Llamada recursiva con la nueva ruta y costo
            generarPermutaciones(siguienteCiudad, nuevasCiudadesPendientes, new ArrayList<>(rutaActual), costoActual + costoASumar);
        }
    }

}
