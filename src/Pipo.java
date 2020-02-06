import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Hashtable;
import java.util.Iterator;


class Pipo {
    String f1; // the learning file
    Scanner in1; // the scanner associated to the file
    Hashtable<String,Hashtable<String,Integer>> LangModel; // the language model
    Random generator;
    
    Pipo(String f1) {
        this.f1 = f1;
        try {in1 = new Scanner(new FileInputStream(f1)); }
        catch (Exception e) {System.out.println(e);}
        LangModel = new Hashtable<String,Hashtable<String,Integer>>();
        generator=new Random(); // Seed to be given... Eventually
    }
    
    public void newWorsSeq(String w1, String w2) {
        //System.out.println(" "+w1+"  "+w2+" ");
        // This is were you need to update the language model (hash of hashes)

        Hashtable<String, Integer> w1HashTable;

        if (LangModel.containsKey(w1)) {
            w1HashTable = this.LangModel.get(w1);
            if(w1HashTable.containsKey(w2)) {
                w1HashTable.put(w2, w1HashTable.get(w2) + 1);
            } else {
                w1HashTable.put(w2, 1);
            }
        } else {
            w1HashTable = new Hashtable<String, Integer>();
            w1HashTable.put(w2, 1);
            this.LangModel.put(w1, w1HashTable);
        }
    }
    
    public void Learn() {
        String word1;
        word1="."; // A ghost word beeing before the first word of the text
        try {
            while (in1.hasNext()) {
                String word2 = in1.next();
                if (word2.matches("(.*)[.,!?<>=+-/]")) {
                    // word2 is glued with a punctuation mark
                    String[] splitedWord= word2.split("(?=[.,!?<>=+-/])|(?<=])");
                    for (String s : splitedWord) {
                        newWorsSeq(word1,s); // update de language model
                        word1=s;
                    }
                } else { // word2 is a single word
                    newWorsSeq(word1,word2); // update de language model
                    word1=word2;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Talk(int nbWord) {
        // Taking advantage of the generative skills of the language model
        Hashtable<String, Integer> w1HashTable;
        Iterator iterator;
        int i, randomWordIndex, modelWords, sum, freqSum, randomNum;
        String w1, w2;

        // **** chosing a costum first word: chose one, or whatever exist in the text
        // w1 = "Dans";
        w1 = "On";
        // w1 = "Nous";

        // **** chosing random first word:
        // disable (comment) the next 6 lines of code to disable random first word choice

        modelWords = this.LangModel.size();
        randomWordIndex = generator.nextInt(modelWords); 
        iterator = this.LangModel.keySet().iterator();
        i = 0;
        while (i++ < randomWordIndex)
            w1 = (String) iterator.next();

        // System.out.println(w1);

        System.out.println("Compte rendu de l'apnée 3 algo 6 :");

        // words generating start
        i = 0;
        do {
            w1HashTable = this.LangModel.get(w1);           // getting hashTable of w1
            freqSum = 0;
            for (String key : w1HashTable.keySet()) {       // calculating freqSum
                freqSum += w1HashTable.get(key);
            }

            iterator = w1HashTable.keySet().iterator();     // iterator to iterat through words next to w1

            // generator = new Random();
            randomNum = generator.nextInt(freqSum);         // chosing random int, used to generat next word
            sum = 0;
            do {                                            // generating the next word (w2)
                w2 = (String) iterator.next();
                sum += w1HashTable.get(w2);
            } while (sum < randomNum);

            // printing words
            System.out.printf("%s ", w1);

            w1 = w2;

        } while (i++ < nbWord);

        System.out.println(".");
        System.out.println("Fin du compte rendu");
    }
}