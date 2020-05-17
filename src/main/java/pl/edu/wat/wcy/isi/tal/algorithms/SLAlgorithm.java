package pl.edu.wat.wcy.isi.tal.algorithms;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class SLAlgorithm extends ColoringAlgorithm {

    @Override
    public void compute() {
        List<Node> nodes = new ArrayList<>();
        Graph copyGraph = Graphs.clone(this.graph);
        Node minNode, coloredNode;

        restartColors(this.graph);

        while (copyGraph.getNodeCount() > 0 || copyGraph.getEdgeCount() > 0) {
            incrementSteps();
            minNode = copyGraph.getNodeSet().stream()
                    .min(Comparator.comparing(Node::getDegree))
                    .orElseThrow(NoSuchElementException::new);

            nodes.add(this.graph.getNode(minNode.getId()));
            copyGraph.removeNode(minNode);
        }

        for (int i = nodes.size() - 1; i >= 0; i--) {
            coloredNode = nodes.get(i);

            changeColor(coloredNode, this.availableColours.get(i));
        }

        countNumberColors();
    }
}
