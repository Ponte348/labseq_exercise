package com.ponte.labseq;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.SecureRandom;

@QuarkusTest
public class LabseqServiceTest {

    @Inject
    LabseqService labseqService;

    @DisplayName("Test base cases (n=0,1,2,3)")
    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 1",
        "2, 0",
        "3, 1"
    })
    void testBaseCases(int input, int expected) {
        assertEquals(expected, labseqService.sequence(input));
    }

    @DisplayName("Test recursive cases (n>3)")
    @ParameterizedTest
    @CsvSource({
        "4, 1",
        "5, 1",
        "6, 1",
        "7, 2",
        "8, 2",
        "9, 2",
        "10, 3",
        "15, 5",
        "20, 11"
    })
    void testRecursiveCases(int input, int expected) {
        assertEquals(expected, labseqService.sequence(input));
    }
    
    @Test
    @DisplayName("Test performance with large number")
    // Not checking value, just measuring time
    void testLargeNumberPerformance() {
        long startTime = System.currentTimeMillis();
        Integer result = labseqService.sequence(10000);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time to calculate l(10000): " + (endTime - startTime) + "ms");
        
        assertEquals(true, result != null);
    }
    
    @Test
    @DisplayName("Test negative input")
    void testNegativeInput() {
        int randomNegative = -1 * (new SecureRandom().nextInt(100) + 1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            labseqService.sequence(randomNegative);
        }, "Should throw IllegalArgumentException for negative input: " + randomNegative);
    }
}