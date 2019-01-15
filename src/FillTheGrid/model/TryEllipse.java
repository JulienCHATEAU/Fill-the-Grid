package FillTheGrid.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.util.Random;

public class TryEllipse extends Ellipse {

    public TryEllipse(int i, double width, double height, ColorsGrid colorsGrid) {
        this.centerXProperty().bind(colorsGrid.xProperty().divide(3));
        this.centerYProperty().bind(colorsGrid.yProperty().add(colorsGrid.getHeight()).subtract(height/2).subtract(i*height/2));
        this.setRadiusX(width/2);
        this.setRadiusY(height/2);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
        Random r = new Random();
        int random = r.nextInt(70);
        this.setFill(Color.rgb(random+100, random+100, random+100));
    }

}
