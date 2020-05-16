package pl.edu.wat.wcy.isi.tal.algorithms;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;

public abstract class ColoringAlgorithm implements Algorithm {
    protected Graph graph;
    private int numberSteps = 0;
    private int numberColors = 0;

    @Override
    public void init(Graph graph) {
        this.graph = graph;
    }

    protected void incrementSteps() {
        this.numberSteps++;
    }

    protected void incrementSteps(int n) {
        this.numberSteps += n;
    }

    public int getNumberSteps() {
        return numberSteps;
    }

    public int getNumberColors() {
        return numberColors;
    }

    protected void setNumberColors(int numberColors) {
        this.numberColors = numberColors;
    }
}
