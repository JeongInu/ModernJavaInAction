package Chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(
            new Apple(80,"green"),
            new Apple(155,"green"),
            new Apple(120,"red"),
    );

  }

  enum Color {
    RED,
    GREEN
  }

}
