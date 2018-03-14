package cpsc3720.userclient;


import cpsc3720.mainUI.ConnectScreen;
//This class simply contains a main method that displays the Connect to Server screen
public class UserClient {
	public static void main(String[] args) {		
		ConnectScreen c = new ConnectScreen();
		c.setVisible(true);
		c.setSize(450, 300);
	}
}
