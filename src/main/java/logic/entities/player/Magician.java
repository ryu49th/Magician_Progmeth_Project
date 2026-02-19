package logic.entities.player;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import logic.entities.Entity;
import logic.control.KeyboardController;
import logic.game.GameController;

public class Magician extends Entity {
    private double speed = 5.0; // ความเร็วมาตรฐาน [cite: 61]
    private final int SIZE = 30;

    public Magician(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {
        KeyboardController kb = GameController.getKeyboardController();

        if (kb.isKeyPressed(KeyCode.W) || kb.isKeyPressed(KeyCode.UP)) y -= speed;
        if (kb.isKeyPressed(KeyCode.S) || kb.isKeyPressed(KeyCode.DOWN)) y += speed;
        if (kb.isKeyPressed(KeyCode.A) || kb.isKeyPressed(KeyCode.LEFT)) x -= speed;
        if (kb.isKeyPressed(KeyCode.D) || kb.isKeyPressed(KeyCode.RIGHT)) x += speed;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE); // ให้ตัวละครเป็นวงกลมสีฟ้า
        gc.fillOval(x, y, SIZE, SIZE);
    }
}