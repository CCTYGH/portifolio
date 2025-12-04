package as1;

import robocode.*;
import robocode.util.Utils;

public class AntiAsRobot extends Robot {
    String targetName;

    public void run() {
        // Gira o radar continuamente
        while (true) {
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (targetName == null) {
            targetName = e.getName();
            out.println("Counterando: " + targetName);
        }
        if (!e.getName().equals(targetName)) return;

        double absoluteBearing = getHeading() + e.getBearing();
        double gunTurn = Utils.normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
        turnGunRight((int) gunTurn);

        // Atira conforme distância
        if (e.getDistance() < 150) {
            fire(3);
        } else {
            fire(2);
        }

        // Movimenta-se perpendicularmente para evadir o As
        turnRight(e.getBearing() + 90);
        ahead(100);

    }

    public void onHitByBullet(HitByBulletEvent e) {
        // Esquiva simples: vira 90 graus e avança
        turnRight(90);
        ahead(100);
    }

    public void onHitWall(HitWallEvent e) {
        back(100);
    }

    public void onWin(WinEvent e) {
        for (int i = 0; i < 20; i++) {
            turnRight(120);
            fire(2);
        }
    }
}
