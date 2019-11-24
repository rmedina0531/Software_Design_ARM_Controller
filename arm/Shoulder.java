package arm;

public class Shoulder{
	int azimuthJoint;
	int elevationJoint;
	
	public Shoulder() {
		this.azimuthJoint = 0;
		this.elevationJoint = 0;
	
	}
	
	public void shoulderGoTo(int x, int y){
		this.setAzimuthJoint(x);
		this.setElevationJoint(y);
		
	}
	
	public int getAzimuthJoint() {
		return azimuthJoint;
	}
	public void setAzimuthJoint(int azimuthJoint) {
		this.azimuthJoint = azimuthJoint;
	}
	public int getElevationJoint() {
		return elevationJoint;
	}
	public void setElevationJoint(int elevationJoint) {
		this.elevationJoint = elevationJoint;
	}
	@Override
	public String toString() {
		return "" + azimuthJoint + "," + elevationJoint;
	}
	
//	public void shoulderGoToPossible(int x, int y){
//	
//	int azimuthTravelD =this.getAzimuthJoint() - x;
//	int elavationTravelD = this.getElevationJoint() - y;
//	
//	// some kind of wait command for azimuth travelD time
//	
//	this.setAzimuthJoint(x);
//	
//	//Some kind of wait command for elevation travelD time
//	this.setElevationJoint(y);
//	
//}

}


