import edu.duke.*;
import java.io.File;

public class Part4 {
    public void readFile() {
        
    }    
    
    public String findYoutube(String urlSorce) {
        URLResource ur = new URLResource(urlSorce);
        
        for (String s : ur.words()) {
            if (s.indexOf("youtube.com") != -1) {
                return s;
            }
            
        }
        
        return "";
    }
}
