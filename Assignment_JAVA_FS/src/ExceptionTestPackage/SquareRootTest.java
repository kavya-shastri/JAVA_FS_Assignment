package ExceptionTestPackage;

public class SquareRootTest {

    private static class SquareRootCalculator {
        private final int input;

        public SquareRootCalculator(int input) {
            if(input < 0)
                throw new ArithmeticException("SquareRootCalculator class cannot take negative input.");
            this.input = input;
        }

        public double getSquareRoot() {
            return Math.sqrt(input);
        }
    }

    public static void main(String[] args) {
        try {
            int input = -1;
            SquareRootCalculator calc =  new SquareRootCalculator(input);
            System.out.println("Square root of " + input + " is " + calc.getSquareRoot());
        } catch(ArithmeticException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
