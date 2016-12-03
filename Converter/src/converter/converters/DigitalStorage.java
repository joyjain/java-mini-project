package converter.converters;

/**
 *
 * convert d = new DigitalStorage(3, 2, 0, 1, 1); d.convert();
 */
public class DigitalStorage {

    /**
     * "Bit", "Kilobit", "Megabit", "Gigabit", "Terabit", "Petabit", "Byte",
     * "Kilobyte", "Megabyte", "Gigabyte", "Terabyte", "Petabyte"
     */
    private int from, to, type1, type2;
    private final double units[][] = {
        {0, 3, 6, 9, 12, 15},
        {0, 3, 6, 9, 12, 15}
    };
    private double input, output;

    public DigitalStorage(int from, int to, int type1, int type2, double input) {
        this.from = from;
        this.to = to;
        this.type1 = type1;
        this.type2 = type2;
        this.input = input;
    }

    public double convert() {
        if (type1 == type2) {
            if (type1 == 0) {
                output = convertBits(from, to);
            } else {
                output = convertBytes(from, to);
            }
        } else if (type1 == 0 && type2 == 1) {
            input = bitsToBytes(convertBits(from, 0));
            output = convertBytes(0, to, input);
        } else {
            input = bytesToBits(convertBytes(from, 0));
            output = convertBits(0, to, input);
        }
        return output;
    }

    public double convertBits(int from, int to) {
        return (Math.pow(10, units[0][from]) / Math.pow(10, units[0][to])) * input;
    }

    public double convertBytes(int from, int to) {
        return (Math.pow(10, units[1][from]) / Math.pow(10, units[1][to])) * input;
    }

    public double convertBits(int from, int to, double input) {
        double output;
        if (from < to) {
            output = (Math.pow(10, units[0][from]) / Math.pow(10, units[0][to])) * input;
        } else {
            output = (Math.pow(10, units[0][to]) / Math.pow(10, units[0][from])) * input;
        }
        return output;
    }

    public double convertBytes(int from, int to, double input) {
        double output;
        if (from < to) {
            output = (Math.pow(10, units[1][from]) / Math.pow(10, units[1][to])) * input;
        } else {
            output = (Math.pow(10, units[1][to]) / Math.pow(10, units[1][from])) * input;
        }
        return output;
    }

    private double bitsToBytes(double input) {
        return ((Math.pow(2, -3)) * input);
    }

    private double bytesToBits(double input) {
        return ((Math.pow(2, 3)) * input);
    }
}
