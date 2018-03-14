package cpsc3720.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import cpsc3720.actions.ActionFireTorpedo;
import cpsc3720.actions.ActionNavigate;
import cpsc3720.actions.ActionSetAlertLevel;
import cpsc3720.base.Base;
import cpsc3720.game.Game;
import cpsc3720.game.Sector;
import cpsc3720.game.Square;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.user.Player;
import cpsc3720.user.User;
import cpsc3720.userservice.UserService;
import cpsc3720.util.Loc;

@SuppressWarnings("serial")
public class MainGameScreen extends JFrame{
	private JTable sectorView = new JTable();
	DefaultTableModel model;
	String[][] sectorTable = new String[8][8];
	private JTable universeView;
	JTextArea actionTextArea;
	JRadioButton alertGreen;
	JRadioButton alertYellow;
	JRadioButton alertRed;
	JLabel stardateLabel;
	JLabel sectorLabel;
	JLabel shipAlertLabel;
	JLabel alertLevelLabel;
	JLabel shipTypeLabel;
	JLabel shieldsLabel;
	JLabel torpedoCountLabel;
	JLabel userLabel;
	JLabel empireLabel;
	Game MainGame;
	UserService service;
	Player player;
	int sx;
	int sy;
	int stardate;
	private JButton attackButton;
	private JButton btnSetAlert;
	private JButton btnRefresh;
	

	public MainGameScreen(Game g, UserService s, Player currentPlayer) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		MainGame = g;
		service = s;
		player = currentPlayer;
		System.out.println(player.toString());

		startSectorView(g.getGrid().getSector(player.getShipLoc().getSector().x(), player.getShipLoc().getSector().y()));
		sx = player.getShipLoc().getSector().x();
		sy = player.getShipLoc().getSector().y();
		stardate = g.getStardate();
		//changeSectorLabel(player.getShipLoc().getSector().x(),player.getShipLoc().getSector().x());
		sectorView.setBounds(49, 59, 371, 301);
		getContentPane().add(sectorView);

		setUniverseView();
		universeView.setBounds(508, 13, 200, 173);
		universeView.setVisible(true);
		getContentPane().add(universeView);
		
		actionTextArea = new JTextArea();
		actionTextArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(actionTextArea);
		scroll.setBounds(475, 228, 283, 328);
		getContentPane().add(scroll);
		actionTextArea.append("Joined Game: " +  g.getGameName() + "\n");
		//actionTextArea.append("This is where text will appear to show that an action has occurred.\n");

		alertGreen = new JRadioButton("Green");
		alertGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertYellow.setSelected(false);
				alertRed.setSelected(false);
			}
		});
		alertGreen.setBounds(345, 529, 109, 23);
		getContentPane().add(alertGreen);

		alertYellow = new JRadioButton("Yellow");
		alertYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertRed.setSelected(false);
				alertGreen.setSelected(false);
			}
		});
		alertYellow.setBounds(345, 501, 109, 23);
		getContentPane().add(alertYellow);

		alertRed = new JRadioButton("Red");
		alertRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertYellow.setSelected(false);
				alertGreen.setSelected(false);
			}
		});
		alertRed.setBounds(345, 473, 109, 23);
		getContentPane().add(alertRed);

		alertLevelLabel = new JLabel("Alert Level");
		alertLevelLabel.setBounds(345, 450, 75, 14);
		getContentPane().add(alertLevelLabel);

		userLabel = new JLabel("User");
		userLabel.setBounds(49, 436, 143, 14);
		getContentPane().add(userLabel);

		shipTypeLabel = new JLabel("Ship Type:");
		shipTypeLabel.setBounds(49, 465, 150, 14);
		getContentPane().add(shipTypeLabel);

		empireLabel = new JLabel("Empire:");
		empireLabel.setBounds(49, 490, 150, 14);
		getContentPane().add(empireLabel);

		shieldsLabel = new JLabel("Shields:");
		shieldsLabel.setBounds(49, 516, 150, 14);
		getContentPane().add(shieldsLabel);
		
		torpedoCountLabel = new JLabel("Torpedoes:");
		torpedoCountLabel.setBounds(49, 568, 150, 14);
		getContentPane().add(torpedoCountLabel);

		shipAlertLabel = new JLabel("Alert Level:");
		shipAlertLabel.setBounds(49, 542, 150, 14);
		getContentPane().add(shipAlertLabel);

		JButton scanCellButton = new JButton("Scan");
		scanCellButton.setBounds(49, 373, 89, 23);
		scanCellButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				scanCell();				
			}
			
		});
		getContentPane().add(scanCellButton);

		JButton warpButton = new JButton("Warp");
		warpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigate();
			}
		});
		warpButton.setBounds(331, 373, 89, 23);
		getContentPane().add(warpButton);

		stardateLabel = new JLabel("Stardate: ");
		stardateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		stardateLabel.setBounds(232, 23, 177, 27);
		getContentPane().add(stardateLabel);
		setStardate(g.getStardate());

		sectorLabel = new JLabel("Sector " + (sx + 1) + ", " + (sy+1));
		sectorLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sectorLabel.setBounds(49, 25, 143, 23);
		getContentPane().add(sectorLabel);

		JButton scanButton = new JButton("Scan");
		scanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scanSector();
			}
		});
		scanButton.setBounds(567, 192, 89, 23);
		getContentPane().add(scanButton);
		
		attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack();
			}
		});
		attackButton.setBounds(188, 373, 89, 23);
		getContentPane().add(attackButton);
		
		btnSetAlert = new JButton("Set");
		btnSetAlert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlert();
			}
		});
		btnSetAlert.setBounds(331, 570, 89, 23);
		getContentPane().add(btnSetAlert);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				refreshGame();
			}
		});
		btnRefresh.setBounds(567, 570, 89, 23);
		getContentPane().add(btnRefresh);
		this.setSize(1000,1000);
		this.setVisible(true);
	}

	//Attack performed when user clicks attack
	public void attack() {
		//TODO: Something along this chain of command should check that there is an enemy in the square
			//when the user clicks Attack
		
		Loc defender = new Loc(sx, sy, sectorView.getSelectedColumn(), sectorView.getSelectedRow());
		Loc attacker = player.getShipLoc();
		ActionFireTorpedo a = new ActionFireTorpedo(MainGame.getId(), attacker, defender);
		service.performFireTorpedo(a);
		MainGame.setGrid(service.getGrid(new Integer(MainGame.getId())));
		changeSectorView(MainGame.getGrid().getSector(sx,sy));
		changeSectorLabel(sx,sy);
		setActionArea(MainGame.getGrid().getSector(sx, sy).getAttackLog());
	}
	
	//Updates the Action Text Area
	public void setActionArea(List<String> log) {
		actionTextArea.setText("");
		for(int i = 0; i < log.size(); i++){
			actionTextArea.append(log.get(i) + "\n");
		}

	}
	
	//Sets the user's ship alert level when Set Alert is pressed
	public void setAlert() {
		ActionSetAlertLevel a;
		if(alertGreen.isSelected()) {
			a = new ActionSetAlertLevel(MainGame.getId(), player.getShipLoc(), Alert.Green);
		} else if (alertYellow.isSelected()) {
			a = new ActionSetAlertLevel(MainGame.getId(), player.getShipLoc(), Alert.Yellow);
		} else {
			a = new ActionSetAlertLevel(MainGame.getId(), player.getShipLoc(), Alert.Red);
		}
		service.performSetAlertLevel(a);
		MainGame.setGrid(service.getGrid(new Integer(MainGame.getId())));
	}

	//Moves the user's ship to a location when Warp is pressed
	public void navigate() {
		int x = sectorView.getSelectedColumn();
		int y = sectorView.getSelectedRow();
		Loc l = new Loc(sx, sy, x, y);
		System.out.println(player.getShipLoc());
		ActionNavigate a = new ActionNavigate(MainGame.getId(), player, l);
		player = service.performNavigation(a);
		MainGame.setGrid(service.getGrid(new Integer(MainGame.getId())));
		changeSectorView(MainGame.getGrid().getSector(sx,sy));
		changeSectorLabel(sx,sy);
	}

	//gets a sector ready to be scanned
	public void scanSector() {
		sx = universeView.getSelectedColumn();
		sy = universeView.getSelectedRow();
		changeSectorView(MainGame.getGrid().getSector(sx, sy));
		changeSectorLabel(sx, sy);

		//return Sector at this location

	}

	//gets a cell ready to be scanned
	public void scanCell() {
		int cx = sectorView.getSelectedColumn();
		int cy = sectorView.getSelectedRow();
		System.out.println(cx + " " + cy);
		changeScanView(MainGame.getGrid().getSquare(sx, sy, cx, cy).getOccupant());
		
		//return the cell at this location

	}

	//changes the Scan View to show the scanned ship's details
	public void changeScanView(Object o) {
		if(o instanceof Ship){
			if(((Ship) o).getAlert().getEnergyDrain() == 0) {
				shipAlertLabel.setText("Alert level: " + "Green");
			} else if (((Ship) o).getAlert().getEnergyDrain() == 5) {
				shipAlertLabel.setText("Alert level: " + "Yellow");
			} else {
				shipAlertLabel.setText("Alert level: " + "Red");
			}
			shipTypeLabel.setText("Ship type: " + ((Ship) o).getShipType().getTitle());
			shieldsLabel.setText("Shield level: " + ((Ship) o ).getCurShield());
			java.util.List<User> users = service.listUsers();
			System.out.println(users.size());
			userLabel.setText("User: " + ((Ship) o).getPlayerName());
			System.out.println("Finished the search");
			empireLabel.setText("Empire: " + ((Ship) o).getShipType().getEmpire());
			torpedoCountLabel.setText("Torpedo count: " + ((Ship) o).getCurMissile());
		}
		

	}
	
	//Changes sector label
	public void changeSectorLabel(int x, int y) {
		sectorLabel.setText("Sector: " + (x+1) + ", " + (y+1));
	}

	//Starts the initial sector view
	public void startSectorView(Sector s) {

		String[] numbers = { "1", "2", "3", "4","5","6","7","8"};
		Square[][] sector = s.getMatrix();
		for(int x = 0; x < 8; x++) {
			//sectorTable[x][0] = numbers[x];
			for(int y = 0; y < 8; y++) {
				if(sector[y][x].getOccupant() != null) {
					//System.out.println(sector[y][x].getOccupant().toString() + " at " + x + " " + y);
					sectorTable[x][y] = sector[y][x].getOccType() + "";
				} else {
					sectorTable[x][y] = "";
				}
			}
		}

		model = new DefaultTableModel(sectorTable,numbers);
		model.fireTableDataChanged();
		sectorView.setModel(model);
		sectorView.setColumnSelectionAllowed(false);
		sectorView.setRowSelectionAllowed(false);
		sectorView.setRowHeight(37);
		for(int z = 0; z < 8; z++) {
			sectorView.getColumnModel().getColumn(z).setPreferredWidth(30);
		}

	}

	//Changes the sector view to the newly scanned sector
	public void changeSectorView(Sector s) {
		String[] numbers = { "1", "2", "3", "4","5","6","7","8"};
		Square[][] sector = s.getMatrix();
		System.out.println("Scanning at sector " + sx + ", " + sy);
		for(int x = 0; x < 8; x++) {
			//sectorTable[x][0] = numbers[x];
			for(int y = 0; y < 8; y++) {
				if(sector[y][x].getOccupant() != null) {
					//System.out.println(sector[y][x].getOccupant().toString() + " at " + y + " " + x);
					if(sector[y][x].getOccupant() instanceof Ship){
						Ship ship = (Ship) sector[y][x].getOccupant();
						if(!player.getAff().getID().equals(ship.getShipType().getEmpire())){
							System.out.println(player.getAff().getID() +" " +  ship.getShipType().getEmpire());
							sectorTable[x][y] = sector[y][x].getOccType() + "E";
						}
						else
							sectorTable[x][y] = sector[y][x].getOccType() + "F";
					}
					else if(sector[y][x].getOccupant() instanceof Base){
						Base base = (Base) sector[y][x].getOccupant();
						if(player.getAff().getID() != base.getE().getName()){
							sectorTable[x][y] = sector[y][x].getOccType() + "E";
						}
					}
					else	
						sectorTable[x][y] = sector[y][x].getOccType() + "F";
				} else {
					sectorTable[x][y] = "";
				}
			}
		}
		model = new DefaultTableModel(sectorTable,numbers);
		sectorView.setModel(model);
		model.fireTableDataChanged();
	}

	//sets the Universe View in the top corner
	public void setUniverseView() {
		Object[][] empty = new Object[8][8];
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				Sector sector = MainGame.getGrid().getSector(x, y);
				int friendly = 0;
				int enemy = 0;
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(sector.getSquare(i, j).hasShip()) {
							
							if(sector.getSquare(i,j).getShip().getShipType().getEmpire().toString().equals(player.getAff().getID())) {
								friendly++;
							} else {
								//System.out.println(sector.getSquare(i,j).getShip().getShipType().getEmpire().toString() + "is not the same as " + player.getAff().getID());
								enemy++;
							}
						}
					}
				}
				
				empty[y][x] = friendly + "," + enemy;
			}
		}
		String[] emptyString = new String[8];
		for(int x = 0; x < 8; x++) {
			emptyString[x] = "";
		}
		universeView = new JTable(empty, emptyString);
		universeView.setColumnSelectionAllowed(false);
		universeView.setRowSelectionAllowed(false);
		universeView.setRowHeight(22);
		for(int z = 0; z < 8; z++) {
			universeView.getColumnModel().getColumn(z).setPreferredWidth(40);
		}
	}
	//refreshes the game screen
	public void refreshGame(){
		MainGame.setGrid(service.getGrid(new Integer(MainGame.getId())));
		changeSectorView(MainGame.getGrid().getSector(sx,sy));
		changeSectorLabel(sx,sy);
		setActionArea(MainGame.getGrid().getSector(sx, sy).getAttackLog());
		setStardate(MainGame.getGrid().getStardate());
		
	}

	//sets the Stardate
	public void setStardate(int i) {
		stardateLabel.setText("Stardate: " + i);
	}
}
