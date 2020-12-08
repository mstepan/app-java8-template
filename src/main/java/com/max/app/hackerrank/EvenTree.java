package com.max.app.hackerrank;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Hackerrank.
 *
 * https://www.hackerrank.com/challenges/even-tree/problem
 */
public final class EvenTree {

    public static void main(String[] args) throws IOException {

        int nodes = 30;
        int edges = 29;
        List<Integer> fromList = new ArrayList<>();
        fromList.add(2);
        fromList.add(3);
        fromList.add(4);
        fromList.add(5);
        fromList.add(6);
        fromList.add(7);
        fromList.add(8);
        fromList.add(9);
        fromList.add(10);
        fromList.add(11);
        fromList.add(12);
        fromList.add(13);
        fromList.add(14);
        fromList.add(15);
        fromList.add(16);
        fromList.add(17);
        fromList.add(18);
        fromList.add(19);
        fromList.add(20);
        fromList.add(21);
        fromList.add(22);
        fromList.add(23);
        fromList.add(24);
        fromList.add(25);
        fromList.add(26);
        fromList.add(27);
        fromList.add(28);
        fromList.add(29);
        fromList.add(30);


        List<Integer> toList = new ArrayList<>();
        toList.add(1);
        toList.add(2);
        toList.add(3);
        toList.add(2);
        toList.add(4);
        toList.add(4);
        toList.add(1);
        toList.add(5);
        toList.add(4);
        toList.add(4);
        toList.add(8);
        toList.add(2);
        toList.add(2);
        toList.add(8);
        toList.add(10);
        toList.add(1);
        toList.add(17);
        toList.add(18);
        toList.add(4);
        toList.add(15);
        toList.add(20);
        toList.add(2);
        toList.add(12);
        toList.add(21);
        toList.add(17);
        toList.add(5);
        toList.add(27);
        toList.add(4);
        toList.add(25);

        int evenForestsCount = evenForest(nodes, edges, fromList, toList);

        System.out.printf("res: %d%n", evenForestsCount);

        System.out.printf("java version: %s%n", System.getProperty("java.version"));
    }

    static int evenForest(int nodes, int edges, List<Integer> fromList, List<Integer> toList) {

        Node root = createRootedTreeFromEdges(nodes, fromList, toList);

        calculateCutsCount(root);

        return root.cutsCount;
    }

    static void calculateCutsCount(Node cur) {
        if (cur == null) {
            return;
        }

        if (cur.children.isEmpty()) {
            cur.subtreeSize = 1;
            cur.cutsCount = 0;
            return;
        }

        // traverse all children recursively first
        for (Node child : cur.children) {
            calculateCutsCount(child);
        }

        int totalSubtreeSize = 1;
        int totalCutsCount = 0;

        for (Node child : cur.children) {
            totalSubtreeSize += child.subtreeSize;
            totalCutsCount += child.cutsCount;

            // even subtree size for 'child', we can cut one more edge
            if ((child.subtreeSize & 1) == 0) {
                ++totalCutsCount;
            }
        }

        cur.subtreeSize = totalSubtreeSize;
        cur.cutsCount = totalCutsCount;
    }

    static Node createRootedTreeFromEdges(int nodes, List<Integer> fromList, List<Integer> toList) {
        assert fromList.size() == toList.size() : "fromList.size != toList.size";

        Map<Integer, List<Integer>> adjList = createAdjList(nodes, fromList, toList);
        Map<Integer, Node> nodesMap = createNodesMap(nodes);

        final Node root = nodesMap.get(1);

        Set<Integer> fullyVisited = new HashSet<>();
        Queue<Node> levelQueue = new ArrayDeque<>();
        levelQueue.add(root);

        while (!levelQueue.isEmpty()) {
            Node cur = levelQueue.poll();
            fullyVisited.add(cur.label);

            for (int childIndex : adjList.get(cur.label)) {
                if (!fullyVisited.contains(childIndex)) {
                    Node child = nodesMap.get(childIndex);

                    cur.children.add(child);
                    assert child.parent == null : "error: child parent already set";

                    child.parent = cur;

                    levelQueue.add(child);
                }
            }
        }

        return root;
    }

    static Map<Integer, List<Integer>> createAdjList(int nodes, List<Integer> fromList, List<Integer> toList) {
        assert fromList.size() == toList.size() : "fromList.size != toList.size";

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int vertexNo = 1; vertexNo <= nodes; ++vertexNo) {
            adjList.put(vertexNo, new ArrayList<>());
        }

        Iterator<Integer> fromIt = fromList.iterator();
        Iterator<Integer> toIt = toList.iterator();

        while (fromIt.hasNext() && toIt.hasNext()) {
            int src = fromIt.next();
            int dest = toIt.next();

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        assert !fromIt.hasNext();
        assert !toIt.hasNext();

        return adjList;
    }

    static Map<Integer, Node> createNodesMap(int nodes) {
        assert nodes >= 2 && nodes <= 100;
        Map<Integer, Node> nodesMap = new HashMap<>();
        for (int nodeIndex = 1; nodeIndex <= nodes; ++nodeIndex) {
            nodesMap.put(nodeIndex, new Node(nodeIndex));
        }
        return nodesMap;
    }

    static class Node {
        final int label;
        final List<Node> children = new ArrayList<>();
        Node parent;

        int subtreeSize;
        int cutsCount;

        Node(int label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return String.valueOf(label);
        }

    }


}
