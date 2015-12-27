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
package de.reimanndaniel.jledcube.viewer;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import de.reimanndaniel.jledcube.system.LEDCube;
import de.reimanndaniel.jledcube.system.LEDCubeDimension;
import de.reimanndaniel.jledcube.system.LEDCubeLight;
import de.reimanndaniel.jledcube.system.LEDCubePoint;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * viewer for the LED cube
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCubeViewer implements Observer {
    /**
     * already painted once?
     */
    private boolean init;
    /**
     * the viewed cube
     */
    protected final LEDCube cube;
    /**
     * default Material to clone
     */
    private final Material defaultMat;
    /**
     *  material cache
     */
    private HashMap<ColorRGBA,Material> colmap;
    /**
     * the note with the objects
     */
    protected Node node;
    /**
     *  the 3D view of each LED
     */
    protected Geometry view[][][];
    /**
     *  the distance between LEDs
     */
    protected final float[] distbetled = { 25f, 25f, 25f };
    
    /**
     * ctor
     * 
     * @param cube cube to view
     * @param material material to use
     */
    public LEDCubeViewer(LEDCube cube, Material material) {
        this.cube = cube;
        this.defaultMat = material;
        node = new Node("LEDCube");
        colmap = new HashMap<ColorRGBA, Material>();
    }
    
    /**
     * @return the node to show
     */
    public Node getNode() {
        return node;
    }
    
    /**
     * Paints the geometries.
     */
    public synchronized void paint() {
        LEDCubeDimension dimension = cube.getDimension();
        if(!init) {
            view = new Geometry[dimension.getWidth()][dimension.getHeight()][dimension.getDepth()];
            float totalx = (dimension.getWidth() - 1) * distbetled[0];
            float totaly = (dimension.getHeight() - 1) * distbetled[1];
            float totalz = (dimension.getDepth() - 1) * distbetled[2];
            // to make the cube central
            float rx = totalx / 2 * -1;
            float ry = totaly / 2 * -1;
            float rz = totalz / 2 * -1;
            Sphere ledshape = new Sphere(64, 64, 2f);
            for(int ax = 0; ax < dimension.getWidth(); ax++) {
                for(int ay = 0; ay < dimension.getHeight(); ay++) {
                    for(int az = 0; az < dimension.getDepth(); az++) {
                        Geometry geo = new Geometry("LED", ledshape);
                        geo.setLocalTranslation(rx, ry, rz);
                        view[ax][ay][az] = geo;
                        node.attachChild(view[ax][ay][az]);
                        rz += distbetled[2];
                    }
                    rz = totalz / 2 * -1;
                    ry += distbetled[1];
                }
                ry = totaly / 2 * -1;
                rx += distbetled[0];
            }
            init = true;
        }
        for(int ax = 0; ax < dimension.getWidth(); ax++) {
            for(int ay = 0; ay < dimension.getHeight(); ay++) {
                for(int az = 0; az < dimension.getDepth(); az++) {
                    setMaterial(new LEDCubePoint(ax, ay, az));
                }
            }
        }
    }
    
    /**
     * Sets the material of a geometry at a certain point
     * 
     * @param point the point of the geometry to change
     */
    protected void setMaterial(LEDCubePoint point) {
        Geometry geo = view[point.getX()][point.getY()][point.getZ()];
        geo.setMaterial(getColorMat(cube.getColor(point)));
    }

    public void update(Observable o, Object arg) {
        if(arg instanceof LEDCubePoint) {
            LEDCubePoint light = (LEDCubePoint) arg;
            setMaterial(light);
        }
        else
            paint();
    }

    /**
     *  Gets a material in a color.
     *  Uses caching to save space.
     *  Currently caches all colors until end of execution.
     *
     *  @param color the color of the material
     *  @return the material in the color
     */
    protected Material getColorMat(ColorRGBA color) {
        if(colmap.containsKey(color)) {
            return colmap.get(color);
        }
        else {
            Material mat = defaultMat.clone();
            mat.setColor("Color", color);
            mat.setColor("GlowColor", color);
            colmap.put(color, mat);
            return mat;
        }
    }
}
