package arm;

public class Turret {
	int turretJoint;
	public Turret(int turretJoint) {
		this.turretJoint=turretJoint;
	}
	
	public int getturretJoint() {
		return turretJoint;
	}
	
	public void setturretJoint(int turretJoint) {
		this.turretJoint = turretJoint;
	}
	
	public void turretGoTo(int theta) {
		this.turretJoint=theta;
	}
	
	public String toString() {
		return  "," + turretJoint; 
	}
}
