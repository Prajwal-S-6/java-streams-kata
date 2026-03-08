package com.learning.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbersList1 = List.of(1,2,3,4,5);
        List<Integer> numbersList2 = List.of(3,11,20,5,7,30);
        List<Integer> numbersList3 = List.of(10,20,70,40,90);
        List<String> words = List.of("java","spring","angular");

        var evenNumbers = numbersList1.stream()
                .filter(num -> num % 2 == 0).toList();
        System.out.println(evenNumbers);

        var upperCaseWords = words.stream()
                .map(String::toUpperCase).toList();
        System.out.println(upperCaseWords);

        var numbersCountGreaterThan10 = numbersList2.stream()
                .filter(num -> num > 10)
                .count();
        System.out.println(numbersCountGreaterThan10);

        var reversedSorted = IntStream.range(0, 10)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .peek(System.out::print)
                .toArray();
        System.out.println("\n");
        var firstElementGreaterThan50 = numbersList3.stream()
                .filter(num -> num > 50)
                .findFirst()
                .orElseGet(() -> -1);
        System.out.println(firstElementGreaterThan50);




    }
}
