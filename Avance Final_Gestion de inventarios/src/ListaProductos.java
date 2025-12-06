import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private List<Producto> items;

    public ListaProductos() {
        items = new ArrayList<>();
    }

    public void agregar(Producto p) {
        for (Producto q : items) {
            if (q.getNombre().equalsIgnoreCase(p.getNombre())) {
                q.setCantidad(q.getCantidad() + p.getCantidad());
                return;
            }
        }
        items.add(p);
    }

    public List<Producto> getItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : items) {
            total += p.getPrecio() * p.getCantidad();
        }
        return total;
    }

    public void imprimirFactura(PrintStream out) {
        out.println("\n----- Factura -----");
        for (Producto p : items) {
            out.println(p.getNombre() + " x" + p.getCantidad() + " = ₡" + (p.getCantidad() * p.getPrecio()));
        }
        out.println("TOTAL = ₡" + calcularTotal());
    }
}

