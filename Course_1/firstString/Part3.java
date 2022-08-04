/**
 * Escreva a descrição da classe Part3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstMatch = stringb.indexOf(stringa);
        int secondMatch = stringb.indexOf(stringa, firstMatch + stringa.length());
        
        if (secondMatch == -1) {
            return false;
        }
        
        return true;
    }
    
    public void testing() {
        String stringa = "a";
        String stringb =  "Banana";
        String secPart = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + secPart);
        
        stringa = "zoo";
        stringb =  "forest";
        secPart = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + secPart);
        
        stringa = "by";
        stringb =  "A history by Abby";
        secPart = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + secPart);
    }
    
    public String lastPart(String stringa, String stringb) {
        boolean twoOccur = twoOccurrences(stringa, stringb);
        
        if (twoOccur == true) {
            int firstMatch = stringb.indexOf(stringa);
            int secondMatch = stringb.indexOf(stringa, firstMatch + stringa.length());
            String secPart = stringb.substring(secondMatch);
            return secPart;
        }
        
        else {
            return stringb;
        }
    }
}
