import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Student{
    private String id,name;
    private int marks;

    public Student(String id, String name,int marks){
        //binds the local variables to the instance variables
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }
    public int getMarks(){
        return marks;
    }

    public String getRole(){
        return "undergrad";
    }

    //annotations are used to provide metadata for the code and to indicate that a method is overriding a superclass method
    @Override
    public String toString(){
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks+ ", " + getRole();
    }
}

class GraduateStudent extends Student{
    private String area;
    public GraduateStudent(String id,String name, int marks,String area){
        super(id,name,marks);
        this.area = area;
    }

    @Override
    public String getRole(){
        return "Grad("+area+")";
    }
}

//new subclass class for teacher who can retrieve marks from student and a new method for creating a report(student id, name , marks)
class Teacher{
    private String id, name;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Teacher retrieves marks from a Student object
    public String generateReport(Student s){
        return "Report - Student ID: " + s.getId() +
               ", Name: " + s.getName() +
               ", Marks: " + s.getMarks();
    }

    @Override
    public String toString() {
        return "Teacher: " + id + ", Name: " + name;
    }
}

//We will use generics to create a repository class that can store any type of object
//we use T as a placeholder for the type inorder to do seamless storage and retrieval of objects
//gives bigger umbrella to search for different types of objects
class Repository<T>{
    private Map<String,T>data= new HashMap<>();
    public void save (String id, T obj){
        data.put(id,obj);
    }

    public T find(String id){
        return data.get(id);
    }

    public void delete(String id){
        data.remove(id);
    }
}

public class Day3 {
    public static void main(String[] args) {
        List<Student>list= new ArrayList<>();
        list.add(new Student("S1","Supriya",85));
        list.add(new Student("S2","Amit",90));
        list.add(new Student("S3","Rohan",78));

        list.add(new GraduateStudent("G1","Neha",88,"Computer Science"));
        list.add(new GraduateStudent("G2","Vikram",92,"Electrical Engineering"));
        list.add(new GraduateStudent("G3","Anita",80,"Mechanical Engineering"));

        List<Teacher> teachers= new ArrayList<>();
        teachers.add(new Teacher("T1","Dr. Sharma"));
        teachers.add(new Teacher("T2","Prof. Gupta"));
        
        Repository<Student>repo = new Repository<>();
        for(Student s:list){
            repo.save(s.getId(),s);
        }

        System.out.println("ALL");
        list.forEach(System.out::println);

        System.out.println("\nLOOKUO S2: ");
        Student s= repo.find("S2");
        System.out.println(s!=null ? s : "Not Found");

        Iterator<Student> it = list.iterator();
        while(it.hasNext()){
            Student st= it.next();
            if(st.getMarks()<90){
                it.remove();
                repo.delete(st.getId());
            }
        }
        System.out.println("\nAfter removing students with marks <90:");
        list.forEach(System.out::println);

        System.out.println("\nTeacher Reports:");
        for (Teacher t : teachers) {
            for (Student stu : list) {
                System.out.println(t.generateReport(stu));
            }
        }

    }
}

