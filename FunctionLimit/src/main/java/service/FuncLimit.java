package service;

import exception.InvalidArgumentException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class FuncLimit implements Solvable {

    private static final int CACHING_SIZE_LIMIT = 10000;
    private final List<BigDecimal> cachedFactorials;
    private boolean isCaching = true;

    public FuncLimit() {
        this.cachedFactorials = new ArrayList<>();
        this.cachedFactorials.add(BigDecimal.ONE);
    }

    @Override
    public BigDecimal solve(int n) {
        if (n < 2)
            throw new InvalidArgumentException();
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal factorial = BigDecimal.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = isCaching ? getFactorial(i) : factorial.multiply(BigDecimal.valueOf(i));
            sum = sum.add(factorial);
        }
        return BigDecimal.ONE.divide(isCaching ? getFactorial(n) : factorial, MathContext.DECIMAL64).multiply(sum);
    }

    private BigDecimal getFactorial(int n) {
        try {
            return cachedFactorials.get(n - 1);
        } catch (IndexOutOfBoundsException e) {
            BigDecimal nextFactorial = cachedFactorials.get(n - 2).multiply(BigDecimal.valueOf(n));
            cacheFactorial(nextFactorial);
            return cachedFactorials.get(n - 1);
        }
    }

    private void cacheFactorial(BigDecimal next) {
        if (isCaching)
            cachedFactorials.add(next);
        if (cachedFactorials.size() >= CACHING_SIZE_LIMIT)
            isCaching = false;
    }
}
