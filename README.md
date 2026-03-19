# MAPS - Sistema de Gestión de Inventario Online

Sistema de gestión de inventario implementado en Java que utiliza diferentes implementaciones de Map (HashMap, TreeMap, LinkedHashMap) para optimizar búsquedas y ordenamiento de productos por categorías.

## Descripción

Este proyecto implementa:

- **ADT Map (Mapa genérico)** con tres implementaciones: `HashMap` - Búsqueda rápida O(1), `TreeMap` - Mantenimiento automático de orden O(log n), `LinkedHashMap` - Orden de inserción O(1)
- **Clase Factory** - Patrón Factory para crear instancias de Map
- **Clase Category** - Gestión de categorías con productos
- **Clase InventoryManager** - Gestor principal del inventario
- **Menú interactivo** para agregar productos y consultar inventario
- **Carga de datos** desde archivo `inventario.txt`

## Operadores Soportados

1. Agregar producto a mi inventario
2. Mostrar la categoría de un producto
3. Mostrar mi inventario (sin orden)
4. Mostrar mi inventario (ordenado por categoría)
5. Mostrar inventario completo de la tienda
6. Mostrar inventario completo (ordenado por categoría)
7. Salir del programa

## Estructura del Proyecto

```
MAPS/
├── README.md
├── .gitignore
├── inventario.txt
└── demo/
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── org/maps/
    │   │   │       ├── Factory.java
    │   │   │       ├── Category.java
    │   │   │       ├── InventoryManager.java
    │   │   │       └── Main.java
    │   │   └── resources/
    │   └── test/
    └── target/
        └── classes/
```

## Requisitos

- Java 27 o superior
- Maven 3.6+ (recomendado)

## Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/AlejandroSagastume/MAPS
cd MAPS
```

### 2. Verificar instalación de Java
```bash
java -version
```
Debe mostrar Java 27 o superior.

### 3. Verificar Maven
```bash
mvn -version
```

## Compilación y Ejecución

### Opción 1: Con Maven (Recomendado)

Ve a la carpeta demo:
```bash
cd demo
```

Compila el proyecto:
```bash
mvn clean compile
```

Ejecuta el programa:
```bash
mvn exec:java -Dexec.mainClass="org.maps.Main"
```

### Opción 2: Sin Maven

Ve a la carpeta de código fuente:
```bash
cd demo/src/main/java/org/maps
javac *.java
cd ../../../../../
java -cp demo/src/main/java org.maps.Main
```

## Cambiar Implementación de Map

El programa permite cambiar entre tres implementaciones de Map.

### Método 1: En Main.java (línea ~18)

Selecciona el tipo de MAP a utilizar:
```
1. HashMap
2. TreeMap
3. LinkedHashMap
```

### Método 2: En Factory.java (línea 15)

```java
private static MapType defaultType = MapType.HASHMAP;  // Cambiar aquí
```

Después de cambiar, recompila:
```bash
cd demo
mvn clean compile
mvn exec:java -Dexec.mainClass="org.maps.Main"
```

## Archivo de Datos

El programa lee productos desde `demo/inventario.txt`

### Formato:
```
Categoría|Producto
Categoría|Producto
...
```

### Ejemplo:
```
Bebidas|Cerveza tibetana Barley
Bebidas|Te frios
Bebidas|Coca cola 1 litro
Condimentos|Sirope de regaliz
Frutas|Peras secas
Frutas|Pasas
Frutas|Manzana roja
Carnes|Res
Carnes|Pollo
Carnes|Cerdo
Lacteos|Queso de cabra
Lacteos|Queso Manchego
Lacteos|Leche descremada
Mueble de terraza|Mesas de jardin
Mueble de terraza|Sillas de jardin
Sillones de masaje|Cojines y colchonetas de masaje
Sillones de masaje|Sillones relax y sofas de masajes
```

## Uso del Programa

### 1. Seleccionar tipo de Map
```
Selecciona el tipo de MAP a utilizar:
1. HashMap
2. TreeMap
3. LinkedHashMap
Opción: 1
```

### 2. Usar el menú
```
========MENU========
1. Agregar producto
2. Mostrar la categoria del producto
3. Mostrar datos del producto
4. Mostrar categoría, datos y cantidad de productos por categoría
5. Mostrar el producto y la categoría de todo el inventario
6. Mostrar el producto y la categoría existentes por categoría
7. Salir
====================
Selecciona una opción:
```

## Complejidad de Tiempo

| Operación | HashMap | TreeMap | LinkedHashMap |
|-----------|---------|---------|---------------|
| Búsqueda | O(1) | O(log n) | O(1) |
| Inserción | O(1) | O(log n) | O(1) |
| Eliminación | O(1) | O(log n) | O(1) |
| Iteración ordenada | O(n) | O(n) | O(n) |

## Patrones de Diseño Implementados

### Factory Pattern

La clase `Factory.java` implementa el patrón Factory para crear instancias de Map según el tipo seleccionado:

```java
Factory.MapType mapType = Factory.MapType.fromValue(opcion);
InventoryManager inventoryManager = new InventoryManager(mapType);
```

### MVC (Modelo-Vista-Controlador)

- **Modelo**: `InventoryManager.java`, `Category.java`
- **Vista**: `Main.java` (interfaz de usuario)
- **Controlador**: `Main.java` (lógica de menú)

## Dependencias

El proyecto utiliza las siguientes librerías (gestionadas automáticamente por Maven):

- JUnit 4.11 - Framework de pruebas (opcional)

## Solución de Problemas

### Error: "cannot find symbol method toList()"

**Solución**: Actualiza el `pom.xml` con Java 27:

```xml
<maven.compiler.source>27</maven.compiler.source>
<maven.compiler.target>27</maven.compiler.target>
```

Luego recompila:
```bash
cd demo
mvn clean compile
```

### Error: "No se encontró inventario.txt"

**Solución**: Asegúrate de que el archivo `inventario.txt` esté en la carpeta `demo/`:

```
demo/
├── pom.xml
├── inventario.txt  ← AQUÍ DEBE ESTAR
├── src/
└── target/
```

## Tecnologías Utilizadas

- **Java 27** - Lenguaje de programación
- **Maven** - Gestión de dependencias y construcción
- **Git/GitHub** - Control de versiones
- **Collections Framework** - HashMap, TreeMap, LinkedHashMap

## Implementaciones de Map

### HashMap
- Basada en tabla hash
- Mejor rendimiento para búsquedas O(1)
- No mantiene orden
- Ideal para búsquedas rápidas

### TreeMap
- Basada en árbol rojo-negro
- Mantiene elementos ordenados automáticamente
- Búsquedas O(log n)
- Ideal para rango de consultas

### LinkedHashMap
- Combina ArrayList y HashMap
- Mantiene orden de inserción
- Búsquedas O(1)
- Ideal para preservar orden de entrada

## Autores

- **Alejandro Sagastume** - sag25257@uvg.edu.gt
- **Jimena Vásquez** - vas25092@uvg.edu.gt

## Curso

- **CC2003** - Sección 20 - Algoritmos y Estructura de Datos
- **Universidad del Valle de Guatemala**
- **Hoja de Trabajo No. 6**

## Licencia

Este proyecto es parte del curso de Algoritmos y Estructura de Datos de la Universidad del Valle de Guatemala.
