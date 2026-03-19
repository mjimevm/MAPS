package org.maps;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        System.out.println("Selecciona el tipo de MAP a utilizar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        Scanner teclado = new Scanner(System.in);
        int opcion = teclado.nextInt();
        Map<String, Integer> map = factory.createMap(Factory.MapType.fromValue(opcion));
        TXTReader reader = new TXTReader();
        String[] lineas = reader.leerArchivo(new java.io.File("src/main/resources/script.txt"));
        // Si se leyo el archivo, mostrar menu 
        if (lineas.length > 0) {
            System.out.println("========MENU========");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar la categoria del producto");
            System.out.println("3. Mostrar datos del producto");
            System.out.println("4. Mostrar categoría, datos y cantidad de productos por categoría");
            System.out.println("5. Mostrar el producto y la categoría de todo el inventario");
            System.out.println("6. Mostrar el producto y la categoría existentes por categoría");
            System.out.println("7. Salir");
            System.out.println("====================");
            System.out.print("Selecciona una opción: ");
            int opcionMenu = teclado.nextInt();
            switch (opcionMenu) {
                case 1:
                    System.out.println("Opción 1 seleccionada: Agregar producto");
                    break;
                case 2:
                    System.out.println("Opción 2 seleccionada: Mostrar la categoría del producto");
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada: Mostrar datos del producto");
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada: Mostrar categoría, datos y cantidad de productos por categoría");
                    break;
                case 5:
                    System.out.println("Opción 5 seleccionada: Mostrar el producto y la categoría de todo el inventario");
                    break;
                case 6:
                    System.out.println("Opción 6 seleccionada: Mostrar el producto y la categoría existentes por categoría");
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
            }
        } else {
            System.out.println("No se pudo leer el archivo o el archivo está vacío.");
        }
    }
}
