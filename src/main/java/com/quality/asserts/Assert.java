package com.quality.asserts;

public interface Assert {
    boolean support(String assertType);

    <C, A> boolean doAssert(C condition, A actual);
}


