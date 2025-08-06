package com.ponte.labseq;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class LabseqService {
    
    private final Map<Integer, BigInteger> cache = new HashMap<>();

    /**
     * Implements the sequence logic for the labseq exercise.
     * For values between 0 and 3, it returns 0, 1, 0, and 1 respectively.
     * @param n input value
     * @return l(n) = l(n-4) + l(n-3) 
     */
    // We could use @CacheResult, but I'll do memoization manually.
    public BigInteger sequence(Integer n){
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }
        
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        if (n == 2) return BigInteger.ZERO;
        if (n == 3) return BigInteger.ONE;

        BigInteger result = sequence(n - 4).add(sequence(n - 3));
        
        cache.put(n, result);
        
        return result;
    }

}