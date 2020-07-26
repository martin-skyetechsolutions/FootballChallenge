package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringTest {
    private int length = 10;

    @Test
    void generateAndLength() {
        String random1 = RandomString.generate(length);
        assertNotNull(random1);
        assertEquals(length,random1.length());
    }

    @Test
    void successiveCallReturnsDifferentString(){
        assertNotEquals(RandomString.generate(length), RandomString.generate(length));
    }
}