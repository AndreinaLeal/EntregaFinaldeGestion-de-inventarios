public class Cliente {
    private String nombre;
    private int prioridad;
    private ListaProductos carrito;
    private long ordenLlegada;
    private String ubicacion;

    public Cliente(String nombre, int prioridad, long ordenLlegada, String ubicacion) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.ordenLlegada = ordenLlegada;
        this.ubicacion = ubicacion;
        this.carrito = new ListaProductos();
    }

    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public long getOrdenLlegada() { return ordenLlegada; }
    public String getUbicacion() { return ubicacion; }
    public ListaProductos getCarrito() { return carrito; }

    @Override
    public String toString() {
        return nombre + " (prio " + prioridad + ", ubicación: " + ubicacion + ")";
    }
}
