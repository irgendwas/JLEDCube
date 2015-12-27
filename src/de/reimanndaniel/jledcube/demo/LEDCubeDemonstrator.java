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
package de.reimanndaniel.jledcube.demo;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.post.FilterPostProcessor;
import com.jme3.system.AppSettings;
import de.reimanndaniel.jledcube.system.LEDCube;
import de.reimanndaniel.jledcube.system.LEDCubeDimension;
import de.reimanndaniel.jledcube.viewer.LEDCubeCamera;
import de.reimanndaniel.jledcube.viewer.LEDCubeViewer;

/**
 * demonstrator for the LED cube
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCubeDemonstrator extends SimpleApplication {
    private LEDCube cube;
    
    /**
     *  Starts the example.
     *
     *  @param args shell arguments
     */
    public static void main(String[] args) {
        LEDCubeDemonstrator demo = new LEDCubeDemonstrator();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("JLEDCube Demo");
        settings.setResolution(800, 600);
        demo.setSettings(settings);
        demo.setShowSettings(false);
        demo.start();
    }

    @Override
    public void simpleInitApp() {
        // view
        LEDCubeDimension dim = new LEDCubeDimension(8);
        cube = new LEDCube(dim, ColorRGBA.Green);
        LEDCubeViewer viewer = new LEDCubeViewer(cube, new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"));
        viewer.paint();
        rootNode.attachChild(viewer.getNode());
        
        // post processor bloom
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        viewPort.addProcessor(fpp);
        viewer.addFilters(fpp);
        
        // for the variable view
        flyCam.setEnabled(false);
        LEDCubeCamera chaser = new LEDCubeCamera(cam, viewer);
        chaser.registerWithSpecialInput(inputManager);
        inputManager.setCursorVisible(false);
    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
    }
}
