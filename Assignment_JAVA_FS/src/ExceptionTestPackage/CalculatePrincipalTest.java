package ExceptionTestPackage;

public class CalculatePrincipalTest {

    private static class PrincipalAmountCalculator {
        private final double interest;
        private final int time;
        private final double rate;

        public PrincipalAmountCalculator(double interest, int time, double rate)
                throws InvalidTimeInputException, InvalidRateInputException {
            if (time == 0) {
                throw new InvalidTimeInputException("Invalid input for time : " + time);
            } else if (Double.compare(rate, 0.0) == 0) {
                throw new InvalidRateInputException("Invalid input for rate : " + rate);
            }
            this.interest = interest;
            this.time = time;
            this.rate = rate;
        }

        public double getPrincipalAmount() {
            return (interest * 100) / (time * rate);
        }
    }

    private static class InvalidTimeInputException extends Exception {
        public InvalidTimeInputException(String errMessage) {
            super(errMessage);
        }
    }

    private static class InvalidRateInputException extends Exception {
        public InvalidRateInputException(String errMessage) {
            super(errMessage);
        }
    }

    public static void main(String[] args) {
        try {
            PrincipalAmountCalculator calc = new PrincipalAmountCalculator(0.0, 2, 0.0);
            System.out.println(calc.getPrincipalAmount());
        } catch (InvalidTimeInputException | InvalidRateInputException e) {
            e.printStackTrace();
        }
    }
}
