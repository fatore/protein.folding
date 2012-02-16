// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 15/2/2010 12:22:59
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ClassicalScaling.java

package mdsj;

import java.util.Random;

// Referenced classes of package mdsj:
//            Data

public class ClassicalScaling
{

    public ClassicalScaling()
    {
    }

    public static void eigen(double matrix[][], double evecs[][], double evals[])
    {
        double eps = 9.9999999999999995E-007D;
        int maxiter = 100;
        int d = evals.length;
        int n = matrix.length;
        for(int m = 0; m < d; m++)
        {
            if(m > 0)
            {
                for(int i = 0; i < n; i++)
                {
                    for(int j = 0; j < n; j++)
                        matrix[i][j] -= evals[m - 1] * evecs[m - 1][i] * evecs[m - 1][j];

                }

            }
            for(int i = 0; i < n; i++)
                evecs[m][i] = Math.random();

            Data.normalize(evecs[m]);
            double r = 0.0D;
            for(int iter = 0; Math.abs(1.0D - r) > 9.9999999999999995E-007D && iter < 100; iter++)
            {
                double q[] = new double[n];
                for(int i = 0; i < n; i++)
                {
                    for(int j = 0; j < n; j++)
                        q[i] += matrix[i][j] * evecs[m][j];

                }

                evals[m] = Data.prod(evecs[m], q);
                Data.normalize(q);
                r = Math.abs(Data.prod(evecs[m], q));
                evecs[m] = q;
            }

        }

    }

    public static double largestEigenvalue(double matrix[][])
    {
        double eps = 9.9999999999999995E-007D;
        int maxiter = 100;
        int n = matrix.length;
        double lambda = 0.0D;
        double x[] = new double[n];
        for(int i = 0; i < n; i++)
            x[i] = 1.0D;

        double r = 0.0D;
        for(int iter = 0; Math.abs(1.0D - r) > 9.9999999999999995E-007D && iter < 100; iter++)
        {
            double q[] = new double[n];
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                    q[i] += matrix[i][j] * x[j];

            }

            lambda = Data.prod(x, q);
            Data.normalize(q);
            r = Math.abs(Data.prod(x, q));
            x = q;
        }

        return lambda;
    }

    public static double smallestEigenvalue(double matrix[][])
    {
        double rho = largestEigenvalue(matrix);
        double eps = 9.9999999999999995E-007D;
        int maxiter = 100;
        int n = matrix.length;
        double lambda = 0.0D;
        double x[] = new double[n];
        Random random = new Random(1L);
        for(int i = 0; i < n; i++)
            x[i] = 0.5D - random.nextDouble();

        Data.normalize(x);
        double r = 0.0D;
        for(int iter = 0; Math.abs(1.0D - r) > 9.9999999999999995E-007D && iter < 100; iter++)
        {
            double q[] = new double[n];
            for(int i = 0; i < n; i++)
            {
                q[i] -= rho * x[i];
                for(int j = 0; j < n; j++)
                    q[i] += matrix[i][j] * x[j];

            }

            lambda = Data.prod(x, q);
            Data.normalize(q);
            r = Math.abs(Data.prod(x, q));
            x = q;
        }

        return lambda + rho;
    }

    public static double[] pivotmds(double input[][], double result[][])
    {
        double evals[] = new double[result.length];
        Data.doubleCenter(input);
        Data.multiply(input, -0.5D);
        svd(input, result, evals);
        for(int i = 0; i < result.length; i++)
        {
            for(int j = 0; j < result[0].length; j++)
                result[i][j] *= Math.sqrt(evals[i]);

        }

        return evals;
    }

    public static double[] lmds(double P[][], double result[][])
    {
        double distances[][] = new double[P.length][P[0].length];
        for(int i = 0; i < distances.length; i++)
        {
            for(int j = 0; j < distances[0].length; j++)
                distances[i][j] = P[i][j];

        }

        Data.squareEntries(distances);
        int k = distances.length;
        int n = distances[0].length;
        int d = result.length;
        double mean[] = new double[n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < k; j++)
                mean[i] += distances[j][i];

        }

        for(int i = 0; i < n; i++)
            mean[i] /= k;

        double lambda[] = new double[d];
        double temp[][] = new double[d][k];
        Data.randomize(temp);
        double K[][] = Data.landmarkMatrix(P);
        Data.squareEntries(K);
        Data.doubleCenter(K);
        Data.multiply(K, -0.5D);
        eigen(K, temp, lambda);
        for(int i = 0; i < temp.length; i++)
        {
            for(int j = 0; j < temp[0].length; j++)
                temp[i][j] *= Math.sqrt(lambda[i]);

        }

        for(int m = 0; m < d; m++)
        {
            for(int i = 0; i < n; i++)
            {
                result[m][i] = 0.0D;
                for(int j = 0; j < k; j++)
                    result[m][i] -= (0.5D * (distances[j][i] - mean[i]) * temp[m][j]) / lambda[m];

            }

        }

        return lambda;
    }

    private static void svd(double matrix[][], double svecs[][], double svals[])
    {
        int k = matrix.length;
        int n = matrix[0].length;
        int d = svecs.length;
        for(int m = 0; m < d; m++)
            svals[m] = Data.normalize(svecs[m]);

        double K[][] = new double[k][k];
        Data.selfprod(matrix, K);
        double temp[][] = new double[d][k];
        for(int m = 0; m < d; m++)
        {
            for(int i = 0; i < k; i++)
            {
                for(int j = 0; j < n; j++)
                    temp[m][i] += matrix[i][j] * svecs[m][j];

            }

        }

        for(int m = 0; m < d; m++)
            svals[m] = Data.normalize(svecs[m]);

        eigen(K, temp, svals);
        double tempOld[][] = new double[d][k];
        for(int m = 0; m < d; m++)
        {
            for(int i = 0; i < k; i++)
            {
                for(int j = 0; j < k; j++)
                    tempOld[m][j] += K[i][j] * temp[m][i];

            }

        }

        for(int m = 0; m < d; m++)
            svals[m] = Data.normalize(tempOld[m]);

        for(int m = 0; m < d; m++)
        {
            svals[m] = Math.sqrt(svals[m]);
            for(int i = 0; i < n; i++)
            {
                svecs[m][i] = 0.0D;
                for(int j = 0; j < k; j++)
                    svecs[m][i] += matrix[j][i] * temp[m][j];

            }

        }

        for(int m = 0; m < d; m++)
            Data.normalize(svecs[m]);

    }

    public static double[] fullmds(double distances[][], double result[][])
    {
        double evals[] = new double[result.length];
        Data.squareEntries(distances);
        Data.doubleCenter(distances);
        Data.multiply(distances, -0.5D);
        eigen(distances, result, evals);
        for(int i = 0; i < result.length; i++)
        {
            if(evals[i] > 0.0D)
                evals[i] = Math.sqrt(evals[i]);
            for(int j = 0; j < result[0].length; j++)
                result[i][j] *= evals[i];

        }

        return evals;
    }
}
