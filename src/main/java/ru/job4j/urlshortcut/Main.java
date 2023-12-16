package ru.job4j.urlshortcut;

import ru.job4j.urlshortcut.service.UrlService;

public class Main {
    public static void main(String[] args) {

        int charSetLength = UrlService.CHAR_SET.length();
        StringBuilder code = new StringBuilder();
        char symb;
        for (int i = 0; i < 10; i++) {
            symb = UrlService.CHAR_SET.charAt((int) (charSetLength * Math.random()));
            code.append(symb);
        }
        System.out.println(code);
        String url = "https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html";
        String siteName = url.split("/")[2];
        System.out.println(siteName);
    }
}
