package hackathon;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.*;

public class CommandInterface {
	CommandManager cmd;
	final int MAX_SPEED;
	IARDrone drone;

	public CommandInterface(IARDrone drone, int speed) {
		this.drone = drone;
		cmd = drone.getCommandManager();
		MAX_SPEED = speed;
	}

	public void upCommand() {
		cmd.up(MAX_SPEED).doFor(100);
		cmd.hover();
	}

	public void downCommand() {
		cmd.down(MAX_SPEED).doFor(100);
		cmd.hover();
	}

	public void takeOff() {
		prepForFlight();
		cmd.takeOff().doFor(3000);
		cmd.calibrate();
	}

	public void prepForFlight() {
		cmd.flatTrim();
	}

	public void land() {
		cmd.landing();
	}

	public void hover() {
		cmd.hover();
	}

	public void stop() {
		cmd.hover();
	}

	public void leftCommand() {
		// cmd.move(50, 0, 0, 0).doFor(100);
		cmd.goLeft(MAX_SPEED).doFor(100);
		cmd.hover();
	}

	public void rightCommand() {
		cmd.goRight(MAX_SPEED).doFor(100);
		cmd.hover();
	}

	public void forwards() {
		cmd.forward(MAX_SPEED).doFor(100);
		cmd.hover();
	}

	public void backwards() {
		cmd.backward(MAX_SPEED).doFor(100);
		cmd.hover();
	}

	public void abortLand() {
		cmd.abortLand();
	}

	public void abortHover() {
		cmd.abortHover();
	}

	public void spinLeft() {
		cmd.spinLeft(100).doFor(100);
		cmd.hover();
	}

	public void spinRight() {
		cmd.spinRight(100).doFor(100);
		cmd.hover();
	}

	public void switchCam() {
		if (drone.getCommandManager().getVideoChannel()
				.equals(VideoChannel.HORI)) {
			drone.getCommandManager().setVideoChannel(VideoChannel.VERT);
		} else if (drone.getCommandManager().getVideoChannel()
				.equals(VideoChannel.VERT)) {
			drone.getCommandManager().setVideoChannel(VideoChannel.HORI);
		}
	}

	public void leapMove(float height, float pitch, float roll) {
		float speedX;
		// input roll: -ve is right, + is left
		// output roll: + is right, - is left
		if (Math.abs(roll) <= 10) {
			speedX = 0;
		} else if (roll > 0) {
			if (roll > 60) {
				speedX = -1 * MAX_SPEED;
			} else {
				speedX = -1 * (pitch / 60) * MAX_SPEED;
			}
		} else {
			if (roll < -60) {
				speedX = MAX_SPEED;
			} else {
				speedX = (pitch / 60) * MAX_SPEED;
			}
		}

		float speedY;
		if (Math.abs(pitch) <= 10) {
			speedY = 0;
		} else if (pitch > 0) {
			if (pitch >= 60) {
				speedY = -1 * MAX_SPEED;
			} else {
				speedY = -1 * (pitch / 60) * MAX_SPEED;
			}
		} else {
			if (pitch <= -60) {
				speedY = MAX_SPEED;
			} else {
				speedY = MAX_SPEED * (pitch / 60);
			}
		}
		speedY /= 4;

		/*
		 * float speedY; // + = backwards if(Math.abs(pitch) <= 10){ speedY = 0;
		 * }else if(pitch >= 60){ speedY = MAX_SPEED; }else if (pitch > 10){
		 * speedY = (pitch/60) * MAX_SPEED; }else if (pitch <= -60){ speedY = -1
		 * * MAX_SPEED; }else { speedY = Math.abs(pitch/60) * -1 * MAX_SPEED; }
		 */

		float speedZ;
		if (height <= 50) {
			speedZ = -1 * MAX_SPEED;
		} else if (height > 50 && height <= 125) {
			speedZ = 0;
		} else {
			speedZ = MAX_SPEED;
		}

		cmd.mover(speedX, speedY, speedZ, 0f).doFor(100);
		cmd.hover();
	}

}
