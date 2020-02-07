class WordPair {
    private String word;
    private int value;

    public WordPair(String word) {
        this.word = word;
        this.value = 1;
    }

    void updateValue() {
        this.value++;
        System.out.println(this.value);
    }

    String getWord() {
        return this.word;
    }

    int getValue() {
        return this.value;
    }
}