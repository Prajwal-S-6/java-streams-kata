package victor.training.stream.dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static victor.training.stream.dish.DishExercise.menu;

public class DishExerciseAdvanced {

  // region flatMap
  public static final Map<String, List<String>> allergens = Map.of(
      "pizza", List.of("milk", "gluten", "fish", "seafood"),
      "french fries", List.of("gluten"),
      "salmon", List.of("fish"),
      "bread", List.of("gluten"),
      "fruit salad", List.of("strawberry"),
      "prawn", List.of("seafood")
  );

  public static List<String> allAllergens() {
    return allergens.values().stream()
            .flatMap(List::stream)
            .distinct()
            .toList();
  }

  public static List<String> allWords() {
    // For example, "season fruit" -> 2 words
    return menu.stream()
            .map(dish -> dish.getName().split(" "))
            .flatMap(Arrays::stream)
            .toList();
  }

  // endregion

  // region .groupingBy / .toMap
  public static Map<String, Dish> dishByName() {
    return menu.stream()
            .collect(toMap(Dish::getName, Function.identity()));
  }

  public static Map<Dish.Type, List<Dish>> dishesByType() {
    return menu.stream()
            .collect(groupingBy(Dish::getType, Collectors.mapping(Function.identity(), toList())));
  }

  public static Map<Dish.Type, Long> numberOfDishesByType() {
    return menu.stream()
            .collect(groupingBy(Dish::getType, counting()));
  }

  public static Map<Dish.Type, Long> totalCaloriesByType() {
    // TODO group dishes by type and sum up the calories of each group
    return menu.stream()
            .collect(groupingBy(Dish::getType, summingLong(Dish::getCalories)));
  }

  public static Map<Dish.Type, Set<String>> dishNamesByCategory() {
    // TODO group dishes by type and sum up the calories of each group
    return null;
  }
  // endregion



}
