/**
 * @author Xinzhe Yuan ON 14/10/2023.
 * @project Exercise2
 **/
// Abstraction
// Base Class
abstract class Shape{
    // Static field
    private static String color;
    private static String name;

    public Shape(String color){
        this.color = color;
    }
    public float calculateArea(){
        System.out.println("Calculating Area...");
        return 0.0F;
    }
    public float calculatePerimeter(){
        System.out.println("Calculating Perimeter...");
        return 0.0F;
    }

    public static void setName(String name) {
        Shape.name = name;
    }
}
// Derived Classes
class Triangle extends Shape{
    public Triangle(String color) {
        super(color);
    }
    // Overriding
    @Override
    public float calculateArea(){
        System.out.println("This is an overridden calculateArea Method.");
        System.out.println("Calculating Area Using Overridden Method...");
        return 0.0F;
    }
}
class Circle extends Shape{
    public Circle(String color) {
        super(color);
    }
}
class Square extends Shape{
    public Square(String color) {
        super(color);
    }
}
// Main Method
public class Main {
    public static void main(String[] args) {
        System.out.println("Exercise 2");
        // Polymorphism
        Circle c = new Circle("Red");
        Shape s = c;
        Object o = c;

        System.out.println(c instanceof Circle);
        System.out.println(s instanceof Circle);
        System.out.println(o instanceof Circle);
    }
}