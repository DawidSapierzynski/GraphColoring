package pl.edu.wat.wcy.isi.tal.gui.menu.fileMenu;

import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.core.SelectedGenerator;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

public class GenerateActionListener implements ActionListener {
    private static final int GRID_GENERATOR_MAX_STEPS = 50;
    private static final int FULL_CONNECTED_GRAPH_GENERATOR_MAX_STEPS = 40;
    private static final DefaultListModel<String> listModel = getListModelGenerate();

    private final Graph graph;
    private int n;
    private SelectedGenerator selectedGenerator;
    private JList<String> list;

    private static DefaultListModel<String> getListModelGenerate() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Stream.of(SelectedGenerator.values()).forEach(g -> listModel.addElement(g.getName()));
        return listModel;
    }

    public GenerateActionListener(Graph graph) {
        this.graph = graph;
        this.n = 1;
        this.selectedGenerator = SelectedGenerator.RANDOM_GENERATOR;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selection = JOptionPane.showConfirmDialog(
                null,
                getPanel(),
                "Generate new graph",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION) {
            graph.clear();
            generateGraph(graph, n);

            setCounterPanel(graph);
        }
    }

    private void generateGraph(Graph graph, int n) {
        Generator gen = getGenerator(selectedGenerator);
        gen.addSink(graph);

        gen.begin();
        for (int i = 0; i < n; i++) {
            gen.nextEvents();
        }
        gen.end();
    }

    private void setCounterPanel(Graph graph) {
        CounterPanel.getInstance().setNodeEdgeCount(graph.getNodeCount(), graph.getEdgeCount());
    }

    private Generator getGenerator(SelectedGenerator selectedGenerator) {
        switch (selectedGenerator) {
            case GRID_GENERATOR:
                if (n > GRID_GENERATOR_MAX_STEPS) {
                    n = GRID_GENERATOR_MAX_STEPS;
                }
                return new GridGenerator();
            case FULL_CONNECTED_GRAPH_GENERATOR:
                if (n > FULL_CONNECTED_GRAPH_GENERATOR_MAX_STEPS) {
                    n = FULL_CONNECTED_GRAPH_GENERATOR_MAX_STEPS;
                }
                return new FullGenerator();
            case WATTS_STROGATZ_GENERATOR:
                return new WattsStrogatzGenerator(n, 4, 0.5);
            case LOBSTER_GENERATOR:
                return new LobsterGenerator();
            default:
                return new RandomGenerator(4);
        }
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        setListGenerator(panel);
        setSpinner(panel);

        panel.setPreferredSize(new Dimension(300, 120));
        return panel;
    }

    private void setListGenerator(JPanel panel) {
        JLabel spinnerLabel = new JLabel("Generator: ");
        this.list = new JList<>(listModel);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.setSelectedIndex(selectedGenerator.getId());
        this.list.addListSelectionListener(e -> {
            this.selectedGenerator = SelectedGenerator.valueOf(list.getSelectedIndex())
                    .orElse(SelectedGenerator.RANDOM_GENERATOR);
        });
        this.list.setVisibleRowCount(3);

        panel.add(spinnerLabel);
        panel.add(new JScrollPane(this.list));
    }

    private void setSpinner(JPanel panel) {
        JLabel spinnerLabel = new JLabel("Number of generator steps: ");
        SpinnerModel model = new SpinnerNumberModel(this.n, 1, 1000, 1);
        JSpinner spinner = new JSpinner(model);

        spinner.addChangeListener((e -> {
            this.n = (int) spinner.getValue();
        }));

        panel.add(spinnerLabel);
        panel.add(spinner);
    }
}
