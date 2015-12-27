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
 * A light in the LED sube.
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCubeLight extends LEDCubePoint {
    /**
     * color of the light
     */
    private final ColorRGBA color;
    
    /**
     * ctor
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param color the color
     */
    public LEDCubeLight(int x, int y, int z, ColorRGBA color) {
        super(x, y, z);
        this.color = color;
    }
    
    /**
     * ctor
     * 
     * @param point the point of the light
     * @param color the color
     */
    public LEDCubeLight(LEDCubePoint point, ColorRGBA color) {
        this(point.getX(), point.getY(), point.getZ(), color);
    }

    /**
     * @return the color of the light
     */
    public ColorRGBA getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 19 * hash + (this.color != null ? this.color.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof LEDCubeLight)) {
            return false;
        }
        final LEDCubeLight other = (LEDCubeLight) obj;
        if (this.color != other.color && (this.color == null || !this.color.equals(other.color))) {
            return false;
        }
        return true;
    }
    
    
}
