package victor.training.stream.dish;

import java.util.function.Function;

public class Dish {
  private final String name;
  private final boolean vegetarian;
  private final int calories;
  private final Type type;

  public enum Type {MEAT, FISH, OTHER}

  public Dish(String name, boolean vegetarian, int calories, Type type) {
    this.name = name;
    this.vegetarian = vegetarian;
    this.calories = calories;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public boolean isVegetarian() {
    return vegetarian;
  }

  public int getCalories() {
    return calories;
  }

  public Type getType() {
    return type;
  }


  @Override
  public String toString() {
    return name;
  }
     String getFormattedDishDetails() {
        return this.isVegetarian() ?
                String.format("%s (%d cal), veg", this.getName(), this.getCalories()) :
                String.format("%s (%d cal)", this.getName(), this.getCalories());
    }



}
