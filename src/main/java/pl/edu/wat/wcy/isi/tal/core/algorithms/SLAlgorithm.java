package pl.edu.wat.wcy.isi.tal.core.algorithms;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SLAlgorithm extends ColoringAlgorithm {
    public SLAlgorithm(CounterPanel counterPanel) {
        super(counterPanel);
    }

    @Override
    public void compute() {
        List<Node> nodes = new ArrayList<>();
        Graph copyGraph = Graphs.clone(this.graph);
        Node minNode;

        restartColors(this.graph, this.counterPanel);

        while (copyGraph.getNodeCount() > 0 || copyGraph.getEdgeCount() > 0) {
            incrementSteps();
            incrementAmountOfSpace();
            minNode = copyGraph.getNodeSet().stream()
                    .min(Comparator.comparing(Node::getDegree))
                    .orElseThrow(NoSuchElementException::new);

            incrementSteps(copyGraph.getNodeCount() + 1);
            nodes.add(this.graph.getNode(minNode.getId()));
            copyGraph.removeNode(minNode);
        }

        colorNode(nodes);
        countNumberColors();
    }

    private void colorNode(List<Node> nodes) {
        Node coloredNode;
        Set<Color> usedColors;
        Iterable<Node> iterableNodes;
        for (int i = nodes.size() - 1; i >= 0; i--) {
            coloredNode = nodes.get(i);
            iterableNodes = coloredNode::getNeighborNodeIterator;
            usedColors = StreamSupport.stream(iterableNodes.spliterator(), false)
                    .map(n -> n.getAttribute(COLOR_ATTRIBUTE))
                    .map(Color.class::cast)
                    .collect(Collectors.toSet());

            incrementSteps((int) StreamSupport.stream(iterableNodes.spliterator(), false).count() + 1);
            changeColor(coloredNode, getFirstColor(usedColors));
        }
    }

    private Color getFirstColor(Set<Color> usedColors) {
        for (Color c : this.availableColours) {
            if (!usedColors.contains(c)) {
                return c;
            }
        }
        return Color.BLACK;
    }
}
