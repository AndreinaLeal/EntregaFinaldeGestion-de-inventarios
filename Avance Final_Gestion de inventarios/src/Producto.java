public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Producto clonarConCantidad(int cant) {
        return new Producto(id, nombre, precio, cant);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - ₡%.2f (stock: %d)", id, nombre, precio, cantidad);
    }
}
