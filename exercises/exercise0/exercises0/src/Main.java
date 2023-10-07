/**
 * @author Xinzhe Yuan ON 2/10/2023.
 * @project INFO5100-Exercise0
 **/
class Laptop{
    private String brand;
    private int weight;
    private int length;
    private int depth;
    private int width;
    private String operationSystem;
    private int hardDiskCapacity;
    private int ramCapacity;
    private int screenSize;
    private String color;

    public Laptop() {
        System.out.println("A laptop has been created. ");
    }

    public Laptop(String brand) {
        this.brand = brand;
        System.out.println("A "+this.brand+" laptop has been created. ");
    }

    public void powerOn() {
    }
    public void shutDown(){

    }

    public String getBrand() {
        return brand;
    }

    public void sleep(){}
    public class Battery{
        private String serialNumber;
        private int powerLife;
        private boolean isCharging;
        private String fitLaptopBrand;

        public Battery(String fitLaptopBrand ) {
            System.out.println("A battery has been created. It has been installed in the "+fitLaptopBrand+" laptop.");
        }

        public void setCharging(){}
        public void supplyPower(){}
        public void cutoffCharging(){}
    }

}
class Desk{
    private int id;
    private int numLegs;
    private int length;
    private int depth;
    private int width;
    private int height;
    private int sizeOfSurfacePanel;
    private String materialOfSurfacePanel;
    private String  materialOfLegs;
    private boolean hasWheels;

    public Desk(int id) {
        this.id = id;
        System.out.println("A Desk with ID "+this.id+" has been created. ");
    }

    public void occupy(){}
    public void move(){}
    public void adjustHeight(){}



}
class Student{
    private String name;
    private int height;
    private int weight;
    private String race;
    private int age;
    private String hairColor;
    private String eyesColor;
    private String diet;
    private boolean disability;

    public Student(String name) {
        this.name = name;
        System.out.println("A student named "+name+" has been created. ");
    }

    public void walk(){}
    public void sit(){}
    public void standUp(){}

}
class Chair{
    private int id;
    private int numLegs;
    private int length;
    private int depth;
    private int width;
    private int height;
    private String color;
    private String materialOfPanel;
    private String  materialOfLegs;
    private boolean hasWheels;

    public Chair(int id) {
        this.id = id;
        System.out.println("A chair with ID "+id+" has been created. ");
    }

    public void sitOn(){}
    public void move(){}
    public void adjustHeight(){}


}
class WaterBottle{
    private String color;
    private int length;
    private int depth;
    private int width;
    private int height;
    private int radiusOfBody;
    private int volume;

    private String materialOfLid;
    private String  materialOfBody;

    public WaterBottle(String color) {
        this.color = color;
        System.out.println("A "+this.color+" water bottle has been created.");
    }

    public void drink(){}
    public void refill(){}
    public void recycle(){}

}
class Camera{
    private String brand;
    private int id;
    private int sizeOfCCD;
    private int length;
    private int width;
    private int depth;

    private boolean POE;
    private boolean nightVisionAbility;
    private boolean isColour;
    private boolean streamingAbility;

    public Camera(int id) {
        this.id = id;
        System.out.println("A camera has been created. Its ID is "+this.id+".");
    }

    public void capture(){}
    public void recode(){}
    public void shutDown(){}



}
class DisplayScreen{
    private String brand;
    private int id;
    private int screenSize;
    private int length;
    private int width;
    private int height;
    private int depth;
    private boolean hasHdmi;
    private boolean isColor;

    public DisplayScreen( int id) {
        this.id = id;
        System.out.println("A display screen has been created. Its ID is "+this.id+".");
    }

    public void turnOn(){}
    public void shutDown(){}
    public void display(){}



}
class Light{
    private String brand;
    private int id;
    private int maxLux;
    private  int radiusOfBubble;
    private int length;
    private  int width;
    private int height;
    private  int colorTemp;
    private int weight;

    public Light(int id) {
        this.id = id;
        System.out.println("A light has been created. Its ID is "+this.id+".");
    }

    public void light(){}
    public void dim(){}
    public void changeColor(){}

}
class Mic{
    private String brand;
    private int id;
    private String shapeOfHead;
    private int length;
    private int width;
    private int height;
    private int weight;
    private int powerLife;
    private boolean hasUSB;

    public Mic(int id) {
        this.id = id;
        System.out.println("A mic has been created. Its ID is "+this.id+".");
    }

    public void mute(){}
    public void adjustVolume(){}
    public void turnOn(){}
    public void shutDown(){}



}
class Cellphone{
    private Long IMEI;
    private String brand;
    private int weight;
    private int length;
    private int width;
    private int height;
    private String color;
    private int screenSize;

    public Cellphone(Long IMEI, String brand) {
        this.IMEI = IMEI;
        this.brand = brand;
        System.out.println("A "+this.brand+" cellphone has been created. Its IMEI is "+this.IMEI+".");
    }

    public void call(){}
    public void message(){}
    public void takePhoto(){}

    public String getBrand() {
        return brand;
    }

    public class SimCard{
        private String carrierName;
        private String phoneNumber;

        public SimCard(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            System.out.println("A sim card has been created. Its phone number is "+this.phoneNumber+".");

        }

        public void activate(){}
        public void deactivate(){}

        public void install(String CellphoneBrand){
            System.out.println("This SIM card has been installed in the "+CellphoneBrand+" Cellphone.");
        }
    }

}

public class Main {

    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("Apple");
        Laptop laptop2 = new Laptop("Banana");
        Laptop laptop3 = new Laptop("Cherry");
        System.out.println();

        Laptop.Battery battery1 = laptop1.new Battery(laptop1.getBrand());
        Laptop.Battery battery2 = laptop2.new Battery(laptop2.getBrand());
        Laptop.Battery battery3 = laptop3.new Battery(laptop3.getBrand());
        System.out.println();

        Desk desk1 = new Desk(1);
        Desk desk2 = new Desk(2);
        Desk desk3 = new Desk(3);
        System.out.println();

        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");
        Student student3 = new Student("Carol");
        System.out.println();

        Chair chair1 = new Chair(1);
        Chair chair2 = new Chair(2);
        Chair chair3 = new Chair(3);
        System.out.println();


        WaterBottle wb1 = new WaterBottle("Red");
        WaterBottle wb2 = new WaterBottle("Blue");
        WaterBottle wb3 = new WaterBottle("Green");
        System.out.println();

        Camera camera1 = new Camera(1);
        Camera camera2 = new Camera(2);
        Camera camera3 = new Camera(3);
        System.out.println();

        DisplayScreen displayScreen1 = new DisplayScreen(1);
        DisplayScreen displayScreen2 = new DisplayScreen(2);
        DisplayScreen displayScreen3 = new DisplayScreen(3);
        System.out.println();

        Light light1 = new Light(1);
        Light light2 = new Light(2);
        Light light3 = new Light(3);
        System.out.println();

        Mic mic1 = new Mic(1);
        Mic mic2 = new Mic(2);
        Mic mic3 = new Mic(3);
        System.out.println();

        Cellphone cellphone1 = new Cellphone(123456789L, "Apple");
        Cellphone cellphone2 = new Cellphone(9876543210L,"Banana");
        Cellphone cellphone3 = new Cellphone(2468101214L,"Cherry");
        System.out.println();

        Cellphone.SimCard simCard1 = cellphone1.new SimCard("111-222-333");
        simCard1.install(cellphone1.getBrand());
        Cellphone.SimCard simCard2 = cellphone2.new SimCard("123-234-456");
        simCard2.install(cellphone2.getBrand());
        Cellphone.SimCard simCard3 = cellphone1.new SimCard("234-567-789");
        simCard2.install(cellphone3.getBrand());
        System.out.println();



    }
}