package Chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Chap02.FilteringApples.Color.GREEN;
import static Chap02.FilteringApples.Color.RED;

public class FilteringApples {

  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(
            new Apple(80, GREEN),
            new Apple(155, GREEN),
            new Apple(120, RED)
    );

    List<Apple> greenApples = filterApplesByColor(inventory, GREEN);
    System.out.println(greenApples);

    List<Apple> redApples = filterApplesByColor(inventory, RED);
    System.out.println(redApples);

    List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
    System.out.println(greenApples2);

    List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
    System.out.println(heavyApples);

    List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
    System.out.println(redAndHeavyApples);

    List<Apple> redApples2 = filter(inventory, new ApplePredicate(){
      public boolean test(Apple a) {
        return a.getColor() == Color.RED;
      }
    });
    System.out.println(redApples2);

  }

  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    //전체 사과 누적 리스트
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      // 초록색 사과만 결과에 반영
      if(apple.getColor() == GREEN) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if(apple.getColor().equals(color)) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int  weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if(apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for(Apple apple : inventory) {
      //Predicate 객체로 사과 검사 조건을 캡슐화
      if(p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  public enum Color {
    RED,
    GREEN
  }

  public static class Apple{

    private int weight = 0;
    private Color color;

    public Apple(int weight, Color color){
      this.weight = weight;
      this.color = color;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }

    @Override
    public String toString(){
      return String.format("Apple weight: %d, color: %s", weight, color);
    }

  }

  //선택 조건을 결정하는 인터페이스
  public interface ApplePredicate{
    boolean test(Apple a);
  }

  public static class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }
  }

  public static class AppleColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
      return GREEN.equals(apple.getColor());
    }
  }

  public static class AppleRedAndHeavyPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.RED && apple.getWeight() > 150;
    }
  }

}
