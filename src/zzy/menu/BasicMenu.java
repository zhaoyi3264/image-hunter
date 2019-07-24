package zzy.menu;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import zzy.util.LoggableWindow;

public abstract class BasicMenu extends JMenu implements ActionListener{
	private static final long serialVersionUID = 7501844552483661760L;
	protected LoggableWindow parent;

	public BasicMenu(LoggableWindow parent) {
		this.parent = parent;
	}

	public void addMenuItems(ActionListener a, JMenuItem... items) {
		for (JMenuItem item : items) {
			add(item);
			item.addActionListener(a);
		}
	}

	public static void setCTRLAccelerator(JMenuItem item, int key) {
		item.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK));
	}

	public static void setALTAccelerator(JMenuItem item, int key) {
		item.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.ALT_DOWN_MASK));
	}
}
