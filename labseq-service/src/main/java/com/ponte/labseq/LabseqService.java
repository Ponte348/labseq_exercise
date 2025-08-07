package com.ponte.labseq;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.cache.CacheResult;
import java.math.BigInteger;

@ApplicationScoped
public class LabseqService {

    /**
     * Calculates the labseq sequence value for a given n.
     * The sequence is defined as:
     * - l(0) = 0
     * - l(1) = 1
     * - l(2) = 0
     * - l(3) = 1
     * - l(n) = l(n-4) + l(n-3) for n > 3
     * @param n the input number
     * @return the labseq sequence value for n
     * @throws IllegalArgumentException if n is negative
     */ 
    @CacheResult(cacheName = "labseqCache")
    public BigInteger sequence(Integer n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }
        
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        if (n == 2) return BigInteger.ZERO;
        if (n == 3) return BigInteger.ONE;
        
        // Could use a circular buffer
        // Base values
        BigInteger nMinus4 = BigInteger.ZERO;  // l(0)
        BigInteger nMinus3 = BigInteger.ONE;   // l(1)
        BigInteger nMinus2 = BigInteger.ZERO;  // l(2)
        BigInteger nMinus1 = BigInteger.ONE;   // l(3)
        BigInteger current = BigInteger.ZERO;
        
        for (int i = 4; i <= n; i++) {
            current = nMinus4.add(nMinus3);
            
            // Shift for next iteration
            nMinus4 = nMinus3;
            nMinus3 = nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = current;
        }
        
        return current;
    }
}