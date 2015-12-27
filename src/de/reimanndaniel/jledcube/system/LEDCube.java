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

/**
 * the real LED cube
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCube {
    /**
     * internal state
     */
    private final ColorRGBA[][][] state;
    
    /**
     * dimension of the LED
     */
    private final LEDCubeDimension dimension;

    /**
     * ctor
     * 
     * @param dimension the dimension of the cube
     */
    public LEDCube(LEDCubeDimension dimension) {
        this(dimension, ColorRGBA.Black);
    }

    /**
     * ctor
     * 
     * @param dimension the dimension of the cube
     * @param initColor the initial color
     */
    public LEDCube(LEDCubeDimension dimension, ColorRGBA initColor) {
        this.dimension = dimension;
        state = new ColorRGBA[dimension.getWidth()][dimension.getHeight()][dimension.getDepth()];
        fill(initColor);
    }
    
    /**
     * @return the dimension of the cube
     */
    public LEDCubeDimension getDimension() {
        return dimension;
    }
    
    /**
     * Fills the whole cube with a color.
     * 
     * @param color the color to fill with
     */
    public final void fill(ColorRGBA color) {
        for(int i = 0; i < state.length; i++) {
            for(int j = 0; j < state[i].length; j++) {
                for(int k = 0; k < state[i][j].length; k++)
                    state[i][j][k] = color;
            }
        }
    }
    
    /**
     * Changes a light in the cube.
     * 
     * @param light the light to change to
     */
    public void setLight(LEDCubeLight light) {
        setColor(light, light.getColor());
    }
    
    /**
     * Gets a light of the cube.
     * 
     * @param point the point of the light to get
     * @return the light at that point
     */
    public LEDCubeLight getLight(LEDCubePoint point) {
        return new LEDCubeLight(point, getColor(point));
    }
    
    /**
     * Changes the color at a certain point.
     * 
     * @param point the point to set the color at
     * @param color the color to set at the point
     */
    public void setColor(LEDCubePoint point, ColorRGBA color) {
        state[point.getX()][point.getY()][point.getZ()] = color;
    }
    
    /**
     * Gets the color at a certain point.
     * 
     * @param point he point to get the color from
     * @return the color at that point
     */
    public ColorRGBA getColor(LEDCubePoint point) {
        return state[point.getX()][point.getY()][point.getZ()];
    }
}
