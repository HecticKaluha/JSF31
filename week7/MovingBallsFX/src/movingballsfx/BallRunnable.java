/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movingballsfx;

/**
 *
 * @author Peter Boots
 */
public class BallRunnable implements Runnable {

    private Ball ball;
    private RW monitor;

    public BallRunnable(Ball ball, RW monitor) {
        this.ball = ball;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ball.move();
                if(ball.isEnteringCs())
                {
                    if(ball.getBallType() == BallType.READER)
                    {
                        monitor.enterReader();   
                    }
                    else if(ball.getBallType() == BallType.WRITER)
                    {
                        monitor.enterWriter();
                    }
                }
                else if(ball.isLeavingCs())
                {
                    if(ball.getBallType() == BallType.READER)
                    {
                        monitor.exitReader();   
                    }
                    else if(ball.getBallType() == BallType.WRITER)
                    {
                        monitor.exitWriter();
                    }

                }
                Thread.sleep(ball.getSpeed());
                
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
