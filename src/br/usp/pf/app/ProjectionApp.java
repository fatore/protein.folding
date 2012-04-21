package br.usp.pf.app;

import java.io.File;
import java.io.IOException;

import matrix.AbstractMatrix;
import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;
import br.usp.pf.projection.FastForceScheme;
import br.usp.pf.projection.ForceScheme;
import distance.DistanceMatrix;

public class ProjectionApp {

	public static void project(String dmatFile, int printInterval, boolean fast) throws IOException {
		
		DistanceMatrix dmat = new DistanceMatrix(dmatFile);
		AbstractMatrix projection = null;
		
		if (fast) {
			FastForceScheme ff = new FastForceScheme();
			ff.setPrintInterval(printInterval);
			projection = ff.project(dmat);
		} else {
			ForceScheme ff = new ForceScheme();
			ff.setPrintInterval(printInterval);
			projection = ff.project(dmat);
		}
		
		File file = new File(dmatFile);
		projection.save(file.getParentFile().getPath() + "/" + file.getName().split("\\.")[0] + ".prj");
		
		ProjectionModelComp model = new ProjectionModelComp();;
		ProjectionFrameComp frame = new ProjectionFrameComp();;
		
		model.input(projection);
		model.execute();

		frame.setTitle(dmatFile);
		frame.input(model.output());
		frame.execute();
		
	}

	public static void main(String[] args) throws IOException {
		String sequence = "43157";
		int gaps = 1000;
		int cut = 3;
		
		String cutString;
		if (cut > 0) {
			cutString = "cut" + cut;
		} else {
			cutString = "full";
		}
		
		String folder;
		
		// static
		folder = "/work1/wokspace/pf/data/" + sequence + "/estatico/" + gaps + "/" + cutString + "/";
		ProjectionApp.project(folder + "comparation.dmat", 1000000, false);
//		ProjectionApp.project(folder + "static.dmat", 1000000, false);
//		ProjectionApp.project(folder + "dynamic.dmat", 100000, false);
		
		// dynamic
//		folder = "/work1/wokspace/pf/data/" + sequence + "/dinamico/" + gaps + "/" + cutString + "/";
		
	}
}
