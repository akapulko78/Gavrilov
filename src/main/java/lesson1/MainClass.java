package lesson1;


public class MainClass {
    public static void main(String[] args) {
        Box<Apple> boxA = new Box<>();
        Box<Apple> boxA2 = new Box<>();
        Apple apple = new Apple();
        boxA.add(apple);
        boxA2.add(apple);
        boxA.move(boxA2);

        Box<Orange> boxO = new Box<>();
        Orange orange = new Orange();
        boxO.add(orange);
        boxA2.compare(boxO);
    }
}
