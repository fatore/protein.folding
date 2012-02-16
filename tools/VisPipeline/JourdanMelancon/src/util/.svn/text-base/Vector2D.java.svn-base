// $Id: Vector2D.java,v 1.1.1.1 2004/01/27 16:44:54 melancon Exp $
package util;

import java.text.DecimalFormat;

/**
* A simple 2D vector class. Nothing fancy, just the usual.
*/
public class Vector2D implements java.io.Serializable {

    /** Formatter for toString() */
    protected static DecimalFormat df = new DecimalFormat();

    static {
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
    }

    public double x;
    public double y;
    
    public Vector2D(double X, double Y)
    {
        x = X;
        y = Y;
    }
    
    public Vector2D()
    {
        x = 0.0;
        y = 0.0;
    }

    public Vector2D(Vector2D v) 
    {
        x = v.x;
        y = v.y;
    }
    
    public void setValue(double X, double Y)
    {
        x = X;
        y = Y;
    }
    
    public void setValue(Vector2D v)
    {
        x = v.x;
        y = v.y;
    }
    
    /**
    * Accessor method for coordinates of the vector.
    */
    public double getX() {
      return x;
    }
        
    /**
    * Accessor method for coordinates of the vector.
    */
    public double getY() {
      return y;
    }
        
    /**
    * Add a vector to the current
    */
    public void add(Vector2D v)
    {
        x += v.x;
        y += v.y;
    }
    
    /**
    * Substract a vector from the current
    */
    public void sub(Vector2D v)
    {
        x -= v.x;
        y -= v.y;
    }
    
    /**
    * Create a sum vector
    */
    public static Vector2D add(Vector2D v1, Vector2D v2)
    {
        return new Vector2D(v1.x+v2.x,v1.y+v2.y);
    }
    
    /**
    * Create a difference vector
    */
    public static Vector2D sub(Vector2D v1, Vector2D v2)
    {
        return new Vector2D(v1.x-v2.x,v1.y-v2.y);
    }    
    
    /**
    * Quadratic distance from a vector
    */
    public double quadDistance(Vector2D v)
    {
        return (v.x-x)*(v.x-x)+(v.y-y)*(v.y-y);
    }
    
    /**
    * Distance from a vector
    */
    public double distance(Vector2D v)
    {
        return Math.sqrt(quadDistance(v));
    }
    
    /**
    * Distance from a vector
    */
    public double distance(double X, double Y)
    {
        return Math.sqrt((x-X)*(x-X) + (y-Y)*(y-Y));
    }
    
    /**
    * Quadratic norm of the vector
    */
    public double quadNorm()
    {
        return x*x+y*y;
    }
    
    /**
    * Norm of the vector
    */
    public double norm()
    {
        return Math.sqrt(quadNorm());
    }
    
    /**
    * Multiply with a scalar
    */
    public void mult(double d)
    {
        x *= d;
        y *= d;
    }

    /**
    * Scalar multiplication with a vector
    */
    public double mult(Vector2D v)
    {
        return x*v.x + y*v.y;
    }
    
    /**
    * Scalar multiplication of two vectors
    */
    public static double mult(Vector2D v1, Vector2D v2)
    {
        return v1.x*v2.x + v1.y*v2.y;
    }
            
    /**
    * Clone the vector
    */
    public Vector2D duplicate()
    {
        return new Vector2D(x,y);
    }
    
    /**
    * Clone with the signature defined by Java
    */
    public Object clone()
    {
        return duplicate();
    }
    
    /**
    * Return a unit Vector in the same direction
    */
    public Vector2D unit()
    {
        return newWithLength(1.0);
    }
    
    /**
    * Return a vector in the same direction and of a specific length
    */
    public Vector2D newWithLength(double d)
    {
        if( x == 0.0 && y == 0.0 ) {
            return new Vector2D();
        } else {
            Vector2D retval = duplicate();
            retval.mult(d/norm());
            return retval;
        }
    }
    
    /**
    * Override the inherited function. Handy for debug
    */
    public String toString()
    {
        return "(x,y)=(" + df.format(x) + "," + df.format(y) + ")";
    }
}
        
    
        
    