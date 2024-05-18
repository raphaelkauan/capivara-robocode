package CyberCapivara;

import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

public class CyberCapivara extends AdvancedRobot {

    public void run() {
        setColors(Color.yellow, Color.yellow, Color.yellow, Color.yellow, Color.yellow);
        setAdjustRadarForRobotTurn(true);
        setAdjustGunForRobotTurn(true);

        while(true) {
            turnRadarRightRadians(Double.POSITIVE_INFINITY);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        double distance = e.getDistance();
        
        setBackAsFront(distance < 200 ? -1 : 1);
        
        setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians()));

        if (Math.abs(getGunTurnRemaining()) < 10) {
            setFire(3);
        }
    }

    public void onHitWall(HitWallEvent e) {
        setBackAsFront(-1);
    }

    void setBackAsFront(int direction) {
        setTurnRightRadians(Math.tan(direction));
        setAhead(Double.POSITIVE_INFINITY * direction);
    }
}

