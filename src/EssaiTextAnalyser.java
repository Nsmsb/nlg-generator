class EssaiTextAnalyser {
    public static void main(String [] args) {   //@args: learning file.
        
        TextAnalyser ta;
        try {
            ta = new TextAnalyser(args[0]);
            ta.analyse();
            for (WordPair w : ta.getNthFreqWords(10) ) {
                System.out.printf("\"%s\" %.4f%% %d\n", w.getWord(), ta.getWerdFrequency(w.getWord()), w.getValue());
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
   }
}