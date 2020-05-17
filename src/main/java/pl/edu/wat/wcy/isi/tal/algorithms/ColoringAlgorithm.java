package pl.edu.wat.wcy.isi.tal.algorithms;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ColoringAlgorithm implements Algorithm {
    public static final String COLOR_ATTRIBUTE = "ui.color";

    protected Graph graph;
    protected List<Color> availableColours;
    private long numberSteps = 0;
    private long numberMemories = 0;
    private int numberColors = 0;

    public static void restartColors(Graph graph) {
        graph.getNodeSet().forEach(n -> n.removeAttribute(COLOR_ATTRIBUTE));
        CounterPanel.getInstance().setStepColorMemoryCount(0, 0, 0);
    }

    @Override
    public void init(Graph graph) {
        this.graph = graph;
        this.availableColours = generateColours(graph.getNodeCount());
    }

    private List<Color> generateColours(int n) {
        Set<Color> colors = new HashSet<>();
        Random random = new Random();
        int r, g, b;
        while (colors.size() < n) {
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
            colors.add(new Color(r, g, b));
        }

        return new ArrayList<>(colors);
    }

    protected void changeColor(Node node, Color color) {
        node.addAttribute(COLOR_ATTRIBUTE, color);
    }

    protected void incrementSteps() {
        this.numberSteps++;
    }

    protected void incrementSteps(long n) {
        this.numberSteps += n;
    }

    public long getNumberSteps() {
        return numberSteps;
    }

    public int getNumberColors() {
        return numberColors;
    }

    protected void incrementMemories() {
        this.numberMemories++;
    }

    protected void incrementMemories(long n) {
        this.numberMemories += n;
    }

    public long getNumberMemories() {
        return numberMemories;
    }

    protected void countNumberColors() {
        this.numberColors = this.graph.getNodeSet().stream()
                .map(n -> n.getAttribute(COLOR_ATTRIBUTE))
                .collect(Collectors.toSet()).size();
    }
}
