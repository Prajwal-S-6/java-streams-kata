package victor.training.stream.dish;

import victor.training.stream.dish.Dish.Type;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static victor.training.stream.dish.Dish.Type.*;

public class DishExercise {
  public static final List<Dish> menu = Arrays.asList(
      new Dish("pork", false, 800, MEAT),
      new Dish("chicken", false, 400, MEAT),
      new Dish("french fries", true, 530, OTHER),
      new Dish("rice", true, 350, OTHER),
      new Dish("season fruit", true, 120, OTHER),
      new Dish("pizza", false, 550, OTHER),
      new Dish("prawns", false, 300, FISH),
      new Dish("salmon", false, 450, FISH));

  public static void main(String[] args) {
    // TODO feel free to call any method here to experiment it
  }

  public static List<Dish> lowCalories() {
     return menu.stream()
            .filter(dish -> dish.getCalories() <=350)
            .toList();
  }

  public static List<Dish> highCalorie() {
    return menu.stream()
            .filter(dish -> dish.getCalories() > 400)
            .limit(4)
            .toList();
  }

  public static List<Dish> vegetarian() {
    return menu.stream()
            .filter(Dish::isVegetarian)
            .toList();
  }

  public static List<Dish> nonFish() {
    return menu.stream()
            .filter(dish -> dish.getType() != FISH)
            .toList();
  }

  public static List<Dish> multiWord() {
    return menu.stream()
            .filter(dish -> dish.getName().contains(" "))
            .toList();
  }

  // region === .map ===
  public static List<String> names() {
    return menu.stream()
            .map(Dish::getName)
            .toList();
  }

  public static List<Dish.Type> types() {
    return menu.stream()
            .map(Dish::getType)
            .toList();
  }

  public static List<String> namesOfVegetarianDishes() {
    return menu.stream()
            .filter(Dish::isVegetarian)
            .map(Dish::getName)
            .toList();
  }

  public static List<String> namesOfLowCalories() {
    return menu.stream()
            .filter(dish -> dish.getCalories() < 400)
            .map(Dish::getName)
            .toList();
  }

  public static List<String> namesInUpper() {
    return menu.stream()
            .map(dish -> dish.getName().toUpperCase())
            .toList();
  }

  public static List<Dish> newDishesWithNameUpperCase() {
    return menu.stream()
            .map(dish -> new Dish(dish.getName().toUpperCase(), dish.isVegetarian(), dish.getCalories(), dish.getType()))
            .toList();
  }

  public static List<Type> allTypesDistinct() {
    return menu.stream()
            .map(Dish::getType)
            .distinct()
            .toList();
  }

  public static Set<Type> allTypesAsSet() {
    return menu.stream()
            .map(Dish::getType)
            .collect(Collectors.toSet());
  }
  // endregion

  // region === .anyMatch / .noneMatch / .allMatch ===
  public Boolean anyVegetarian() {
    return menu.stream()
            .anyMatch(Dish::isVegetarian);
  }

  public Boolean noneFish() {
      return menu.stream()
              .filter(dish -> dish.getType() == FISH)
              .noneMatch(dish -> dish.getCalories() > 500);
  }

  public Boolean vegetarianArentMeat() {
    return menu.stream()
            .noneMatch(dish -> dish.isVegetarian() && dish.getType() == MEAT);
  }

  public Boolean allAreUnder1000cal() {
    return menu.stream()
            .allMatch(dish -> dish.getCalories() < 1000);
  }

  public Boolean kForMeat() {
    return menu.stream()
            .filter(dish -> dish.getType() == MEAT)
            .allMatch(dish -> dish.getName().contains("k"));
  }
  // endregion

  // region === .findFirst ===
  public static Dish lowCaloriesMeat() {
    return menu.stream()
            .filter(dish -> dish.getType() == MEAT && dish.getCalories() < 500)
            .findFirst()
            .orElseThrow();
  }
  public static Dish fries() {
    return menu.stream()
            .filter(dish -> dish.getName().contains("fries"))
            .findAny()
            .orElse(null);
  }

  public Integer pizzaCalories() {
    return menu.stream()
            .filter(dish -> dish.getName() == "pizza")
            .map(Dish::getCalories)
            .findFirst()
            .orElseThrow();
  }
  // endregion

  // region === .max(c) & .sorted(c) with c=Comparator.comparing(element->sorting key) ===
  public static List<Dish> sortedByName() {
    return menu.stream()
            .sorted(Comparator.comparing(Dish::getName))
            .toList();
  }

  public static List<Dish> sortedByCalories() {
    return menu.stream()
            .sorted(Comparator.comparing(Dish::getCalories))
            .toList();
  }

  public static List<Dish> sortedByCaloriesDescending() {
      return menu.stream()
              .sorted(Comparator.comparing(Dish::getCalories, Comparator.reverseOrder()))
              .toList();
  }

  public static List<Dish> sortedByTypeThenByName() {
    return menu.stream()
            .sorted(Comparator.comparing(Dish::getType).thenComparing(Dish::getName))
            .toList();
  }

  public static Dish maxCaloric() {
    return menu.stream()
            .max(Comparator.comparing(Dish::getCalories))
            .get();
  }

  public static List<Dish> top3Caloric() {
    return menu.stream()
            .sorted(Comparator.comparing(Dish::getCalories).reversed())
            .limit(3)
            .toList();
  }

  public static Dish secondMostCaloric() {
      return menu.stream()
              .sorted(Comparator.comparing(Dish::getCalories).reversed())
              .skip(1)
              .findFirst()
              .get();

  }

  public static List<Dish> secondAndThirdMostCaloric() {
    // TODO find out the 2nd and 3rd most caloric items
    return menu.stream()
            .sorted(Comparator.comparing(Dish::getCalories).reversed())
            .skip(1)
            .limit(2)
            .toList();
  }

  public String higherVegetarianCalories() {
    return menu.stream()
            .filter(Dish::isVegetarian)
            .max(Comparator.comparing(Dish::getCalories))
            .map(Dish::getName)
            .get();
  }

  public static List<Dish> meatOptions() {
    // TODO find 2 dishes with meat. Don't return more than 2.
    return menu.stream()
            .filter(dish -> dish.getType() == MEAT)
            .limit(2)
            .toList();
  }
  // endregion

  // region === .collect(Collectors.joining) ===
  public static String namesCommaSeparated() {
    return menu.stream()
            .map(Dish::getName)
            .sorted()
            .collect(Collectors.joining(","));
  }
  public static String toMenuString() {
    // transform each Dish to look like
    //  "salmon (300 cal)"
    //  "french fries (530 cal), veg" -> veg means it's vegetarian
    // separate each lines with \n
    return menu.stream()
            .map(Dish::getFormattedDishDetails)
            .collect(Collectors.joining("\n"));
  }


    public static String toIstambulMenuString() {
    // same as above, but skipping the item named 'pork'
        return menu.stream()
                .filter(dish -> dish.getName() != "pork")
                .map(Dish::getFormattedDishDetails)
                .collect(Collectors.joining("\n"));
  }
  // endregion

  // region === Int|DoubleStream / .sum / .average / .reduce ===
  public static int totalCalories() {
    // return the sum of all calories in the menu.
    //  If there is no item in the menu, return 0;
    return menu.stream()
            .mapToInt(Dish::getCalories)
            .sum();
  }
  public static double averageMeatCalories() {
    // return the average of all calories in the menu.
    //  If there is no item in the menu, return 0;
    return menu.stream()
            .mapToDouble(Dish::getCalories)
            .average()
            .orElse(0);
  }

  public static double averageFishCalories() {
    // return the average of all calories of the FISH dishes. orElse(0)
    return menu.stream()
            .filter(dish -> dish.getType() == FISH)
            .mapToDouble(Dish::getCalories)
            .average()
            .orElse(0);
  }

  public static int charactersInTheNames() {
    // return the total of all the name lengths (eg: "pork" has length = 4)
    return menu.stream()
            .map(dish -> dish.getName().length())
            .reduce(0, Integer::sum);
  }

  public static BigDecimal totalPriceForFish(Map<Dish, BigDecimal> pricePerDish) {
    // TODO return the sum of all the prices of the dishes.
    //  The prices are provided as a method parameter
    return null;
  }
  // endregion
}
