package capivara;
import java.awt.Color;
import robocode.*;

public class Capivara extends AdvancedRobot {
    boolean movingForward = true;

    public void run() {
        setColors(Color.black, Color.black, Color.black, Color.black, Color.black);
        setTurnRadarRight(Double.POSITIVE_INFINITY);
        

        while (true) {
            setTurnRadarRight(360);
            move();
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        setTurnRadarRightRadians(robocode.util.Utils.normalRelativeAngle(absoluteBearing - getRadarHeadingRadians()) * 2);
        setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians()));
        
        fire(3);

        setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(absoluteBearing - getHeadingRadians()));
        setAhead(e.getDistance());
    }

    public void onHitWall(HitWallEvent e) {
        setTurnRight(90);
        setAhead(100);
    }

    private void move() {
        if (movingForward) {
            setAhead(Double.POSITIVE_INFINITY);
        } else {
            setBack(Double.POSITIVE_INFINITY);
        }
    }

    public double normalize(double angle) {
        while (angle < 0) {
            angle += 360;
        }
        while (angle >= 360) {
            angle -= 360;
        }
        return angle;
    }
}