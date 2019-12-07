package arm;

public class Elbow {
	int elbowJoint;
	
	public Elbow() {
		this.elbowJoint=0;
	}
	public Elbow(int elbowJoint) {
		this.elbowJoint=elbowJoint;
	}
	
	public int getElbowJoint() {
		return elbowJoint;
	}

	public void setElbowJoint(int elbowJoint) {
		this.elbowJoint = elbowJoint;
	}

	public void elbowGoTo(int theta) {
		this.elbowJoint=theta;
	}
	
	public String toString() {
		return  "," + elbowJoint+","; 
	}
}
