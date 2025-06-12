package timing;

import java.io.IOException;
import java.io.OutputStream;

public class ClipboardHelper {
    public static void copyToClipboard(String text) {
        try {
            Process p = new ProcessBuilder("xclip", "-selection", "clipboard").start();
            OutputStream os = p.getOutputStream();
            os.write(text.getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}