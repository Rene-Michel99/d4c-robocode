
package steelBallRun;
import robocode.*;
import java.util.Random;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Lolicon - a robot by (felipe, renê)
 */

public class steelBallRun extends AdvancedRobot {
	Random gerador = new Random();

	public void run() {
		// Set colors
		setMaxVelocity(10);
		setColors(Color.yellow,Color.green,Color.blue); // body,gun,radar
		while (true) {
			anda();
		}
	}
	public void anda(){
		ahead(100);
		turnLeft(gerador.nextInt(((90 - 15) + 1) + 15));
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		
		if (e.getEnergy() > 16) {
			fire(3);
		} else if (e.getEnergy() > 10) {
			fire(2);
		} else if (e.getEnergy() > 4) {
			fire(1);
		} else if (e.getEnergy() > 2) {
			fire(.5);
		} else if (e.getEnergy() > .4) {
			fire(.1);
		}
		
	}
}

