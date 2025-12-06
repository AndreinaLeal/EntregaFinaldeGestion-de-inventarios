import java.util.*;

public class Grafo {

    private final Map<String, List<Arista>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarVertice(String vertice) {
        listaAdyacencia.putIfAbsent(vertice, new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, int peso) {
        agregarVertice(origen);
        agregarVertice(destino);
        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    public boolean estaConectado(String inicio, String objetivo) {
        if (!listaAdyacencia.containsKey(inicio) || !listaAdyacencia.containsKey(objetivo))
            return false;

        Queue<String> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            if (actual.equals(objetivo)) return true;

            for (Arista a : listaAdyacencia.get(actual)) {
                if (!visitados.contains(a.getDestino())) {
                    visitados.add(a.getDestino());
                    cola.add(a.getDestino());
                }
            }
        }
        return false;
    }

    public void dijkstra(String inicio, Map<String, Integer> dist, Map<String, String> pred) {
        PriorityQueue<Vertice> pq = new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        for (String v : listaAdyacencia.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            pred.put(v, null);
        }

        dist.put(inicio, 0);
        pq.add(new Vertice(inicio, 0));

        while (!pq.isEmpty()) {
            Vertice v = pq.poll();
            String actual = v.getNombre();

            for (Arista a : listaAdyacencia.get(actual)) {
                int nuevaDist = dist.get(actual) + a.getPeso();

                if (nuevaDist < dist.get(a.getDestino())) {
                    dist.put(a.getDestino(), nuevaDist);
                    pred.put(a.getDestino(), actual);
                    pq.add(new Vertice(a.getDestino(), nuevaDist));
                }
            }
        }
    }

    public List<String> reconstruir(String inicio, String destino, Map<String, String> pred) {
        List<String> camino = new ArrayList<>();

        for (String v = destino; v != null; v = pred.get(v)) {
            camino.add(v);
        }

        Collections.reverse(camino);

        if (!camino.get(0).equals(inicio)) return new ArrayList<>();
        return camino;
    }

    public void mostrarGrafo() {
        System.out.println("\n=== Grafo ===");
        for (String v : listaAdyacencia.keySet()) {
            System.out.print(v + ": ");
            for (Arista a : listaAdyacencia.get(v)) {
                System.out.print("(" + a.getDestino() + ", " + a.getPeso() + ") ");
            }
            System.out.println();
        }
    }
}

