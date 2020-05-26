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
    private static final int GRID_GENERATOR_MAX_STEPS = 40;
    private static final int FULL_CONNECTED_GRAPH_GENERATOR_MAX_STEPS = 30;
    private static final DefaultListModel<String> listModel = getListModelGenerate();

    private final Graph graph;
    private final CounterPanel counterPanel;
    private int n;
    private int averageDegree;
    private SelectedGenerator selectedGenerator;
    private JList<String> list;
    private JSpinner spinnerAverageDegree;
    private JLabel spinnerLabelAverageDegree;

    private static DefaultListModel<String> getListModelGenerate() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Stream.of(SelectedGenerator.values()).forEach(g -> listModel.addElement(g.getName()));
        return listModel;
    }

    public GenerateActionListener(Graph graph, CounterPanel counterPanel) {
        this.graph = graph;
        this.counterPanel = counterPanel;
        this.n = 1;
        this.averageDegree = 1;
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
            this.graph.clear();
            generateGraph(this.graph);
            setCounterPanel(this.graph);
        }
    }

    private void generateGraph(Graph graph) {
        Generator gen = getGenerator(selectedGenerator);
        gen.addSink(graph);

        gen.begin();
        for (int i = 0; i < this.n; i++) {
            gen.nextEvents();
        }
        gen.end();

        graph.addAttribute("ui.stylesheet", "url('style.css')");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
    }

    private void setCounterPanel(Graph graph) {
        this.counterPanel.setNodeEdgeCount(graph.getNodeCount(), graph.getEdgeCount());
    }

    private Generator getGenerator(SelectedGenerator selectedGenerator) {
        switch (selectedGenerator) {
            case GRID_GENERATOR:
                if (this.n > GRID_GENERATOR_MAX_STEPS) {
                    this.n = GRID_GENERATOR_MAX_STEPS;
                }
                return new GridGenerator();

            case FULL_CONNECTED_GRAPH_GENERATOR:
                if (this.n > FULL_CONNECTED_GRAPH_GENERATOR_MAX_STEPS) {
                    this.n = FULL_CONNECTED_GRAPH_GENERATOR_MAX_STEPS;
                }
                return new FullGenerator();

            case WATTS_STROGATZ_GENERATOR:
                if (this.averageDegree >= this.n) {
                    this.averageDegree = this.n - 1;
                }
                if (this.averageDegree % 2 != 0) {
                    this.averageDegree--;
                }
                return new WattsStrogatzGenerator(this.n, this.averageDegree, 0.5);

            case LOBSTER_GENERATOR:
                return new LobsterGenerator();

            default:
                return new RandomGenerator(averageDegree);
        }
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        setListGenerator(panel);
        setStepsSpinner(panel);
        setAverageDegreeSpinner(panel);

        panel.setPreferredSize(new Dimension(300, 120));
        return panel;
    }

    private void setListGenerator(JPanel panel) {
        JLabel spinnerLabel = new JLabel("Generator: ");
        this.list = new JList<>(listModel);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.setSelectedIndex(selectedGenerator.getId());
        this.list.addListSelectionListener(e -> {
            this.selectedGenerator = SelectedGenerator.valueOf(this.list.getSelectedIndex())
                    .orElse(SelectedGenerator.RANDOM_GENERATOR);
            boolean isAverageDegree = this.selectedGenerator.compareTo(SelectedGenerator.RANDOM_GENERATOR) == 0 || this.selectedGenerator.compareTo(SelectedGenerator.WATTS_STROGATZ_GENERATOR) == 0;
            this.spinnerAverageDegree.setVisible(isAverageDegree);
            this.spinnerLabelAverageDegree.setVisible(isAverageDegree);
        });
        this.list.setVisibleRowCount(3);

        panel.add(spinnerLabel);
        panel.add(new JScrollPane(this.list));
    }

    private void setStepsSpinner(JPanel panel) {
        JLabel spinnerLabelSteps = new JLabel("Number of generator steps: ");
        SpinnerModel modelSteps = new SpinnerNumberModel(this.n, 1, 1000, 1);
        JSpinner spinnerSteps = new JSpinner(modelSteps);

        spinnerSteps.addChangeListener((e -> this.n = (int) spinnerSteps.getValue()));

        panel.add(spinnerLabelSteps);
        panel.add(spinnerSteps);
    }

    private void setAverageDegreeSpinner(JPanel panel) {
        this.spinnerLabelAverageDegree = new JLabel("The average degree of node: ");
        SpinnerModel modelAverageDegree = new SpinnerNumberModel(this.averageDegree, 1, 1000, 1);
        this.spinnerAverageDegree = new JSpinner(modelAverageDegree);

        this.spinnerAverageDegree.addChangeListener((e -> this.averageDegree = (int) this.spinnerAverageDegree.getValue()));

        panel.add(this.spinnerLabelAverageDegree);
        panel.add(this.spinnerAverageDegree);
    }
}
