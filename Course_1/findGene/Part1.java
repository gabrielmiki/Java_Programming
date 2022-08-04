import edu.duke.*;
import java.io.File;

public class Part1 {
    public String findGene(String dna1, int where) {
        String dna = dna1.toUpperCase();
        
        int startIndex = dna.indexOf("ATG", where);
        
        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        
        else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        
        if (minIndex == -1) { 
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
        
    public int findStopCodon(String dna1, int startIndex, String stopCodon) {
        String dna = dna1.toUpperCase();
            
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
            
        while (currIndex != -1) {
                
            if ((startIndex - currIndex) % 3 == 0) {
                    return currIndex;
            }
                
            else {
                    currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
            
        return -1;
    }
    
    public StorageResource getAllGene(String dna1) {
        String dna = dna1.toUpperCase();
        
        StorageResource geneList = new StorageResource();
        
        int startIndex = 0;
        
        while (true) {
            String currGene = findGene(dna, startIndex);
            
            if (currGene.isEmpty()) {
                break;
            }
            
            geneList.add(currGene);
            
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        
        return geneList;
    }
    
    public double cgRatio(String dna1) {
        String dna = dna1.toUpperCase();
        
        int startIndexg = 0;
        int startIndexc = 0;
        
        double countg = 0.0;
        double countc = 0.0;
        
        while (true) {
            int currGeneg = dna.indexOf("G", startIndexg);
            
            if (currGeneg == -1) {
                break;
            }
            
            countg = countg + 1;
            
            startIndexg = currGeneg + 1;
        }
        
        while (true) {
            int currGenec = dna.indexOf("C", startIndexc);
            
            if (currGenec == -1) {
                break;
            }
            
            countc = countc + 1;
            
            startIndexc = currGenec + 1;
        }
        
        return (countg + countc) / dna.length();
    }
    
    public int countCTG(String dna1) {
        String dna = dna1.toUpperCase();
        
        int startIndex = 0;
        
        int count = 0;
        
        while (true) {
            int currGene = dna.indexOf("CTG", startIndex);
            
            if (currGene == -1) {
                break;
            }
            
            count = count + 1;
            
            startIndex = currGene + 1;
        }
        
        return count;
    }
    
    public void testing() {
        FileResource fr = new FileResource();
        int count = 0;
        int countLen = 0;
        int countcg = 0;
        int biggest = 0;
        for (String s : getAllGene(fr.asString()).data()) {
            count = count + 1;
            if (s.length() > 60) {
                countLen = countLen + 1;
            }
            
            if (cgRatio(s) > 0.35) {
                countcg = countcg + 1;
            }
            
            if (s.length() > biggest) {
                biggest = s.length();
            }
        }
        
        System.out.println("Number of genes: " + count + "\n" +
                           "Bigger than 60: " + countLen + "\n" +
                           "cgRatio bigger than 0.35: " + countcg + "\n" +
                           "Num of CTG: " + countCTG(fr.asString()) + "\n" +
                           "Biggest Gene: " + biggest);
    }
    
    public void test() {
        String dna = "Batatalalalatalalalalatatata";
        
        String dna2 = dna.toUpperCase();
        
        System.out.println(mystery(dna2));
    }
    
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
            return dna;
        }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }
}

