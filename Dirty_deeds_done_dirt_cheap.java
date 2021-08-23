package laliho;
import robocode.*;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import java.util.Random;

import java.awt.*;

//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Lolicon - a robot by (your name here)
 */


public class Lolicon extends Robot {
	int turnDirection = 1; // Clockwise or counterclockwise
	Random gerador = new Random();

	public void run() {
		// Set colors
		setBodyColor(Color.blue);
		setGunColor(Color.red);
		setRadarColor(Color.lightGray);
		while (true) {
			turnRight(5 * turnDirection);
			anda();
		}
	}
	public void anda(){
		ahead(gerador.nextInt(26));
		turnLeft(gerador.nextInt(90));
	}
	/**
	 * onScannedRobot:  We have a target.  Go get it.
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}

		turnRight(e.getBearing());
		fire(3);
		scan(); // Might want to move ahead again!
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(90 - (getHeading() - e.getHeading()));
		ahead(150);
		turnLeft(55);
		back(500);
		ahead(500);
	}
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnLeft(e.getBearing());
	}

	/**
	 * onHitRobot:  Turn to face robot, fire hard, and ram him again!
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());

		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
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
		ahead(40); // Ram him again!
	}
}

