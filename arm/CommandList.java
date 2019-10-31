package arm;

import java.util.LinkedList;
import java.util.Queue;

public class CommandList {
	private static Queue<String> commandList = new LinkedList<>();
	
	public static String getNextCommand() {
		return commandList.poll();
	}
	public static boolean hasCommand() {
		if (commandList.size() > 0){
			return true;
		}
		else {
			return false;
		}
	}
	public static void addCommand(String command) {
		commandList.offer(command);
	}
}
