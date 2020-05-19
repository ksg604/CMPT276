package com.example.kevin.servingsizecalculator;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertNotEquals;

public class PotTest {

    @Test
    public void testGetMethods() {
        Pot NewPot = new Pot("NewPot", 350);
        assertEquals("NewPot",NewPot.getName());
        assertEquals(350, NewPot.getWeightInG());
    }

    @Test
    public void testgetWeightInG() {
        Pot OldPot = new Pot("OldPot", 125);
        assertEquals(125, OldPot.getWeightInG());
    }
    @Test
    public void testgetWeightInGFail() {
        Pot OldPot = new Pot("OldPot", 125);
        assertNotEquals(124, OldPot.getWeightInG());
    }

    @Test
    public void testSetWeightInG() {
        Pot ShinyPot = new Pot("ShinyPot", 115);
        ShinyPot.setWeightInG(95);
        assertEquals(95, ShinyPot.getWeightInG());
    }
    @Test
    public void testSetWeightInGFail() {
        Pot ThePot = new Pot("ThePot", 115);
        assertNotEquals(-5, ThePot.getWeightInG());
    }

    @Test
    public void testGetName() {
        Pot RustyPot = new Pot("RustyPot", 254);
        assertEquals("RustyPot", RustyPot.getName());
    }

    @Test
    public void testGetNameFail() {
        Pot NotWeirdPot = new Pot("NotWeirdPot", 521);
        assertNotEquals("WeirdPot", NotWeirdPot.getName());
    }

    @Test
    public void setName() {
        Pot DirtyPot = new Pot("DirtyPot", 175);
        DirtyPot.setName("CleanPot");
        assertEquals("CleanPot", DirtyPot.getName());
    }

    @Test
    public void setNameFail() {

        Pot BrokenPot = new Pot("BrokenPot", 10);
        assertNotEquals("", BrokenPot.getName());
        assertNotEquals(null, BrokenPot);
    }


}