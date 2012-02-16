// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 15/2/2010 12:24:48
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StressMinimization.java

package mdsj;


// Referenced classes of package mdsj:
//            Data

public class StressMinimization
{

    public StressMinimization(double d[][], double x[][], double w[][])
    {
        this.x = x;
        this.d = d;
        this.w = w;
    }

    public StressMinimization(double d[][], double x[][])
    {
        this.x = x;
        this.d = d;
        w = weightMatrix(d, 0.0D);
    }

    public double[][] getDissimilarities()
    {
        return d;
    }

    public double[][] getWeights()
    {
        return w;
    }

    public double[][] getPositions()
    {
        return x;
    }

    public void setDissimilarities(double d[][])
    {
        this.d = d;
    }

    public void setWeights(double w[][])
    {
        this.w = w;
    }

    public void setPositions(double x[][])
    {
        this.x = x;
    }

    public String iterate()
    {
        return iterate(1);
    }

    public String iterate(int n)
    {
        return majorize(x, d, w, n, 0, 0);
    }

    public String iterate(int iter, int timeout, int threshold)
    {
        return majorize(x, d, w, iter, timeout, threshold);
    }

    public double getStress()
    {
        return stress(d, w, x);
    }

    public double getNormalizedStress()
    {
        return normalizedStress(d, w, x);
    }

    public static double[][] weightMatrix(double D[][], double exponent)
    {
        int n = D[0].length;
        int k = D.length;
        double result[][] = new double[k][n];
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < n; j++)
                if(D[i][j] > 0.0D)
                    result[i][j] = Math.pow(D[i][j], exponent);

        }

        return result;
    }

    public static String majorize(double x[][], double d[][], double w[][], int iter, int threshold, int timeout)
    {
        String report = "";
        int n = x[0].length;
        int k = d.length;
        int dim = x.length;
        int index[] = Data.landmarkIndices(d);
        double wSum[] = new double[n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < k; j++)
                wSum[i] += w[j][i];

        }

        double eps = Math.pow(10D, -threshold);
        long time = System.nanoTime();
        if(iter == 0)
            iter = 0x989680;
        for(int c = 0; c < iter; c++)
        {
            double change = 0.0D;
            double magnitude = 0.0D;
            for(int i = 0; i < n; i++)
            {
                double xnew[] = new double[dim];
                for(int j = 0; j < k; j++)
                {
                    double inv = 0.0D;
                    for(int m = 0; m < dim; m++)
                        inv += Math.pow(x[m][i] - x[m][index[j]], 2D);

                    if(inv != 0.0D)
                        inv = Math.pow(inv, -0.5D);
                    for(int m = 0; m < dim; m++)
                        xnew[m] += w[j][i] * (x[m][index[j]] + d[j][i] * (x[m][i] - x[m][index[j]]) * inv);

                }

                if(wSum[i] != 0.0D)
                {
                    for(int m = 0; m < dim; m++)
                    {
                        change += Math.pow(xnew[m] / wSum[i] - x[m][i], 2D);
                        magnitude += Math.pow(x[m][i], 2D);
                        x[m][i] = xnew[m] / wSum[i];
                    }

                }
            }

            change = Math.sqrt(change / magnitude);
            long timediff = (System.nanoTime() - time) / 0xf4240L;
            if(timeout > 0 && timediff > (long)timeout)
                return (new StringBuilder(String.valueOf(c + 1))).append(" iterations, ").append(timediff).append(" milliseconds, ").append(change).append(" relative change").toString();
            if(threshold > 0 && change < eps)
                return (new StringBuilder(String.valueOf(c + 1))).append(" iterations, ").append(timediff).append(" milliseconds, ").append(change).append(" relative change").toString();
            if(iter > 0 && c >= iter - 1)
                report = (new StringBuilder(String.valueOf(c + 1))).append(" iterations, ").append(timediff).append(" milliseconds, ").append(change).append(" relative change").toString();
        }

        return report;
    }

    public static void majorize(double x[][], double d[][], double w[][], int iter)
    {
        int n = x[0].length;
        int dim = x.length;
        double wSum[] = new double[n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                wSum[i] += w[i][j];

        }

        for(int c = 0; c < iter; c++)
        {
            for(int i = 0; i < n; i++)
            {
                double xnew[] = new double[dim];
                for(int j = 0; j < n; j++)
                {
                    double inv = 0.0D;
                    for(int k = 0; k < dim; k++)
                        inv += Math.pow(x[k][i] - x[k][j], 2D);

                    if(inv != 0.0D)
                        inv = Math.pow(inv, -0.5D);
                    for(int k = 0; k < dim; k++)
                        xnew[k] += w[i][j] * (x[k][j] + d[i][j] * (x[k][i] - x[k][j]) * inv);

                }

                if(wSum[i] != 0.0D)
                {
                    for(int k = 0; k < dim; k++)
                        x[k][i] = xnew[k] / wSum[i];

                }
            }

        }

    }

    public static double stress(double d[][], double w[][], double x[][])
    {
        double result = 0.0D;
        int n = x[0].length;
        int k = d.length;
        int dim = x.length;
        int index[] = Data.landmarkIndices(d);
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < n; j++)
            {
                double dist = 0.0D;
                for(int m = 0; m < dim; m++)
                    dist += Math.pow(x[m][index[i]] - x[m][j], 2D);

                result += w[i][j] * Math.pow(d[i][j] - Math.sqrt(dist), 2D);
            }

        }

        return result;
    }

    public static double normalizedStress(double d[][], double w[][], double x[][])
    {
        double result = 0.0D;
        int n = x[0].length;
        int k = d.length;
        int dim = x.length;
        int index[] = Data.landmarkIndices(d);
        double sum = 0.0D;
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < n; j++)
            {
                double dist = 0.0D;
                for(int m = 0; m < dim; m++)
                    dist += Math.pow(x[m][index[i]] - x[m][j], 2D);

                result += w[i][j] * Math.pow(d[i][j] - Math.sqrt(dist), 2D);
                sum += w[i][j] * Math.pow(d[i][j], 2D);
            }

        }

        return result / sum;
    }

    private double x[][];
    private double d[][];
    private double w[][];
}
