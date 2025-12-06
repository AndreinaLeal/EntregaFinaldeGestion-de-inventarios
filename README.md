# EntregaFinaldeGestion-de-inventarios
#  Sistema de Gestión de Inventarios — Avance Final  
Proyecto desarrollado para el curso **Estructuras de Datos** en CENFOTEC.  
Incluye el manejo de inventarios con árbol binario, cola de atención por prioridad, carritos de compra, facturación, y un grafo interno para rutas dentro de la tienda.

---

##  Integrantes
- **Andreina Leal Vega**
- **Fabiola Román Barrantes**

##  Profesor
**Romario Salas Cerdas**  


---

#  Descripción General del Proyecto

Este sistema simula la operación de una **tienda inteligente**, capaz de:

### 1. **Gestionar el inventario**  
Mediante un **Árbol Binario de Búsqueda (ABB)** que almacena productos ordenados por nombre.  
Permite:
- Insertar productos.
- Incrementar stock si el producto ya existe.
- Actualizar cantidades.
- Listar inventario en orden alfabético.
- Buscar productos por nombre.

---

###  2. **Atender clientes por prioridad**  
Mediante una **cola personalizada**, donde la prioridad se define como:
- 1 = Básico  
- 2 = Afiliado  
- 3 = Premium  

La cola no es FIFO estándar:  
 **Siempre se atiende primero al cliente con mayor prioridad**.  
En caso de empate, se respeta el orden de llegada.

---

###  3. **Carrito de compras por cliente**  
Cada cliente puede:
- Agregar productos desde el inventario.
- Recibir advertencias si no hay stock suficiente.
- Ver la factura calculada automáticamente al ser atendido.

---

###  4. **Facturación**
Al atender un cliente:
- Se valida si el inventario tiene suficiente stock para TODOS los productos.
- Si se cumple, se descuenta y se genera factura en consola.
- Si no se cumple, la compra se **deniega** y no se descuenta inventario.

---

###  5. **Grafo de la tienda** (Requisito adicional)
El sistema incluye un **Grafo No Dirigido** con:
- Vértices que representan áreas de la tienda (ej. Tienda, A, B, C…)
- Aristas con pesos para distancias.
- Creación de vértices **antes del menú** (como pide la consigna).
- Distancias calculadas con **Dijkstra**.
- Reconstrucción del camino más corto.

Permite representar mapas, secciones o rutas internas.

---

###  6. **Menú interactivo**
Usando:
- `BufferedReader` para entrada
- `PrintStream` para salida  
(no se usa Scanner, según las reglas)

Opciones del menú:
1. Insertar producto  
2. Listar inventario  
3. Insertar cliente y llenar carrito  
4. Atender siguiente cliente  
5. Listar cola  
6. Cargar datos de ejemplo  
0. Salir  

---

#  Tecnologías y Estructuras Utilizadas

### Lenguaje
- **Java 17**

### IDE
- **IntelliJ IDEA**

### Estructuras implementadas manualmente:
- Árbol Binario de Búsqueda (ABB)
- Lista dinámica de productos
- Cola de clientes con prioridad
- Grafo con lista de adyacencia
- Algoritmo de Dijkstra desde cero

---

#  Estructura del Proyecto
src/
├── Arista.java
├── Vertice.java
├── Grafo.java
├── Producto.java
├── NodoProducto.java
├── ArbolProductos.java
├── Cliente.java
├── ListaProductos.java
├── ColaClientes.java
├── Tienda.java
└── Main.java



