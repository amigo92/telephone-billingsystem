package sg.edu.nus.iss.billsys.tools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * @author Win Kyi Tin
 */

public class QueryTableModel extends AbstractTableModel {
	List<String[]> table; // will hold String[] objects . . .
	int colCount;
	String[] headers;
	String currentURL;

	public QueryTableModel() {
		table = new ArrayList<String[]>();
	}

	public String getColumnName(int i) {
		System.out.println(i);
		return headers[i];
	}

	public int getColumnCount() {
		return colCount;
	}

	public int getRowCount() {
		return table.size();
	}

	public Object getValueAt(int row, int col) {
		return ((String[]) table.get(row))[col];
	}

	public void updateTable(List<String[]> newlist) {
		colCount = newlist.get(0).length;
		headers = newlist.get(0);
		newlist.remove(0);
		table = newlist;
		fireTableChanged(null); // notify everyone that we have a new table.
	}
}

