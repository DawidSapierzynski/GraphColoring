package pl.edu.wat.wcy.isi.tal.gui;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import pl.edu.wat.wcy.isi.tal.gui.menu.GraphColoringMenuBar;

import javax.swing.*;
import java.awt.*;

public class GraphColoringFrame extends JFrame {
    private final Graph graph;
    private final CounterPanel counterPanel;

    public GraphColoringFrame() throws HeadlessException {
        this.setTitle("GraphColoring");
        this.graph = getDefaultGraph();
        this.counterPanel = new CounterPanel();

        initUI();
    }

    private Graph getDefaultGraph() {
        Graph graph = new SingleGraph("Graph");
        graph.addAttribute("ui.stylesheet", "url('style.css')");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        return graph;
    }

    private void initUI() {
        setJMenuBar(new GraphColoringMenuBar(this.graph, this.counterPanel));
        setContentPane(getDefaultView(this.graph));
        setCounterPanel(this.counterPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1200, 800));
        this.pack();
        this.setVisible(true);
    }

    private void setCounterPanel(CounterPanel counterPanel) {
        SpringLayout springLayout = new SpringLayout();
        Container cont = getContentPane();
        cont.setLayout(springLayout);

        springLayout.putConstraint(SpringLayout.WEST, counterPanel, 10, SpringLayout.WEST, cont);
        springLayout.putConstraint(SpringLayout.SOUTH, counterPanel, -10, SpringLayout.SOUTH, cont);
        cont.add(counterPanel);
    }

    private static JPanel getDefaultView(Graph graph) {
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();

        return viewer.addDefaultView(false);
    }

}
