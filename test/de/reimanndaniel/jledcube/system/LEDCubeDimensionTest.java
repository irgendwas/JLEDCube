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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class LEDCubeDimensionTest {
    
    public LEDCubeDimensionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of in method, of class LEDCubeDimension.
     */
    @Test
    public void testIn() {
        System.out.println("in");
        LEDCubePoint point1 = new LEDCubePoint(0, 1, 0);
        LEDCubePoint point2 = new LEDCubePoint(1, 1, 1);
        LEDCubePoint point3 = new LEDCubePoint(0, 2, 1);
        LEDCubePoint point4 = new LEDCubePoint(0, 1, 3);
        LEDCubePoint point5 = new LEDCubePoint(3, 20, 6);
        LEDCubePoint point6 = new LEDCubePoint(0, 1, 2);
        LEDCubeDimension instance = new LEDCubeDimension(1, 2, 3);
        assertEquals(true, instance.in(point1));
        assertEquals(false, instance.in(point2));
        assertEquals(false, instance.in(point3));
        assertEquals(false, instance.in(point4));
        assertEquals(false, instance.in(point5));
        assertEquals(true, instance.in(point6));
    }
}