package christmas.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateTest {
    @Test
    public void 시간범위_예외테스트() throws Exception {

        assertThrows(IllegalArgumentException.class, () -> {
            new Date(32);
        });
    }

}