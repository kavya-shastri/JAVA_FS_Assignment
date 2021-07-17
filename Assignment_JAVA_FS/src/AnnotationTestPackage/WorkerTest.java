package AnnotationTestPackage;
public class WorkerTest {
    private static class Worker {
        private int id;
        private String name;

        public Worker (int id, String name){
            this.id = id;
            this.name = name;
        }

        //Throws an exception if trying to change the name with override annotation.
        //Also works only when the overridden function name is same as that of the parent class.
        //Without overriding this, toString calls the object class's default implementation and prints the classname with the hashcode.
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("id : ");
            sb.append(this.id);
            sb.append("\n");
            sb.append("name : ");
            sb.append(this.name);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker(1, "Name1");
        System.out.println(worker.toString());
    }
}
