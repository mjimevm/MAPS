package org.maps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Factory - Implementación del Patrón de Diseño Factory
 * 
 * Crea instancias de diferentes implementaciones de Map en tiempo de ejecución.
 * Permite seleccionar entre HashMap, TreeMap y LinkedHashMap.
 * 
 * @author Alejandro Sagastume
 * @version 1.0
 */
public class Factory {
    
    public enum MapType {
        HASHMAP(1, "HashMap"),
        TREEMAP(2, "TreeMap"),
        LINKEDHASHMAP(3, "LinkedHashMap");
        
        private final int value;
        private final String name;
        
        MapType(int value, String name) {
            this.value = value;
            this.name = name;
        }
        
        public int getValue() {
            return value;
        }
        
        public String getName() {
            return name;
        }
        
        public static MapType fromValue(int value) {
            for (MapType type : MapType.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Tipo de MAP inválido: " + value);
        }
    }
    
    /**
     * Crea y retorna una instancia de Map del tipo especificado
     * @param type El tipo de Map a crear
     * @return Una instancia de Map<String, Integer> del tipo especificado
     */
    public static Map<String, Integer> createMap(MapType type) {
        switch (type) {
            case HASHMAP:
                return new HashMap<>();
            case TREEMAP:
                return new TreeMap<>();
            case LINKEDHASHMAP:
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Tipo de MAP desconocido: " + type);
        }
    }
}