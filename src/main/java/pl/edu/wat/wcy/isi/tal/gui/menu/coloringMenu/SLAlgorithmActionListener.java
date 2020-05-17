package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.algorithms.SLAlgorithm;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SLAlgorithmActionListener implements ActionListener {
    private final Graph graph;

    public SLAlgorithmActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getNodeCount() == 0 && graph.getEdgeCount() == 0) {
            JOptionPane.showMessageDialog(null, "The graph is empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            SLAlgorithm slAlgorithm = new SLAlgorithm();
            slAlgorithm.init(graph);
            slAlgorithm.compute();
            CounterPanel.getInstance().setStepColorCount(slAlgorithm.getNumberSteps(), slAlgorithm.getNumberColors());
        }
    }
}
