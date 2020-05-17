package pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;

public class ColoringMenu extends JMenu {
    public ColoringMenu(Graph graph, CounterPanel counterPanel) {
        super("Coloring");
        initUI(graph, counterPanel);
    }

    private void initUI(Graph graph, CounterPanel counterPanel) {
        JMenuItem exactAlgorithmMenuItem = new JMenuItem("Exact algorithm");
        exactAlgorithmMenuItem.setToolTipText("Exact algorithm");

        JMenuItem SLAlgorithmMenuItem = new JMenuItem("SL algorithm");
        SLAlgorithmMenuItem.setToolTipText("Smallest last algorithm");

        JMenuItem resetMenuItem = new JMenuItem("Reset colors");
        resetMenuItem.setToolTipText("Reset colors");

        exactAlgorithmMenuItem.addActionListener(new ExactAlgorithmActionListener(graph, counterPanel));
        SLAlgorithmMenuItem.addActionListener(new SLAlgorithmActionListener(graph, counterPanel));
        resetMenuItem.addActionListener(new ResetColorsActionListener(graph, counterPanel));

        this.add(exactAlgorithmMenuItem);
        this.add(SLAlgorithmMenuItem);
        this.addSeparator();
        this.add(resetMenuItem);
    }
}
