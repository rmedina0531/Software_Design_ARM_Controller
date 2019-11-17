package arm;

import chatclient.ChatClient;

public class Talker extends Thread{
	private ChatClient client;
	
	
	public Talker(ChatClient client) {
		this.client = client;
	}
	
	public void run() {
		while(true) {
			try {
				if(ChatPrint.hasLine()) {
					String output = ChatPrint.getLine();
					client.sendToChat(output);
					
				}
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}
