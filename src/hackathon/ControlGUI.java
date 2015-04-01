package hackathon;

import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import de.yadrone.base.IARDrone;

public class ControlGUI extends JFrame {

	private JButton AbortLand = new JButton("Abort - Land");
	private JButton AbortHover = new JButton("Abort - Hover");
	private JButton TakeOff = new JButton("TakeOff");
	private JButton Land = new JButton("Land");
	private JButton Up = new JButton("Up");
	private JButton Down = new JButton("Down");
	private JButton Left = new JButton("Left");
	private JButton Right = new JButton("Right");
	private JButton Forwards = new JButton("Forwards");
	private JButton Backwards = new JButton("Backwards");
	private JButton leftSpin = new JButton("Left Spin");
	private JButton RightSpin = new JButton("Right Spin");
	private JButton SwapCam = new JButton("Swap Camera");
	private JButton Leap = new JButton("Leap");

	private Timer leapTimer;

	private final int MAX_SPEED = 20;

	private CommandInterface cmi;

	private LeapController2 leapControl;

	private KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == 87) {
					// w
					cmi.forwards();
				} else if (e.getKeyCode() == 83) {
					// s
					cmi.backwards();
				} else if (e.getKeyCode() == 68) {
					// a
					cmi.rightCommand();
				} else if (e.getKeyCode() == 65) {
					// d
					cmi.leftCommand();
				} else if (e.getKeyCode() == 37) {
					// arrow left
					cmi.spinLeft();
				} else if (e.getKeyCode() == 39) {
					// arrow right
					cmi.spinRight();
				} else if (e.getKeyCode() == 38) {
					// arrow up
					cmi.upCommand();
				} else if (e.getKeyCode() == 40) {
					// arrow down
					cmi.downCommand();
				} else if (e.getKeyCode() == 79) {
					// o
					cmi.takeOff();
				} else if (e.getKeyCode() == 80) {
					// p
					cmi.land();
					leapTimer.stop();
				} else if (e.getKeyCode() == 47) {
					// /
					cmi.switchCam();
				} else if (e.getKeyCode() == 32) {
					// space
					// cmi.abortLand();
				} else if (e.getKeyCode() == 81) {
					// q
					cmi.abortHover();
				} else if (e.getKeyCode() == 69) {
					// e
					leapTimer.stop();
					cmi.abortLand();
				} else if (e.getKeyCode() == 67) {
					System.out.println("test");
					leapControl = new LeapController2();
					double height = leapControl.getHeight();
					double pitch = leapControl.getPitch();
					double roll = leapControl.getRoll();
					cmi.leapMove((float) height, (float) pitch, (float) roll);
				} else {
				}
				// Do Nothing
			}
			return false;
		}
	};

	private class ButtonMoveListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == AbortLand) {
				leapTimer.stop();
				cmi.abortLand();
			} else if (arg0.getSource() == AbortHover) {
				cmi.abortHover();
			} else if (arg0.getSource() == TakeOff) {
				cmi.takeOff();
			} else if (arg0.getSource() == Land) {
				leapTimer.stop();
				cmi.land();
			} else if (arg0.getSource() == Up) {
				cmi.upCommand();
			} else if (arg0.getSource() == Down) {
				cmi.downCommand();
			} else if (arg0.getSource() == Left) {
				cmi.leftCommand();
			} else if (arg0.getSource() == Right) {
				cmi.rightCommand();
			} else if (arg0.getSource() == Forwards) {
				cmi.forwards();
			} else if (arg0.getSource() == Backwards) {
				cmi.backwards();
			} else if (arg0.getSource() == leftSpin) {
				cmi.spinLeft();
			} else if (arg0.getSource() == RightSpin) {
				cmi.spinRight();
			} else if (arg0.getSource() == SwapCam) {
				cmi.switchCam();
			} else if (arg0.getSource() == Leap) {
				if (leapTimer.isRunning()) {
					leapTimer.stop();
				} else {
					leapTimer.start();
				}
			} else {
				System.out.println(arg0.getSource());
			}
		}
	}

	public ControlGUI(IARDrone drone) {
		System.out.println("Start");
		this.cmi = new CommandInterface(drone, MAX_SPEED);

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(keyEventDispatcher);

		setTitle("Control GUL");
		setSize(250, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ActionListener buttonListener = new ButtonMoveListener();

		setLayout(new GridLayout(0, 1));

		add(AbortLand);
		add(AbortHover);
		add(TakeOff);
		add(Leap);
		add(Land);
		add(Up);
		add(Down);
		add(Left);
		add(Right);
		add(Forwards);
		add(Backwards);
		add(leftSpin);
		add(RightSpin);
		add(SwapCam);

		AbortLand.addActionListener(buttonListener);
		AbortHover.addActionListener(buttonListener);
		TakeOff.addActionListener(buttonListener);
		Land.addActionListener(buttonListener);
		Up.addActionListener(buttonListener);
		Down.addActionListener(buttonListener);
		Left.addActionListener(buttonListener);
		Right.addActionListener(buttonListener);
		Forwards.addActionListener(buttonListener);
		Backwards.addActionListener(buttonListener);
		leftSpin.addActionListener(buttonListener);
		RightSpin.addActionListener(buttonListener);
		SwapCam.addActionListener(buttonListener);
		Leap.addActionListener(buttonListener);

		setVisible(true);

	}

	private class LeapTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			leapControl = new LeapController2();
			System.out.println("AE called");
			if (leapControl.isLeft()) {
				leapTimer.stop();
			} else {
				double height = leapControl.getHeight();
				double pitch = leapControl.getPitch();
				double roll = leapControl.getRoll();
				cmi.leapMove((float) height, (float) pitch, (float) roll);
			}
			leapControl = null;
		}

	}
}
