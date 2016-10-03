/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import calculate.KochFractal;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

/**
 *
 * @author Stefan
 */
public class KochManager implements Observer
{
    private JSF31KochFractalFX application;
    private KochFractal kochFractal;
    private ArrayList<Edge> edges = new ArrayList<>();
     
    public KochManager(JSF31KochFractalFX application)
    {
        this.application = application;
        
        kochFractal = new KochFractal();
        kochFractal.addObserver(this);
      
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        Edge e = (Edge) arg;
        edges.add(e);
        
    }
    
    public void changeLevel(int nxt)
    {
        kochFractal.setLevel(nxt);
        
        TimeStamp timeStamp = new TimeStamp();
        timeStamp.setBegin("begin process creation");
        
        drawEdges();
        
        timeStamp.setEnd("process created");
        
        application.setTextCalc(timeStamp.toString());
        application.setTextNrEdges("" + kochFractal.getNrOfEdges());
    }
    
    public void drawEdges() 
    {
        application.clearKochPanel();
        edges.clear();
        
        kochFractal.generateLeftEdge();
        kochFractal.generateBottomEdge();
        kochFractal.generateRightEdge();
        
        for(Edge edge : edges)
        {
           application.drawEdge(edge);
        }
    }

}
