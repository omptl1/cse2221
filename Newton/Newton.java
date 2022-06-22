import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Om Patil
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        final double precision = 0.01;

        while (Math.abs((r * r) - x) / x < (precision * precision)) {
            r = (r + (x / r)) / 2;
        }

        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println(
                "Enter the value of the positive double you would like to compute the root of: ");
        double number = in.nextDouble();

        out.println(
                "The square of the positive double you entered (to relative error of 0.01%) is: "
                        + sqrt(number));

        in.close();
        out.close();
    }

}
