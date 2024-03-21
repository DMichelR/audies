package audiesparty;

import java.util.*;

class DisjointSet {
    Map<String, String> parent = new HashMap<>();

    public void makeSet(String item) {
        parent.put(item, item);
    }

    public String findSet(String item) {
        String parentItem = parent.get(item);
        if (parentItem.equals(item)) {
            return item;
        }
        return findSet(parentItem);
    }

    public void union(String item1, String item2) {
        String parent1 = findSet(item1);
        String parent2 = findSet(item2);
        parent.put(parent1, parent2);
    }
}
