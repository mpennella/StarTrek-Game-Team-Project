/*
 * Written by Isaac Roberts
 */

package cpsc3720.util;

import java.util.*;
import java.awt.*;

public class Util {
    
    /*
    
    A class for general utility functions
    
    Just things that you would be writing over and over,
        that don't make sense to be in a particular class.
        Most of these probably wont' be useful in TSG2:EB, but I already had them written.
    
    */
    
    
    
    public static double sqrdDist(Coord p1,Coord p2)
    {
        return Math.pow(p1.x()-p2.x(),2) + Math.pow(p1.y()-p2.y(),2); 
    }
    public static int distance(Coord p1,Coord p2)
    {
        return Math.abs(p1.x()-p2.x()) + Math.abs(p1.y()-p2.y()); 
    }
    public static double pythDist(Coord p1,Coord p2)
    {
        return Math.sqrt(sqrdDist(p1,p2));
    }
    public static Rectangle makeRect(int x1,int y1,int x2,int y2)
    {  //make a rectangle thats (lowest point, highest-lowest point)
        if (x2<x1)
        {
            int temp=x1;
            x1=x2;
            x2=temp;
        }
        if (y2<y1)
        {
            int temp=y1;
            y1=y2;
            y2=temp;
        }
        return new Rectangle(x1,y1,x2-x1,y2-y1);
    }
    public static Rectangle makeRect(int x1,int y1,int x2,int y2,int addWidth,int addLength)
    {  //make a rectangle thats (lowest point, highest-lowest point)
        if (x2<x1)
        {
            int temp=x1;
            x1=x2;
            x2=temp;
        }
        if (y2<y1)
        {
            int temp=y1;
            y1=y2;
            y2=temp;
        }
        return new Rectangle(x1,y1,x2-x1 +addWidth,y2-y1 +addLength);
    }
    public static boolean broken(float f)
    {
        return (Float.isInfinite(f)||Float.isNaN(f));
    }
    public static int roundUp(double d)
    {
        if (d%1.0>0)
            d+=1;
        return (int)d;
    }
    public static int min(int a,int b)
    {
        if (a>b)
            return b;
        else return a;
    }
    public static int max(int a,int b)
    {
        if (a>b)
            return a;
        else return b;
    }
    public static float min(float a,float b)
    {
        if (a>b)
            return b;
        else return a;
    }
    public static float max(float a,float b)
    {
        if (a>b)
            return a;
        else return b;
    }
    public static int colorBound(double a)
    {
        if (a>255)
            a=255;
        else if (a<0)
            a=0;
        return (int)a;
    }
    public static int colorBound(int a)
    {
        if (a>255)
            a=255;
        else if (a<0)
            a=0;
        return a;
    }
    public static Color makeValid(int red,int grn,int blu,int alpha)
    {
        return new Color(colorBound(red),colorBound(grn),colorBound(blu),colorBound(alpha));
    }
    public static Color makeValid(float red,float grn,float blu,float alpha)
    {
        return new Color(colorBound(red),colorBound(grn),colorBound(blu),colorBound(alpha));
    }
    public static Color makeValid(float r,float g,float b) {
        return makeValid((int)r,(int)g,(int)b);
    }
    public static Color makeValid(double r,double g,double b) {
        return makeValid((int)r,(int)g,(int)b);
    }
    public static Color makeValid(int red,int grn,int blu)
    {
        return new Color(colorBound(red),colorBound(grn),colorBound(blu));
    }
    public static float factorial(float n)
    {
        if (n>=100)
        {
            float pow= n/125 + n/5 + n/25 + n/625;
            return (float)Math.pow(10,pow);
        }
        if (n<=1)
            return 1;
        return n*factorial(n-1);
    }
    public static class intDubPair 
    {
        int integer;
        double dub;
        public intDubPair(int setint,double setdub) {
            integer=setint;
            dub=setdub;
        }
    }
    
    
}
