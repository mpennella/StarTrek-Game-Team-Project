package cpsc3720.mainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cpsc3720.userservice.UserService;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/*This class displays the screen that allows a user to enter their login credentials, connects to the specified
server, validates the username and password and then displays the Game Select Screen. It may also reset the server
if the server has not yet been initialized. */

@SuppressWarnings("serial")
public class ConnectScreen extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	//This method handles all functionality described above in creating and displaying the screen and connecting,
	//validating and displaying the Game Select Screen.
	public ConnectScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Connect to a Server");
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serverName = System.getProperty("server.name", textField.getText());
				String serverPort = System.getProperty("server.port", textField_1.getText());
				
				String url = "http://" + serverName + ":" + serverPort;
				System.out.println("Client sending requests to: " + url);
				
				try{ 
					UserService service = new RestAdapter.Builder()
						.setLogLevel(LogLevel.FULL)
						.setEndpoint(url)
						.build().create(UserService.class);
	
					service.resetStatus();
					int userID = -1;	
					for(int i = 0; i < service.listUsers().size(); i++){
						if(service.listUsers().get(i).getName().equals(textField_2.getText()))
							userID = i;
					}
					if(textField_3.getText().equals(service.listUsers().get(userID).getPass()) && userID != -1){	
						GameSelectScreen screen = new GameSelectScreen(service, service.listUsers().get(userID));
						screen.setSize(600, 300);
						screen.setVisible(true);
						ConnectScreen.this.dispose();
					}
					else{
						JOptionPane.showMessageDialog(new JFrame(),"Invalid username or password");
					}
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(new JFrame(),"Could not connect to server: '" + textField.getText() + "'");				
				}
			}
		});
		btnNewButton.setBounds(95, 200, 95, 40);
		getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(228, 200, 95, 40);
		getContentPane().add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(148, 11, 240, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Server Address");
		lblNewLabel.setBounds(30, 11, 134, 26);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPortNumber = new JLabel("Port Number");
		lblPortNumber.setBounds(30, 48, 89, 26);
		getContentPane().add(lblPortNumber);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(148, 48, 240, 26);
		getContentPane().add(textField_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(30, 85, 89, 26);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 122, 89, 26);
		getContentPane().add(lblPassword);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(148, 85, 240, 26);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(148, 122, 240, 26);
		getContentPane().add(textField_3);
	}
}
