package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.core.algorithms.SLAlgorithm;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SLAlgorithmActionListener implements ActionListener {
    private final Graph graph;
    private final CounterPanel counterPanel;

    public SLAlgorithmActionListener(Graph graph, CounterPanel counterPanel) {
        this.graph = graph;
        this.counterPanel = counterPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getNodeCount() == 0 && graph.getEdgeCount() == 0) {
            JOptionPane.showMessageDialog(null, "The graph is empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            SLAlgorithm slAlgorithm = new SLAlgorithm(this.counterPanel);
            slAlgorithm.init(graph);
            slAlgorithm.compute();
            this.counterPanel.setStepColorSpaceCount(
                    slAlgorithm.getNumberSteps(),
                    slAlgorithm.getNumberColors(),
                    slAlgorithm.getAmountOfSpace()
            );
        }
    }
}
