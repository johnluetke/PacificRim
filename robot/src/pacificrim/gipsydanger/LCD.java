package pacificrim.gipsydanger;

import java.util.Queue;

public class LCD extends lejos.nxt.LCD {

    private static Queue buffer = new Queue();
    
    public static void drawScrollingString(String str) {
        buffer.push(str);
        if (buffer.size() > LCD.DISPLAY_CHAR_DEPTH) {
            buffer.pop();
        }
        
        for (byte i = 0; i < buffer.size(); i++) {
            drawString((String)buffer.elementAt(i), 0, i);
        }
    }
}
