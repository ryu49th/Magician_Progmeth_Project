package logic.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import logic.level.FieldCanvas;
import logic.control.KeyboardController;

public class GameController {
    private static VBox root;
    private static FieldCanvas fieldCanvas;
    private static Scene scene;
    private static boolean isGameEnded;
    private static KeyboardController keyboardController;

    public static void setupScene() {
        root = new VBox();
        scene = new Scene(root, 800, 600);
        keyboardController = new KeyboardController();
        fieldCanvas = new FieldCanvas(800, 600);
        fieldCanvas.setFocusTraversable(true);
        root.getChildren().add(fieldCanvas);
        isGameEnded = false;

        // 1. ดักจับการกดปุ่มเพื่อส่งให้ KeyboardController
        scene.setOnKeyPressed(e -> keyboardController.addKey(e.getCode()));
        scene.setOnKeyReleased(e -> keyboardController.removeKey(e.getCode()));

        // 2. สร้าง Game Loop เพื่อวาดเฟรมใหม่และอัปเดตการเดิน (60 FPS)
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isGameEnded) {
                    fieldCanvas.renderFrame(); // เรียกให้อัปเดตภาพทุกเฟรม
                }
            }
        };
        gameLoop.start(); // สั่งให้ Loop เริ่มทำงาน

        updateScore();
    }

    public static void updateScore(){
        /*====================FILL CODE============================*/
        new Thread(() -> {
            while (!GameController.isGameEnded()) {
                try {
                    Thread.sleep(1000); // Wait 1 second

                    // นำโค้ดอัปเดต UI ของ MenuPane ออกไปแล้ว
                    // สามารถใส่ Logic อื่นๆ แทนได้หากจำเป็น

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static boolean isGameEnded() {
        return isGameEnded;
    }

    public static void setIsGameEnded(boolean isGameEnded){
        GameController.isGameEnded = isGameEnded;
        // นำการเซ็ต text game over ของ MenuPane ออกไปแล้ว
    }

    public static Scene getScene() {
        return scene;
    }

    public static KeyboardController getKeyboardController() {
        return keyboardController;
    }
}