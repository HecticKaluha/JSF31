package movingballsfx;

import java.util.Random;
import javafx.scene.paint.Color;

public class Ball {

    private int xPos, yPos, speed;
    private int minX, maxX;
    private BallType ballType;
    private Color color;
    private int minCsX;
    private int maxCsX;

    public Ball(int minX, int maxX, int minCsX, int maxCsX, int yPos, BallType ballType) {
        this.xPos = minX;
        this.yPos = yPos;
        this.minX = minX;
        this.maxX = maxX;
        this.minCsX = minCsX;
        this.maxCsX = maxCsX;
        this.speed = 10 + (new Random()).nextInt(5);
        this.ballType = ballType;
        
        if(ballType != null)
        {
            switch(ballType)
            {
                case READER:
                    color = Color.RED;
                    break;
                case WRITER:
                    color = Color.BLUE;
                    break;
                default:
                    color = Color.WHITE;
                    break;
            }            
        }
    }

    public BallType getBallType()
    {
        return ballType;
    }

    public void setBallType(BallType ballType)
    {
        this.ballType = ballType;
    }
    
    public void move() {
        xPos++;
        if (xPos > maxX) {
            xPos = minX;
        }
    }

    public int getXPos() {
        return xPos;
    }

   public int getYPos() {
        return yPos;
    }

    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isEnteringCs() {
        return xPos == minCsX;
    }
    
    public boolean isLeavingCs() {
        return xPos == maxCsX;
    }
}
