import java.io.PrintStream;

public class ArbolProductos {
    private NodoProducto raiz;

    public ArbolProductos() { raiz = null; }

    public void insertar(Producto p) {
        raiz = insertarRec(raiz, p);
    }

    private NodoProducto insertarRec(NodoProducto nodo, Producto p) {
        if (nodo == null) return new NodoProducto(p);

        int cmp = p.getNombre().compareToIgnoreCase(nodo.producto.getNombre());

        if (cmp < 0) nodo.izquierdo = insertarRec(nodo.izquierdo, p);
        else if (cmp > 0) nodo.derecho = insertarRec(nodo.derecho, p);
        else nodo.producto.setCantidad(nodo.producto.getCantidad() + p.getCantidad());

        return nodo;
    }

    public Producto buscarPorNombre(String nombre) {
        NodoProducto n = buscarRec(raiz, nombre);
        return (n == null) ? null : n.producto;
    }

    private NodoProducto buscarRec(NodoProducto nodo, String nombre) {
        if (nodo == null) return null;

        int cmp = nombre.compareToIgnoreCase(nodo.producto.getNombre());
        if (cmp == 0) return nodo;
        if (cmp < 0) return buscarRec(nodo.izquierdo, nombre);

        return buscarRec(nodo.derecho, nombre);
    }

    public void listarInventario(PrintStream out) {
        out.println("=== Inventario ===");
        listarRec(raiz, out);
    }

    private void listarRec(NodoProducto nodo, PrintStream out) {
        if (nodo == null) return;
        listarRec(nodo.izquierdo, out);
        out.println(nodo.producto.toString());
        listarRec(nodo.derecho, out);
    }

    public boolean reducirCantidad(String nombre, int cant) {
        NodoProducto n = buscarRec(raiz, nombre);
        if (n == null) return false;

        if (n.producto.getCantidad() < cant) return false;

        n.producto.setCantidad(n.producto.getCantidad() - cant);
        return true;
    }
}
