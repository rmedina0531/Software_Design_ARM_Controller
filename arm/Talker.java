package arm;

import chatclient.ChatClient;

public class Talker extends Thread{
	private ChatClient client;
//	private Queue<String> printList;
	
	
	public Talker(ChatClient client) {
		this.client = client;
//		this.printList = new LinkedList<>();
	}
	
	public void run() {
		while(true) {
			try {
				if(ChatPrint.hasLine()) {
//					client.sendToChat("I am talking");
					client.sendToChat(ChatPrint.getLine());
				}
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			ChatPrint.toPrint("Talker is running");
//			System.out.println("Talker is running");
		}
	}	
}
