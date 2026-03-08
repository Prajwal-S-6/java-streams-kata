package com.learning.stream;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5);
        List<String> words = List.of("java","spring","angular");

        var evenNumbers = numbers.stream().filter(num -> num % 2 == 0).toList();
        System.out.println(evenNumbers);

        var upperCaseWords = words.stream().map(String::toUpperCase).toList();
        System.out.println(upperCaseWords);
    }
}
