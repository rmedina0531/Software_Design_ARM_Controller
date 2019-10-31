package arm;

import java.util.LinkedList;
import java.util.Queue;

public class ChatListener extends Thread {
	//doublecheck to see if talker is required in this class
	
	private Queue<String> chat;
	private final int RATE = 20;
	private Arm a;
	
	public ChatListener(Arm a) {
		this.chat = new LinkedList<>();
		this.a = a;
	}
	
	public void newLine(String line) {
		chat.offer(line);
	}
	
	private Queue getChat() {
		return this.chat;
	}
	public void run() {
		while (true) {
//			System.out.println("Chat Listener Active");
			chat = getChat();
			try {
				this.sleep(RATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (chat.size()>0) {
				String line = chat.poll();
//				System.out.println(line);
				//check to see if ccds is the one that sent the message
				
//				if (false && username.toLowerCase().equals("CCDS")) {
				int index = line.indexOf("CCDS");
				
				if (index != -1) {
					//splits the CCSD:command, only command is stored
					
					String command = line.substring(index + 5, line.length() - 1);
					
//					String command = line2.split(":")[1].split(" ")[1];
					
					String[] commandSplit = command.split("_");
					String subsystem = commandSplit[0];
					if (subsystem.equals("ARM")){
						System.out.println("arm command recognized");
						//if command is ARM_START_SIMULATION
						if (commandSplit.length > 1) {
							if (commandSplit[1].equals("START") && commandSplit[2].equals("SIMULATION")) {
								a.startSimulation();
								System.out.println("ARM_SIMULATION_STARTUP");
//								ChatPrint.toPrint("ARM_SIMULATION_ACTIVE");
							}else if (!this.a.isAlive()){
								//do nothing if the main arm thread is not alive
								String text1 = "ARM SIMULATION INACTIVE";
								String text2 = "Please Start Simulation by issuing ARM_START_SIMULATION";
								System.out.println(text1);
								ChatPrint.toPrint(text1);
								System.out.println(text2);
								ChatPrint.toPrint(text2);
							}else {
								CommandList.addCommand(command);
							}
						}
						else {
							ChatPrint.toPrint("ARM INSTRUCTION ERROR");
						}
					}
					
//					System.out.println(line);
				}
			}
		}
	}
}
