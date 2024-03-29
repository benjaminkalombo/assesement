package com.discovery.assesement.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;


class FileLoaderController extends AbstractController {
    private Stage stage;

    FileLoaderController(Stage stage) {
        this.stage = stage;
    }

    Scene getScene() {
        VBox centerScr = new VBox(20);
        centerScr.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Welcome to galaxy path finder!!!!!!");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrappingWidth(500);

        Text t = new Text("Please drag and drop csv file with graph data or click browse button to open file chooser");
        t.setFont(Font.font("Verdana", 15));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setWrappingWidth(500);

        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        ImageView csvImg = imgFromResource("images/csv.png", 35);

        Button browseButton = new Button("Browse Files", csvImg);
        browseButton.setContentDisplay(ContentDisplay.TOP);
        browseButton.setPrefSize(150, 30);
        browseButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                AppController.getInstance().showGraphMenu(file);
            }
        });

        ImageView graphLogo = imgFromResource("images/graph.png", 300);

        HBox exampleGraphMenu = new HBox(20);
        Button sampleGraph1 = new Button("Quick demo with default data");
        sampleGraph1.setOnAction(event -> showDefaultGraph("graphData/defaultGraph.csv"));

        //Button sampleGraph2 = new Button("use complex example graph");
        //sampleGraph2.setOnAction(event -> showDefaultGraph("graphData/advancedGraph.csv"));
        exampleGraphMenu.getChildren().addAll(sampleGraph1);
        exampleGraphMenu.setAlignment(Pos.CENTER);

        centerScr.getChildren().addAll(title, t, graphLogo, browseButton, exampleGraphMenu);

        HBox bottom = new HBox(20);
        ImageView infoImage = imgFromResource("images/halp.png", 20);
        Button helpButton = new Button("Help", infoImage);
        bottom.getChildren().add(helpButton);
        bottom.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderPane = new BorderPane(centerScr);
        borderPane.setBottom(bottom);
        borderPane.setMinHeight(650);

        borderPane.setPadding(new Insets(10));

//        ScrollPane sp = new ScrollPane();
//        sp.setContent(borderPane);
//        sp.setFitToWidth(true);
//        sp.setFitToHeight(true);

        Scene scene = new Scene(borderPane);

        helpButton.setOnAction(e -> displayHelpInfo());
        scene.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        // Dropping over surface
        scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                File f = db.getFiles().get(0);
                String extention = getExtension(f.getName());
                if ("csv".equals(extention)) {
                    AppController.getInstance().showGraphMenu(f);
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        return scene;
    }

    private void displayHelpInfo() {
        String title = "About the program";
        String header = "The App is a system that will allow them to find the shortest path from point “A”, being Earth, through the galaxy to any of the planets";
        String content = "The above graph represents an interstellar transport system used by Earth’s inhabitants in\n" +
                "the year 2145.You are provided with a list of node names and their respective distances\n" +
                "between their linked planets. In addition, you need to ensure that the system will work\n" +
                "from any source to destination coordinates specified.";
        showAlertWindow(title, header, content, Alert.AlertType.INFORMATION);

    }

    private String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1) {//if the name is not empty
            extension = fileName.substring(i + 1).toLowerCase();
        }
        return extension;
    }

    private void configureFileChooser(FileChooser fc) {
        fc.setTitle("View Pictures");
        fc.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
    }

    private void showDefaultGraph(String resourcePath) {
        InputStream rsc = getResourceAsInputStream(resourcePath);
        AppController.getInstance().showGraphMenu(rsc);
    }
}
