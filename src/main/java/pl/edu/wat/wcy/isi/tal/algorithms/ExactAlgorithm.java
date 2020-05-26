package pl.edu.wat.wcy.isi.tal.algorithms;

import org.graphstream.graph.Node;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import java.util.ArrayList;
import java.util.List;

public class ExactAlgorithm extends ColoringAlgorithm {
    public ExactAlgorithm(CounterPanel counterPanel) {
        super(counterPanel);
    }

    @Override
    public void compute() {
        List<List<Node>> listOfSubNodes = new ArrayList<>();
        boolean isColored = isColored(new ArrayList<>(graph.getNodeSet()));
        if (isColored) {
            for (Node n : graph) {
                changeColor(n, availableColours.get(0));
            }
        } else {
            List<Integer> listOfIndexes = generateListOfIndexes();
            listOfSubNodes = colorOfMultipleColors(listOfSubNodes, listOfIndexes);
            setColors(listOfSubNodes);
        }
        countNumberColors();
    }

    private List<List<Node>> colorOfMultipleColors(List<List<Node>> listOfSubNodes, List<Integer> listOfIndexes) {
        int i = 0;
        boolean isColored = false;
        while (!isColored && i <= listOfIndexes.size()) {
            List<List<List<Integer>>> listOfSubSets = generateListOfSubsets(listOfIndexes, i + 2);
            for (List<List<Integer>> lists : listOfSubSets) {
                listOfSubNodes = generateSubsets(lists);
                for (List<Node> l : listOfSubNodes) {
                    isColored = isColored(l);
                    if (!isColored) {
                        break;
                    }
                }
                if (isColored) {
                    break;
                }
            }
            i++;
        }
        return listOfSubNodes;
    }

    private List<Integer> generateListOfIndexes() {
        List<Integer> listOfIndexes = new ArrayList<>();
        for (int i = 0; i < graph.getNodeCount(); i++) {
            listOfIndexes.add(i);
            incrementAmountOfSpace();
        }
        return listOfIndexes;
    }

    private void setColors(List<List<Node>> listOfSubNodes) {
        int i;
        for (i = 0; i < listOfSubNodes.size(); i++) {
            for (Node n : listOfSubNodes.get(i)) {
                changeColor(n, availableColours.get(i));
            }
        }
    }

    private List<List<Node>> generateSubsets(List<List<Integer>> rlist) {
        List<List<Node>> subNode = new ArrayList<>();
        List<Node> nodes;

        for (List<Integer> l : rlist) {
            nodes = new ArrayList<>();
            for (Integer i : l) {
                nodes.add(this.graph.getNode(i));
            }
            subNode.add(nodes);
        }
        return subNode;
    }

    private boolean isColored(List<Node> listOfSubNodes) {
        incrementSteps();
        for (int i = 0; i < listOfSubNodes.size() - 1; i++) {
            for (int j = i + 1; j < listOfSubNodes.size(); j++) {
                if (listOfSubNodes.get(i).hasEdgeBetween(listOfSubNodes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<List<List<Integer>>> generateListOfSubsets(List<Integer> ori, int m) {
        List<List<List<Integer>>> listOfSubsets = new ArrayList<>();
        if (ori.size() < m || m < 1) return listOfSubsets;

        if (m == 1) {
            List<List<Integer>> partition = new ArrayList<>();
            partition.add(new ArrayList<>(ori));
            listOfSubsets.add(partition);
            return listOfSubsets;
        }

        List<List<List<Integer>>> prev1 = generateListOfSubsets(ori.subList(0, ori.size() - 1), m);
        for (List<List<Integer>> lists : prev1) {
            for (int j = 0; j < lists.size(); j++) {
                List<List<Integer>> l = new ArrayList<>();
                for (List<Integer> inner : lists) {
                    l.add(new ArrayList<>(inner));
                    incrementSteps();
                    incrementAmountOfSpace();
                }

                l.get(j).add(ori.get(ori.size() - 1));
                listOfSubsets.add(l);
            }
        }

        List<Integer> set = new ArrayList<>();
        set.add(ori.get(ori.size() - 1));
        List<List<List<Integer>>> prev2 = generateListOfSubsets(ori.subList(0, ori.size() - 1), m - 1);
        for (List<List<Integer>> lists : prev2) {
            List<List<Integer>> l = new ArrayList<>(lists);
            l.add(set);
            listOfSubsets.add(l);
        }

        return listOfSubsets;
    }
}
