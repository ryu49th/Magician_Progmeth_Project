package logic.control;

import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class KeyboardController {
    private Set<KeyCode> activeKeys;

    public KeyboardController() {
        activeKeys = new HashSet<>();
    }

    public void addKey(KeyCode code) {
        activeKeys.add(code);
    }

    public void removeKey(KeyCode code) {
        activeKeys.remove(code);
    }

    public boolean isKeyPressed(KeyCode code) {
        return activeKeys.contains(code);
    }
}