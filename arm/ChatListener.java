package arm;

import java.util.Arrays;
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
			chat = getChat();
			try {
				this.sleep(RATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (chat.size()>0) {
				String line = chat.poll();
				
				
				//Ignore all lines said by Us the arm user
				if (line.indexOf("ARM:") != -1) {
					continue;
				}
				
				int index = line.indexOf(": ");
				
				///Split the line to subsystem and command
				String command = line.substring(index + 2, line.length());
				String[] tempSplit = command.split("_");
				
				//skip command if not enough arguments are passed
				if (tempSplit.length <2) {
					ChatPrint.toPrint("ARM_COMMAND_NOT_RECOGNIZED_[" + command + "]");
					continue;
				}
				String subsystem = tempSplit[0];
				String[] commandList = Arrays.copyOfRange(tempSplit, 1, tempSplit.length - 1);
				
				//make sure the arm simulation is running
				if (!a.isAlive()) {
					a.startSimulation();
				}
				//add in here any other subsystem we need to listen to
				if (subsystem.equals("ARM")) {
					CommandList.addCommand(command);
					continue;
				}
				if (subsystem.contentEquals("")) {
				}
				
			}
		}
	}
}
