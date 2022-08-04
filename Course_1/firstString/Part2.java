/**
 * Escreva a descrição da classe Part2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part2 {
    public String findgeneSimple(String dna, String startCodon, String stopCodon) {
        String result = "";
        
        String toUpperDNA = dna.toUpperCase();
        String toUpperSrt = startCodon.toUpperCase();
        String toUpperStp = stopCodon.toUpperCase();
        
        int startIndex = toUpperDNA.indexOf(toUpperSrt);
        if (startIndex == -1) {
            return "";
        }
        int endIndex = toUpperDNA.indexOf(toUpperStp, startIndex + 3);
        if (endIndex == -1) {
            return "";
        }
        if ((endIndex - startIndex) % 3 != 0) {
            return "";
        }
        result = dna.substring(startIndex, endIndex + 3);
        
        return result;
    }
    
    public void testfindgeneSimple() {
        String dna = "adfagvmidaskjhcuetaacrnej";
        System.out.println("DNA is " + dna);
        String gene = findgeneSimple(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "ahcunatgocosjeksn";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "njdkcorlelnvkslpqwkekmr";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "akisoekmatgosmrmdhtaa";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "ATGJDKSOELTROAKTOETAA";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna, "atg", "taa");
        System.out.println("Gene is " + gene);
        
        dna = "atgtaa";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "atgksrtocfntemckadtaa";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna, "atg", "taa");
        System.out.println("Gene is " + gene);
    }
 }
