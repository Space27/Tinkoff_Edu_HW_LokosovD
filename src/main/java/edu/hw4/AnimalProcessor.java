package edu.hw4;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AnimalProcessor {

    private AnimalProcessor() {
    }

    public static List<Animal> sortForHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortForWeightReversedAngGetFirstKAnimals(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Integer> countAnimalTypes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, val -> 1, Integer::sum));
    }

    public static Animal animalWithTheLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex dominantSex(List<Animal> animals) {
        return animals.stream()
            .map(Animal::sex)
            .sorted()
            .skip(animals.size() / 2)
            .findFirst()
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> heaviestGropedByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                (existing, replacement) -> {
                    if (existing.weight() < replacement.weight()) {
                        return replacement;
                    } else {
                        return existing;
                    }
                }
            ));
    }

    public static Animal kOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public static Animal heaviestAnimalLowerK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Integer pawsSum(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> animalsWhichPawsAreNotEqualAge(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    public static List<Animal> animalsThatCanBiteHigherThan100(List<Animal> animals) {
        final int lowestHeight = 100;
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > lowestHeight)
            .toList();
    }

    public static Integer countAnimalsWhichWeightHigherThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .mapToInt(animal -> 1)
            .sum();
        // можно использовать сразу count, но тогда кастить придется
    }

    public static List<Animal> animalsWhichNamesContainMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean isDogHigherK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Integer summaryWeightOfAnimalsWithAgeInRange(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> sortAnimalsForTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .toList();
    }

    public static Boolean doSpidersBiteOftenThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER)
            .filter(Animal::bites)
            .mapToInt(animal -> animal.type() == Animal.Type.SPIDER ? 1 : -1)
            .sum() > 0;
    }

    public static Animal heaviestFish(List<List<Animal>> animalsLists) {
        return animalsLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> findErrorsInAnimalRecords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !Validator.findErrors(animal).isEmpty())
            .collect(Collectors.toMap(Animal::name, Validator::findErrors));
    }

    public static Map<String, String> findErrorsInAnimalRecordsAndSaveThemInStr(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !Validator.findErrors(animal).isEmpty())
            .collect(Collectors.toMap(Animal::name, Validator::findErrors))
            .entrySet().stream()
            .map(x -> new AbstractMap.SimpleEntry<>(
                x.getKey(),
                x.getValue().stream()
                    .map(ValidationError::invalidField)
                    .collect(Collectors.joining(" "))
            ))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
