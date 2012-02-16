// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 15/2/2010 12:24:43
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Data.java

package mdsj;

import java.io.PrintStream;
import java.util.Random;

public class Data
{

    public Data()
    {
    }

    public static String format(double matrix[][])
    {
        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
                sb.append((new StringBuilder(String.valueOf(matrix[i][j]))).append(" ").toString());

            sb.append("\n");
        }

        return sb.toString();
    }

    public static void squareDoubleCenter(double matrix[][])
    {
        squareEntries(matrix);
        doubleCenter(matrix);
    }

    public static void squareEntries(double matrix[][])
    {
        int n = matrix[0].length;
        int k = matrix.length;
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < n; j++)
                matrix[i][j] = Math.pow(matrix[i][j], 2D);

        }

    }

    public static void multiply(double matrix[][], double factor)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
                matrix[i][j] *= factor;

        }

    }

    public static double distance(double matrix[][], int i, int j)
    {
        double result = 0.0D;
        for(int m = 0; m < matrix.length; m++)
            result += Math.pow(matrix[m][i] - matrix[m][j], 2D);

        return Math.sqrt(result);
    }

    public static double[][] distanceMatrix(double matrix[][])
    {
        int n = matrix[0].length;
        double result[][] = new double[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                result[i][j] = distance(matrix, i, j);

        }

        return result;
    }

    public static void doubleCenter(double matrix[][])
    {
        int n = matrix[0].length;
        int k = matrix.length;
        for(int j = 0; j < k; j++)
        {
            double avg = 0.0D;
            for(int i = 0; i < n; i++)
                avg += matrix[j][i];

            avg /= n;
            for(int i = 0; i < n; i++)
                matrix[j][i] -= avg;

        }

        for(int i = 0; i < n; i++)
        {
            double avg = 0.0D;
            for(int j = 0; j < k; j++)
                avg += matrix[j][i];

            avg /= matrix.length;
            for(int j = 0; j < k; j++)
                matrix[j][i] -= avg;

        }

    }

    public static double[][] randomPivotMatrix(double matrix[][], int k)
    {
        int n = matrix[0].length;
        double result[][] = new double[k][n];
        boolean isPivot[] = new boolean[n];
        int pivot = 0;
        for(int i = 0; i < k; i++)
        {
            do
                pivot = (int)(Math.random() * (double)n);
            while(isPivot[pivot]);
            isPivot[pivot] = true;
            for(int j = 0; j < n; j++)
                result[i][j] = distance(matrix, pivot, j);

        }

        return result;
    }

    public static double[][] maxminPivotMatrix(double matrix[][], int k)
    {
        int n = matrix[0].length;
        double result[][] = new double[k][n];
        int pivot = 0;
        double min[] = new double[n];
        for(int i = 0; i < n; i++)
            min[i] = 1.7976931348623157E+308D;

        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < n; j++)
                result[i][j] = distance(matrix, pivot, j);

            pivot = 0;
            for(int j = 0; j < n; j++)
            {
                min[j] = Math.min(min[j], result[i][j]);
                if(min[j] > min[pivot])
                    pivot = j;
            }

        }

        return result;
    }

    public static void randomize(double matrix[][])
    {
        Random random = new Random(1L);
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
                matrix[i][j] = 0.5D - random.nextDouble();

        }

    }

    public static double prod(double x[], double y[])
    {
        double result = 0.0D;
        int length = Math.min(x.length, y.length);
        for(int i = 0; i < length; i++)
            result += x[i] * y[i];

        return result;
    }

    public static void selfprod(double d[][], double result[][])
    {
        int k = d.length;
        int n = d[0].length;
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j <= i; j++)
            {
                double sum = 0.0D;
                for(int m = 0; m < n; m++)
                    sum += d[i][m] * d[j][m];

                result[i][j] = sum;
                result[j][i] = sum;
            }

        }

    }

    public static double normalize(double x[])
    {
        double norm = Math.sqrt(prod(x, x));
        for(int i = 0; i < x.length; i++)
            x[i] /= norm;

        return norm;
    }

    public static void scale(double x[][], double D[][])
    {
        int n = x[0].length;
        int d = x.length;
        double xysum = 0.0D;
        double dsum = 0.0D;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                double dxy = 0.0D;
                for(int k = 0; k < d; k++)
                    dxy += Math.pow(x[k][i] - x[k][j], 2D);

                xysum += Math.sqrt(dxy);
                dsum += D[i][j];
            }

        }

        dsum /= xysum;
        for(int i = 0; i < n; i++)
        {
            for(int k = 0; k < d; k++)
                x[k][i] *= dsum;

        }

    }

    public static double[][] pivotRows(double matrix[][], int k)
    {
        int K = matrix.length;
        if(k >= K)
            return matrix;
        int n = matrix[0].length;
        System.out.println((new StringBuilder(String.valueOf(n))).append(" ").append(k).append(" ").append(K).toString());
        double result[][] = new double[k][n];
        int pivot = 0;
        double min[] = new double[n];
        for(int i = 0; i < n; i++)
            min[i] = 1.7976931348623157E+308D;

        for(int i = 0; i < k; i++)
        {
            int argmax = 0;
            for(int j = 0; j < n; j++)
            {
                result[i][j] = matrix[i][pivot];
                min[j] = Math.min(min[j], result[i][j]);
                if(min[j] > min[argmax])
                    argmax = j;
            }

            pivot = argmax;
        }

        return result;
    }

    public static int[] landmarkIndices(double matrix[][])
    {
        int k = matrix.length;
        int n = matrix[0].length;
        int result[] = new int[k];
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < n; j++)
                if(matrix[i][j] == 0.0D)
                    result[i] = j;

        }

        return result;
    }

    public static double[][] landmarkMatrix(double matrix[][])
    {
        int k = matrix.length;
        int n = matrix[0].length;
        double result[][] = new double[k][k];
        int index[] = landmarkIndices(matrix);
        for(int i = 0; i < k; i++)
        {
            for(int j = 0; j < k; j++)
                result[i][j] = matrix[i][index[j]];

        }

        return result;
    }

    public static void normalize(double x[][])
    {
        for(int i = 0; i < x.length; i++)
            normalize(x[i]);

    }
}
