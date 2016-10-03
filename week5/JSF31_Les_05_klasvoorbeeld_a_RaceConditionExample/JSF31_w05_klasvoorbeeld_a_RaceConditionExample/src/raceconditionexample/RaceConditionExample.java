/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raceconditionexample;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter Boots
 */
public class RaceConditionExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final int NrOfRuns = 10000000;
        final SharedCount s = new SharedCount();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < NrOfRuns; i++) {
                    s.increase();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < NrOfRuns; i++) {
                    s.decrease();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RaceConditionExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(s.count);
    }
}
