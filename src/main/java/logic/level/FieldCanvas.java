package logic.level;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.entities.player.Magician;

public class FieldCanvas extends Canvas {
    private Magician player;

    private GraphicsContext gc;
    private final int TILE_SIZE = 40; // ขนาดของแต่ละช่อง (40x40 px)

    // Array แผนที่จำลอง (15 แถว x 20 คอลัมน์)
    // 0 = พื้นว่าง (Walkable), 1 = กำแพงหิน (Unbreakable), 2 = กำแพงไม้ (Breakable)
    private int[][] mapData = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 1},
            {1, 2, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 2, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 2, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 2, 1},
            {1, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public FieldCanvas(double width, double height) {
        super(width, height);
        gc = this.getGraphicsContext2D();

        // สร้างผู้เล่นที่ตำแหน่ง X=40, Y=40 (ช่องว่างมุมซ้ายบน)
        player = new Magician(40, 40);

        drawMap();
    }

    public void renderFrame() { //
        // 1. อัปเดตตำแหน่งผู้เล่น
        player.update();

        // 2. เคลียร์หน้าจอแล้ววาดแผนที่ทับใหม่
        gc.clearRect(0, 0, getWidth(), getHeight());
        drawMap();

        // 3. วาดผู้เล่นลงบนแผนที่
        player.render(gc);
    }

    // ฟังก์ชันสำหรับวาดแผนที่ลงบน Canvas
    private void drawMap() {
        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                int tileType = mapData[row][col];

                // เลือกสีตามประเภทของ Tile (อนาคตคุณสามารถเปลี่ยนเป็น drawImage แทนการเทสีได้)
                if (tileType == 1) {
                    gc.setFill(Color.DARKGRAY); // กำแพงหิน (พังไม่ได้)
                } else if (tileType == 2) {
                    gc.setFill(Color.SADDLEBROWN); // กำแพงไม้ (พังได้)
                } else {
                    gc.setFill(Color.LIGHTGREEN); // พื้นหญ้า
                }

                // วาดสี่เหลี่ยมลงบนพิกัด X, Y
                gc.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // วาดเส้นขอบตารางบางๆ เพื่อให้เห็นช่อง Grid ชัดเจนขึ้น (ลบออกได้ในภายหลัง)
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(0.5);
                gc.strokeRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}