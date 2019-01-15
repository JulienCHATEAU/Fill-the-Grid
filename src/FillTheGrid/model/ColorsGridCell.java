package FillTheGrid.model;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Random;

public class ColorsGridCell extends Rectangle {

    private int i;
    private int j;

    public ColorsGridCell(int i, int j, double rectContentSize, ReadOnlyDoubleProperty xProperty, ReadOnlyDoubleProperty yProperty, Paint color) {
        super();
        this.i = i;
        this.j = j;
        double xOffset = j * rectContentSize;
        double yOffset = i * rectContentSize;
        this.xProperty().bind(xProperty.add(xOffset));
        this.yProperty().bind(yProperty.add(yOffset));
        this.setWidth(rectContentSize);
        this.setHeight(rectContentSize);
        Random r = new Random();
        this.setFill(color);
        this.setOpacity(0.70);
    }

    public void paintNeightboors(Paint currentColor, Paint nextColor, ColorsGridCell[][] gridContent) {
        if (this.getFill().equals(currentColor)) {
            this.setFill(nextColor);
            if (this.i+1 < gridContent.length) {
                gridContent[this.i+1][this.j].paintNeightboors(currentColor, nextColor, gridContent);
            }
            if (this.j+1 < gridContent.length) {
                gridContent[this.i][this.j+1].paintNeightboors(currentColor, nextColor, gridContent);
            }
            if (this.i-1 >= 0) {
                gridContent[this.i-1][this.j].paintNeightboors(currentColor, nextColor, gridContent);
            }
            if (this.j-1 >= 0) {
                gridContent[this.i][this.j-1].paintNeightboors(currentColor, nextColor, gridContent);
            }
        }
    }

}
