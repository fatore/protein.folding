package br.usp.pf.app;

import distance.DistanceMatrix;
import java.io.IOException;
import br.usp.pf.projections.FastForceScheme2D;

public class ProjectionApp {

	public static void project(String dmatFile, int printInterval) throws IOException {
		
		DistanceMatrix dmat = new DistanceMatrix(dmatFile);
		
		FastForceScheme2D ff = new FastForceScheme2D();
		ff.setEnergy(-84.0f);
		ff.setPrintInterval(printInterval);
		ff.project(dmat);
		
	}

	public static void main(String[] args) throws IOException {
		int noConf = 1200;
		String type = "11";
		ProjectionApp.project("data/23-09-11/"+ noConf +"/" + type + ".dmat", 99999999);
	}
}
