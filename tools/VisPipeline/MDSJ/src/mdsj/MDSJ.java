// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 15/2/2010 12:24:52
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MDSJ.java

package mdsj;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Arrays;

// Referenced classes of package mdsj:
//            Data, ClassicalScaling, StressMinimization, IO

public class MDSJ
{

    public static double[][] classicalScaling(double d[][], int dim)
    {
        int n = d[0].length;
        double dist[][] = new double[d.length][d[0].length];
        for(int i = 0; i < d.length; i++)
        {
            for(int j = 0; j < d[0].length; j++)
                dist[i][j] = d[i][j];

        }

        double result[][] = new double[dim][n];
        Data.randomize(result);
        ClassicalScaling.lmds(dist, result);
        return result;
    }

    public static double[][] classicalScaling(double d[][])
    {
        return classicalScaling(d, 2);
    }

    public static double[][] stressMinimization(double d[][], int dim)
    {
        double x[][] = classicalScaling(d, dim);
        StressMinimization sm = new StressMinimization(d, x);
        sm.iterate(0, 0, 5);
        return x;
    }

    public static double[][] stressMinimization(double d[][], double w[][], int dim)
    {
        double x[][] = classicalScaling(d, dim);
        StressMinimization sm = new StressMinimization(d, x, w);
        sm.iterate(0, 0, 3);
        return x;
    }

    public static double[][] stressMinimization(double d[][])
    {
        return stressMinimization(d, 2);
    }

    public static double[][] stressMinimization(double d[][], double w[][])
    {
        return stressMinimization(d, w, 2);
    }

    private static String align(double val)
    {
        String temp = (new DecimalFormat("0.######")).format(val);
        int b = temp.indexOf(".");
        if(b < 0)
            b = temp.length();
        int prefix = Math.max(0, 8 - b);
        int suffix = 15 - prefix - temp.length();
        return (new StringBuilder(String.valueOf("                                              ".substring(0, prefix)))).append(temp).append("                                              ".substring(0, suffix)).toString();
    }

    private void printAnalysis(double distances[][])
    {
        DecimalFormat format = new DecimalFormat(" 0.######;-0.######");
        int n = distances.length;
        Data.squareEntries(distances);
        Data.doubleCenter(distances);
        Data.multiply(distances, -0.5D);
        double trace = 0.0D;
        for(int i = 0; i < n; i++)
            trace += distances[i][i];

        System.out.println((new StringBuilder("  trace: ")).append(format.format(trace)).toString());
        double lambda[] = new double[dimensions];
        double temp[][] = new double[dimensions][n];
        Data.randomize(temp);
        double a = ClassicalScaling.smallestEigenvalue(distances);
        double b = ClassicalScaling.largestEigenvalue(distances);
        System.out.println((new StringBuilder("  spectral range: [")).append(format.format(Math.min(a, b))).append(", ").append(format.format(Math.max(a, b))).append("]").toString());
        ClassicalScaling.eigen(distances, temp, lambda);
        for(int i = 0; i < dimensions; i++)
            if((new Double(lambda[i])).equals(Double.valueOf((0.0D / 0.0D))))
                lambda[i] = 0.0D;

        Arrays.sort(lambda);
        double newLambda[] = new double[dimensions];
        for(int i = 0; i < dimensions; i++)
            newLambda[dimensions - i - 1] = lambda[i];

        lambda = newLambda;
        System.out.println("  --------------------------------------------------------------------");
        System.out.println("    dim    eigenvalue     relative (%)   cumulative (%) residual (%)  ");
        System.out.println("  --------------------------------------------------------------------");
        double cumul = 0.0D;
        for(int i = 0; i < dimensions; i++)
        {
            cumul += lambda[i];
            System.out.println((new StringBuilder("    ")).append(i).append(align(lambda[i])).append(align((100D * lambda[i]) / trace)).append(align((100D * cumul) / trace)).append(align(100D * (1.0D - cumul / trace))).toString());
        }

        System.out.println("  --------------------------------------------------------------------");
    }

    public MDSJ(String args[])
        throws Exception
    {
        accuracy = 5;
        dimensions = 2;
        exponent = 0;
        quiet = false;
        rounds = 0;
        stresschange = 0;
        timeout = 0;
        int z = 2;
        if(!args[args.length - 1].startsWith("-"))
            infile = args[args.length - 1];
        else
            throw new Exception((new StringBuilder("parsing error, last option is not a file: \"")).append(args[args.length - 1]).append("\"").toString());
        if(args.length > 1 && !args[args.length - 2].startsWith("-"))
        {
            outfile = args[args.length - 1];
            infile = args[args.length - 2];
        } else
        {
            z = 1;
        }
        for(int i = 0; i < args.length - z; i++)
        {
            if(!args[i].startsWith("-"))
                throw new Exception((new StringBuilder("parsing error, options must start with a minus: \"")).append(args[i]).append("\"").toString());
            String option = args[i].substring(1);
            try
            {
                if(option.startsWith("c"))
                {
                    accuracy = Integer.parseInt(option.substring(1));
                    if(accuracy < 0)
                        throw new Exception((new StringBuilder("option error, accuracy must be nonnegative: ")).append(accuracy).toString());
                } else
                if(option.startsWith("d"))
                {
                    dimensions = Integer.parseInt(option.substring(1));
                    if(dimensions <= 0)
                        throw new Exception((new StringBuilder("option error, dimensionality must be positive: ")).append(dimensions).toString());
                } else
                if(option.startsWith("e"))
                    exponent = Integer.parseInt(option.substring(1));
                else
                if(option.startsWith("q"))
                    quiet = true;
                else
                if(option.startsWith("r"))
                {
                    rounds = Integer.parseInt(option.substring(1));
                    if(rounds <= 0)
                        throw new Exception((new StringBuilder("option error, number of rounds must be positive: ")).append(rounds).toString());
                } else
                if(option.startsWith("s"))
                {
                    stresschange = Integer.parseInt(option.substring(1));
                    if(stresschange <= 0)
                        throw new Exception((new StringBuilder("option error, threshold must be positive: ")).append(stresschange).toString());
                } else
                if(option.startsWith("t"))
                {
                    timeout = Integer.parseInt(option.substring(1));
                    if(timeout <= 0)
                        throw new Exception((new StringBuilder("option error, timeout (milliseconds) must be positive: ")).append(timeout).toString());
                } else
                {
                    throw new Exception((new StringBuilder("parsing error, unknown option: \"")).append(args[i]).append("\"").toString());
                }
            }
            catch(Exception e)
            {
                throw e;
            }
        }

    }

    public static void main(String args[]) throws Exception
    {
        if(args.length == 0)
        {
            System.out.println("MDSJ - Multidimensional Scaling for Java v0.2  (c) 2009 University of Konstanz\n  usage: MDSJ  [options]  infile  [outfile]        see manual for more details\n  options \n   -dD   output dimensions (default: D=2)\n   -eE   use exponential weight=dissimilarity^E (default: E=0)\n   -fF   data format: -fm matrix (default), -fl list, -fo optimized list \n   -q    quiet mode, do not print analysis results\n   -rR   stress minimization for at most R rounds\n   -sS   stress minimization until relative change is below 10^(-S)\n   -tT   stress minimization for up to T milliseconds\n  example:  MDSJ  -t2000  -s4 -d3  -q  in.txt  out.txt\n  -rR -sS -tT may be used jointly (termination once any condition holds)\n  output is written to stdout when no outfile specified");
            return;
        }
        double result[][];
        MDSJ mdsj = new MDSJ(args);
        if(!mdsj.quiet)
            System.out.println("MDSJ - Multidimensional Scaling for Java v0.2  (c) 2009 University of Konstanz");
        long time = System.nanoTime();
        if(!mdsj.quiet)
            System.out.print("  ...reading input file... ");
        double D[][] = IO.read(mdsj.infile);
        int k = D.length;
        if(!mdsj.quiet)
        {
            System.out.println((new StringBuilder("done (")).append((System.nanoTime() - time) / 0xf4240L).append(" ms)").toString());
            System.out.println((new StringBuilder("  read ")).append(D[0].length).append(" rows, ").append(D.length).append(" columns").toString());
        }
        if(mdsj.dimensions >= k)
        {
            if(!mdsj.quiet)
            {
                System.out.println((new StringBuilder("Warning: ")).append(mdsj.dimensions).append(" dimensions, but only ").append(k).append(" pivot(s) present, ").append(mdsj.dimensions + 1).append(" required").toString());
                System.out.println((new StringBuilder("Outputting only ")).append(k - 1).append(" dimensions (consider using more pivots)").toString());
            }
            mdsj.dimensions = k - 1;
        }
        if(!mdsj.quiet)
            System.out.println((new StringBuilder("  using ")).append(k).append(" pivots for ").append(mdsj.dimensions).append(" dimensions").toString());
        double L[][] = Data.landmarkMatrix(D);
        if(!mdsj.quiet)
            mdsj.printAnalysis(L);
        result = new double[mdsj.dimensions][D[0].length];
        Data.randomize(result);
        ClassicalScaling.lmds(D, result);
        if(mdsj.rounds > 0 || mdsj.stresschange > 0 || mdsj.timeout > 0)
        {
            StressMinimization sm = new StressMinimization(D, result, StressMinimization.weightMatrix(D, mdsj.exponent));
            if(!mdsj.quiet)
                System.out.println((new StringBuilder("  normalized stress = ")).append(sm.getNormalizedStress()).toString());
            String report = sm.iterate(mdsj.rounds, mdsj.stresschange, mdsj.timeout);
            if(!mdsj.quiet)
            {
                System.out.println((new StringBuilder("  ")).append(report).toString());
                System.out.println((new StringBuilder("  normalized stress = ")).append(sm.getNormalizedStress()).toString());
            }
        }
        if(mdsj.outfile != null)
        {
            time = System.nanoTime();
            if(!mdsj.quiet)
                System.out.print("  ...writing output file... ");
            IO.write(result, mdsj.outfile);
            if(!mdsj.quiet)
            {
                System.out.println((new StringBuilder("done (")).append((System.nanoTime() - time) / 0xf4240L).append(" ms)").toString());
                System.out.println((new StringBuilder("  wrote ")).append(result[0].length).append(" rows, ").append(result.length).append(" columns").toString());
                return;
            }
        }
        try
        {
            IO.writeStandardOut(result);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return;
    }

    private int accuracy;
    private int dimensions;
    private int exponent;
    private boolean quiet;
    private int rounds;
    private int stresschange;
    private int timeout;
    private String infile;
    private String outfile;
}
