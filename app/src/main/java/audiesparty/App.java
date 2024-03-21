package audiesparty;

import java.util.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>();

        FileReader.read(edges);

        DisjointSet ds = new DisjointSet();

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        List<Edge> invitedEdges = new ArrayList<>();

        for (Edge edge : edges) {
            ds.makeSet(edge.u);
            ds.makeSet(edge.v);
        }

        for (Edge edge : edges) {
            if (edge.weight > x) {
                String parentU = ds.findSet(edge.u);
                String parentV = ds.findSet(edge.v);
                if (!parentU.equals(parentV)) {
                    ds.union(parentU, parentV);
                    invitedEdges.add(edge);
                }
            }
        }

        // Print the invited villagers
        Map<String, Set<String>> groupMap = new HashMap<>();
        for (Edge edge : invitedEdges) {
            String parentU = ds.findSet(edge.u);
            if (!groupMap.containsKey(parentU)) {
                groupMap.put(parentU, new HashSet<>());
            }
            groupMap.get(parentU).add(edge.u);
            groupMap.get(parentU).add(edge.v);
        }

        Set<String> largestGroup = Collections.max(groupMap.values(), Comparator.comparingInt(Set::size));
        for (String node : largestGroup) {
            System.out.print(node + " ");
        }
        System.out.println("");

        // --------------------------------
        // Number of groups
        int k = scanner.nextInt();
        scanner.close();

        // Sort the invited edges by weight in ascending order
        Collections.sort(invitedEdges, Comparator.comparingInt(edge -> edge.weight));

        // Remove the k-1 smallest edges
        if (k - 1 > invitedEdges.size()) {
            System.out.println("It is not possible");
            return;
        }
        // Create a set to store the nodes
        Set<String> nodes = new HashSet<>();

        // Populate the set with all the nodes from the edges
        for (Edge edge : invitedEdges) {
            nodes.add(edge.u);
            nodes.add(edge.v);
        }
        for (int i = 0; i < k - 1; i++) {
            Edge edge = invitedEdges.remove(0);
        }

        // Initialize the disjoint set with all nodes
        ds = new DisjointSet();
        for (String node : nodes) {
            ds.makeSet(node);
        }

        // Add the edges to the disjoint set
        for (Edge edge : invitedEdges) {
            String parentU = ds.findSet(edge.u);
            String parentV = ds.findSet(edge.v);
            if (!parentU.equals(parentV)) {
                ds.union(parentU, parentV);
            }
        }

        // Group the villagers
        Map<String, Set<String>> groups = new HashMap<>();
        for (String node : ds.parent.keySet()) {
            String parent = ds.findSet(node);
            if (!groups.containsKey(parent)) {
                groups.put(parent, new HashSet<>());
            }
            groups.get(parent).add(node);
        }

        // Print the groups
        for (Set<String> group : groups.values()) {
            for (String node : group) {
                System.out.print(node + " ");
            }
            System.out.println("");
        }

        // Find the group with the strongest and least friendly relationship
        Edge strongestEdge = Collections.max(invitedEdges, Comparator.comparingInt(edge -> edge.weight));
        Edge leastEdge = Collections.min(invitedEdges, Comparator.comparingInt(edge -> edge.weight));
        System.out.print("Group with strongest friendly relationship: ");
        printGroups(groups, strongestEdge.u, ds);
        System.out.print("Group with least friendly relationship: ");
        printGroups(groups, leastEdge.u, ds);
    }

    private static void printGroups(Map<String, Set<String>> groups, String edge, DisjointSet ds) {
        Set<String> group = new HashSet<>();
        group = groups.get(ds.findSet(edge));
        for (String node : group) {
            System.out.print(node + " ");
        }
        System.out.println("");
    }
}