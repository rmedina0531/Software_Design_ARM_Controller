package arm;

import java.util.LinkedList;
import java.util.Queue;

public class ChatPrint {
	private static Queue<String> output = new LinkedList<>();
	
	public static void toPrint(String s) {
		output.offer(s);
	}
	public static Queue<String> getPrintQueue(){
		return output;
	}
	public static boolean hasLine() {
		return !output.isEmpty();
	}
	public static String getLine() {
		return output.poll();
	}
}
