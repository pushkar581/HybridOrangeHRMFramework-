package tests.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleJUnitTest {

    @Test
    void simpleAdditionTest() {
        int sum = 2 + 3;
        assertEquals(5, sum, "Sum should be 5");
    }
}