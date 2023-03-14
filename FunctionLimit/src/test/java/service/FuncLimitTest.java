package service;

import exception.InvalidArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class FuncLimitTest {

    private final Solvable func = new FuncLimit();

    @Test
    @DisplayName("Reduced calculation time when factorials cached")
    void solve_WhenFactorialsCached() {
        assertTimeout(Duration.ofMillis(50), () -> func.solve(1000));
        assertTimeout(Duration.ofMillis(10), () -> func.solve(1000));
    }

    @Test
    @DisplayName("Catch invalid argument exception")
    void solve_WhenArgumentInvalid() {
        assertThrows(InvalidArgumentException.class, () -> func.solve(1));
    }
}