import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class AppendSerilizationExample {
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
            AppendObjectOutputStream writer=new AppendObjectOutputStream(new FileOutputStream(file,true));
            writer.writeObject(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static public void readData() {
        System.out.println();
        try {
            FileInputStream file=new FileInputStream("D:\\append.ser");
            AppendObjectInputStream reader=new AppendObjectInputStream(file);
            while (file.available()!=0){
                Student s = (Student) reader.readObject();
                System.out.println("Id :" + s.id + " Name :" + s.name);
            }
            reader.close(); file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static public void searchData(){
        System.out.println();
        System.out.print("Enter id to search :"); int id=input.nextInt();
        boolean found=false;
        try {
            FileInputStream file=new FileInputStream("D:\\append.ser");
            AppendObjectInputStream reader=new AppendObjectInputStream(file);
            while (file.available()!=0){
                Student s = (Student) reader.readObject();
                if(s.id==id) {
                    System.out.println("Id :" + s.id + " Name :" + s.name);
                    found=true;
                }
            }
            reader.close(); file.close();
            if(!found)
                System.out.println("Student Data not found..");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        static public void deleteData() {
            System.out.println();
            System.out.print("Enter id to delete :");
            int id = input.nextInt();
            boolean found = false;
            try {
                FileInputStream file = new FileInputStream("D:\\append.ser");
                AppendObjectInputStream reader = new AppendObjectInputStream(file);
                AppendObjectOutputStream temp = new AppendObjectOutputStream(new FileOutputStream("D:\\temp.ser", true));
                Student s;
                while (file.available() != 0) {
                    s = (Student) reader.readObject();
                    if (s.id == id) {
                        System.out.println("Id :" + s.id + " Name :" + s.name);
                        found = true;
                    } else {
                        temp.writeObject(s);
                    }
                }
                if (found) System.out.println("Student data succesfully deleted...");
                if (!found)
                    System.out.println("Student Data not found..");
                reader.close();
                temp.close();
                file.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            changeFileName();
        }
        static public void changeFileName(){
        //deleting..
            File file=new File("D:\\append.ser");
            file.delete();
            //renaming
            File file1 = new File("D:\\temp.ser");
            File newFile = new File("D:\\append.ser");
          file1.renameTo(newFile);
        }
}
