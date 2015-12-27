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

import com.jme3.input.ChaseCamera;
import static com.jme3.input.ChaseCamera.ChaseCamToggleRotate;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 * a camera to view the cube
 * 
 * @version 0.9.1
 * @since 0.9.1
 */
public class LEDCubeCamera extends ChaseCamera {
    /**
     * the viewer to show
     */
    protected LEDCubeViewer viewer;

    /**
     * ctor
     * 
     * @param cam the cam to use
     * @param target the target to show
     */
    public LEDCubeCamera(Camera cam, final LEDCubeViewer target) {
        super(cam);
        viewer = target;
        Vector3f size = viewer.getSize();
        float max = Math.max(Math.max(size.getX(), size.getY()), size.getZ());
        setDefaultDistance( max * 2f );
        setMinDistance( max );
        setMaxDistance( max * 4f );
    }

    /**
     *  Registers the inputs for the LED cube camera controls.
     *
     *  @param inputManager the manager of the application to register inputs
     */
    public void registerWithSpecialInput(InputManager inputManager) {
        String[] inputs = {
            "ChaseCamToggleRotate",
            "ChaseCamDown",
            "ChaseCamUp",
            "ChaseCamMoveLeft",
            "ChaseCamMoveRight",
            "ChaseCamZoomIn",
            "ChaseCamZoomOut"
        };

        this.inputManager = inputManager;
        // look out for concurrent access to invertYaxis & invertXaxis
        inputManager.addMapping("ChaseCamDown", new MouseAxisTrigger(MouseInput.AXIS_Y, !invertYaxis), new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("ChaseCamUp", new MouseAxisTrigger(MouseInput.AXIS_Y, invertYaxis), new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("ChaseCamMoveLeft", new MouseAxisTrigger(MouseInput.AXIS_X, !invertXaxis), new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("ChaseCamMoveRight", new MouseAxisTrigger(MouseInput.AXIS_X, invertXaxis), new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("ChaseCamZoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false), new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("ChaseCamZoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true), new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping(
            "ChaseCamToggleRotate",
            new MouseButtonTrigger(MouseInput.BUTTON_LEFT),
            new MouseButtonTrigger(MouseInput.BUTTON_RIGHT),
            new KeyTrigger(KeyInput.KEY_R)
        );
        inputManager.addListener(this, inputs);
    }

    @Override
    public void onAction(String name, boolean keyPressed, float tpf) {
        if(dragToRotate) {
            if(enabled && name.equals(ChaseCamToggleRotate)) {
                if(keyPressed) {
                    canRotate = true;
                }
                else {
                    canRotate = false;
                }
            }
        }
    }
}
