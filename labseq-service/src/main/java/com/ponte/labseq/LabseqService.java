package com.ponte.labseq;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabseqService {
    
    /**
     * Implements the sequence logic for the labseq exercise.
     * For values between 0 and 3, it returns 0, 1, 0, and 1 respectively.
     * @param a input value
     * @return l(n) = l(n-4) + l(n-3) 
     */
    public Integer sequence(Integer a){
        if (a < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }
        if (a == 0) return 0;
        if (a == 1) return 1;
        if (a == 2) return 0;
        if (a == 3) return 1;

        return sequence(a - 4) + sequence(a - 3);
    }

}