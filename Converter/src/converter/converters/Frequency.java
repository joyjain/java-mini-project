package converter.converters;

/**
 *
 * convert f = new Frequency(3, 0, 1); f.convert();
 */
public class Frequency {

    /**
     * "Hertz", "Kilohertz", "Megahertz", "Gigahertz"
     */
    private int from, to;
    private final double units[] = {0, 3, 6, 9};
    private double input, output;

    public Frequency(int from, int to, double input) {
        this.from = from;
        this.to = to;
        this.input = input;
    }

    public double convert() {
        if (from < to) {
            output = (Math.pow(10, units[to]) / Math.pow(10, units[from])) * input;
        } else {
            output = (Math.pow(10, units[from]) / Math.pow(10, units[to])) * input;
        }
        return output;
    }
}
