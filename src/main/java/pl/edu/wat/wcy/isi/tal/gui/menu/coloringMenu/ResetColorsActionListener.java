package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetColorsActionListener implements ActionListener {
    private final Graph graph;

    public ResetColorsActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getNodeCount() == 0 && graph.getEdgeCount() == 0) {
            JOptionPane.showMessageDialog(null, "The graph is empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No implementation ...");
        }
    }
}
