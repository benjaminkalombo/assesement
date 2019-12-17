package com.discovery.assesement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.discovery.assesement.engine.DijkstraAlgorithm;
import com.discovery.assesement.engine.PathNotFoundException;
import com.discovery.assesement.models.Graph;
import com.discovery.assesement.models.Vertex;
import com.discovery.assesement.models.VertexNotFoundException;
import com.discovery.assesement.utils.GraphFromCsvCreator;
import com.discovery.assesement.utils.GraphVisualizer;

import java.io.File;
import java.io.IOException;

import java.io.File;

@SpringBootApplication
public class ShortPathServicesApplication {

    public static void main(String[] args) throws IOException, VertexNotFoundException {

    /*
        File theDir = new File("src/output");
        theDir.mkdir();
        Graph g = new GraphFromCsvCreator().createFromResources("graphData/defaultGraph.csv");
        GraphVisualizer.saveImage(g, "src/output/graph.png");
        System.out.println("Visualisation of your graph has been saved to src/output/graph.png\n");
        DijkstraAlgorithm engine = new DijkstraAlgorithm(g);
        try {
            System.out.println(engine.calculateShortestPath("Athens", "Paris"));
            GraphVisualizer.saveImage(g, "src/output/shortestPath.png");
            System.out.println("\nShortest path image has been saved to src/output/graph.png");

        } catch (PathNotFoundException e) {
            System.out.println(e.getMessage());
        }*/
        SpringApplication.run(ShortPathServicesApplication.class, args);
    }

}
