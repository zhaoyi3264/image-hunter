package zzy.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import zzy.util.LoggableWindow;

/**
 * A menu for manipulating AutoScrollPane
 * 
 * @author Zhaoyi
 *
 */
public class ConsoleMenu extends BasicMenu {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a menu
	 * 
	 * @param parent - the parent window for the menu
	 */
	public ConsoleMenu(LoggableWindow parent) {
		super(parent);
		setText("Console");
		setMnemonic('c');
		JCheckBoxMenuItem lineWrap = new JCheckBoxMenuItem("Line wrap");
		JMenuItem clearC = new JMenuItem("Clear console", KeyEvent.VK_N);

		setCTRLAccelerator(lineWrap, KeyEvent.VK_ENTER);
		setCTRLAccelerator(clearC, KeyEvent.VK_N);
		addMenuItems(this, lineWrap, clearC);
	}

	/**
	 * Determine which item is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Line wrap":
				parent.toggleLineWrap();
				break;
			case "Clear console":
				parent.clearConsole();
				break;
		}
	}
}