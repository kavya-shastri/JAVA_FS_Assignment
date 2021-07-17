package SerializationTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EmployeeTest {

    private static class Employee implements Serializable {
        private final String name;
        private final int age;
        private final String sex;

        private static final long serialVersionUID = 1L;

        public Employee(String name, int age, String sex) {
            this.age = age;
            this.name = name;
            this.sex = sex;
        }

        private void serialize(File outputFile) {
            FileOutputStream fileStream = null;
            ObjectOutputStream stream = null;
            try {
                try {
                    fileStream = new FileOutputStream(outputFile);
                    stream = new ObjectOutputStream(fileStream);
                    stream.writeObject(this);
                } finally {
                    if(fileStream!=null)
                        fileStream.close();
                    if(stream!=null)
                        stream.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println(outputFile + " does not exist.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Exception while writing Employee object");
                e.printStackTrace();
            }
        }

        private Employee deserialize(File inputFile) {
            FileInputStream inputStream = null;
            ObjectInputStream stream = null;
            Employee retValue = null;
            try {
                try {
                    inputStream = new FileInputStream(inputFile);
                    stream = new ObjectInputStream(inputStream);
                    try {
                        retValue = (Employee)stream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } finally {
                    if(inputStream!=null)
                        inputStream.close();
                    if(stream!=null)
                        stream.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println(inputFile + " does not exist.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Exception while writing Employee object");
                e.printStackTrace();
            }
            return retValue;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Employee Name : ");
            sb.append(this.name);
            sb.append("\n");
            sb.append("Employee Sex : ");
            sb.append(this.sex);
            sb.append("\n");
            sb.append("Employee Age : ");
            sb.append(this.age);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Name1", 25, "female");
        System.out.println("Serialized object : ");
        System.out.println(emp.toString());
        File file = new File(System.getProperty("user.dir") + "empInfo.txt");
        emp.serialize(file);

        System.out.println("Deserialised object : ");
        System.out.println(emp.deserialize(file).toString());
    }
    
}
