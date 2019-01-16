package FillTheGrid.model;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import static FillTheGrid.controller.ControllerGame.BORDER_COLOR;

public class ColorsGrid extends Rectangle {

    private ColorsGridCell[][] content;
    private int rectCountPerLine;

    /**
     * Constructs a ColorsGrid depending on the pane size
     * @param pane the pane that its coordinates depends
     * @param width the width of the ColorsGrid
     * @param height the height of the ColorsGrid
     */
    public ColorsGrid(Pane pane, double width, double height, int rectCountPerLine, List<Paint> colorList) {
        super();
        this.rectCountPerLine = rectCountPerLine;
        this.xProperty().bind(Bindings.createDoubleBinding(new RoundXPosition(pane.widthProperty(), width, (double)2/3), pane.widthProperty()));
        this.yProperty().bind(Bindings.createDoubleBinding(new RoundXPosition(pane.heightProperty(), height, 0.5), pane.heightProperty()));
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(BORDER_COLOR);
        this.setStrokeWidth(5);
        this.setStrokeType(StrokeType.OUTSIDE);

        this.content = new ColorsGridCell[rectCountPerLine][rectCountPerLine];
        double rectContentSize = width / rectCountPerLine;
        pane.getChildren().add(this);
        for (int i = 0; i < rectCountPerLine; i++) {
            for (int j = 0; j < rectCountPerLine; j++) {
                Paint randomPaint = colorList.get(new Random().nextInt(colorList.size()));
                ColorsGridCell cgc = new ColorsGridCell(i, j, rectContentSize, this.xProperty(), this.yProperty(), randomPaint);
                this.content[i][j] = cgc;
                pane.getChildren().add(cgc);
            }
        }
    }

    public boolean paintTopLeftCorner(Paint nextColor) {
        boolean rightColor = false;
        Paint current = this.content[0][0].getFill();
        if (!nextColor.equals(current)) {
            this.content[0][0].paintNeightboors(current, nextColor, this.content);
            rightColor = true;
        }
        return rightColor;
    }

    public boolean isGameWon() {
        boolean won = true;
        int i = 0;
        int j = 0;
        Paint currentColor = this.content[0][0].getFill();
        while (i<this.rectCountPerLine && won) {
            while (j<this.rectCountPerLine && won) {
                if (!this.content[i][j].getFill().equals(currentColor)) {
                    won = false;
                } else {
                    j++;
                }
            }
            j = 0;
            i++;
        }
        return won;
    }

}
