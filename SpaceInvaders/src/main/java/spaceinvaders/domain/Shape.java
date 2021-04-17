package spaceinvaders.domain;

import javafx.geometry.Point2D;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class Shape {
    private Polygon shape;
    ColorInput color = new ColorInput();
    Blend blend = new Blend(BlendMode.DIFFERENCE);

    public Shape(Polygon polygon, Color color, int x, int y) {
        this.shape = polygon;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);

        this.color.setPaint(color);
        blend.setBottomInput(this.color);
        this.shape.setEffect(blend);
    }

    public Polygon getShape() {
        return this.shape;
    }

    public void setColor(Color color) {
        this.color.setPaint(color);
        blend.setBottomInput(this.color);
        this.shape.setEffect(blend);
    }

    public void moveLeft() {
        this.shape.setTranslateX(this.shape.getTranslateX() - 3.5);
    }

    public void moveRight() {
        this.shape.setTranslateX(this.shape.getTranslateX() + 3.5);
    }
}
