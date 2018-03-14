package cpsc3720.mainUI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cpsc3720.game.Game;
import cpsc3720.userserver.GameDatabase;

/* This class is responsible for organizing and displaying data in the table on the Game Select Screen. All methods
should be self-explanatory. */
@SuppressWarnings("serial")
public class GameTableModel extends AbstractTableModel {

	private List<String> gamedata;

	public GameTableModel(List<String> list){
		gamedata = list;
	}
	
	@Override
	public int getColumnCount() {
		return 1;
	}
	
	@Override
	public String getColumnName(int col){
		return "Available Games";
	}

	@Override
	public int getRowCount() {
		return gamedata.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return gamedata.get(rowIndex);
	}
	
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

}
