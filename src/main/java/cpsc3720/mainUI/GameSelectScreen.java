package cpsc3720.mainUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cpsc3720.game.Game;
import cpsc3720.loader.FileLoader;
import cpsc3720.user.Player;
import cpsc3720.user.User;
import cpsc3720.userservice.UserService;
import cpsc3720.windows.MainGameScreen;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/* This class is responsible for creating and displaying the Game Select Screen, including retrieving a 
list of games and displaying them in a table and allowing a player to join a selected game, restore a saved game
or manually reset the server. */
@SuppressWarnings("serial")
public class GameSelectScreen extends JFrame {
	private JTable table;
	private UserService service;
	
	//This method is responsible for handling all functionality in the class, including creating and displaying
	//the screen and implementing button functionality and communicating with the server
	public GameSelectScreen(final UserService s, final User currentUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		service = s;
		setTitle("Select a Game");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 370, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(370, 239);
		scrollPane.setLocation(0, 0);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBounds(10, 0, 350, 239);
		panel.add(scrollPane);

		GameTableModel model = new GameTableModel(s.listGames());
		table.setModel(model);
		
		JButton btnNewButton = new JButton("Join Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game=service.getGameByIndex(table.getSelectedRow());
				if(game!=null && currentUser.getPlayer(game.getId()) != null){
					MainGameScreen main = new MainGameScreen(game, service, currentUser.getPlayer(game.getId()));
					main.setSize(800,800);
					main.setVisible(true);
					GameSelectScreen.this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"You are not currently a part of selected game. Please select another game.");	
				}
			}
		});
		btnNewButton.setBounds(402, 38, 172, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnCreateNewGame = new JButton("Create New Game");
		btnCreateNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//nothing yet
			}
		});
		btnCreateNewGame.setBounds(402, 72, 172, 23);
		getContentPane().add(btnCreateNewGame);
		
		JButton btnRestoreSavedGame = new JButton("Restore Saved Game");
		btnRestoreSavedGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player p=FileLoader.loadGameFile("Trek.dat",service);
				int gameID=p.getGameId();
				Game game=service.getGame(gameID);
				currentUser.addPlayer(gameID,p);
				MainGameScreen main=new MainGameScreen(game,service,p);
				main.setSize(800,800);
				main.setVisible(true);
				GameSelectScreen.this.dispose();
			}
		});
		btnRestoreSavedGame.setBounds(402, 106, 172, 23);
		getContentPane().add(btnRestoreSavedGame);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSelectScreen.this.dispose();
				ConnectScreen connect = new ConnectScreen();
				connect.setSize(450,300);
				connect.setVisible(true);
			}
		});
		btnCancel.setBounds(402, 227, 172, 23);
		getContentPane().add(btnCancel);
		
		JButton btnResetServer = new JButton("Reset Server");
		btnResetServer.setBounds(402, 193, 172, 23);
		getContentPane().add(btnResetServer);
		
		JLabel lblCurrentUser = new JLabel("Current User:");
		lblCurrentUser.setBounds(402, 11, 97, 14);
		getContentPane().add(lblCurrentUser);
		
		JLabel label = new JLabel(currentUser.getName());
		label.setBounds(509, 11, 65, 14);
		getContentPane().add(label);
		btnResetServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.resetServer();				
				JOptionPane.showMessageDialog(new JFrame(),"Attempting to reset server.");	
			}
		});
	}
}
