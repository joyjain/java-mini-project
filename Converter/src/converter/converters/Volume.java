package converter.converters;

/**
 * v = new Volume(1, 0, 0, 1, 1); v.convert();
 */
public class Volume {

    /**
     * "Millilitre", "Litre", "Cubic metre", "Cubic inch", "Cubic foot"
     */
    private int from, to, type1, type2;
    private final double units[][] = {
        {0, 3, 6},
        {1, 1728}
    };
    private double input, output;

    public Volume(int from, int to, int type1, int type2, double input) {
        this.from = from;
        this.to = to;
        this.type1 = type1;
        this.type2 = type2;
        this.input = input;
    }

    public double convert() {
        if (type1 == type2) {
            if (type1 == 0) {
                output = convertImperial(from, to);
            } else {
                output = convertUS(from, to);
            }
        } else if (type1 == 0 && type2 == 1) {
            input = imperialToUS(convertImperial(from, 0));
            output = convertUS(0, to, input);
        } else {
            input = USToImperial(convertUS(from, 0));
            output = convertImperial(0, to, input);
        }
        return output;
    }

    public double convertImperial(int from, int to) {
        return (Math.pow(10, units[0][from]) / Math.pow(10, units[0][to])) * input;
    }

    public double convertUS(int from, int to) {
        return (units[1][from] / units[1][to]) * input;
    }

    public double convertImperial(int from, int to, double input) {
        double output;
        if (from < to) {
            output = (Math.pow(10, units[0][from]) / Math.pow(10, units[0][to])) * input;
        } else {
            output = (Math.pow(10, units[0][to]) / Math.pow(10, units[0][from])) * input;
        }
        return output;
    }

    public double convertUS(int from, int to, double input) {
        double output;
        if (from < to) {
            output = (units[1][from] / units[1][to]) * input;
        } else {
            output = (units[1][to] / units[1][from]) * input;
        }
        return output;
    }

    private double imperialToUS(double input) {
        return (0.061023 * input);
    }

    private double USToImperial(double input) {
        return (16.387052 * input);
    }
}
