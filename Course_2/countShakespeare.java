
/**
 * Algorithm developed to count the ocurrencies of words and letters in Shakespeare's texts
 * 
 * @Gabriel Miki 
 * @07/22
 */

import edu.duke.*;

public class countShakespeare {
    public int indexOf(String[] list, String word) {
        for (int k = 0; k < list.length; k++) {
            if (list[k].equals(word)) {
                return k;
            }
        }
        
        return -1;
    }
    
    public void countWords(FileResource resource, String[] common, int[] count) {
        for (String s : resource.words()) {
            s = s.toLowerCase();
            
            for (int k = 0; k < common.length; k++) {
                if (common[k].equals(s)) {
                    count[k] += 1;
                }
            }
        }
    }
    
    public String[] getCommon() {
        FileResource resource = new FileResource("data/common.txt");
        
        String[] common = new String[20];
        
        int index = 0;
        
        for (String s : resource.words()) {
            common[index] = s;
            
            index += 1;
        }
        
        return common;
    }
    
    public void countShakes() {
        // String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] plays = {"manywords.txt"};
        
        String[] common = getCommon();
        
        int[] counts = new int[common.length];
        
        for (int k = 0; k < plays.length; k++) {
            FileResource resource = new FileResource("data/" + plays[k]);
            
            countWords(resource, common, counts);
            
            System.out.println("Done with " + plays[k]);
        }
        
        for (int k = 0; k < common.length; k++) {
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
}
