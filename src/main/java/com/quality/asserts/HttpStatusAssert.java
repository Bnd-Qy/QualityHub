package com.quality.asserts;

public class HttpStatusAssert implements Assert {
    @Override
    public boolean support(String assertType) {
        return false;
    }

    @Override
    public <C, A> boolean doAssert(C condition, A actual) {
        return false;
    }
}
