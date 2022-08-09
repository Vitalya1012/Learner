package com.vitaliy.learner;

public class Word {

    private String russian;
    private String foreign;
    private String examples;

    public Word(String russian, String foreign, String examples) {
        this.russian = russian;
        this.foreign = foreign;
        this.examples = examples;
    }

    public Word() {
    }

    public String getRussian() {
        return russian;
    }

    public String getForeign() {
        return foreign;
    }

    public String getExamples() {
        return examples;
    }

    @Override
    public String toString() {
        return "Word{" +
                "russian='" + russian + '\'' +
                ", foreign='" + foreign + '\'' +
                ", examples='" + examples + '\'' +
                '}';
    }
}
