package zzy.menu.collector;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import zzy.menu.BasicMenu;
import zzy.util.LoggableWindow;

/**
 * A menu for advance collection option (unavailable)
 * 
 * @author Zhaoyi
 */
public class AdvancedMenu extends BasicMenu {
	private static final long serialVersionUID = -1230887912920031978L;

	/**
	 * Construct a menu
	 * 
	 * @param parent - the parent window of the menu
	 */
	public AdvancedMenu(LoggableWindow parent) {
		super(parent);
		setText("Advanced");
		setMnemonic(KeyEvent.VK_A);
	}

	/**
	 * Determine which menu item is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
