package org.maps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Category - Representa una categoría de productos
 * 
 * Encapsula el nombre de la categoría y sus productos asociados.
 * Implementa Comparable para permitir ordenamiento.
 * 
 * @author Alejandro Sagastume
 * @version 1.0
 */
public class Category implements Comparable<Category> {
    
    private String name;
    private Set<String> products;
    
    /**
     * Constructor
     * @param name El nombre de la categoría
     */
    public Category(String name) {
        this.name = name;
        this.products = new HashSet<>();
    }
    
    /**
     * Obtiene el nombre de la categoría
     * @return El nombre
     */
    public String getName() {
        return name;
    }
    
    /**
     * Obtiene la cantidad de productos en la categoría
     * @return Cantidad de productos
     */
    public int getProductCount() {
        return products.size();
    }
    
    /**
     * Agrega un producto a la categoría
     * @param product El nombre del producto
     */
    public void addProduct(String product) {
        products.add(product.trim());
    }
    
    /**
     * Verifica si un producto existe en la categoría
     * @param product El nombre del producto
     * @return true si existe, false en caso contrario
     */
    public boolean containsProduct(String product) {
        return products.contains(product.trim());
    }
    
    /**
     * Obtiene los productos ordenados alfabéticamente
     * @return Lista de productos ordenada
     */
    public List<String> getProductsSorted() {
        return products.stream()
                .sorted()
                .toList();
    }
    
    /**
     * Compara por nombre de categoría
     */
    @Override
    public int compareTo(Category other) {
        return this.name.compareTo(other.name);
    }
    
    /**
     * Verifica igualdad por nombre
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category other = (Category) obj;
        return this.name.equals(other.name);
    }
    
    /**
     * Retorna el hash code basado en el nombre
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}