package zzy.util;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import zzy.dialog.DisplayDialog;
import zzy.dialog.MessageDialog;
import zzy.view.processor.AutoScrollPane;

public interface Showable {
	default boolean hasRecords(Collection<String> records, JFrame parent) {
		if (records.size() != 0)
			return true;
		new MessageDialog(parent, "Error", "There are no such records");
		return false;
	}

	default void showRecords(Collection<String> records, String type, DisplayDialog display) {
		display.log(records.size() + " " + type + " records:");
		Iterator<String> itr = records.iterator();
		while (itr.hasNext())
			display.log("    " + itr.next());
	}

	default void showByType(Collection<String> c, String type, JFrame parent) {
		if (hasRecords(c, parent))
			showRecords(c, type, new DisplayDialog(parent, type + " records"));
	}

	default void showBoth(Collection<String> c1, Collection<String> c2, String type1, String type2,
			JFrame parent) {
		DisplayDialog dialog = new DisplayDialog(parent, "Both records");
		showRecords(c1, type1, dialog);
		showRecords(c2, type2, dialog);
	}

	default void clearRecords(Collection<String> c1, Collection<String> c2, String type1,
			String type2, JFrame parent, AutoScrollPane console) {
		int result = JOptionPane.showOptionDialog(parent,
				"This operation is irreversable!\r\nWhich record do you want to clear?", "WARNING",
				YES_NO_OPTION, WARNING_MESSAGE, null,
				new String[] { "Both", type1, type2, "Cancel" }, "Cancel");
		if (result == 0) {
			c1.clear();
			c2.clear();
			new MessageDialog(parent, "Message", "Both records are cleared");
		} else if (result == 1) {
			c1.clear();
			new MessageDialog(parent, "Message", "All unexported records are cleared");
		} else if (result == 2) {
			c2.clear();
			new MessageDialog(parent, "Message", "All failed records are cleared");
		}
	}
}
