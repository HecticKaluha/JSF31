/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import ThreadManagement.EdgeGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

/**
 *
 * @author Stefan
 */
public class KochManager
{
    private JSF31KochFractalFX application;
    //private KochFractal kochFractal;
    private KochFractal kochFractal1 = new KochFractal();
    private KochFractal kochFractal2 = new KochFractal();
    private KochFractal kochFractal3 = new KochFractal();
    
    private ArrayList<Edge> edges = new ArrayList<>();
     
    public KochManager(JSF31KochFractalFX application)
    {
        this.application = application;
    }
    
    public synchronized void addEdges(Edge edge) 
    {
        edges.add(edge);
        
        if(edges.size() == kochFractal1.getNrOfEdges())
        {
            drawEdges();
        }
        
    }
    
    public void changeLevel(int nxt)
    {
        kochFractal1.setLevel(nxt);
        kochFractal2.setLevel(nxt);
        kochFractal3.setLevel(nxt);
        
        TimeStamp timeStampCalc = new TimeStamp();
        TimeStamp timeStampDraw = new TimeStamp();
        
        edges.clear();
        
        timeStampCalc.setBegin("" + (nxt - 1));
        
        //kochFractal.generateLeftEdge();
        //kochFractal.generateBottomEdge();
        //kochFractal.generateRightEdge();
        Thread thread1 = new Thread(new EdgeGenerator(0, this, kochFractal1));
        Thread thread2 = new Thread(new EdgeGenerator(1, this, kochFractal2));
        Thread thread3 = new Thread(new EdgeGenerator(2, this, kochFractal3));
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        timeStampCalc.setEnd("" + nxt);
        
        timeStampDraw.setBegin("" + (nxt - 1));
        
        //drawEdges();
        
        timeStampDraw.setEnd("" + nxt);
        
        application.setTextCalc(timeStampCalc.toString());
        application.setTextDraw(timeStampDraw.toString());
        application.setTextNrEdges("" + kochFractal1.getNrOfEdges());
    }
    
    public synchronized void drawEdges() 
    {
        application.clearKochPanel();
        
        for(Edge edge : edges)
        {
           application.drawEdge(edge);
        }
    }
    
    public List<Edge> getEdges()
    {
        return edges;
    }

}
