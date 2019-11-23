package arm;

public class Wrist {	
	int wristJoint;
	
	public Wrist() {
		this.wristJoint=0;
	}
	public Wrist(int wristJoint) {
		this.wristJoint=wristJoint;
	}
	
	public int getWristJoint() {
		return wristJoint;
	}

	public void setWristJoint(int wristJoint) {
		this.wristJoint = wristJoint;
	}

	public void wristGoTo(int theta) {
		this.wristJoint=theta;
	}
	
	public String toString() {
		return  "," + wristJoint+","; 
	}
}
