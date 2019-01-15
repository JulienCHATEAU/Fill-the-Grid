package FillTheGrid.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;

import java.util.concurrent.Callable;

public class RoundXPosition implements Callable<Double> {

    private final double colorsGridSize;
    private final double coeff;
    private final ReadOnlyDoubleProperty paneSizeProperty;

    public RoundXPosition(ReadOnlyDoubleProperty paneSize, double colorsGridSize, double coeff) {
        this.colorsGridSize = colorsGridSize;
        this.paneSizeProperty = paneSize;
        this.coeff = coeff;
    }
    
    @Override
    public Double call() throws Exception {
        return (double) Math.round(this.paneSizeProperty.get() * this.coeff - this.colorsGridSize / 2);
    }
    
}
