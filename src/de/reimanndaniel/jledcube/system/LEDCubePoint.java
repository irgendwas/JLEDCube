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
 * a point in the LED cube
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCubePoint {
    /**
     * the x coordinate
     */
    private final int x;
    /**
     * the y coordinate
     */
    private final int y;
    /**
     * the z coordinate
     */
    private final int z;

    /**
     * ctor
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public LEDCubePoint(int x, int y, int z) {
        if (x < 0)
            throw new IllegalArgumentException("X coordinate must not be less than zero.");
        if (y < 0)
            throw new IllegalArgumentException("Y coordinate must not be less than zero.");
        if (z < 0)
            throw new IllegalArgumentException("Z coordinate must not be less than zero.");
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * @return the z coordinate
     */
    public int getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LEDCubePoint)) {
            return false;
        }
        final LEDCubePoint other = (LEDCubePoint) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }
    
    
}
