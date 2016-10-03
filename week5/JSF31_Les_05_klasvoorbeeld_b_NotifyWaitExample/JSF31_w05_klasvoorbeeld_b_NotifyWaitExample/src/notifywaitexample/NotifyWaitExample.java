/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package notifywaitexample;

/**
 *
 * @author Peter Boots
 */
public class NotifyWaitExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Buffer buffer = new BufferWrong(); /* or BufferOK() */ 

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    buffer.put(i);
                }
            }
        }, "Producer").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    buffer.get();
                }
            }
        }, "Consumer").start();
    }
}
