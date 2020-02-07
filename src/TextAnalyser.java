import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;

import javafx.util.Pair; 

class TextAnalyser {

    private Hashtable<String,WordPair> wordsFreqs;
    private String f1; // the learning file
    private Scanner in1; // the scanner associated to the file
    private int words;


    public TextAnalyser(String f1) {
        this.wordsFreqs = new Hashtable<String,WordPair>();
        this.words = 0;
        this.f1 = f1;
        try {in1 = new Scanner(new FileInputStream(f1)); }
        catch (Exception e) {System.out.println(e);}
    }

    public int getwords() {
        return this.words;
    }

    public int getWerdFrequency(String word) {
        if (this.wordsFreqs.contains(word))
            return (wordsFreqs.get(word).getValue() / this.words);
        return 0;
    }

    public void analyse() {
        String[] words;
        try {
            while (in1.hasNext()) {
                words = in1.next().split("\\W+");

                for (String word : words) {
                    if (wordsFreqs.containsKey(word)) {
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

    public WordPair[] getNthFreqWords(int n) {
        WordPair[] result = new WordPair[this.wordsFreqs.size()];
        result = this.wordsFreqs.values().toArray(result);
        System.out.println("hhhhh");
        Arrays.sort(result, new Comparator<WordPair>() {
            public int compare(WordPair w1, WordPair w2) {
                return w2.getValue() - w1.getValue();
            }
        });

        return Arrays.copyOf(result, n);

    }


    
}