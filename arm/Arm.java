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
	private boolean stow_mode = false;
	private boolean arm_break = false;
	
//	private Shoulder shoulder;
//	private Elbow elbow;
//	private Wrist wrist;
//	private Turret turret;
	
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
		if (commandArray.length == 7) {
			//ARM_MOVE_1_2_3_4_5
			//move arm commands
			//shoulder.shoulderGoTo(commandArray[2], commandArray[3]);
			//elbow([4])
			//wrist([5])
			//turret([6])
		}
		
		
		//Swich for the rest of the commands
		switch (command)
		{
			case "ARM_POWER_ON":
				ChatPrint.toPrint("ARM_RESPONSE_POWER_ON");
				this.power = true;
				break;
			case "ARM_HEAT":
				//do stuff
				ChatPrint.toPrint("ARM_RESPONSE_HEAT_ON");
				break;
			case "ARM_STOW":
				ChatPrint.toPrint("ARM_RESPONSE_STOW");
				//do stuff
				break;
			case "ARM_POWER_OFF":
				ChatPrint.toPrint("ARM_RESPONSE_POWER_OFF");
				this.power = false;
				//do stuff
				break;
			case "ARM_BRAKE":
				ChatPrint.toPrint("ARM_RESPONSE_BRAKE");
				//do stuff
				this.arm_break = true;
				break;
			case "ARM_COLLISION_CHECK":
				ChatPrint.toPrint("ARM_RESPONSE_COLLISION_CHECK");
				//do stuff
				break;
			case "ARM_ENTER_READY_STATE":
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
				//do stuff
				break;
			case "ARM_POSITION":
				//do stuff
				break;
			case "ARM_TURRET_POSITION":
				//do stuff
				break;
			default:
				ChatPrint.toPrint("Command Not Recognized [" + command + "]");
		}
	}
	
	public ChatListener getChatListener() {
		return this.l;
	}
	public Talker getTalker() {
		return this.t;
	}
}
