/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsf3
 */
public class ProcessBuilderCalc implements Runnable {
private String[] args;
    public ProcessBuilderCalc(String[] args)
    {
        this.args = args;
    }
    
    @Override
    public void run() {
        TimeUtil tu = new TimeUtil();
        tu.setBegin("begin process creation");
        ProcessBuilder pb = new ProcessBuilder(args);
        tu.setEnd("process created");
        
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        String line;
        
        try {
            Process p = pb.start();
            is = p.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            while(true)
            {
                if((line = br.readLine()) != null)
                {
                    System.out.println(line);
                }
                else
                {
                    break;
                }
            }
            String result = "process ";
            for(int i =0;i<args.length;i++)
            {
                result +=  args[i] + " "     ;
            }
            result += "has finished" ;
            tu.setEnd(result);
            System.out.println(tu.toString());
           p.destroy();            
            
        } catch (IOException ex) {
            Logger.getLogger(ProcessBuilderCalc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
