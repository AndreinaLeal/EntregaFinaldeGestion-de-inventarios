import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

public class Main {

    private static long contadorLlegada = 0;

    private static Tienda tienda = new Tienda();
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static PrintStream out = System.out;

    public static void main(String[] args) throws Exception {

        out.println("=== Sistema de Gestión de Inventarios — Avance Final ===");

        // VÉRTICE PRINCIPAL
        tienda.getGrafo().agregarVertice("Tienda");

        // VÉRTICES BASE
        tienda.getGrafo().agregarVertice("Centro");
        tienda.getGrafo().agregarVertice("Residencial");
        tienda.getGrafo().agregarVertice("Industrial");
        tienda.getGrafo().agregarVertice("Parque");

        // ARISTAS BASE
        tienda.getGrafo().agregarArista("Tienda", "Centro", 5);
        tienda.getGrafo().agregarArista("Centro", "Residencial", 3);
        tienda.getGrafo().agregarArista("Residencial", "Industrial", 4);
        tienda.getGrafo().agregarArista("Tienda", "Parque", 6);

        menu();
    }

    public static void menu() throws Exception {
        boolean salir = false;

        while (!salir) {
            out.println("\n=== Menú Principal ===");
            out.println("1) Insertar producto");
            out.println("2) Listar inventario");
            out.println("3) Insertar cliente");
            out.println("4) Atender cliente");
            out.println("5) Listar clientes");
            out.println("6) Agregar vértice al grafo");
            out.println("7) Agregar arista al grafo");
            out.println("8) Ver grafo");
            out.println("0) Salir");
            out.print("Seleccione opción: ");

            String op = in.readLine();

            switch (op) {
                case "1": opcionInsertarProducto(); break;
                case "2": tienda.getInventario().listarInventario(out); break;
                case "3": opcionInsertarCliente(); break;
                case "4": opcionAtenderCliente(); break;
                case "5": tienda.getCola().listarCola(out); break;
                case "6": opcionAgregarVertice(); break;
                case "7": opcionAgregarArista(); break;
                case "8": tienda.getGrafo().mostrarGrafo(); break;
                case "0": salir = true; break;
                default: out.println("Opción inválida.");
            }
        }
    }

    private static void opcionInsertarProducto() throws Exception {
        out.print("ID: "); String id = in.readLine();
        out.print("Nombre: "); String nombre = in.readLine();
        out.print("Precio: "); double precio = Double.parseDouble(in.readLine());
        out.print("Cantidad: "); int cant = Integer.parseInt(in.readLine());

        tienda.agregarProductoAlInventario(new Producto(id, nombre, precio, cant));
        out.println("Producto agregado.");
    }

    private static void opcionInsertarCliente() throws Exception {

        out.print("Nombre: ");
        String nombre = in.readLine();

        out.print("Prioridad (1-3): ");
        int prio = Integer.parseInt(in.readLine());

        out.print("Ubicación del cliente: ");
        String ubic = in.readLine();

        tienda.getGrafo().agregarVertice(ubic);

        contadorLlegada++;
        Cliente c = new Cliente(nombre, prio, contadorLlegada, ubic);

        boolean agregar = true;
        while (agregar) {
            out.println("1) Agregar producto al carrito");
            out.println("2) Ver inventario");
            out.println("0) Finalizar carrito");
            String op = in.readLine();

            if (op.equals("1")) {
                out.print("Nombre producto: ");
                String n = in.readLine();
                Producto p = tienda.getInventario().buscarPorNombre(n);

                if (p == null) out.println("No existe.");
                else {
                    out.print("Cantidad: ");
                    int q = Integer.parseInt(in.readLine());

                    if (q <= p.getCantidad())
                        c.getCarrito().agregar(p.clonarConCantidad(q));
                    else
                        out.println("No hay stock suficiente.");
                }

            } else if (op.equals("2")) {
                tienda.getInventario().listarInventario(out);
            } else if (op.equals("0")) {
                agregar = false;
            }
        }

        tienda.getCola().encolar(c);
        out.println("Cliente agregado.");
    }

    private static void opcionAtenderCliente() throws Exception {
        if (tienda.getCola().estaVacia()) {
            out.println("No hay clientes.");
            return;
        }

        Cliente c = tienda.getCola().atenderSiguiente();

        if (!tienda.getGrafo().estaConectado("Tienda", c.getUbicacion())) {
            out.println("\nERROR: la ubicación del cliente NO está conectada.");
            return;
        }

        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> pred = new HashMap<>();

        tienda.getGrafo().dijkstra("Tienda", dist, pred);
        List<String> camino = tienda.getGrafo().reconstruir("Tienda", c.getUbicacion(), pred);

        out.println("\n=== Ruta de entrega ===");
        out.println("Camino: " + camino);
        out.println("Distancia total: " + dist.get(c.getUbicacion()));

        out.println("\n=== Atendiendo a " + c.getNombre() + " ===");

        if (!tienda.procesarCompra(c)) {
            out.println("Stock insuficiente. Venta cancelada.");
            return;
        }

        c.getCarrito().imprimirFactura(out);
        out.println("Compra procesada.");
    }

    private static void opcionAgregarVertice() throws Exception {
        out.print("Nombre del vértice: ");
        tienda.getGrafo().agregarVertice(in.readLine());
    }

    private static void opcionAgregarArista() throws Exception {
        out.print("Origen: ");
        String o = in.readLine();

        out.print("Destino: ");
        String d = in.readLine();

        out.print("Peso: ");
        int p = Integer.parseInt(in.readLine());

        tienda.getGrafo().agregarArista(o, d, p);
    }
}
