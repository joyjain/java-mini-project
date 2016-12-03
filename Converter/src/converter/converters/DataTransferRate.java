package converter.converters;

/**
 *
 * d = new DataTransferRate(3, 2, 1, 0, 1); d.convert();
 */
public class DataTransferRate {

    /**
     * "Bit per second", "Kilobit per second", "Megabit per second", "Gigabit
     * per second", "Terabit per second", "Byte per second", "Kilobyte per
     * second", "Megabyte per second", "Gigabyte per second", "Terabyte per
     * second"
     */
    private int from, to, type1, type2;
    private final double units[][] = {
        {0, 3, 6, 9, 12},
        {0, 3, 6, 9, 12}
    };
    private double input, output;

    public DataTransferRate(int from, int to, int type1, int type2, double input) {
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
        return 0.125 * input;
    }

    private double bytesToBits(double input) {
        return 8 * input;
    }
}
