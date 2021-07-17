package ExceptionTestPackage;

import java.util.Calendar;
import java.util.Formatter;

import javax.naming.TimeLimitExceededException;

public class BakePizzaTest {
    private static class PizzaBaker {
        private static final long MAX_DURATION = 300000; //5 mins
        private OVEN_STATE ovenState;
        private enum OVEN_STATE{
            ON,
            OFF;
        } 

        public void bakePizza(long duration) {
            try {
                turnOnOven(duration);
            } catch (TimeLimitExceededException e) {
                e.printStackTrace();
            }
            finally {
                turnOffOven();
            }
        }

        private static String getCurrentTime() {
            Formatter formate = new Formatter();
            Calendar calender = Calendar.getInstance();
            formate.format("%tl:%tM:%tS", calender,
                        calender, calender);
            String retValue = formate.toString();
            formate.close();
            return retValue;
        }

        private long timer(long timeOut) {
            long maxTimeOut = MAX_DURATION;
            try {
                Thread.sleep(Math.min(timeOut, maxTimeOut));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return maxTimeOut-timeOut;
        }

        public void turnOnOven(long duration) throws TimeLimitExceededException{
            this.ovenState = OVEN_STATE.ON;
            System.out.println("Oven turned on at : " + getCurrentTime());
            long timeoutInMs = duration*60*1000;
            long diff = timer(timeoutInMs);
            if(diff < 0)
                throw new TimeLimitExceededException("TIMEOUT : Time out for the baker. Pizza is not baked.");
        }

        public void turnOffOven() {
            this.ovenState = OVEN_STATE.OFF;
            System.out.println("Oven turned off at : " + getCurrentTime());
        }

        public OVEN_STATE getOvenState() {
            return this.ovenState;
        }
    }

    public static void main(String[] args) {
        PizzaBaker baker = new PizzaBaker();
        baker.bakePizza(6);
    }
    
}
