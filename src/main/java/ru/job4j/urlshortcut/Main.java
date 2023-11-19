package ru.job4j.urlshortcut;

public class Main {
    public static void main(String[] args) {

        StringBuilder password = new StringBuilder();
        char symb;
        for (int i = 0; i < 5; i++) {
            symb = (char) (48 + (int) (10 * Math.random()));
            password.append(symb);
        }
        System.out.println(password);
    }
}
