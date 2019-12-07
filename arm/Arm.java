package arm;

import java.util.LinkedList;
import java.util.Queue;

import chatclient.ChatClient;

public class Arm extends Thread{
	private Talker t;
	private ChatListener l;
	private final int RATE = 20;
//	private Interpreter i;
	
	//ARM STATES
	private boolean power = false;
	private boolean heat = false;
	private boolean stow_mode = true;
	private boolean arm_break = false;
	
	//fault protection states
	private boolean fault_power = true;
	
	private Shoulder shoulder;
	private Elbow elbow;
	private Wrist wrist;
	private Turret turret;
	
	public Arm(ChatClient c) {
		this.t = new Talker(c);
		this.l = new ChatListener(this);
//		this.l.run();
		//add all other objects that need to be instantiated
	}
	
	public void startSimulation() {
		//Starts all the threads of the simulation
		
		//Main thread of the arm simulation
		this.start();
		this.shoulder = new Shoulder();
		this.wrist = new Wrist();
		this.turret = new Turret();
		this.elbow = new Elbow();

	}
	
	
	//main Program
	
	public void run() {
		while (true) {
			//send the command to the appropriate method associated with the subCLass of the arm
			//command is assumed to be arriving with the ARM_ prefix
			
			//slow down the rate at which the loop executes
			try {
				this.sleep(RATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (CommandList.hasCommand()) {
				runCommand(CommandList.getNextCommand());
//				ChatPrint.toPrint("I recieved the following command: " + CommandList.getNextCommand());
			}
		}
	}
	
	public void runCommand(String command) {
		//get next command from queue
		
		//look for commands that pass argumemts
		
		String[] commandArray = command.split("_");
		
		//fault protection switch
		switch (command)
		{
			case "ARM_FP_POWER_OFF":
				this.fault_power = false;
				return;
			case "ARM_FP_POWER_ON":
				this.fault_power = true;
				return;
		}
		//Swich for the rest of the commands
		if (this.fault_power == false) {
			ChatPrint.toPrint("ARM_DISABLED_BY_FAULT_PROTECTION");
			return;
		}
		if (this.power == false) {
			if (command.equals("ARM_POWER_ON")) {
				this.power = true;
				ChatPrint.toPrint("ARM_STATUS_POWER_ON");
			}else {
				ChatPrint.toPrint("ARM_STATUS_POWER_OFF");
			}
		}else {
			if (commandArray.length == 7) {
				String shoulder_h = commandArray[2];
				String shoulder_v = commandArray[3];
				String elbow = commandArray[4];
				String wrist = commandArray[5];
				String turret = commandArray[6];
				
				ChatPrint.toPrint("ARM_RESPONSE_MOVE_ACK");
				
				this.shoulder.setAzimuthJoint(Integer.parseInt(shoulder_h));
				this.shoulder.setElevationJoint(Integer.parseInt(shoulder_v));
				this.elbow.setElbowJoint(Integer.parseInt(elbow));
				this.wrist.setWristJoint(Integer.parseInt(wrist));
				this.turret.setturretJoint(Integer.parseInt(turret));
				
				this.stow_mode = false;
				this.arm_break = false;
				
				try {
					this.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ChatPrint.toPrint("ARM_RESPONSE_MOVE_COMPLETE");
				
				//ARM_MOVE_1_2_3_4_5
				//move arm commands
				//shoulder.shoulderGoTo(commandArray[2], commandArray[3]);
				//elbow([4])
				//wrist([5])
				//turret([6])
				return;
			}
			
			switch (command)
			{
				case "ARM_HEAT_ON":
					//do stuff
					this.heat = true;
					ChatPrint.toPrint("ARM_RESPONSE_HEAT_ACK");
					break;
				case "ARM_HEAT_OFF":
					//do stuff
					this.heat = false;
					ChatPrint.toPrint("ARM_RESPONSE_HEAT_ACK");
					break;
				case "ARM_STOW":
					//to implement
					this.stow_mode = true;
					this.stowMode();
					ChatPrint.toPrint("ARM_RESPONSE_STOW_ACK");
					
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ChatPrint.toPrint("ARM_RESPONSE_STOW_COMPLETE");
					//do stuff
					break;
				case "ARM_POWER_OFF":
					ChatPrint.toPrint("ARM_RESPONSE_POWER_OFF_ACK");
					this.power = false;
					//do stuff
					break;
				case "ARM_BRAKE_ON":
					ChatPrint.toPrint("ARM_RESPONSE_BRAKE_ACK");
					//do stuff
					this.arm_break = true;
					break;
				case "ARM_BRAKE_OFF":
					ChatPrint.toPrint("ARM_RESPONSE_BRAKE_ACK");
					//do stuff
					this.arm_break = false;
					break;
				case "ARM_COLLISION_CHECK":
					ChatPrint.toPrint("ARM_RESPONSE_COLLISION_CHECK_ACK");
					//do stuff
					break;
				case "ARM_ENTER_READY_STATE_ACK":
					//do stuff
					break;
				case "ARM_STATUS":
					String status;
					if (power) {
						status = "ON";
					}else {
						status = "OFF";
					}
					ChatPrint.toPrint("ARM_STATUS_" + status);
					ChatPrint.toPrint("ARM_STATUS_STOW_MODE_" + String.valueOf(this.stow_mode).toUpperCase());
					ChatPrint.toPrint("ARM_STATUS_BRAKE_MODE_" + String.valueOf(this.arm_break).toUpperCase());
					ChatPrint.toPrint("ARM_STATUS_HEAT_MODE_" + String.valueOf(this.heat).toUpperCase());
					//do stuff
					break;
				case "ARM_POSITION":
					ChatPrint.toPrint(getArmPosition());
					//do stuff
					break;
				default:
					ChatPrint.toPrint("Command Not Recognized [" + command + "]");
			}
		}
	}
	
	public ChatListener getChatListener() {
		return this.l;
	}
	public Talker getTalker() {
		return this.t;
	}
	public String getArmPosition() {
		StringBuilder output = new StringBuilder();
		output.append("ARM_CURRENT_POS_");
		output.append(this.shoulder.getAzimuthJoint());
		output.append("_");
		output.append(this.shoulder.getElevationJoint());
		output.append("_");
		output.append(this.elbow.getElbowJoint());
		output.append("_");
		output.append(this.wrist.getWristJoint());
		output.append("_");
		output.append(this.turret.getturretJoint());
		
		
		return output.toString();
	}
	public void stowMode() {
		this.shoulder.setAzimuthJoint(0);
		this.shoulder.setElevationJoint(0);
		this.elbow.setElbowJoint(0);
		this.wrist.setWristJoint(0);
		this.turret.setturretJoint(0);
	}
}
