package ch8_graph;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GraphController {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get input for constructing graph
        String str = br.readLine();
        String[] s = str.split(" ");
        int vertexCount = Integer.parseInt(s[1]);
        int edgeCount = Integer.parseInt(s[2]);

        // Exception1) Too much edge
        if (edgeCount >= vertexCount * (vertexCount - 1))
            throw new Exception("Too many Edges");

        Graph test = new Graph(vertexCount);

        for (int i = 0; i < edgeCount; i++) {
            String temp = br.readLine();
            String[] values = temp.split(" ");

            // Exception2) Out of vertex
            int startVertex = Integer.parseInt(values[0]);
            int endVertex = Integer.parseInt(values[1]);

            if ((startVertex >= vertexCount || startVertex < 0) || (endVertex >= vertexCount || endVertex < 0)) {
                throw new Exception("Out of Vertex");
            }
            int weight = Integer.parseInt(values[2]);
            test.addEdge(startVertex, endVertex, weight);
        }

        // Get input for checking graph
        while (true) {
            String temp = br.readLine();
            String[] values = temp.split(" ");
            if (values[0].equalsIgnoreCase("I")) {
                int startVertex = Integer.parseInt(values[1]);
                int endVertex = Integer.parseInt(values[2]);
                //Exception
                if ((startVertex >= vertexCount || startVertex < 0) || (endVertex >= vertexCount || endVertex < 0)) {
                    throw new Exception("Out of Vertex");
                }
                System.out.printf("%d %d %b\n", startVertex, endVertex, test.isEdge(startVertex, endVertex));
            } else if (values[0].equalsIgnoreCase("N")) {
                int startVertex = Integer.parseInt(values[1]);
                //Exception
                if ((startVertex >= vertexCount || startVertex < 0)) {
                    throw new Exception("Out of Vertex");
                }
                test.printNeighbors(startVertex);
            } else if (values[0].equalsIgnoreCase("STOP")) {
                System.out.println("End the program.");
                break;
            } else if (values[0].equalsIgnoreCase("DFS")) {
                test.depthFirstSearch(0);
            } else if (values[0].equalsIgnoreCase("BFS")) {
                test.breathFirstSearch(0);
            } else if (values[0].equalsIgnoreCase("T")) {
                test.topologicalSort();
            } else if (values[0].equalsIgnoreCase("S")) {
                test.dijkstra(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
            }
        }
    }
}
