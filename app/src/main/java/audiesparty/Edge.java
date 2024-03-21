package audiesparty;

public class Edge implements Comparable<Edge> {
    String u, v;
    int weight;

    public Edge(String u, String v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return compareEdge.weight - this.weight;
    }
}