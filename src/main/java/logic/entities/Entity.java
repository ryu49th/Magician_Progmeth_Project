package logic.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x, y;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);
}