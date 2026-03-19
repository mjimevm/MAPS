package org.maps;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    // Variables globales para usar en todo el programa
    static Scanner teclado = new Scanner(System.in);
    static InventoryManager inventoryManager;
    
    public static void main(String[] args) {
        // 1. MOSTRAR BIENVENIDA
        System.out.println("Selecciona el tipo de MAP a utilizar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        System.out.print("Opción: ");
        
        // 2. LEER OPCIÓN DEL USUARIO
        int opcion = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer
        
        // 3. CREAR FACTORY Y OBTENER EL TIPO DE MAP
        Factory.MapType mapType = Factory.MapType.fromValue(opcion);
        
        // 4. CREAR GESTOR DE INVENTARIO CON EL MAP SELECCIONADO
        inventoryManager = new InventoryManager(mapType);
        
        // 5. MOSTRAR INFORMACIÓN DEL SISTEMA
        System.out.println("\n--- Sistema cargado ---");
        System.out.println("Categorías: " + inventoryManager.obtenerCantidadCategorias());
        System.out.println("Productos: " + inventoryManager.obtenerTotalProductos());
        
        // 6. CICLO PRINCIPAL - MOSTRAR MENÚ REPETIDAMENTE
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcionMenu = teclado.nextInt();
            teclado.nextLine();
            
            // 7. EJECUTAR LA OPCIÓN SELECCIONADA
            switch (opcionMenu) {
                case 1:
                    opcion1();
                    break;
                case 2:
                    opcion2();
                    break;
                case 3:
                    opcion3();
                    break;
                case 4:
                    opcion4();
                    break;
                case 5:
                    opcion5();
                    break;
                case 6:
                    opcion6();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        
        teclado.close();
    }
    
    // FUNCIÓN PARA MOSTRAR EL MENÚ
    static void mostrarMenu() {
        System.out.println("\n========MENU========");
        System.out.println("1. Agregar producto");
        System.out.println("2. Mostrar la categoria del producto");
        System.out.println("3. Mostrar datos del producto");
        System.out.println("4. Mostrar categoría, datos y cantidad de productos por categoría");
        System.out.println("5. Mostrar el producto y la categoría de todo el inventario");
        System.out.println("6. Mostrar el producto y la categoría existentes por categoría");
        System.out.println("7. Salir");
        System.out.println("====================");
        System.out.print("Selecciona una opción: ");
    }
    
    // OPERACIÓN 1: AGREGAR PRODUCTO
    static void opcion1() {
        System.out.println("\n--- Agregar producto ---");
        
        // Mostrar todas las categorías disponibles
        System.out.println("Categorías disponibles:");
        inventoryManager.obtenerNombresCategorias().stream().sorted().forEach(c -> System.out.println("  - " + c));
        
        // Pedir categoría
        System.out.print("Ingrese la categoría: ");
        String categoria = teclado.nextLine().trim();
        
        // Verificar que la categoría existe
        Category cat = inventoryManager.obtenerCategoria(categoria);
        if (cat == null) {
            System.out.println("ERROR: Categoría no encontrada");
            return;
        }
        
        // Mostrar productos de esa categoría
        System.out.println("Productos en esta categoría:");
        cat.getProductsSorted().forEach(p -> System.out.println("  - " + p));
        
        // Pedir producto
        System.out.print("Ingrese el producto: ");
        String producto = teclado.nextLine().trim();
        
        // Agregar el producto
        if (inventoryManager.agregarProducto(categoria, producto)) {
            System.out.println("Producto agregado exitosamente");
        } else {
            System.out.println("Error al agregar el producto");
        }
    }
    
    // OPERACIÓN 2: MOSTRAR LA CATEGORÍA DE UN PRODUCTO
    static void opcion2() {
        System.out.println("\n--- Mostrar categoría del producto ---");
        System.out.print("Ingrese el nombre del producto: ");
        String prod = teclado.nextLine().trim();
        
        // Buscar la categoría del producto
        String cat = inventoryManager.obtenerCategoriaProducto(prod);
        if (cat != null) {
            System.out.println("Producto: " + prod + " - Categoría: " + cat);
        } else {
            System.out.println("Producto no encontrado");
        }
    }
    
    // OPERACIÓN 3: MOSTRAR MI INVENTARIO
    static void opcion3() {
        System.out.println("\n--- Mi inventario ---");
        
        // Obtener inventario del usuario
        Map<String, Integer> inv = inventoryManager.obtenerInventarioUsuario();
        
        if (inv.isEmpty()) {
            System.out.println("Su inventario está vacío");
        } else {
            // Mostrar cada producto con su cantidad
            inv.forEach((producto, cantidad) -> 
                System.out.println(producto + " - Cantidad: " + cantidad)
            );
        }
    }
    
    // OPERACIÓN 4: MOSTRAR MI INVENTARIO ORDENADO POR CATEGORÍA
    static void opcion4() {
        System.out.println("\n--- Mi inventario (ordenado por categoría) ---");
        
        // Obtener inventario ordenado
        List<Map.Entry<String, Integer>> invOrd = inventoryManager.obtenerInventarioOrdenado();
        
        if (invOrd.isEmpty()) {
            System.out.println("Su inventario está vacío");
        } else {
            // Mostrar cada producto con su cantidad
            invOrd.forEach(entrada -> 
                System.out.println(entrada.getKey() + " - Cantidad: " + entrada.getValue())
            );
        }
    }
    
    // OPERACIÓN 5: MOSTRAR INVENTARIO COMPLETO sin orden
    static void opcion5() {
        System.out.println("\n--- Inventario completo de la tienda ---");
        
        // Obtener todas las categorías
        inventoryManager.obtenerInventarioCompleto().values().forEach(categoria -> {
            // Mostrar nombre de categoría y cantidad de productos
            System.out.println("Categoría: " + categoria.getName());
            // Mostrar todos los productos de esa categoría
            categoria.getProductsSorted().forEach(producto -> 
                System.out.println("  - " + producto)
            );
        });
    }
    
    // OPERACIÓN 6: MOSTRAR PRODUCTO Y CATEGORÍA EXISTENTES, ORDENADO POR CATEGORÍA (solo los que existen)

    static void opcion6() {
        System.out.println("\n--- Inventario completo (ordenado por categoría) ---");
        inventoryManager.obtenerInventarioCompletoOrdenado().forEach(categoria -> {
            // Mostrar nombre de categoría
            System.out.println("Categoría: " + categoria.getName());
            // Mostrar solo los productos de esa categoría que existen en el inventario del usuario
            categoria.getProductsSorted().forEach(producto -> {
                String clave = producto + " (" + categoria.getName() + ")";
                int cantidad = inventoryManager.obtenerInventarioUsuario().getOrDefault(clave, 0);
                if (cantidad > 0) {
                    System.out.println("  - " + producto + " (Cantidad: " + cantidad + ")");
                }
            });
        });
    }
}