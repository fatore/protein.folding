package br.usp.pf.app;

import java.io.IOException;

import matrix.AbstractMatrix;
import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;
import br.usp.pf.projections.FastForceScheme2D;
import br.usp.pf.projections.ForceScheme2D;
import distance.DistanceMatrix;

public class ProjectionApp {

	public static void project(String dmatFile, int printInterval, boolean fast) throws IOException {
		
		DistanceMatrix dmat = new DistanceMatrix(dmatFile);
		AbstractMatrix projection = null;
		
		if (fast) {
			FastForceScheme2D ff = new FastForceScheme2D();
			ff.setPrintInterval(printInterval);
			projection = ff.project(dmat);
		} else {
			ForceScheme2D ff = new ForceScheme2D();
			ff.setPrintInterval(printInterval);
			projection = ff.project(dmat);
		}
		
		ProjectionModelComp model = new ProjectionModelComp();;
		ProjectionFrameComp frame = new ProjectionFrameComp();;
		
		model.input(projection);
		model.execute();

		frame.setTitle(dmatFile);
		frame.input(model.output());
		frame.execute();
		
	}

	public static void main(String[] args) throws IOException {
		String noConf = "1200";
		String type = "50-1-0-max";
		ProjectionApp.project("data/23-09-11/"+ noConf +"/" + type + ".dmat", 100100, false);
	}
}
