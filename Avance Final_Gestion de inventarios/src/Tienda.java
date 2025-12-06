public class Tienda {
    private ArbolProductos inventario;
    private ColaClientes cola;
    private Grafo grafo;

    public Tienda() {
        inventario = new ArbolProductos();
        cola = new ColaClientes();
        grafo = new Grafo();
    }

    public ArbolProductos getInventario() { return inventario; }
    public ColaClientes getCola() { return cola; }
    public Grafo getGrafo() { return grafo; }

    public void agregarProductoAlInventario(Producto p) {
        inventario.insertar(p);
    }

    public boolean procesarCompra(Cliente cliente) {
        for (Producto p : cliente.getCarrito().getItems()) {
            Producto inv = inventario.buscarPorNombre(p.getNombre());
            if (inv == null || inv.getCantidad() < p.getCantidad()) return false;
        }
        for (Producto p : cliente.getCarrito().getItems()) {
            inventario.reducirCantidad(p.getNombre(), p.getCantidad());
        }
        return true;
    }
}
