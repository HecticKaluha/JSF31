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
        while (!Thread.currentThread().isInterrupted())
        {
            try {
                ball.move();
                if(ball.isEnteringCs())
                {
                    enter();
                }
                else if(ball.isLeavingCs())
                {
                    exit();
                }
                Thread.sleep(ball.getSpeed());
                
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        
        exit();
    }
    
    public void exit()
    {
        if(ball.isIsInside())
        {
            if(ball.getBallType() == BallType.READER)
            {
                monitor.exitReader();
                ball.setIsInside(false);
            }
            else if(ball.getBallType() == BallType.WRITER)
            {
                monitor.exitWriter();
                ball.setIsInside(false);
            }            
        }
    }
    
    public void enter() throws InterruptedException
    {
        if(!ball.isIsInside())
        {
            if(ball.getBallType() == BallType.READER)
            {
                monitor.enterReader();
                ball.setIsInside(true);
            }
            else if(ball.getBallType() == BallType.WRITER)
            {
                monitor.enterWriter();
                ball.setIsInside(true);
            }
        }
    }
}
