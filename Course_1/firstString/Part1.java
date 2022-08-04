public class Part1 {
    public String findgeneSimple(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
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
        String dna = "ADFAGVMIDJEJNSNTAAASKOCIEC";
        System.out.println("DNA is " + dna);
        String gene = findgeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ADFATGVMIDJEJNSNTASKOCIEC";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ADFAGVMIDJEJNSNTSKOCIEC";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ADFATGVMIDJEJNSNTAAASKOCIEC";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGJDKSOELTROAKTOETAA";
        System.out.println("DNA is " + dna);
        gene = findgeneSimple(dna);
        System.out.println("Gene is " + gene);
    }    
}
