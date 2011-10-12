package br.usp.pf.app;

import distance.DistanceMatrix;
import java.io.IOException;

import br.usp.pf.projections.CreateDistanceMatrix;
import br.usp.pf.projections.FastForceScheme2D;
import br.usp.pf.projections.MemoryDump;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;

public class ProjectionApp {

	public static void project(String dyFile, String distFile, float w1,
			float w2, float w3, int printInterval)
			throws IOException {
		MemoryDump.dump();

		// arquivo de conformações (DY)
		AbstractMatrix matrix = MatrixFactory.getInstance(dyFile);

		// Cria a matriz de distâncias combinando os valores desejados
		CreateDistanceMatrix cdm = new CreateDistanceMatrix();

		DistanceMatrix dmat;
			
		dmat = cdm.execute(matrix, w1, w2, w3, distFile);

		// faz a projeção
		FastForceScheme2D ff = new FastForceScheme2D();
		// número de iterações
		// fixa na projeção (estado nativo)
		ff.setEnergy(-84.0f);
		ff.setPrintInterval(printInterval);
		ff.project(dmat);
		
		MemoryDump.finished();
	}

	public static void main(String[] args) throws IOException {
		int noConf = 600;
		ProjectionApp.project(
				"data/23-09-11/"+ noConf +"/dy_file.data",
				"data/23-09-11/"+ noConf + "/dist_file.data", 
				0, 0, 1, 50000);
	}
}
