package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;

import javax.swing.*;

public class ColoringMenu extends JMenu {
    public ColoringMenu(Graph graph) {
        super("Coloring");
        initUI(graph);
    }

    private void initUI(Graph graph) {
        JMenuItem exactAlgorithmMenuItem = new JMenuItem("Exact algorithm");
        exactAlgorithmMenuItem.setToolTipText("Exact algorithm");

        JMenuItem SLAlgorithmMenuItem = new JMenuItem("SL algorithm");
        SLAlgorithmMenuItem.setToolTipText("Smallest last algorithm");

        JMenuItem resetMenuItem = new JMenuItem("Reset colors");
        resetMenuItem.setToolTipText("Reset colors");

        exactAlgorithmMenuItem.addActionListener(new ExactAlgorithmActionListener(graph));
        SLAlgorithmMenuItem.addActionListener(new SLAlgorithmActionListener(graph));
        resetMenuItem.addActionListener(new ResetColorsActionListener(graph));

        this.add(exactAlgorithmMenuItem);
        this.add(SLAlgorithmMenuItem);
        this.addSeparator();
        this.add(resetMenuItem);
    }
}
