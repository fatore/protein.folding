/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection3d.reproject;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import plsp.technique.PLSPProjection2DComp;
import projection.technique.idmap.IDMAPProjectionComp;
import projection.technique.isomap.ISOMAPProjectionComp;
import projection.technique.lle.LLEProjectionComp;
import projection.technique.lsp.LSPProjection2DComp;
import projection.technique.mds.ClassicalMDSProjectionComp;
import projection.technique.pca.PCAProjectionComp;
import projection.technique.projclus.ProjClusProjectionComp;
import projection.technique.sammon.SammonMappingProjectionComp;
import projection3d.reproject.OrthogonalProjection.OrthogonalPlane;
import vispipeline.view.WizardDialog;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vtk.vtkRenderer;


/**
 *
 * @author jpocom
 */
public class ReprojectionFactory {

    public enum ProjectionType {

        VIEW, ORTHOGONAL, IDMAP, ISOMAP, LLE, LSP, MDS, PCA, PLSP, PROJClus, SammonMapping, SampleProj, FASTMAP
    }

    public static AbstractMatrix runInstance(ProjectionType type, vtkRenderer ren, AbstractMatrix input) throws IOException {
        if (type.equals(ProjectionType.VIEW)) {
            ViewProjection proj = new ViewProjection(ren);
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(DissimilarityType.COSINE_BASED);
            return proj.project(input, diss);
        } else if (type.equals(ProjectionType.ORTHOGONAL)) {
            OrthogonalProjection  proj = new OrthogonalProjection(OrthogonalPlane.XY);
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(DissimilarityType.COSINE_BASED);
            return proj.project(input, diss);
        } else if (type.equals(ProjectionType.IDMAP)) {
            IDMAPProjectionComp comp = new IDMAPProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.ISOMAP)) {
            ISOMAPProjectionComp comp = new ISOMAPProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.LLE)) {
            LLEProjectionComp comp = new LLEProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.LSP)) {
            LSPProjection2DComp comp = new LSPProjection2DComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.MDS)) {
            ClassicalMDSProjectionComp comp = new ClassicalMDSProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        }  else if (type.equals(ProjectionType.PCA)) {
            PCAProjectionComp comp = new PCAProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.PLSP)) {
            PLSPProjection2DComp comp = new PLSPProjection2DComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.PROJClus)) {
            ProjClusProjectionComp comp = new ProjClusProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } else if (type.equals(ProjectionType.SammonMapping)) {
            SammonMappingProjectionComp comp = new SammonMappingProjectionComp();
            showDialog(comp);
            comp.input(input);
            comp.execute();
            return comp.output();
        } 

        return null;
    }


    private static int showDialog(AbstractComponent comp) {
        int result = WizardDialog.SUCESS;
        AbstractParametersView parameditor = comp.getParametersEditor();
        if (parameditor != null) {
            parameditor.reset();
            WizardDialog dialog = WizardDialog.getInstance(null, parameditor);
            result = dialog.display();
        }
        return result;
    }
}
