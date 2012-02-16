/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.view;

import vtk.vtkActor;
import vtk.vtkArrowSource;
import vtk.vtkConeSource;
import vtk.vtkCutter;
import vtk.vtkDataSet;
import vtk.vtkDataSetMapper;
import vtk.vtkGlyph3D;
import vtk.vtkLookupTable;
import vtk.vtkPlane;
import vtk.vtkSphereSource;
import vtk.vtkTransform;
import vtk.vtkTransformPolyDataFilter;

/**
 * This class implements the plane cutters in x,y or z direction.
 * @author naid
 *
 */
public class PlaneCutterClass {

    private vtkActor[] planeCutterActor = null;
    private double[] scalarRange;
    private int[] xyzPlanesVisibility;
    private vtkPlane[] xyzPlanes;
    private double[][] planeNormals;
    private double[][] planesPos;
    private final int GLYPHTYPE_PLANE = 0;
    private final int GLYPHTYPE_ARROW = 1;
    private final int GLYPHTYPE_CONE = 2;
    private final int GLYPHTYPE_SPHERE = 3;
    private int[] glyphType;
    private vtkGlyph3D[] SphereGlyph = new vtkGlyph3D[3];
    private vtkGlyph3D[] ConeGlyph = new vtkGlyph3D[3];
    private vtkGlyph3D[] ArrowGlyph = new vtkGlyph3D[3];
    private vtkCutter[] planeCutter = null;
    private vtkDataSetMapper[] planeCutterMapp = new vtkDataSetMapper[3];

    public PlaneCutterClass() {
        scalarRange = new double[2];
        planeCutterActor = new vtkActor[3];
        xyzPlanesVisibility = new int[3];
        xyzPlanes = new vtkPlane[3];
        planeCutter = new vtkCutter[3];
        planeNormals = new double[3][3];

        planeNormals[0][0] = 1;
        planeNormals[1][1] = 1;
        planeNormals[2][2] = 1;
        planesPos = new double[3][3];
        glyphType = new int[3];
        glyphType[0] = GLYPHTYPE_PLANE;
        glyphType[1] = GLYPHTYPE_PLANE;
        glyphType[2] = GLYPHTYPE_PLANE;
    }

    /**
     * Method addPlaneCutter.Adds a plane cut through the data set.
     * @param dataSet The input dataset to cut.
     * @param whichPlane The plane x=0, y=0, z=0;
     */
    public vtkActor addPlaneCut(vtkDataSet dataSet, int whichPlane, boolean show) {
        double[] midlePoint = new double[3];
        vtkActor returnVal = null;
        if (planeCutterActor[whichPlane] == null) {
            vtkLookupTable lut = new vtkLookupTable();
            lut.SetHueRange(0.6667, 0.0);

            xyzPlanes[whichPlane] = new vtkPlane();
            midlePoint = dataSet.GetCenter();
            xyzPlanes[whichPlane].SetNormal(planeNormals[whichPlane]);
            xyzPlanes[whichPlane].SetOrigin(midlePoint[0], midlePoint[1], midlePoint[2]);
            planeCutter[whichPlane] = new vtkCutter();
            planeCutter[whichPlane].SetCutFunction(xyzPlanes[whichPlane]);
            planeCutter[whichPlane].SetInput(dataSet);
            planeCutterMapp[whichPlane] = new vtkDataSetMapper();
            planeCutterMapp[whichPlane].SetLookupTable(lut);
            //arrow glyph
            vtkArrowSource arrow = new vtkArrowSource();
            ArrowGlyph[whichPlane] = new vtkGlyph3D();
            ArrowGlyph[whichPlane].SetInput(planeCutter[whichPlane].GetOutput());
            ArrowGlyph[whichPlane].SetSource(arrow.GetOutput());
            ArrowGlyph[whichPlane].SetColorModeToColorByScalar();
            //ArrowGlyph[whichPlane].SetScaleFactor(0.04);
            //cone glyphs
            vtkConeSource cone = new vtkConeSource();
            cone.SetResolution(10);
            vtkTransform coneTransfrom = new vtkTransform();
            coneTransfrom.Translate(0.5, 0.0, 0.0);
            vtkTransformPolyDataFilter transformCone = new vtkTransformPolyDataFilter();
            transformCone.SetInput(cone.GetOutput());
            transformCone.SetTransform(coneTransfrom);
            ConeGlyph[whichPlane] = new vtkGlyph3D();
            ConeGlyph[whichPlane].SetInput(planeCutter[whichPlane].GetOutput());
            ConeGlyph[whichPlane].SetSource(transformCone.GetOutput());
            ConeGlyph[whichPlane].SetVectorModeToUseVector();//Normal();
            ConeGlyph[whichPlane].SetScaleModeToScaleByVector();
            ConeGlyph[whichPlane].SetColorModeToColorByScalar();
            ConeGlyph[whichPlane].SetScaleFactor(0.03);
            //sphere glyphs
            vtkSphereSource sphere = new vtkSphereSource();
            sphere.SetRadius(0.8);

            SphereGlyph[whichPlane] = new vtkGlyph3D();
            SphereGlyph[whichPlane].SetInput(planeCutter[whichPlane].GetOutput());
            SphereGlyph[whichPlane].SetSource(sphere.GetOutput());
            SphereGlyph[whichPlane].SetColorModeToColorByScalar();
            SphereGlyph[whichPlane].SetScaleFactor(0.004);

            switch (glyphType[whichPlane]) {
                case GLYPHTYPE_PLANE:
                    planeCutterMapp[whichPlane].SetInput(planeCutter[whichPlane].GetOutput());
                    break;
                case GLYPHTYPE_ARROW:
                    planeCutterMapp[whichPlane].SetInput(ArrowGlyph[whichPlane].GetOutput());
                    break;
                case GLYPHTYPE_CONE:
                    planeCutterMapp[whichPlane].SetInput(ConeGlyph[whichPlane].GetOutput());
                    break;
                case GLYPHTYPE_SPHERE:
                    planeCutterMapp[whichPlane].SetInput(SphereGlyph[whichPlane].GetOutput());
                    break;
            }
            planeCutterActor[whichPlane] = new vtkActor();
            planeCutterActor[whichPlane].SetMapper(planeCutterMapp[whichPlane]);
            planeCutterActor[whichPlane].GetProperty().SetOpacity(1.0);

            returnVal = planeCutterActor[whichPlane];
        }
        if (show) {
            xyzPlanesVisibility[whichPlane] = 1;
        } else {
            xyzPlanesVisibility[whichPlane] = 0;
        }
        planeCutterActor[whichPlane].SetVisibility(xyzPlanesVisibility[whichPlane]);

        return returnVal;
    }

    /**
     * Method deleteActorPlane.
     * @param deleteActor
     */
    public void deleteActorPlane(vtkActor deleteActor) {
        for (int i = 0; i < 3; i++) {
            if (planeCutterActor[i] == deleteActor) {
                planeCutterActor[i] = null;
                xyzPlanesVisibility[i] = 0;
            }
        }
    }

    /**
     * Method deleteActorPlane.
     */
    public void deleteActorPlanes() {
        for (int i = 0; i < 3; i++) {
            if (planeCutterActor[i] != null) {
                planeCutterActor[i] = null;
                xyzPlanesVisibility[i] = 0;
            }
        }
    }

    /**
     * Returns the xyzPlanesVisibility.
     * @return int[]
     */
    public int[] getXyzPlanesVisibility() {
        return xyzPlanesVisibility;
    }

    /**
     * Sets the xyzPlanesVisibility.
     * @param xyzPlanesVisibility The xyzPlanesVisibility to set
     */
    public void setxyzPlanesVisibility(int[] xyzPlanesVisibility) {
        this.xyzPlanesVisibility = xyzPlanesVisibility;
        for (int i = 0; i < 3; i++) {
            if (planeCutterActor[i] != null) {
                planeCutterActor[i].SetVisibility(xyzPlanesVisibility[i]);
            }
        }
    }

    /**
     * Sets the PlaneVisibility.
     * @param plane The plane for which to set the visibility
     * @param PlaneVisibility The PlaneVisibility to set
     */
    public void setPlaneVisibility(int plane, int PlaneVisibility) {
        this.xyzPlanesVisibility[plane] = PlaneVisibility;
        if (planeCutterActor[plane] != null) {
            planeCutterActor[plane].SetVisibility(PlaneVisibility);
        }
    }

    /**
     * Returns the planeNormals.
     * @return double[][]
     */
    public double[][] getPlaneNormals() {
        return planeNormals;
    }

    /**
     * Sets the planeNormals.
     * @param planeNormals The planeNormals to set
     */
    public void setPlaneNormals(double[][] planeNormals) {
        this.planeNormals = planeNormals;
        for (int i = 0; i < 3; i++) {
            if (xyzPlanes[i] != null) {
                xyzPlanes[i].SetNormal(planeNormals[i]);
            }
        }
    }

    /**
     * Sets the planeNormal.
     * @param whichPlane The plane for which to set the normal
     * @param planeNormal The planeNormal to set
     */
    public void setPlaneNormal(int whichPlane, double[] planeNormal) {
        this.planeNormals[whichPlane] = planeNormal;
        if (xyzPlanes[whichPlane] != null) {
            xyzPlanes[whichPlane].SetNormal(planeNormal);
        }
    }

    /**
     * Returns the planesPos.
     * @return double[][]
     */
    public double[][] getPlanesPos() {
        return planesPos;
    }

    /**
     * Sets the planesPos.
     * @param planesPos The planesPos to set
     */
    public void setPlanesPos(double[][] planesPos) {
        this.planesPos = planesPos;
        for (int i = 0; i < 3; i++) {
            if (xyzPlanes[i] != null) {
                xyzPlanes[i].SetOrigin(planesPos[i][0], planesPos[i][1], planesPos[i][2]);
            }
        }
    }

    /**
     * Sets the planesPos.
     * @param whichPlane The plane for which to set the position
     * @param planePos The planePosition to set
     */
    public void setPlanePos(int whichPlane, double[] planePos) {
        this.planesPos[whichPlane] = planePos;
        if (xyzPlanes[whichPlane] != null) {
            xyzPlanes[whichPlane].SetOrigin(planePos[0], planePos[1], planePos[2]);
        }

    }

    /**
     * Returns the planesPos.
     * @return double[][]
     */
    public double[] getPlanePos(int whichPlane) {
        return planesPos[whichPlane];
    }

    /**
     * Sets the transparency level of the plane actor.
     * @param whichPlane The plane for which to set the transparency level
     */
    public void setPlaneTransparency(int whichPlane, double transpValue) {
        if (planeCutterActor[whichPlane] != null) {
            planeCutterActor[whichPlane].GetProperty().SetOpacity(transpValue);
        }
    }

    /**
     * Returns the glyphType.
     * @return int
     */
    public int getGlyphType(int whichPlane) {
        return glyphType[whichPlane];
    }

    /**
     * Sets the glyphType.
     * @param glyphType The glyphType to set
     */
    public void setGlyphType(int glyphType, int whichPlane) {
        this.glyphType[whichPlane] = glyphType;
        if (planeCutterMapp[whichPlane] != null) {
            switch (this.glyphType[whichPlane]) {
                case GLYPHTYPE_PLANE:
                    planeCutterMapp[whichPlane].SetInput(planeCutter[whichPlane].GetOutput());
                    break;
                case GLYPHTYPE_ARROW:
                    planeCutterMapp[whichPlane].SetInput(ArrowGlyph[whichPlane].GetOutput());
                    break;
                case GLYPHTYPE_CONE:
                    planeCutterMapp[whichPlane].SetInput(ConeGlyph[whichPlane].GetOutput());
                    break;
                case GLYPHTYPE_SPHERE:
                    planeCutterMapp[whichPlane].SetInput(SphereGlyph[whichPlane].GetOutput());
                    break;
            }
        }
    }

    /**
     * Method setGlyphScaler.
     * @param glyphType
     * @param whichPlane
     * @param scale
     */
    public void setGlyphScaler(int glyphType, int whichPlane, double scale) {
        if (planeCutterMapp[whichPlane] != null) {
            switch (this.glyphType[whichPlane]) {
                case GLYPHTYPE_PLANE:
                    break;
                case GLYPHTYPE_ARROW:
                    ArrowGlyph[whichPlane].SetScaleFactor(0.5 * scale);
                    break;
                case GLYPHTYPE_CONE:
                    ConeGlyph[whichPlane].SetScaleFactor(0.3 * scale);
                    break;
                case GLYPHTYPE_SPHERE:
                    SphereGlyph[whichPlane].SetScaleFactor(0.04 * scale);
                    break;
            }
        }
    }
}
