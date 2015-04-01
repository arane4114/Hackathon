package hackathon;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

class LeapListener extends Listener{
	
	double pitch = 0;	
	double roll = 0;
	float height = 0;
	boolean isRight = true;
	
	int dataCount = 0;
	
	public void onInit(Controller controler){
		System.out.println("Initialized");
	}
	public void connect(Controller controller){
		System.out.println("Conected to sensor");
	}
	
	public void onDisconnect(Controller controller){
		System.out.println("Disconect");
	}
	
	public void onEixt(Controller controller){
		System.out.println("Exit");
	}
	
	public void onFrame(Controller controller){
		Frame frame = controller.frame();
		for(Hand hand : frame.hands()){
			if(hand.isLeft()){
				isRight = false;
			}
			//String handType = hand.isLeft() ? "Left Hand" : "Right Hand";
			//System.out.println("handType: " + handType);
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();
			
			pitch = Math.toDegrees(direction.pitch());
			roll = Math.toDegrees(normal.roll());
			height = hand.palmPosition().getY();
			
			dataCount++;
			
			System.out.println("Pitch: " + Math.toDegrees(direction.pitch()));
			System.out.println("Roll: " + Math.toDegrees(normal.roll()));;
			System.out.println("Height: " + hand.palmPosition().getY());
			System.out.println("-----------------------------------");
			
		}
	}
	
	public int dataCount(){
		return dataCount;
	}
	
	public double getPitch(){
		return pitch;
	}
	
	public double getRoll(){
		return roll;
	}
	
	public float getHeight(){
		return height;
	}
}

public class LeapController2 {
	LeapListener listener;
	
	public LeapController2(){
		System.out.println("Enter leap controller");
		listener = new LeapListener();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		double i = 0;
		while(listener.dataCount() < 1){
			i++;
			System.out.print("");
		}
		System.out.println(i);
		
		controller.removeListener(listener);
	}
	
	public double getPitch(){
		return listener.getPitch();
	}
	
	public double getRoll(){
		return listener.getRoll();
	}
	
	public double getHeight(){
		return (double) listener.getHeight();
	}
	
	public boolean isLeft(){
		return listener.isRight;
	}
}
