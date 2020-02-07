import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Scanner;

import javafx.util.Pair; 

class TextAnalyser {

    Hashtable<String,WordPair> wordsFreqs;
    String f1; // the learning file
    Scanner in1; // the scanner associated to the file
    int words;


    public TextAnalyser(String f1) {
        this.wordsFreqs = new Hashtable<String,WordPair>();
        this.words = 0;
        this.f1 = f1;
        try {in1 = new Scanner(new FileInputStream(f1)); }
        catch (Exception e) {System.out.println(e);}
    }

    void analyse() {
        String[] words;
        try {
            while (in1.hasNext()) {
                words = in1.next().split("\\W+");

                for (String word : words) {
                    if (wordsFreqs.contains(word)) {
                        wordsFreqs.get(word).updateValue();
                        this.words++;
                    }
                    else
                        wordsFreqs.put(word, new WordPair(word));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    
}