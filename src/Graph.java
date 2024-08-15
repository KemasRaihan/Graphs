import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {

    int value;
    ArrayList<Node> edges = new ArrayList<Node>();

    Node(int v) {
        this.value = v;
    }
}

public class Graph {

    static ArrayList<Node> dfs = new ArrayList<Node>();

    // adds a Node object to the array list dfs
    public static void addNode(Node n) {
        dfs.add(n);
    }

    // adds a node to the array list: 'edges' in the Node class
    // which represents a list of nodes that the current node is connected to
    void setEdge(Node n1, Node n2) {

        // if the n1 does not have an edge with n2 then add n2 to the n1 'edges' list
        if (n1.edges.contains(n2)==false) {
            n1.edges.add(n2);
        }

        // if the n2 does not have an edge with n1 then add n1 to the n2 'edges' list
        if (n2.edges.contains(n1)==false) {
            n2.edges.add(n1);
        }
    }

    // return node from dfs that contains the value n or return null if it does not contain that value
    public static Node getNode(int n) {
        Node res = null; // initialise res
        for(int i=0; i<dfs.size(); i++) { // traverse through all the nodes in dfs
            if(dfs.get(i).value == n)  // if the corresponding node contains that value then assign to res
                res = dfs.get(i);
        }
        return res; // returns the value if found or null if not found
    }

    // returns an array list of nodes that Node n is connected to
    public static ArrayList<Node> getEdges (Node n) {
        int i = dfs.indexOf(n); // find the index of the node in dfs
        ArrayList<Node> nn = dfs.get(i).edges; // use index to assign array list of nodes that n has edges with
        return nn;
    }

    public static void printEdges (Node n) {
        for (Node x : n.edges ) {
            System.out.print(x.value+" ");
        }
    }

    // traverses through all the nodes in dfs and prints their values
    public static void printGraphNodes() {

        for (int i=0; i<dfs.size(); i++) {
            System.out.print(dfs.get(i).value+" ");
        }
    }

    //
    public static boolean containNode (int n) {

        boolean res = false;
        for (Node x : dfs) { // traverses through every node in dfs
            if(x.value == n) // checks each node for the value n
                res = true; // assign true to res if found
        }
        return res;
    }

//    value  = the node’s value
//    If containNode (value)
    //    Let firstNode = node of value
    //    Let queue = an empty queue
    //    add firstNode to queue
    //    Let visited = empty list
    //  while (queue is not empty)
    //      Let n = remove a node from queue
    //      if visited not contains(n)
            //    print n value
            //    add n into visited
            //    Let edges = get all edges in n
            //    for i=0 to i<edges.size()
            //          add every edge into queue
    //else
    //    Print there is no node value

    public static void traverseBFS(int nodeValue) {
        if(containNode(nodeValue)){
            Node firstNode = getNode(nodeValue);
            Queue<Node> queue = new LinkedList<>();
            queue.add(firstNode);
            ArrayList<Node> visited = new ArrayList<>();
            while(!queue.isEmpty()){
                Node n = queue.remove();
                if(!visited.contains(n)){
                    System.out.print(n.value+",");
                    visited.add(n);
                    ArrayList<Node> edges = n.edges;
                    for(Node edge : edges){
                        queue.add(edge);
                    }
                }
            }
        }else{
            System.out.println("There is no node value.");
        }


        //Write your code here
    }


    public static ArrayList<Node> traverseDFSAlgo2 (int nodeValue, ArrayList<Node> visited) {
        if(containNode(nodeValue)){
            Node aNode = getNode(nodeValue);
            if(visited.isEmpty()){
                visited.add(aNode);
            }
            for(Node edge : aNode.edges){
                if(!visited.contains(edge)){
                    visited.add(edge);
                    traverseDFSAlgo2(edge.value, visited);
                }
            }

        }


        //Write your code here
        return visited;
    }

    public static void traverseDFSAlgo1 (int nodeValue) {

        //Write your code here
        if(containNode(nodeValue)){
            Node firstNode = getNode(nodeValue);

            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(firstNode);

            ArrayList<Node> visited = new ArrayList<>();

            while(!nodes.isEmpty()){
                Node n = nodes.remove(nodes.size()-1); // get ndoe from last element
                if(!visited.contains(n)){
                    System.out.print(n.value + ",");
                    visited.add(n);
                    ArrayList<Node> edges = n.edges;
                    for(int i = 0; i < edges.size(); i++){
                        nodes.add(edges.get(i));
                    }

                }
            }
        }else{
            System.out.println("There is no Node " + nodeValue);
        }
    }

    public void createGraph() {

        Graph graph = new Graph();

        Node a = new Node(10);
        Node b = new Node(20);
        Node c = new Node(30);
        Node d = new Node(40);
        Node e = new Node(50);
        Node f = new Node(60);

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);

        //edges for a
        graph.setEdge(a,b);
        graph.setEdge(a,e);

        //edges for b
        graph.setEdge(b,a);
        graph.setEdge(b,c);
        graph.setEdge(b,f);

        //edges for c
        graph.setEdge(c,b);
        graph.setEdge(c,d);
        graph.setEdge(c,e);

        //edges for d
        graph.setEdge(d,c);

        //edges for e
        graph.setEdge(e,a);
        graph.setEdge(e,c);

        //edges for f
        graph.setEdge(f,b);

    }

    //
    public static void printNodes (ArrayList<Node> n) {
        for(Node x : n) {
            System.out.print(x.value+" ");
        }
    }


    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.createGraph();

        System.out.println("Number of nodes in the graph : "+dfs.size());
        System.out.println("Nodes in the graph are : ");
        printGraphNodes();

        System.out.println("\nThe graph contains 60 ? : "+graph.containNode(60));

        System.out.println("\nEdges in 10 : ");
        printEdges(dfs.get(dfs.indexOf(getNode(10))));

        System.out.println("\nEdges in 20 : ");
        printEdges(dfs.get(1));

        System.out.println("\nEdges in 30 : ");
        printEdges(dfs.get(dfs.indexOf(getNode(30))));



        System.out.println();

        System.out.println("\nDFS Traverse starts from node 60 Algo 1 : ");
        traverseDFSAlgo1(60);

        System.out.println("\nBFS Traverse starts from node 60 : ");
        traverseBFS(60);

        System.out.println("\nDFS Traverse starts from node 60 Algo 2 : ");
        ArrayList<Node> visited = new ArrayList<Node>();
        visited = traverseDFSAlgo2(60,visited);

        printNodes(visited);


    }

}