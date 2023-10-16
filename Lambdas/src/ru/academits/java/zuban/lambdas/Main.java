package ru.academits.java.zuban.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Кира", 13),
                new Person("Петр", 24),
                new Person("Вася", 46),
                new Person("Марина", 23),
                new Person("Коля", 32),
                new Person("Петр", 32),
                new Person("Ярик", 22),
                new Person("Влад", 10)
        );

        System.out.println("Задача А");
        System.out.println("Получить список уникальных имен");
        System.out.println();

        List<String> uniqueNamesList = getUniqueNames(personsList);

        System.out.println("Проверка:");
        uniqueNamesList.forEach(System.out::println);

        System.out.println();

        System.out.println("Задача Б");
        System.out.println("Вывести список уникальных имен");
        System.out.println();

        String uniqueNamesString = getNamesString(uniqueNamesList);

        System.out.println(uniqueNamesString);
        System.out.println();

        System.out.println("Задача В");
        System.out.println("Получить список людей младше 18");
        System.out.println("Посчитать для них средний возраст");
        System.out.println();

        List<Person> personsUnder18List = getPersonsUnder18(personsList);
        System.out.println("Список людей младше 18:");

        personsUnder18List.forEach(System.out::println);

        if(!personsUnder18List.isEmpty()) {
            System.out.println("Средний возраст людей младше 18 равен: " + getPersonsAverageAge(personsUnder18List));
        }else{
            System.out.println("Средний возраст людей младше 18 равен: 0");
        }

        System.out.println();

        System.out.println("Задача Г");
        System.out.println("При помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст");
        System.out.println();

        Map<String, Double> averageAgesByNames = getAverageAgesByNames(personsList);

        System.out.println("Группировка людей");

        averageAgesByNames.forEach((name, averageAge) ->
                System.out.println(name + ": " + averageAge)
        );

        System.out.println();

        System.out.println("Задача Д");
        System.out.println("Получить людей, возраст которых от 20 до 45, вывести в консоль " +
                "их имена в порядке убывания возраста");
        System.out.println();

        List<Person> personsWithAgeFrom20To45 = getPersonsWithAgeFrom20To45(personsList);

        personsWithAgeFrom20To45.forEach(System.out::println);
    }

    private static List<Person> getPersonsWithAgeFrom20To45(List<Person> personsList) {
        return personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());
    }

    private static Map<String, Double> getAverageAgesByNames(List<Person> personsList) {
        return personsList.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));
    }

    private static OptionalDouble getPersonsAverageAge(List<Person> personsList) {
        if (personsList.isEmpty()) {
            return OptionalDouble.empty();
        }

        return personsList.stream()
                .mapToInt(Person::getAge)
                .average();
    }

    private static List<Person> getPersonsUnder18(List<Person> personsList) {
        return personsList.stream()
                .filter(x -> x.getAge() < 18)
                .toList();
    }

    private static String getNamesString(List<String> personsList) {
        if (personsList == null) {
            throw new NullPointerException("Переданный список имеет значение: null");
        }

        return personsList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
    }

    private static List<String> getUniqueNames(List<Person> personsList) {
        return personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
    }
}