package ExternalizationTest;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.StringJoiner;

public class ExternalizationTest {

    private static class Employee implements Externalizable {
        private String name;
        private int age;
        private String sex;
        private static String companyName = "XYZ";

        //Need to define default constructor to 
        public Employee() {

        }

        public Employee(String name, int age, String sex) {
            this.age = age;
            this.name = name;
            this.sex = sex;
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
            sb.append("\n");
            sb.append("Employee Company name : ");
            sb.append(this.companyName);
            return sb.toString();
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            StringJoiner sj = new StringJoiner(",");
            sj.add(this.name);
            sj.add(this.age + "");
            sj.add(this.sex);
            sj.add(this.companyName);
            out.writeObject(sj.toString());
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            String[] strArray = ((String)in.readObject()).split(",");
            this.name = strArray[0];
            this.age = Integer.parseInt(strArray[1]);
            this.sex = strArray[2];
            this.companyName = strArray[3];
        }

        public void externalise(File outputFile) {
            FileOutputStream fileStream = null;
            ObjectOutputStream stream = null;
            try {
                try {
                    fileStream = new FileOutputStream(outputFile);
                    stream = new ObjectOutputStream(fileStream);
                    stream.writeObject(this);
                    stream.flush();
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

        private Employee internalise(File inputFile) {
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
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Name1", 25, "female");
        System.out.println("Extern object : ");
        System.out.println(emp.toString());
        File file = new File(System.getProperty("user.dir") + "empInfoExt.txt");
        emp.externalise(file);

        System.out.println("Read object : ");
        System.out.println(emp.internalise(file).toString());
    }
}
