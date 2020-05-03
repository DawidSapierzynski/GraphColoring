package pl.edu.wat.wcy.isi.tal.core;

import java.util.Arrays;
import java.util.Optional;

public enum SelectedGenerator {
    RANDOM_GENERATOR(0, "Random generator"),
    GRID_GENERATOR(1, "Generator for square grids"),
    FULL_CONNECTED_GRAPH_GENERATOR(2, "Full connected generator"),
    WATTS_STROGATZ_GENERATOR(3, "Watts-Strogatz generator"),
    LOBSTER_GENERATOR(4, "Lobster generator");

    private final int id;
    private final String name;

    SelectedGenerator(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<SelectedGenerator> valueOf(int id) {
        return Arrays.stream(values())
                .filter(g -> g.id == id)
                .findFirst();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
