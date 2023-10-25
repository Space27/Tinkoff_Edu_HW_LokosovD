package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimalProcessorTest {

    @Test
    @DisplayName("Сортировка по росту")
    void sortForHeight_ShouldSortAnimalsForTheirHeight() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 50, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, -100, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 200, 0, true)
        ));
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, -100, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 50, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 200, 0, true)
        ));

        List<Animal> sortedList = AnimalProcessor.sortForHeight(animals);

        assertThat(sortedList)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по весу в обратном порядке и срез по k")
    void sortForWeightReversedAngGetFirstKAnimals_ShouldSortAnimalsForWeightReversedAndGetKLenList() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, -100, -100, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 200, 200, true)
        ));
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 200, 200, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true)
        ));

        List<Animal> sortedKList = AnimalProcessor.sortForWeightReversedAngGetFirstKAnimals(animals, 3);

        assertThat(sortedKList)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Частотный словарь по типам")
    void countAnimalTypes_ShouldCountAnimalTypes() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 0, -100, -100, true),
            new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 200, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 200, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 200, true)
        ));
        Map<Animal.Type, Integer> expected = new HashMap<>(Map.of(
            Animal.Type.DOG, 2,
            Animal.Type.CAT, 1,
            Animal.Type.SPIDER, 1,
            Animal.Type.BIRD, 3
        ));

        Map<Animal.Type, Integer> freqDict = AnimalProcessor.countAnimalTypes(animals);

        assertThat(freqDict)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Животное с самым длинным именем")
    void animalWithTheLongestName_ShouldReturnAnimalWithMaxLenName() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 0, -100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 200, true)
        ));
        Animal expected = new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 200, true);

        Animal animal = AnimalProcessor.animalWithTheLongestName(animals);

        assertThat(animal)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Доминирующий пол")
    void dominantSex_ShouldReturnDominantSex() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 0, -100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.F, 0, 200, 200, true)
        ));
        Animal.Sex expected = Animal.Sex.M;

        Animal.Sex sex = AnimalProcessor.dominantSex(animals);

        assertThat(sex)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Самые тяжелые по типу")
    void heaviestGropedByType_ShouldReturnHeaviestAnimalInType() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 0, -100, -100, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 200, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 100, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 300, true)
        ));
        Map<Animal.Type, Animal> expected = new HashMap<>(Map.of(
            Animal.Type.DOG, new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 100, 100, true),
            Animal.Type.CAT, new Animal("", Animal.Type.CAT, Animal.Sex.M, 0, -100, -100, true),
            Animal.Type.BIRD, new Animal("", Animal.Type.BIRD, Animal.Sex.M, 0, 200, 300, true)
        ));

        Map<Animal.Type, Animal> heaviestAnimals = AnimalProcessor.heaviestGropedByType(animals);

        assertThat(heaviestAnimals)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("к-е самое старое животное")
    void kOldestAnimal_ShouldReturnKOldestAnimal() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 2, -100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 0, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 5, 200, 200, true)
        ));
        Animal expected = new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true);

        Animal animal = AnimalProcessor.kOldestAnimal(animals, 2);

        assertThat(animal)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Самое тяжелое животное ниже k")
    void heaviestAnimalLowerK_ShouldReturnHeaviestAnimalLowerK() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 2, -100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 0, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 5, 200, 200, true)
        ));
        Animal expected = new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true);

        Animal animal = AnimalProcessor.heaviestAnimalLowerK(animals, 150);

        assertThat(animal)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Сумма лап")
    void pawsSum_ShouldReturnPawsSum() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 50, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 2, -100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 0, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 5, 200, 200, true)
        ));
        Integer expected = 22;

        Integer pawsSum = AnimalProcessor.pawsSum(animals);

        assertThat(pawsSum)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Животные, у кого число лап и возраст не совпадают")
    void animalsWhichPawsAreNotEqualAge_ShouldReturnAnimalsWhichPawsAreNotEqualAge() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 4, 100, 100, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 50, 50, true),
            new Animal("", Animal.Type.FISH, Animal.Sex.M, 0, -100, -100, true),
            new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 8, 0, 0, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 1, 200, 200, true)
        ));
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 50, 50, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 1, 200, 200, true)
        ));

        List<Animal> animalsWithNotEqualAgeAndPaws = AnimalProcessor.animalsWhichPawsAreNotEqualAge(animals);

        assertThat(animalsWithNotEqualAgeAndPaws)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Животные, которые могут кусать, выше 100 см")
    void animalsThatCanBiteHigherThan100_ShouldReturnAnimalsThatCanBiteHigherThan100() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 4, 120, 100, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true),
            new Animal("", Animal.Type.FISH, Animal.Sex.M, 0, 150, -100, false),
            new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 8, 120, 0, true),
            new Animal("", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false)
        ));
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 4, 120, 100, true),
            new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 8, 120, 0, true)
        ));

        List<Animal> animalsThatCanBiteHigherThan100 = AnimalProcessor.animalsThatCanBiteHigherThan100(animals);

        assertThat(animalsThatCanBiteHigherThan100)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Число животных, у кого вес больше роста")
    void countAnimalsWhichWeightHigherThanHeight_ShouldCountAnimalsWhichWeightHigherThanHeight() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 2, 100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 5, 200, 300, true)
        ));
        Integer expected = 3;

        Integer animalsCount = AnimalProcessor.countAnimalsWhichWeightHigherThanHeight(animals);

        assertThat(animalsCount)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Животные с именами, в которых больше 2 слов")
    void animalsWhichNamesContainMoreThanTwoWords_ShouldReturnAnimalsWhichNamesContainMoreThanTwoWords() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("J M B", Animal.Type.DOG, Animal.Sex.M, 4, 120, 100, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true),
            new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, -100, false),
            new Animal("J B", Animal.Type.SPIDER, Animal.Sex.M, 8, 120, 0, true),
            new Animal("Mac", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("J M B", Animal.Type.DOG, Animal.Sex.M, 4, 120, 100, true),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));

        List<Animal> animalsWhichNamesContainMoreThanTwoWords =
            AnimalProcessor.animalsWhichNamesContainMoreThanTwoWords(animals);

        assertThat(animalsWhichNamesContainMoreThanTwoWords)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("В списке есть собака выше k")
    void isDogHigherK_ShouldReturnTrueIfSameDogInList() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("J M B", Animal.Type.DOG, Animal.Sex.M, 4, 120, 100, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true),
            new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, -100, false),
            new Animal("J B", Animal.Type.SPIDER, Animal.Sex.M, 8, 120, 0, true),
            new Animal("Mac", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));

        assertThat(AnimalProcessor.isDogHigherK(animals, 100))
            .isTrue();
    }

    @Test
    @DisplayName("В списке нет собаки выше k")
    void isDogHigherK_ShouldReturnTrueIfSameDogIsNotInList() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("J M B", Animal.Type.DOG, Animal.Sex.M, 4, 120, 100, true),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true),
            new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, -100, false),
            new Animal("J B", Animal.Type.SPIDER, Animal.Sex.M, 8, 120, 0, true),
            new Animal("Mac", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));

        assertThat(AnimalProcessor.isDogHigherK(animals, 200))
            .isFalse();
    }

    @Test
    @DisplayName("Сумма весов животных в диапазоне")
    void summaryWeightOfAnimalsWithAgeInRange_ShouldReturnSummaryWeightOfAnimalsWithAgeInRange() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, true),
            new Animal("Kirk", Animal.Type.CAT, Animal.Sex.M, 2, 100, -100, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, true),
            new Animal("Barbario", Animal.Type.BIRD, Animal.Sex.M, 5, 200, 300, true)
        ));
        Integer expected = 400;

        Integer summaryWeight = AnimalProcessor.summaryWeightOfAnimalsWithAgeInRange(animals, 3, 15);

        assertThat(summaryWeight)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по типу, полу, имени")
    void sortAnimalsForTypeSexName_ShouldSortAnimalsForTypeSexName() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("B", Animal.Type.DOG, Animal.Sex.F, 4, 120, 100, true),
            new Animal("A", Animal.Type.DOG, Animal.Sex.F, 2, 100, 50, true),
            new Animal("AAA", Animal.Type.DOG, Animal.Sex.M, 0, 150, -100, false),
            new Animal("AAAA", Animal.Type.BIRD, Animal.Sex.M, 8, 120, 0, true),
            new Animal("AAA", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true),
            new Animal("AAA", Animal.Type.DOG, Animal.Sex.M, 0, 150, -100, false),
            new Animal("A", Animal.Type.DOG, Animal.Sex.F, 2, 100, 50, true),
            new Animal("B", Animal.Type.DOG, Animal.Sex.F, 4, 120, 100, true),
            new Animal("AAA", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("AAAA", Animal.Type.BIRD, Animal.Sex.M, 8, 120, 0, true)
        ));

        List<Animal> sortedAnimals = AnimalProcessor.sortAnimalsForTypeSexName(animals);

        assertThat(sortedAnimals)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Пауки кусают чаще")
    void doSpidersBiteOftenThanDogs_ShouldReturnTrueIfBitingSpidersMoreThanBitingDogs() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, false),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, false),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, true)
        ));

        Boolean answer = AnimalProcessor.doSpidersBiteOftenThanDogs(animals);

        assertThat(answer)
            .isTrue();
    }

    @Test
    @DisplayName("Собаки кусают чаще")
    void doSpidersBiteOftenThanDogs_ShouldReturnFalseIfBitingSpidersLessThanBitingDogs() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, true),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, true)
        ));

        Boolean answer = AnimalProcessor.doSpidersBiteOftenThanDogs(animals);

        assertThat(answer)
            .isFalse();
    }

    @Test
    @DisplayName("Нет кусающих собак и пауков")
    void doSpidersBiteOftenThanDogs_ShouldReturnFalseIfThereAreNoBitingDogsAndSpiders() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("John", Animal.Type.DOG, Animal.Sex.M, 10, 100, 100, false),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, false),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 60, false),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, false),
            new Animal("James", Animal.Type.SPIDER, Animal.Sex.M, 20, 0, 1, false)
        ));

        Boolean answer = AnimalProcessor.doSpidersBiteOftenThanDogs(animals);

        assertThat(answer)
            .isFalse();
    }

    @Test
    @DisplayName("Самая тяжелая рыба в списках")
    void heaviestFish_ShouldReturnHeaviestFishInAllLists() {
        List<List<Animal>> animalLists = new ArrayList<>(List.of(
            new ArrayList<>(List.of(
                new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, -100, false)
            )),
            new ArrayList<>(List.of(
                new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, 0, false),
                new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 0, 50, 300, false)
            )),
            new ArrayList<>(List.of(
                new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, 10, false)
            ))
        ));

        Animal expected = new Animal("Mac Pac", Animal.Type.FISH, Animal.Sex.M, 0, 150, 10, false);

        Animal animal = AnimalProcessor.heaviestFish(animalLists);

        assertThat(animal)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на ошибки в записях")
    void findErrorsInAnimalRecords_ShouldFindAllErrorsOfNotValidAnimals() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 120, 100, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.F, -2, 100, 50, true),
            new Animal(" ", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false),
            new Animal("AAAA", Animal.Type.BIRD, Animal.Sex.M, 8, 120, 0, true),
            new Animal("AAA", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));
        Map<String, Set<ValidationError>> expected = new HashMap<>(Map.of(
            "", new HashSet<>(List.of(new ValidationError("Name should contain words", "name"))),
            "a", new HashSet<>(List.of(new ValidationError("Age should be positive number", "age"))),
            " ", new HashSet<>(List.of(
                new ValidationError("Name should contain words", "name"),
                new ValidationError("Weight should be positive number", "weight"),
                new ValidationError("Height should be positive number", "height")
            )),
            "AAAA", new HashSet<>(List.of(new ValidationError("Weight should be positive number", "weight")))
        ));

        Map<String, Set<ValidationError>> answer = AnimalProcessor.findErrorsInAnimalRecords(animals);

        assertThat(answer)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на ошибки в удобном виде")
    void findErrorsInAnimalRecordsAndSaveThemInStr_ShouldFindAllErrorsOfNotValidAnimals() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.F, 4, 120, 100, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.F, -2, 100, 50, true),
            new Animal(" ", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false),
            new Animal("AAAA", Animal.Type.BIRD, Animal.Sex.M, 8, 120, 0, true),
            new Animal("AAA", Animal.Type.BIRD, Animal.Sex.M, 1, 50, 200, false),
            new Animal("Ronnie James Dio", Animal.Type.CAT, Animal.Sex.M, 2, 100, 50, true)
        ));
        Map<String, String> expected = new HashMap<>(Map.of(
            "",  "name",
            "a", "age",
            " ", "height weight name",
            "AAAA", "weight"
        ));

        Map<String, String> answer = AnimalProcessor.findErrorsInAnimalRecordsAndSaveThemInStr(animals);

        assertThat(answer)
            .isEqualTo(expected);
    }
}
