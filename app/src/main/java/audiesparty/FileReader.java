package audiesparty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static void read(List<Edge> edges) {
        try {
            File file = new File("app/src/main/resources/neighbors.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(" ")) {
                    String[] parts = line.split(" ");
                    String villager1 = parts[0];
                    String villager2 = parts[1];
                    int relationship = Integer.parseInt(parts[2]);
                    edges.add(new Edge(villager1, villager2, relationship));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Collections.sort(edges);
    }
}
