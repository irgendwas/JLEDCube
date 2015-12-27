/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reimanndaniel.jledcube.viewer;

import com.jme3.material.FixedFuncBinding;
import com.jme3.material.Material;
import com.jme3.material.MaterialDef;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.shader.VarType;
import de.reimanndaniel.jledcube.system.LEDCube;
import de.reimanndaniel.jledcube.system.LEDCubeDimension;
import de.reimanndaniel.jledcube.system.LEDCubeLight;
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
public class LEDCubeViewerTest {
    
    public LEDCubeViewerTest() {
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
     * Test of getNode method, of class LEDCubeViewer.
     */
    @Test
    public void testGetNode() {
        System.out.println("getNode");
        LEDCubeDimension dim = new LEDCubeDimension(3);
        LEDCube cube = new LEDCube(dim);
        LEDCubeViewer instance = new LEDCubeViewer(cube, new Material());
        Node result = instance.getNode();
        assertNotNull(result);
    }

    /**
     * Test of paint method, of class LEDCubeViewer.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        LEDCubeDimension dim = new LEDCubeDimension(3);
        LEDCube cube = new LEDCube(dim);
        MaterialDef materialDef = new MaterialDef(null, "Test");
        materialDef.addMaterialParam(VarType.Vector4, "Color", null, FixedFuncBinding.Color);
        materialDef.addMaterialParam(VarType.Vector4, "GlowColor", null, FixedFuncBinding.Color);
        LEDCubeViewer instance = new LEDCubeViewer(cube, new Material(materialDef));
        instance.paint();
        assertEquals(27, instance.getNode().getChildren().size());
        instance.paint();
        assertEquals(27, instance.getNode().getChildren().size());
        assertEquals(ColorRGBA.Black, ((Geometry) instance.getNode().getChild(2)).getMaterial().getParam("Color").getValue());
        assertEquals(ColorRGBA.Black, ((Geometry) instance.getNode().getChild(2)).getMaterial().getParam("GlowColor").getValue());
    }

    /**
     * Test of update method, of class LEDCubeViewer.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        LEDCubeDimension dim = new LEDCubeDimension(3);
        LEDCube cube = new LEDCube(dim);
        MaterialDef materialDef = new MaterialDef(null, "Test");
        materialDef.addMaterialParam(VarType.Vector4, "Color", null, FixedFuncBinding.Color);
        materialDef.addMaterialParam(VarType.Vector4, "GlowColor", null, FixedFuncBinding.Color);
        LEDCubeViewer instance = new LEDCubeViewer(cube, new Material(materialDef));
        instance.paint();
        assertEquals(27, instance.getNode().getChildren().size());
        assertEquals(ColorRGBA.Black, ((Geometry) instance.getNode().getChild(2)).getMaterial().getParam("Color").getValue());
        assertEquals(ColorRGBA.Black, ((Geometry) instance.getNode().getChild(2)).getMaterial().getParam("GlowColor").getValue());
        cube.setLight(new LEDCubeLight(0, 0, 2, ColorRGBA.Blue));
        instance.update(null, new LEDCubeLight(0, 0, 2, ColorRGBA.Blue));
        assertEquals(ColorRGBA.Blue, ((Geometry) instance.getNode().getChild(2)).getMaterial().getParam("Color").getValue());
        assertEquals(ColorRGBA.Blue, ((Geometry) instance.getNode().getChild(2)).getMaterial().getParam("GlowColor").getValue());
    }
}