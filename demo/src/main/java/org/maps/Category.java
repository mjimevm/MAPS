package org.maps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Category implements Comparable<Category> {

    private String name;
    private Set<String> products;

    public Category(String name) {
        this.name = name;
        this.products = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getProductCount() {
        return products.size();
    }

    public void addProduct(String product) {
        products.add(product.trim());
    }

    public boolean containsProduct(String product) {
        return products.contains(product.trim());
    }

    public List<String> getProductsSorted() {
        return products.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Category other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category other = (Category) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}