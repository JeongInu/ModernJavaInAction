package Chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(
      new Apple(80,"green"),
      new Apple(155,"green"),
      new Apple(120,"red"),
      new Apple(160,"brown")
    );

    List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
    System.out.println(greenApples);

    List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
    System.out.println(heavyApples);

    List<Apple> greeenApples2 = filterApples(inventory, FilteringApples::isGreenApple);
    System.out.println(greeenApples2);

    List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
    System.out.println(heavyApples2);

    List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
    System.out.println(weirdApples);

  }

  public static List<Apple> filterGreenApples(List<Apple> inventory){
    //처음에는 비어있지만 녹색 사과로 채워 넣을 것.
    List<Apple> result = new ArrayList<Apple>();

    for (Apple apple : inventory) {
      //apple의 color값을 가져와 green과 같은지 비교
      if ("green".equals(apple.getColor())) {
        //같다면 채운다.s
        result.add(apple);
      }
    }

    return result;
  }

  public static List<Apple> filterHeavyApples(List<Apple> inventory){
    List<Apple> result = new ArrayList<>();

    for(Apple apple : inventory){
      //150보다 무거운 사과만 추가
      if(apple.getWeight() > 150){
        result.add(apple);
      }
    }

    return result;
  }

  public static boolean isGreenApple(Apple apple){
    return "green".equals(apple.getColor());
  }

  public static boolean isHeavyApple(Apple apple){
    return apple.getWeight() > 150;
  }
  
  //Predicate - 인수로 값을 받아 true 혹은 false 반환
  static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
      //사과가 p가 제시하는 조건에 부합하는가 판단.
      if(p.test(apple)){
        result.add(apple);
      }
    }
    return result;
  }

}
