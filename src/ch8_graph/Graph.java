package ch8_graph;
/*
    Todo: Construct 'directed graphs' with adjacency lists with n vertices and m weighted edges.
        Condition
        (1). If the construction fails, immediately terminate the program with error message.
        (2). Input type
            Construct: C n m, then get a(출발 vertex), b(도착 vertex), c(weight) m times.
            Is edge?: I n m, if edge exists return T or F.
            Neighbors: N n, return n's adjacent vertex.
 */
/*
    Point: Use 2D List(ArrayList) to implement Adjacency List
         : To express 'End node' and 'Weight', use Pair class as a value.
         : Just think like the picture.(List element value가 의미하는 건 목적지 노드이다!)
*/

import java.util.*;
import java.util.List;

class Pair{
    public int end;
    public int weight;

    public Pair(int end, int weight){
        this.end = end;
        this.weight = weight;
    }
}

class DijkstraNode{
    public int node;
    public int distance;

    public DijkstraNode(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Graph {
    private int vertexCount;
    private List<List<Pair>> adjacentVertex;
    private int[] inDegree;

    public Graph(int vertexCount){
        this.vertexCount = vertexCount;
        adjacentVertex = new ArrayList<>();
        for(int i=0; i<this.vertexCount; i++){
            adjacentVertex.add(new ArrayList<Pair>());
        }
        inDegree = new int[vertexCount]; // In-degree 기록용.
    }

    public void addEdge(int start, int end, int weight){
        adjacentVertex.get(start).add(new Pair(end, weight));
        inDegree[end]+=1;
    }

    public boolean isEdge(int start, int end){
        List<Pair> temp = adjacentVertex.get(start);
        for(Pair i:temp){
            if(i.end == end)
                return true;
        }
        return false;
    }

    public void printNeighbors(int vertex){
        List<Pair> temp = adjacentVertex.get(vertex);
        for(Pair i:temp){
            System.out.printf("%d ", i.end);
        }
        System.out.println();
    }

    public void depthFirstSearch(int startNode){
        Stack<Integer> temp = new Stack<>(); // DFS에 Stack처리.
        boolean[] isVisited = new boolean[vertexCount]; // Visited Checking을 위한 array 관리.
        temp.add(startNode);

        System.out.print("DFS: ");
        while(!temp.empty()) {
            int u = temp.pop();
            if (!isVisited[u]) {
                isVisited[u] = true;
                System.out.print(u + " ");

                // AdjacentVertex를 넣을 때 번호순으로 넣었듯이, 출력할때도 번호순으로 빼서 결과값을 유지하도록.
                List<Pair> currentList = adjacentVertex.get(u);
                currentList = currentList.reversed();
                for (Pair i : currentList) {
                    if (!isVisited[i.end])
                        temp.add(i.end);
                }
            }
        }
        System.out.println();
    }

    public void breathFirstSearch(int startNode){
        Queue<Integer> temp = new LinkedList<>(); // BFS를 위한 Queue 사용.
        boolean[] isVisited = new boolean[vertexCount]; // Visited Checking by boolean list.
        isVisited[startNode] = true; // Step: Turn true -> Insert to queue -> pop and adjacent
        temp.add(startNode);

        System.out.print("BFS: ");
        while(!temp.isEmpty()){
            int u = temp.poll();
            System.out.print(u + " ");
            List<Pair> currentList = adjacentVertex.get(u);
            for(Pair i:currentList){
                if(!isVisited[i.end]){
                    isVisited[i.end] = true;
                    temp.add(i.end);
                }
            }
        }
        System.out.println();
    }

    public void topologicalSort(){
        List<Integer> t = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        // Step1. Insert who's in-degree is 0 O(v)
        for(int i=0; i<vertexCount; i++){
            if(inDegree[i] == 0)
                q.add(i);
        }

        // Step2. Pop from the queue, add to t, and decreases 1 adjacent to it(O(E): Explore every edges)
        while(!q.isEmpty()){
            int u = q.poll();
            t.add(u);
            List<Pair> currentList = adjacentVertex.get(u);
            for(Pair i: currentList){
                inDegree[i.end] -= 1;
                if(inDegree[i.end] == 0) // Step3. If in-degree becomes zero, Insert into the queue.
                    q.add(i.end);
            }
        }

        // Step4. Print sort
        System.out.println("Topological Sort: " + t);
    }

    public void dijkstra(int startNode, int endNode){
        int[] predecessor = new int[vertexCount]; // Path 출력용 Predecessor List

        // Step1. Initialize MinPriorityQueue using distance
        DijkstraNode[] nodes= new DijkstraNode[vertexCount];
        for(int i=0; i<vertexCount; i++){
           nodes[i]= new DijkstraNode(i, Integer.MAX_VALUE);
        }
        nodes[startNode].distance = 0;
        PriorityQueue<DijkstraNode> minQueue = new PriorityQueue<>((a, b) -> a.distance - b.distance); // Sort by distance
        for(int i=0; i<vertexCount; i++){
            minQueue.add(nodes[i]);
        }

        // Step2. Remove min, update distance(and into minQueue), and update predecessor
        while(!minQueue.isEmpty()){
            DijkstraNode u = minQueue.poll();
            List<Pair> currentList = adjacentVertex.get(u.node); // 이때 currentList는 u의 adjacents.

            for(Pair v: currentList){
                if(u.distance + v.weight < nodes[v.end].distance){
                    nodes[v.end].distance = u.distance + v.weight;
                    minQueue.add(new DijkstraNode(v.end, nodes[v.end].distance)); // 기존의 것을 Update하기보단 add하는 게 더 효율이 좋음.
                    predecessor[v.end] = u.node;
                }
            }
        }

        // Step3. Print along the predecessor.
        List<Integer> printList = new ArrayList<>();
        printList.add(endNode);
        while(true){
            printList.add(predecessor[endNode]);
            if(predecessor[endNode] == startNode)
                break;
            endNode = predecessor[endNode];
        }
        System.out.println("The shortest path: " + printList.reversed());
    }
}
