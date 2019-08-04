package zzy.menu;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import zzy.util.LoggableWindow;

/**
 * A base class for menu in the menu bar
 * 
 * @author Zhaoyi
 */
public abstract class BasicMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 7501844552483661760L;
	protected LoggableWindow parent;

	/**
	 * Construct a menu that holds a reference to the window
	 * 
	 * @param parent - the parent window for the menu
	 */
	public BasicMenu(LoggableWindow parent) {
		this.parent = parent;
	}

	/**
	 * Add menu items into the menu and register listener for them
	 * 
	 * @param a     - action listener
	 * @param items - menu item to add
	 */
	public void addMenuItems(ActionListener a, JMenuItem... items) {
		for (JMenuItem item : items) {
			add(item);
			item.addActionListener(a);
		}
	}

	/**
	 * Set a CTRL shortcut for a menu item
	 * 
	 * @param item - menu item to set shortcut
	 * @param key  - the shortcut key
	 */
	public static void setCTRLAccelerator(JMenuItem item, int key) {
		item.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK));
	}

	/**
	 * Set a ALT shortcut for a menu item
	 * 
	 * @param item - menu item to set shortcut
	 * @param key  - the shortcut key
	 */
	public static void setALTAccelerator(JMenuItem item, int key) {
		item.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.ALT_DOWN_MASK));
	}
}
