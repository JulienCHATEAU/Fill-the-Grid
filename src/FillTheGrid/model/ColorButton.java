package FillTheGrid.model;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static FillTheGrid.controller.ControllerGame.BORDER_COLOR;

public class ColorButton extends Rectangle {

    public ColorButton(int i, Pane bottomPane, double colorButtonWidth, double colorButtonHeight, int colorNumber, Paint color, ColorsGrid colorsGrid, List<Ellipse> tryList) {
        super();
        this.setWidth(colorButtonWidth);
        this.setHeight(colorButtonHeight);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(5);
        this.setArcWidth(10);
        this.setArcHeight(10);
        this.setOpacity(0.70);
        final int final_i = i;
        this.xProperty().bind(Bindings.createDoubleBinding(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                int i1 = final_i;
                double n = colorNumber;
                double r = colorButtonWidth;
                return (double) (i1 +1)*Math.round((bottomPane.widthProperty().get()-n*r)/(n+1))+ i1 * r;
            }
        }, bottomPane.widthProperty()));
//        this.yProperty().bind(bottomPane.heightProperty().subtract(colorButtonHeight).divide(2));
        this.setFill(color);

        this.setOnMouseClicked(event -> {
            boolean rightColor = colorsGrid.paintTopLeftCorner(color);
            if (rightColor) {
                tryList.remove(tryList.size()-1);
            }
        });

        this.setOnMousePressed(event -> {
            this.setWidth(this.getWidth()-2);
            this.setHeight(this.getHeight()-2);
            this.setOpacity(0.9);
        });

        this.setOnMouseReleased(event -> {
            this.setWidth(this.getWidth()+2);
            this.setHeight(this.getHeight()+2);
            this.setOpacity(0.70);
        });
    }

}
