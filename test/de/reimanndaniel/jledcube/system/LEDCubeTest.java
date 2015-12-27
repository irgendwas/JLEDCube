/*
 *  Copyright 2015 Daniel Reimann
 *
 *  This file is part of JLEDCube.
 *
 *  JLEDCube is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  JLEDCube is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with JLEDCube.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package de.reimanndaniel.jledcube.system;

import com.jme3.math.ColorRGBA;
import java.util.Observable;
import java.util.Observer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * LEDCube Test
 */
public class LEDCubeTest implements Observer {
    private int countObserved;
    private Object observedObject;
    
    public LEDCubeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        countObserved = 0;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ctor method, of class LEDCube.
     */
    @Test
    public void ctor() {
        System.out.println("ctor");
        ColorRGBA expResult = ColorRGBA.Black;
        LEDCubeDimension dimension = new LEDCubeDimension(3);
        LEDCube instance = new LEDCube(dimension);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    assertEquals(expResult, instance.getColor(new LEDCubePoint(i, j, k)));
                }
            }
        }
    }
    
    /**
     * Test of ctor method, of class LEDCube.
     */
    @Test
    public void ctor2() {
        ColorRGBA expResult = ColorRGBA.Blue;
        LEDCubeDimension dimension = new LEDCubeDimension(3);
        LEDCube instance = new LEDCube(dimension, expResult);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    assertEquals(expResult, instance.getColor(new LEDCubePoint(i, j, k)));
                }
            }
        }
    }

    /**
     * Test of getDimension method, of class LEDCube.
     */
    @Test
    public void testGetDimension() {
        System.out.println("getDimension");
        LEDCubeDimension expResult = new LEDCubeDimension(3);
        LEDCube instance = new LEDCube(expResult);
        LEDCubeDimension result = instance.getDimension();
        assertEquals(expResult, result);
    }

    /**
     * Test of fill method, of class LEDCube.
     */
    @Test
    public void testFill() {
        System.out.println("fill");
        ColorRGBA expResult = ColorRGBA.Blue;
        LEDCubeDimension dimension = new LEDCubeDimension(3);
        LEDCube instance = new LEDCube(dimension);
        instance.registerObserver(this);
        assertEquals(ColorRGBA.Black, instance.getColor(new LEDCubePoint(1, 1, 1)));
        instance.fill(expResult);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    assertEquals(expResult, instance.getColor(new LEDCubePoint(i, j, k)));
                }
            }
        }
        assertEquals(1, countObserved);
        assertEquals(null, observedObject);
    }

    /**
     * Test of setLight method, of class LEDCube.
     */
    @Test
    public void testSetGetLight() {
        System.out.println("setgetLight");
        LEDCubeLight expResult = new LEDCubeLight(1, 1, 1, ColorRGBA.Blue);
        LEDCubeDimension dimension = new LEDCubeDimension(3);
        LEDCube instance = new LEDCube(dimension);
        instance.registerObserver(this);
        assertEquals(ColorRGBA.Black, instance.getColor(expResult));
        instance.setLight(expResult);
        assertEquals(expResult, instance.getLight(expResult));
        assertEquals(1, countObserved);
        assertEquals(expResult, observedObject);
    }

    /**
     * Test of getColor method, of class LEDCube.
     */
    @Test
    public void testSetGetColor() {
        System.out.println("setgetColor");
        LEDCubePoint point = new LEDCubePoint(1, 1, 1);
        LEDCubeDimension dimension = new LEDCubeDimension(3);
        LEDCube instance = new LEDCube(dimension);
        instance.registerObserver(this);
        ColorRGBA expResult = ColorRGBA.Blue;
        assertEquals(ColorRGBA.Black, instance.getColor(point));
        instance.setColor(point, expResult);
        ColorRGBA result = instance.getColor(point);
        assertEquals(expResult, result);
        assertEquals(1, countObserved);
        assertEquals(point, observedObject);
    }

    public void update(Observable o, Object arg) {
        countObserved++;
        observedObject = arg;
    }
}