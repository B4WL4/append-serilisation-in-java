import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//get directory
//String currentDir = System.getProperty("user.dir");
// System.out.println("Current dir using System:" + currentDir);
// Files.deleteIfExists(paths.get(filename));

public class SerArraylistExample {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        addData();
        addData();
        readData();
        searchData();
        deleteData();
        readData();
    }
    static public void addData(){
        System.out.println();
        System.out.print("Enter id and name: ");
        Student s=new Student(input.nextInt(),input.next());
        File file=new File("D:\\append.ser");
        try {
            if(file.length()==0) {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                ArrayList<Student> studentList=new ArrayList<>();
                studentList.add(s);
                out.writeObject(studentList);
                out.close();
            }else {
                ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
                ArrayList<Student> studentList=(ArrayList<Student>)in.readObject();
                in.close();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                studentList.add(s);
                out.writeObject(studentList);
                out.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static public void readData() {
        System.out.println();
        boolean check=false;
        int i=1;
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("D:\\append.ser"));
            ArrayList<Student> List=(ArrayList<Student>)in.readObject();
            in.close();
            //parked vehicles data
            for (Student s:List) {
                System.out.println("Student "+i);
                System.out.println("Id :" + s.id + " Name :" + s.name);
                i++;
                check=true;
            }
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(!check)
            System.out.println("NO RECORD Found.....");
    }
    static public void searchData(){
        System.out.println();
        System.out.print("Enter id to search :"); int id=input.nextInt();
        boolean check=false;
        int i=1;
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("D:\\append.ser"));
            ArrayList<Student> List=(ArrayList<Student>)in.readObject();
            in.close();
            //parked vehicles data
            for (Student s:List) {
                if(s.id==id){
                System.out.println("Student searched data");
                System.out.println("Id :" + s.id + " Name :" + s.name);
                i++;
                check=true;}
            }
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(!check)
            System.out.println("NO RECORD Found.....");
    }
    static public void deleteData() {
        System.out.println();
        System.out.print("Enter id to delete :"); int id=input.nextInt();
        boolean check=false;
        int i=1;
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("D:\\append.ser"));
            ArrayList<Student> List=(ArrayList<Student>)in.readObject();
            ArrayList<Student> temp=new ArrayList<>();
            in.close();
            //parked vehicles data
            for (Student s:List) {
                if(s.id==id){
                    System.out.println("Student data deleting..");
                    System.out.println("Id :" + s.id + " Name :" + s.name);
                    i++;
                    check=true;
                }
              else temp.add(s);
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\append.ser"));
            out.writeObject(temp);
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(!check)
            System.out.println("NO RECORD Found.....");
    }
}
