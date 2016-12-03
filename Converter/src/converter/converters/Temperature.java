package converter.converters;

/**
 *
 * convert t = new Temperature(2, 1, 256); t.convert();
 */
public class Temperature {

    /**
     * "Celsius", "Fahrenheit", "Kelvin"
     */
    private int from, to;
    private double input, output;

    public Temperature(int from, int to, double input) {
        this.from = from;
        this.to = to;
        this.input = input;
    }

    public double convert() {
        if (from == 0 && to == 1) {
            output = ((input * 9) / 5) + (32.00);
        } else if (from == 0 && to == 2) {
            output = (input + 273.15);
        } else if (from == 1 && to == 0) {
            output = ((input - 32.00) * 5) / (9);
        } else if (from == 1 && to == 2) {
            input = ((input - 32.00) * 5) / (9);
            output = (input + 273.15);
        } else if (from == 2 && to == 0) {
            output = (input - 273.15);
        } else if (from == 2 && to == 1) {
            input = (input - 273.15);
            output = ((input * 9) / 5) + (32.00);
        }
        return output;
    }
}
