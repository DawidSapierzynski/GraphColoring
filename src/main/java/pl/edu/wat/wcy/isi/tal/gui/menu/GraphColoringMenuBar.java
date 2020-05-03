package pl.edu.wat.wcy.isi.tal.gui.menu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.gui.menu.coloringMenu.ColoringMenu;
import pl.edu.wat.wcy.isi.tal.gui.menu.fileMenu.FileMenu;

import javax.swing.*;

public class GraphColoringMenuBar extends JMenuBar {
    private final Graph graph;

    public GraphColoringMenuBar(Graph graph) {
        this.graph = graph;
        initUI();
    }

    private void initUI() {
        this.add(new FileMenu(graph));
        this.add(new ColoringMenu(graph));
    }
}
