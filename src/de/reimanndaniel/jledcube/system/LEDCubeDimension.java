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

/**
 * The size of the LED cube.
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCubeDimension {
    /**
     * width of the cube
     */
    private int width;
    /**
     * height of the cube
     */
    private int height;
    /**
     * depth of the cube
     */
    private int depth;

    /**
     * ctor
     * 
     * @param cube the length for all sides
     */
    public LEDCubeDimension(int cube) {
        this(cube, cube, cube);
    }

    /**
     * ctor
     * 
     * @param width the width of the dimension
     * @param height the height of the dimension
     * @param depth the depth of the dimension
     */
    public LEDCubeDimension(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    /**
     * @return the width of the dimension
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the dimension
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the depth of the dimension
     */
    public int getDepth() {
        return depth;
    }
    
    /**
     * Determines if a point is in the dimension.
     * 
     * @param point point to test
     * @return whether the point is in the dimension
     */
    public boolean in(LEDCubePoint point) {
        return point.getX() < width && point.getY() < height && point.getZ() < depth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LEDCubeDimension other = (LEDCubeDimension) obj;
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.depth != other.depth) {
            return false;
        }
        return true;
    }
}
