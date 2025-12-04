package as1;

import robocode.*;
import robocode.util.Utils;

public class As extends Robot {
   double giro = 10;
    String trackName;
    double gunTurnAmt;
	int zig =1;

    public void run() {

        trackName = null;
        gunTurnAmt = 10; 

        setAdjustGunForRobotTurn(true); 
 setAdjustRadarForGunTurn(true);
       
        while (true) {
            turnRadarRight(360); 
			
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        double absoluteBearing = getHeading() + e.getBearing();
        double turn = Utils.normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

        // Mira no inimigo
        turnGunRight(turn);

        // Atira de acordo com a distância
        if (e.getDistance() < 150) {
            fire(3);
        } else {
            fire(2.5);
        }

        // Move em direção ao inimigo se estiver longe
        if (e.getDistance() > 70) {
            turnRight(e.getBearing() + giro);
            ahead(e.getDistance() - 50);
        }

        // Registra alvo
        if (trackName == null) {
            trackName = e.getName();
            out.println("Tracking " + trackName);
        }
		

//direção

 
		
    }

    public void onWin(WinEvent e) {
        for (int i = 0; i < 50; i++) {
			ahead(3000);
			back(2000);
			fire(1);
        }
    }
}
