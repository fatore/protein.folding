package br.usp.pf.app;

import distance.DistanceMatrix;
import java.io.IOException;

import matrix.AbstractMatrix;

import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;
import br.usp.pf.projections.FastForceScheme2D;

public class ProjectionApp {

	public static void project(String dmatFile, int printInterval) throws IOException {
		
		DistanceMatrix dmat = new DistanceMatrix(dmatFile);
		
		FastForceScheme2D ff = new FastForceScheme2D();
		//ff.setEnergy(-84.0f);
		ff.setPrintInterval(printInterval);
		AbstractMatrix projection = ff.project(dmat);
		
		ProjectionModelComp model = new ProjectionModelComp();;
		ProjectionFrameComp frame = new ProjectionFrameComp();;
		
		model.input(projection);
		model.execute();

		frame.setTitle(dmatFile);
		frame.input(model.output());
		frame.execute();
		
	}

	public static void main(String[] args) throws IOException {
		int noConf = 1200;
		String type = "7-1-0-count";
		ProjectionApp.project("data/23-09-11/"+ noConf +"/" + type + ".dmat", 99999999);
	}
}
