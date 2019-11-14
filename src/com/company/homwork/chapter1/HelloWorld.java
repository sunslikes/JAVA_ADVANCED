package com.company.homwork.chapter1;

public class HelloWorld {
    private String centence;

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setCentence("HelloWorld");
        System.out.println(helloWorld.getCentence());
    }

    public String getCentence() {
        return centence;
    }

    public void setCentence(String centence) {
        this.centence = centence;
    }
}
