package cpsc3720.mainUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import cpsc3720.empire.Empire;
import cpsc3720.game.Game;
import cpsc3720.userserver.EmpireDatabase;
import cpsc3720.userserver.ShipTypeDatabase;
import cpsc3720.userservice.UserService;
import cpsc3720.windows.MainGameScreen;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/* This class was responsible for creating and displaying the screen that allowed a new player to join a game
they were not already a part of and select an empire and ship. This screen is not implemented in the current
version of the game */

@SuppressWarnings("serial")
public class NewPlayerScreen extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	public NewPlayerScreen(final int gameID, final UserService service) {
/*		setTitle("Prepare for Battle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblChooseAnEmpire = new JLabel("Choose an Empire");
		lblChooseAnEmpire.setBounds(10, 11, 113, 24);
		getContentPane().add(lblChooseAnEmpire);
				
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 35, 414, 20);
		getContentPane().add(comboBox);

		JLabel lblChooseAShip = new JLabel("Choose a Ship");
		lblChooseAShip.setBounds(10, 55, 113, 24);
		getContentPane().add(lblChooseAShip);
		
		JComboBox <String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(10, 79, 414, 20);
		getContentPane().add(comboBox_1);
			
		JLabel lblEnergyWeapon = new JLabel("Energy Weapon");
		lblEnergyWeapon.setBounds(20, 126, 119, 14);
		getContentPane().add(lblEnergyWeapon);		
		
		JLabel lblMissleWeapon = new JLabel("Missile Weapon");
		lblMissleWeapon.setBounds(20, 164, 119, 14);
		getContentPane().add(lblMissleWeapon);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(149, 123, 275, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(149, 161, 275, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		
		

		int m = 0;
		for(int i = 0; i < service.getGame(gameID).getEmpires().size(); i++){
			comboBox.addItem(service.getGame(gameID).getEmpires().get(i).getName());
			m = i;
		}
				
/*		
			ADD ITEMS TO SHIP COMBO BOX --------------------------------------------------------
		for(int i = 0; i < game.getEmpires().getAll().get(m).getShipTypes().size(); i++){
			comboBox_1.addItem(game.getEmpires().getAll().get(m).getShipTypes().get(i).getTitle());
		}
			
			
			DISPLAY ENERGY WEAPON IN TEXT BOX --------------------------------------------------
		for(int i = 0; i < game.getEmpires().getAll().size(); i++){
			for(int k = 0; k < game.getEmpires().getAll().get(i).getShipTypeList().size(); k++)
			if(game.getEmpires().getAll().get(i).getShipTypeList().get(k).getTitle().equals(comboBox.getSelectedItem()))
					textField.setText(game.getEmpires().getAll().get(i).getShipTypeList().get(k).getEnergy().getName());
		}
	
			DISPLAY MISSILE WEAPON IN TEXT BOX ---------------------------------------------------
		for(int i = 0; i < game.getEmpires().getAll().size(); i++){
			for(int k = 0; k < game.getEmpires().getAll().get(i).getShipTypeList().size(); k++)
			if(game.getEmpires().getAll().get(i).getShipTypeList().get(k).getTitle().equals(comboBox_1.getSelectedItem()))
					textField_1.setText(game.getEmpires().getAll().get(i).getShipTypeList().get(k).getMissle().getName());
		}
//	
		
		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set game user empire and ship to chosen types, open main game screen and load passed game
				MainGameScreen main = new MainGameScreen(service.getGame(gameID), service);
				main.setSize(800,800);
				main.setVisible(true);
				NewPlayerScreen.this.dispose();
			}
		});
		btnJoin.setBounds(80, 228, 89, 23);
		getContentPane().add(btnJoin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				GameSelectScreen screen = new GameSelectScreen(service);
				screen.setSize(600, 300);
				screen.setVisible(true);
				NewPlayerScreen.this.dispose();
			}
		});
		btnCancel.setBounds(265, 228, 89, 23);
		getContentPane().add(btnCancel);
	}
	*/
}
}
