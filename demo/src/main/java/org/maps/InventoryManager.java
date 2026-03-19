package org.maps;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {

    private Map<String, Integer> miInventario;
    private Map<String, Category> categorias;
    private Factory.MapType tipoMap;

    public InventoryManager(Factory.MapType mapType) {
        this.tipoMap = mapType;
        this.miInventario = Factory.createMap(mapType);
        this.categorias = new HashMap<>();
        cargarInventario();
    }

    private void cargarInventario() {
        String[] rutasPosibles = {
                "inventario.txt",
                "demo/inventario.txt",
                "src/main/java/org/maps/inventario.txt",
                "demo/src/main/java/org/maps/inventario.txt"
        };

        for (String ruta : rutasPosibles) {
            File file = new File(ruta);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        String[] partes = linea.split("\\|");
                        if (partes.length == 2) {
                            String nombreCategoria = partes[0].trim();
                            String nombreProducto = partes[1].trim();

                            if (!categorias.containsKey(nombreCategoria)) {
                                categorias.put(nombreCategoria, new Category(nombreCategoria));
                            }
                            categorias.get(nombreCategoria).addProduct(nombreProducto);
                        }
                    }
                    return;
                } catch (IOException e) {
                    // Continúa buscando en otras rutas
                }
            }
        }
    }

    public boolean agregarProducto(String nombreCategoria, String nombreProducto) {
        if (!categorias.containsKey(nombreCategoria)) {
            return false;
        }

        Category categoria = categorias.get(nombreCategoria);
        if (!categoria.containsProduct(nombreProducto)) {
            return false;
        }

        String clave = nombreProducto + " (" + nombreCategoria + ")";
        miInventario.put(clave, miInventario.getOrDefault(clave, 0) + 1);
        return true;
    }

    public String obtenerCategoriaProducto(String nombreProducto) {
        for (Category categoria : categorias.values()) {
            if (categoria.containsProduct(nombreProducto)) {
                return categoria.getName();
            }
        }
        return null;
    }

    public Map<String, Integer> obtenerInventarioUsuario() {
        return miInventario;
    }

    public List<Map.Entry<String, Integer>> obtenerInventarioOrdenado() {
        return miInventario.entrySet().stream()
                .sorted((e1, e2) -> {
                    String cat1 = extraerCategoria(e1.getKey());
                    String cat2 = extraerCategoria(e2.getKey());
                    int comparacionCat = cat1.compareTo(cat2);
                    return comparacionCat != 0 ? comparacionCat : e1.getKey().compareTo(e2.getKey());
                })
                .collect(Collectors.toList());
    }

    public Map<String, Category> obtenerInventarioCompleto() {
        return categorias;
    }

    public List<Category> obtenerInventarioCompletoOrdenado() {
        return categorias.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private String extraerCategoria(String claveProducto) {
        int ultimoParentesis = claveProducto.lastIndexOf("(");
        if (ultimoParentesis != -1) {
            return claveProducto.substring(ultimoParentesis + 1, claveProducto.length() - 1);
        }
        return "";
    }

    public Map<String, Category> obtenerCategorias() {
        return categorias;
    }

    public Category obtenerCategoria(String nombre) {
        return categorias.get(nombre);
    }

    public Set<String> obtenerNombresCategorias() {
        return categorias.keySet();
    }

    public int obtenerCantidadCategorias() {
        return categorias.size();
    }

    public int obtenerTotalProductos() {
        int total = 0;
        for (Category cat : categorias.values()) {
            total += cat.getProductCount();
        }
        return total;
    }
}