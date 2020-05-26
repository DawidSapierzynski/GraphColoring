package pl.edu.wat.wcy.isi.tal.gui.menu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;
import pl.edu.wat.wcy.isi.tal.gui.menu.coloring_menu.ColoringMenu;
import pl.edu.wat.wcy.isi.tal.gui.menu.fileMenu.FileMenu;

import javax.swing.*;

public class GraphColoringMenuBar extends JMenuBar {
    public GraphColoringMenuBar(Graph graph, CounterPanel counterPanel) {
        initUI(graph, counterPanel);
    }

    private void initUI(Graph graph, CounterPanel counterPanel) {
        this.add(new FileMenu(graph, counterPanel));
        this.add(new ColoringMenu(graph, counterPanel));
    }
}
