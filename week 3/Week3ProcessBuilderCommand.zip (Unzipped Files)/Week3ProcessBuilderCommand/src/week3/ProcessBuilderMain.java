/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week3;

import java.io.IOException;
import static java.lang.System.gc;
import java.lang.ProcessBuilder;

/**
 *
 * @author jsf3
 */
public class ProcessBuilderMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        for(int i =0;i<args.length;i++)
        {
//            String variable = args[i] + "=" + args[i+1];
            String[] args2 = new String[2];
            args2[0] = args[i];
            args2[1] = args[i+1];
            Thread thrd = new Thread(new ProcessBuilderCalc(args2));
            thrd.start();
            thrd.sleep(10000);
            i++;
         }
    }
    
}
