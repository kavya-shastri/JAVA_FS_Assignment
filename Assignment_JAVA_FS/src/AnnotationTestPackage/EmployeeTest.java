package AnnotationTestPackage;
public class EmployeeTest {

    //Can be used here since the element type is defined as type.
    @AuthorAnnotation(emailId = "default@email.com", name = "defaultAuthorName", empType = "EmployeeClass")
    private static class Employee {
        private final int id;
        private final String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Deprecated
        public void printEmployee() {
            StringBuilder sb = new StringBuilder();
            sb.append("id : ");
            sb.append(this.id);
            sb.append("\n");
            sb.append("name : ");
            sb.append(this.name);
            System.out.println(sb.toString());
        }

        //fields with default values do not need to be initialised.
        //Also observed that if the elementType is not defined for method, we cannot use annotations here.
        @AuthorAnnotation(emailId = "overridden@email.com", name = "methodAuthorName")
        public void methodAnnotationTest() {
            System.out.println("Annotated method called");
        }
    }

    public static void main(String[] args) {
        Employee emp = new Employee(1, "Test1");
        // Depracated usually means the function is available but will not be enhanced
        // or could be removed from future versions of a class.
        emp.printEmployee();

        // Test to print the annotation values at class level. 
        //Retention type should be defined as runtime so that jvm has access to annotations during runtime
        AuthorAnnotation authorAnnotation = emp.getClass().getAnnotation(AuthorAnnotation.class);
        System.out.println("emailId : " + authorAnnotation.emailId());
        System.out.println("name : " + authorAnnotation.name());
        System.out.println("empType : " + authorAnnotation.empType());

        //Test to print the annotated values at method level.
        try {
            authorAnnotation = emp.getClass().getMethod("methodAnnotationTest").getAnnotation(AuthorAnnotation.class);
            System.out.println("emailId : "
                    + authorAnnotation.emailId());
            System.out.println("name : "
                    + authorAnnotation.name());
            System.out.println("empType : "
                    + authorAnnotation.empType());
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
