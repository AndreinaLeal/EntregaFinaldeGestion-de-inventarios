import java.io.PrintStream;
import java.util.LinkedList;

public class ColaClientes {

    private LinkedList<Cliente> cola = new LinkedList<>();

    public void encolar(Cliente c) {
        cola.add(c);
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public Cliente atenderSiguiente() {
        if (cola.isEmpty()) return null;

        Cliente mejor = cola.get(0);
        int idx = 0;

        for (int i = 1; i < cola.size(); i++) {
            Cliente actual = cola.get(i);
            if (actual.getPrioridad() > mejor.getPrioridad()) {
                mejor = actual;
                idx = i;
            }
        }
        return cola.remove(idx);
    }

    public void listarCola(PrintStream out) {
        out.println("=== Cola de clientes ===");
        if (cola.isEmpty()) {
            out.println("(vacía)");
            return;
        }
        for (int i = 0; i < cola.size(); i++) {
            out.println((i+1) + ") " + cola.get(i).toString());
        }
    }
}
