package FillTheGrid.controller;


import FillTheGrid.model.ColorButton;
import FillTheGrid.model.ColorsGrid;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class is the controller of the drawingEditorView
 */
public class ControllerGame implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private Pane topPane;
    @FXML private Pane centerPane;
    @FXML private Pane bottomPane;

    private ColorsGrid colorsGrid;

    public final static Color BORDER_COLOR = Color.web("#453f3e");

    public final static int COLOR_COUNT = 4;
    public final static int COLOR_BUTTON_WIDTH = 50;
    public final static int COLOR_BUTTON_HEIGHT = 50;

    public final static int TRY_COUNT = 15;


    public final static int COLORS_GRID_WIDTH = 300;
    public final static int COLORS_GRID_HEIGHT = 300;
    public final static int COLORS_GRID_DIVISION = 20;


    public final static int TRY_PIECE_WIDTH = 60;
    public final static int TRY_PIECE_HEIGHT = 25;

    private List<Paint> colorList;
    private ObservableList<Ellipse> tryList;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or <tt>null</tt> if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.centerPane.setStyle("-fx-background-color: #a5a09f");
        this.topPane.setStyle("-fx-background-color: #a5a09f");
        this.bottomPane.setStyle("-fx-background-color: #a7a2a1");

        this.colorList = new ArrayList<>();
        this.tryList = FXCollections.observableArrayList();
        this.tryList.addListener((ListChangeListener<Ellipse>) event -> {
            if(event.next()) {
                if (event.wasAdded()) {
                    for (Ellipse ell : event.getAddedSubList()) {
                        this.centerPane.getChildren().add(ell);
                    }
                } else if (event.wasRemoved()) {
                    for (Ellipse ell : event.getRemoved()) {
                        this.centerPane.getChildren().remove(ell);
                    }
                }
            }
        });
        for (int i = 0; i < COLOR_COUNT; i++) {
            Paint randomColor = this.randomColor();
            this.colorList.add(randomColor);
        }
        this.colorsGrid = new ColorsGrid(centerPane, COLORS_GRID_WIDTH, COLORS_GRID_HEIGHT, COLORS_GRID_DIVISION, this.colorList);

        for (int i = 0; i < TRY_COUNT; i++) {
            Ellipse tryPiece = new Ellipse();
            tryPiece.centerXProperty().bind(this.colorsGrid.xProperty().divide(3));
            tryPiece.centerYProperty().bind(this.colorsGrid.yProperty().add(this.colorsGrid.getHeight()).subtract(TRY_PIECE_HEIGHT/2).subtract(i*TRY_PIECE_HEIGHT/2));
            tryPiece.setRadiusY(TRY_PIECE_HEIGHT/2);
            tryPiece.setRadiusX(TRY_PIECE_WIDTH/2);
            tryPiece.setStroke(Color.BLACK);
            tryPiece.setStrokeWidth(2);
            Random r = new Random();
            int random = r.nextInt(70);
            tryPiece.setFill(Color.rgb(random+100, random+100, random+100));
            this.tryList.add(tryPiece);
        }

        for (int i = 0; i < COLOR_COUNT; i++) {
            ColorButton cb = new ColorButton(i, bottomPane, COLOR_BUTTON_WIDTH, COLOR_BUTTON_HEIGHT, COLOR_COUNT, this.colorList.get(i), this.colorsGrid, this.tryList);
            bottomPane.getChildren().add(cb);
        }
    }

    public Paint randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }

}
