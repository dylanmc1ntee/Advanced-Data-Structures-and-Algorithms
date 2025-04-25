// Author: Dylan McIntee
// Course: COP3503
// Semester: Spring 2025

import java.util.*;
import java.io.*;

public class StudentSkipList {

    class Node {

        String name; // students first name
        int id; // students id
        Node[] forward; // pointers

        public Node(String name, int id, int level) { // set components of node

            this.name = name;
            this.id = id;
            forward = new Node[level + 1];

        }

    }

    private Node head; // head
    private int level; // current level of list
    private Random rand; // to determine if we promote
    private int size; // total number

    public StudentSkipList() {

        head = new Node("", -1, 100); // start with empty node
        level = 0;
        rand = new Random();
        size = 0;

    }

    private int getRandomLevel(int maxLevel) {

        int lvl = 0;

        // while the coin flip is true and we are below the max level
        while (rand.nextBoolean() && lvl < maxLevel) { 

            lvl++; // promote

        }

        return lvl;

    }

    public void insert(String name, int id) {

        Node[] update = new Node[100]; // array of nodes to update
        Node current = head;

        for (int i = level; i >= 0; i--) { // go from highest level to lowest

            // traverse while the next node is not null and name is less than new node name
            while (current.forward[i] != null && current.forward[i].name.compareTo(name) < 0) {

                current = current.forward[i]; // move forward

            }

            update[i] = current; // save last smaller node

        }

        size++; // increase size
        int maxLevel = (int) (Math.log(size) / Math.log(2)); // calculate max level
        int nodeLevel = getRandomLevel(maxLevel); // flip coin to for levels

        if (nodeLevel > level) {

            for (int i = level + 1; i <= nodeLevel; i++) {

                update[i] = head; // point to head for new levels

            }

            level = nodeLevel; // update level

        }

        Node newNode = new Node(name, id, nodeLevel); // create node

        for (int i = 0; i <= nodeLevel; i++) {

            newNode.forward[i] = update[i].forward[i]; // adjust pointers
            update[i].forward[i] = newNode; // point to new node

        }

        System.out.println("Inserted: " + name + " " + id + ", Levels: " + (nodeLevel + 1)); // print

    }

    public void search(String name) {

        Node current = head;

        // go from highest level to lowest
        for (int i = level; i >= 0; i--) {

            // go forward in the current level while the next nodes name is less
            while (current.forward[i] != null && current.forward[i].name.compareTo(name) < 0) {

                current = current.forward[i]; // traverse

            }
        }

        current = current.forward[0]; // go to next

        // check
        if (current != null && current.name.equals(name)) {

            System.out.println("Found: " + current.name + " " + current.id); // found

        } else {

            System.out.println("Student " + name + " not found."); // not found

        }

    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        String fileName = userInput.nextLine().trim();
        Scanner file = new Scanner(new File(fileName));

        StudentSkipList skipList = new StudentSkipList();

        while (file.hasNext()) {

            String name = file.next(); // read name
            int id = file.nextInt(); // read id
            skipList.insert(name, id); // insert

        }

        // do two searches (modify as needed for test case)
        skipList.search("SpongeBob"); // search existing
        skipList.search("Zach"); // search non-existing

        file.close();
        userInput.close();

    }
}
