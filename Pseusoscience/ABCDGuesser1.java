import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Program approximates a user-generated mathematical constant using four
 * user-generated numbers and jager's exponents.
 *
 * @author Om Patil
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        boolean run = true;
        double positive = 0;

        while (run) {
            out.println("\nPlease enter a double value:");
            String number = in.nextLine();

            if (FormatChecker.canParseDouble(number)) {
                positive = Double.parseDouble(number);

                if (positive > 0) {
                    run = false;
                }
            }
        }
        return positive;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        boolean run = true;
        double positive = 0;

        while (run) {
            out.println(
                    "\nPlease enter a double value that is not equal to 1.0:");
            String number = in.nextLine();

            if (FormatChecker.canParseDouble(number)) {
                positive = Double.parseDouble(number);

                if (positive > 0 && positive != 1.0) {
                    run = false;
                }
            }
        }
        return positive;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        //create input and output streams
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Declarations
        double mu, w, x, y, z, error, estimate, bestEstimate, finalA = 0,
                finalB = 0, finalC = 0, finalD = 0;
        int a = 0, b = 0, c = 0, d = 0;

        // ask user for a mathematical constant mu
        out.println(
                "First, I need the mathematical constant you would like to calcluate:");
        mu = getPositiveDouble(in, out);

        // establish four numbers which are personal to the user
        out.println(
                "\nNext, I will utilize 4 positive numbers that are personal to you.");
        out.println("These numbers cannot be equal to one.");

        w = getPositiveDoubleNotOne(in, out);
        x = getPositiveDoubleNotOne(in, out);
        y = getPositiveDoubleNotOne(in, out);
        z = getPositiveDoubleNotOne(in, out);

        // declare and initialize array of the 17 jager's exponents.
        final double[] jager = { -5, -4, -3, -2, -1, -1 / 2.0, -1 / 3.0,
                -1 / 4.0, 0, 1 / 4.0, 1 / 3.0, 1 / 2.0, 1, 2, 3, 4, 5 };

        // estimate the mathematical constant using jager's exponents

        // initialize least difference to be difference for first set of exponents
        bestEstimate = ((Math.pow(w, jager[0])) * (Math.pow(x, jager[0]))
                * (Math.pow(y, jager[0])) * (Math.pow(z, jager[0])));

        // loop through the exponents and calculate the estimate for each set of exponents
        while (d < jager.length) {
            while (c < jager.length) {
                while (b < jager.length) {
                    while (a < jager.length) {
                        estimate = ((Math.pow(w, jager[a]))
                                * (Math.pow(x, jager[b]))
                                * (Math.pow(y, jager[c]))
                                * (Math.pow(z, jager[d])));
                        //out.println(estimate);

                        // check for least error, and update values accordingly
                        if (Math.abs(estimate - mu) < Math
                                .abs(bestEstimate - mu)) {
                            bestEstimate = estimate;

                            finalA = jager[a];
                            finalB = jager[b];
                            finalC = jager[c];
                            finalD = jager[d];

                        }
                        // increment exponent index values after calculations
                        a++;
                    }
                    // reset exponent indices find all combinations of exponents
                    b++;
                    a = 0;
                }
                c++;
                b = 0;
            }
            d++;
            c = 0;
        }

        // calculate error
        error = Math.abs(((bestEstimate - mu) / mu) * 100);
        // print calculations
        out.println(
                "\nThe combination of exponents that minimizes the estimate of mu are: ");
        out.println(finalA);
        out.println(finalB);
        out.println(finalC);
        out.println(finalD);

        out.println("\nThe error is approximately: ");

        out.print(error, 2, false);
        out.println(" percent.");
        out.println("\nThe final approximation is: " + bestEstimate);

    }
    // main end

}
// class end
