package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.algorithms.ExactAlgorithm;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExactAlgorithmActionListener implements ActionListener {
    private final Graph graph;
    private final CounterPanel counterPanel;

    public ExactAlgorithmActionListener(Graph graph, CounterPanel counterPanel) {
        this.graph = graph;
        this.counterPanel = counterPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getNodeCount() == 0 && graph.getEdgeCount() == 0) {
            JOptionPane.showMessageDialog(null, "The graph is empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ExactAlgorithm exactAlgorithm = new ExactAlgorithm(this.counterPanel);
            exactAlgorithm.init(graph);
            exactAlgorithm.compute();
            this.counterPanel.setStepColorSpaceCount(
                    exactAlgorithm.getNumberSteps(),
                    exactAlgorithm.getNumberColors(),
                    exactAlgorithm.getAmountOfSpace()
            );
            //JOptionPane.showMessageDialog(null, "No implementation ...");
        }
    }
}
