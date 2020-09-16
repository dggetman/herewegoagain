package functions;

public class AbsFunction implements MathFunction {

    public AbsFunction(double x) {
    }

    @Override
    public AbsFunction apply(double x) {
        return new AbsFunction (Math.abs(x));
    }
}
