package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphParser {
    static Graph<String> parseGraph(Scanner scanner) {
        String digraphType = scanner.next();
        boolean isDigraph = digraphType.equals("digraph");
        String graphName = scanner.next();

        // The opening paren
        scanner.nextLine();

        Graph<String> graph = new Graph<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            if (line.trim().equals("}")) {
                break;
            }

            String[] pair = line.split(" -> ");
            String a = pair[0].replace("\"", "").trim();
            String b = pair[1].replace("\"", "").trim();

            graph.addEdge(a, b);
        }
        return graph;
    }

    static Graph<String> parseGraph(String data) {
        return parseGraph(new Scanner(data));
    }

    static Graph<String> parseGraphFromFile(String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            return parseGraph(myReader);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
