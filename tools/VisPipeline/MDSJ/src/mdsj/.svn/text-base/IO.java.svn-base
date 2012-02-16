// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 15/2/2010 12:24:18
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IO.java

package mdsj;

import java.io.*;

public class IO
{

    public IO()
    {
    }

    public static double[][] read(String filename)
        throws Exception
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int n = 0;
            String line = "";
            while(reader.ready()) 
            {
                line = reader.readLine();
                n++;
            }
            String strings[] = line.split(" +?");
            int k = 0;
            for(int i = 0; i < strings.length; i++)
                if(strings[i].length() > 0)
                    k++;

            reader.close();
            double data[][] = new double[k][n];
            reader = new BufferedReader(new FileReader(filename));
            for(int i = 0; reader.ready(); i++)
            {
                k = 0;
                line = reader.readLine();
                strings = line.split(" +?");
                for(int j = 0; j < strings.length; j++)
                    if(strings[j].length() > 0)
                    {
                        double val = Double.parseDouble(strings[j].trim());
                        if(val < 0.0D)
                            throw new Exception("parsing error: negative dissimilarity");
                        data[k][i] = val;
                        k++;
                    }

            }

            return data;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public static void write(double data[][], String filename)
        throws Exception
    {
        try
        {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for(int j = 0; j < data[0].length; j++)
            {
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < data.length; i++)
                    sb.append((new StringBuilder(String.valueOf(data[i][j]))).append(" ").toString());

                out.println(sb.toString().trim());
            }

            out.close();
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public static void writeStandardOut(double data[][])
        throws Exception
    {
        try
        {
            PrintWriter out = new PrintWriter(System.out);
            for(int j = 0; j < data[0].length; j++)
            {
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < data.length; i++)
                    sb.append((new StringBuilder(String.valueOf(data[i][j]))).append(" ").toString());

                out.println(sb.toString().trim());
            }

            out.close();
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}
