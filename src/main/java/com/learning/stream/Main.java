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

        //----------------------------------------------------------------------------------------
        List<Integer> numbersList4 = List.of(1,2,2,3,4,4,5);
        var distinctNumbers = numbersList4.stream()
                .distinct()
                .toList();
        System.out.println(distinctNumbers);

        List<Integer> numbersList5 = List.of(1,2,3,4);
        var sumOfAllNumbers = numbersList5.stream()
                .reduce(Integer::sum)
                .orElseGet(() -> 0);
        System.out.println(sumOfAllNumbers);

        var maxNumber = numbersList5.stream()
                .max(Comparator.naturalOrder())
                .orElseGet(() -> 0);
        System.out.println(maxNumber);

        List<Integer> numbersList6 = List.of(10,50,30,20,70,60);
        var top3HighestNumber = numbersList6.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
        System.out.println(top3HighestNumber);

        var skipFirst3Nums = numbersList6.stream()
                .skip(3)
                .toList();
        System.out.println(skipFirst3Nums);

        //--------------------------------------------------------------------------------
        List<Employee> employees = List.of(
                new Employee(1, "A", 26, "IT", 20000),
                new Employee(2, "B", 23, "Sales", 30000),
                new Employee(3, "C", 21, "Engineering", 70000)
        );
        List<String> employeeNames = employees.stream()
                .map(Employee::name)
                .toList();
        System.out.println(employeeNames);

        List<Employee> employeeWithSalaryMoreThan50k = employees.stream()
                .filter(emp -> emp.salary() > 50000)
                .toList();
        System.out.println(employeeWithSalaryMoreThan50k);

        Double averageSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::salary));
        System.out.println(averageSalary);

        Comparator<Employee> employeeComparator = (e1, e2) -> e1.salary() > e2.salary() ? 1 : -1;
        Employee employeeWithHighestSalary = employees.stream()
                .max(employeeComparator)
                .get();
        System.out.println(employeeWithHighestSalary);

        List<Employee> sortedEmployeeBySalary = employees.stream()
                .sorted(Comparator.comparing(Employee::salary))
                .toList();
        System.out.println(sortedEmployeeBySalary);

        //--------------------------------------------------------------------------------------------------


    }
}
