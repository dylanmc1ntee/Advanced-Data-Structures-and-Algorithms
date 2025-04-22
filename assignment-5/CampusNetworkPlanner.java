// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CampusNetworkPlanner {

    ArrayList<Edge> edges = new ArrayList<>(); // stores all connections
    HashMap<String, Integer> nameToIndex = new HashMap<>(); // maps campus names to index
    ArrayList<String> indexToName = new ArrayList<>(); // maps index back to name
    int[] disjoint; // array

    public CampusNetworkPlanner(int numConnections, String fileName) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new File(fileName)); // new Scanner

        while (scanner.hasNext()) {

            String first = scanner.next(); // first campus
            String second = scanner.next(); // second campus

            int cost = scanner.nextInt(); // cost between them

            if (!(nameToIndex.containsKey(first))) { // if first campus not added

                nameToIndex.put(first, indexToName.size()); // add to map
                indexToName.add(first); // add to list
            }

            if (!(nameToIndex.containsKey(second))) { // if second campus not added

                nameToIndex.put(second, indexToName.size()); // add to map
                indexToName.add(second); // add to list
            }

            edges.add(new Edge(first, second, cost)); // add edge to list
        }

        scanner.close(); // close file

        disjoint = new int[indexToName.size()]; // set size

        for (int i = 0; i < disjoint.length; i++) {

            disjoint[i] = i; // every node starts as its own parent
        }
    }

    public String buildNetwork() {

        StringBuilder result = new StringBuilder(); // to store output

        Collections.sort(edges); // sort all edges by cost and name
        ArrayList<String> chosen = new ArrayList<>(); // list for chosen connections
        int totalCost = 0; // sum of costs

        for (Edge e : edges) {

            int first = nameToIndex.get(e.firstCampus); // get index of first campus
            int second = nameToIndex.get(e.secondCampus); // get index of second campus

            if (find(first) != find(second)) { // only connect if not already connected

                union(first, second); // connect them
                chosen.add(e.toString()); // store the connection
                totalCost += e.cost; // add cost
            }
        }

        Collections.sort(chosen); // sort connections

        for (String line : chosen) {

            result.append(line).append("\n"); // add each line
        }

        result.append("\nTotal Cost: $").append(totalCost); // final cost line
        return result.toString(); // return all
    }

    private int find(int x) { // find root of the set

        if (disjoint[x] != x) {

            disjoint[x] = find(disjoint[x]); // finds root recursively
        }

        return disjoint[x]; // return root
    }

    private void union(int x, int y) { // unites 2 sets

        int rootX = find(x);
        int rootY = find(y);

        disjoint[rootY] = rootX; // make one root the parent
    }

    class Edge implements Comparable<Edge> {

        String firstCampus;
        String secondCampus;
        int cost; // cost to connect

        Edge(String first, String second, int cost) {

            if (first.compareTo(second) < 0) { // store in alphabetical order

                this.firstCampus = first;
                this.secondCampus = second;

            } else {

                this.firstCampus = second;
                this.secondCampus = first;
            }

            this.cost = cost;
        }

        public int compareTo(Edge tempEdge) {

            if (this.cost != tempEdge.cost) {

                return this.cost - tempEdge.cost; // sort by cost
            }

            if (!(this.firstCampus.equals(tempEdge.firstCampus))) {

                return this.firstCampus.compareTo(tempEdge.firstCampus); // sort by first campus
            }

            return this.secondCampus.compareTo(tempEdge.secondCampus); // sort by second campus
        }

        public String toString() {
            
            return (firstCampus + "---" + secondCampus + "\t$" + cost); // prints output
        }
    }
} 
