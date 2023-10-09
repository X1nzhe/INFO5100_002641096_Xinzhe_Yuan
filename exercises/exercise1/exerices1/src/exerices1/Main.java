package exerices1;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author Xinzhe Yuan ON 7/10/2023.
 * @project INFO5100-Exerices1
 **/

class Student{

    private final String name;
    private final int id;
    private final int[] quizzesScores;
    protected int[] examsScores;
    private final boolean isFullTime;
    static private final int numQuizzes = 15;

    public Student(String name, int id, boolean isFullTime){
        this.name = name;
        this.id = id;
        this.quizzesScores = new int[numQuizzes];
        this.isFullTime = isFullTime;

    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public double getTotalQuizScores(){
        return Arrays.stream(this.quizzesScores).sum();
    }
    public int getQuizScore(int index) {
        return this.quizzesScores[index];
    }
    public int[] getExamsScores(){
        return this.examsScores;
    }
    public boolean isFullTime(){
        return this.isFullTime;
    }
    public void setQuizzesScores(){
        for(int i = 0;i<numQuizzes;i++){
            this.quizzesScores[i] = (int) (Math.random()*99+1 );
        }
    }
    public void setExamsScores(int numExams){
        for (int i=0; i<numExams; i++){
            this.examsScores[i] = (int)(Math.random()*99+1);
        }
    }
}
class FullTimeStudent extends Student{

    public FullTimeStudent(String name, int id, boolean isFullTime){
        super(name,id, isFullTime);
        int numExams = 4;
        super.examsScores = new int[numExams];
        super.setExamsScores(numExams);
        super.setQuizzesScores();
    }
}

class PartTimeStudent extends Student{
    private int numExams = 2;
    public PartTimeStudent(String name, int id,boolean isFullTime){
        super(name,id, isFullTime);
        super.examsScores = new int[numExams];
        super.setExamsScores(numExams);
        super.setQuizzesScores();
    }
}

class Session{
    //ArrayList<Student> students= new ArrayList<>{}
    private int numStudents;
    static private int maxNumStudents = 20;
    private Student[] students = new Student[maxNumStudents];

    public void addStudent(Student student) {
       if(numStudents < maxNumStudents){
           students[numStudents] = student;
           numStudents++;
       }else{
           System.out.println("Current session is full. Couldn't add students.");
       }
    }
    public int getNumStudents() {
        return this.numStudents;
    }

    public double calAveQuizScores(){
        double totalQuizScores = 0;
        int totalStudents = 0;
        for (Student student:students){
            double aveScore = student.getTotalQuizScores()/15;
            DecimalFormat format = new DecimalFormat("#.00");
            String str = format.format(aveScore);
            double formatAveScore = Double.parseDouble(str);
            System.out.println("Student name: "+student.getName()+" Quiz average scores: "+formatAveScore);
            totalQuizScores += student.getTotalQuizScores();
            totalStudents += 1;
        }
        return totalQuizScores / totalStudents;
    }

    public void printQuizScoresAscending(){
        int numQuizzes = 15;

        for (int quizNum = 0; quizNum < numQuizzes; quizNum++){
            //ArrayList<Integer> currQuizScores = new ArrayList<>();
            Map<Integer, Integer> currQuizScores = new HashMap<>();
            for(Student student: students){
                //currQuizScores.add(student.getQuizScore(quizNum));
                currQuizScores.put(student.getId(),student.getQuizScore(quizNum));
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer,Integer> entry: currQuizScores.entrySet()){
                list.add(entry.getValue());
            }
            Collections.sort(list);
            LinkedHashMap<Integer,Integer> sortedMap = new LinkedHashMap<>();
            for (int num:list){
                for(Map.Entry<Integer, Integer> entry: currQuizScores.entrySet()){
                    if(entry.getValue().equals(num)){
                        sortedMap.put(entry.getKey(),num);
                    }
                }
            }
            System.out.println("Quiz No."+quizNum);
            for (Integer id: sortedMap.keySet()){
                System.out.println("Student ID "+id+", Score "+sortedMap.get(id)+";");
            }
        }
        }
        public void printNamesOfPartTimeStudents(){
        System.out.println("Name list of part-time students");
        for(Student student: students){
            if (!student.isFullTime()){
                System.out.println(student.getName());
            }
        }
        }
        public void printScoresOfFullTimeStudents(){
        System.out.println("Scores of full-time students");
        for(Student student:students){
            if(student.isFullTime()){
                System.out.println("Student Id "+student.getId());
                //System.out.println(student.getExamsScores());
                for(int score:student.getExamsScores()){
                    System.out.print(score+" ");
                }
                System.out.println();
            }
        }
        }
    }



public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        System.out.println("A new session has been created...");
        for(int i = 0; i<20;i++){
            int random = (int) (Math.random()*20);
            System.out.println(session.getNumStudents());

            if(random%2==0){
                FullTimeStudent ftStudent = new FullTimeStudent(EnglishNames.nameList[random],i,true);
                session.addStudent(ftStudent);
            }else{
                PartTimeStudent ptStudent = new PartTimeStudent(EnglishNames.nameList[random],i,false);
                session.addStudent(ptStudent);
            }
        }
        System.out.println("20 students has been add...");

        //Calculate average quiz scores per student for the whole class
        System.out.println("The average quiz scores per student for the whole class is "+session.calAveQuizScores());

        //Print the list of quiz scores in ascending order for one session
        System.out.println("Printing the list of quiz scores in ascending order for one session...");
        session.printQuizScoresAscending();
        //Print names of part-time students
        System.out.println("Printing names of part-time students...");
        session.printNamesOfPartTimeStudents();
        //Print exams scores of full-time students
        session.printScoresOfFullTimeStudents();


    }
}