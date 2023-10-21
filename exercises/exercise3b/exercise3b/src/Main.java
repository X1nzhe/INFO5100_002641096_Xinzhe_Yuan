import java.io.*;

/**
 * @author Xinzhe Yuan ON 21/10/2023.
 * @project Exercise 3b
 **/
// Abstraction
// Base Class
abstract class Shape implements Serializable {
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

    public void setName(String name) {
        Shape.name = name;
    }
    public String getName(){
        return Shape.name;
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
        c.setName("MyCircle");
        Shape s = c;
        Object o = c;

        System.out.println(c instanceof Circle);
        System.out.println(s instanceof Circle);
        System.out.println(o instanceof Circle);

        // Serialization
        System.out.println();
        System.out.println("Serialization...");
        try{
            FileOutputStream fileOut = new FileOutputStream("circle.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(c);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in circle.ser");
        }catch (IOException i){
            i.printStackTrace();
        }
        // Deserialization
        System.out.println();
        System.out.println("Deserialization...");
        Circle newC = null;
        try{
            FileInputStream fileIn = new FileInputStream("circle.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newC = (Circle) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("The name of the circle is "+newC.getName());
            System.out.println(newC.calculateArea());
        }catch(IOException  i) {
            i.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Circle class not found");
            e.printStackTrace();
        }
    }
}