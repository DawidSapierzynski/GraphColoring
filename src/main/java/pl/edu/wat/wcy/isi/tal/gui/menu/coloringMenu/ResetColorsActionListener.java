package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.core.algorithms.ColoringAlgorithm;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetColorsActionListener implements ActionListener {
    private final Graph graph;
    private final CounterPanel counterPanel;

    public ResetColorsActionListener(Graph graph, CounterPanel counterPanel) {
        this.graph = graph;
        this.counterPanel = counterPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.graph.getNodeCount() == 0 && this.graph.getEdgeCount() == 0) {
            JOptionPane.showMessageDialog(null, "The graph is empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ColoringAlgorithm.restartColors(this.graph, this.counterPanel);
        }
    }
}
