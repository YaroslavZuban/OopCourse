package ru.academits.java.zuban.lambdas_main;

import ru.academits.java.zuban.lambdas.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

        List<String> uniqueNamesList = getListUniqueNames(personsList);

        System.out.println("Проверка: ");
        uniqueNamesList.forEach(System.out::println);

        System.out.println();

        System.out.println("Задача Б");
        System.out.println("Вывести список уникальных имен");
        System.out.println();

        String uniqueNames = getStringUniqueNames(personsList);

        System.out.println("Имена: " + uniqueNames);
        System.out.println();

        System.out.println("Задача В");
        System.out.println("Получить список людей младше 18");
        System.out.println("Посчитать для них средний возраст");
        System.out.println();

        List<Person> personsUnder18List = getListPersonsUnder18(personsList);
        System.out.println("Список людей младше 18: ");

        personsUnder18List.forEach(System.out::println);

        System.out.println("Cредний возраст людей младше 18 равен: " + getPersonsAverageAge(personsUnder18List));
        System.out.println();

        System.out.println("Задача Г");
        System.out.println("При помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст");
        System.out.println();

        Map<String, Double> persons = getGroupingsMap(personsList);

        System.out.println("Группировка людей");

        persons.forEach((name, averageAge) -> {
            System.out.println(name + ": " + averageAge);
        });

        System.out.println();

        System.out.println("Задача Д");
        System.out.println("Получить людей, возраст которых от 20 до 45, вывести в консоль " +
                "их имена в порядке убывания возраста");
        System.out.println();

        List<Person> personsAgeFrom20To45List = getListPersonAgeFrom20To45(personsList);

        personsAgeFrom20To45List.forEach(System.out::println);
        System.out.println();
    }

    private static List<Person> getListPersonAgeFrom20To45(List<Person> personsList) {
        return personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());
    }

    private static Map<String, Double> getGroupingsMap(List<Person> personsList) {
        return personsList.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingDouble(Person::getAge)));
    }

    private static double getPersonsAverageAge(List<Person> personsList) {
        return personsList.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);
    }

    private static List<Person> getListPersonsUnder18(List<Person> personsList) {
        return personsList.stream()
                .filter(x -> x.getAge() < 18).toList();
    }

    private static String getStringUniqueNames(List<Person> personsList) {
        return personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    private static List<String> getListUniqueNames(List<Person> personsList) {
        return personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
    }
}