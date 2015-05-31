import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;

public class ExampleRobot extends AdvancedRobot {
    private ExampleHelperClass helper;
    public ExampleRobot() {
        helper = new ExampleHelperClass();
    }

    // the 'run' method is pretty much always simple
    // because you really want to do most of your work
    // when you react to an event
    // but you do want an infinite loop in here, otherwise your robot will
    // just sit there doing nothing
    public void run () {
        setColors(Color.BLUE, Color.BLACK, Color.YELLOW);
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
        //noinspection InfiniteLoopStatement
        while(true) {
            setTurnRadarLeftRadians(Double.POSITIVE_INFINITY);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyAbsoluteBearing = getHeadingRadians() + e.getBearingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(enemyAbsoluteBearing - getGunHeadingRadians()));
        helper.doSomething();
        setFire(3.0);
        // this is a weird way of just randomly turning in some direction
        // without being 'inefficient' about it
        setTurnLeftRadians((1.0 - Math.random()*2) * Math.PI / 2);
        // and then we go forward some
        setAhead(Math.random() * 150.0);
        execute();
    }
}
