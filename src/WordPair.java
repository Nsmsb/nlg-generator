class WordPair {
    String word;
    int value;

    public WordPair(String word) {
        this.word = word;
        this.value = 1;
    }

    void updateValue() {
        this.value++;
    }

    int getValue() {
        return this.value;
    }
}