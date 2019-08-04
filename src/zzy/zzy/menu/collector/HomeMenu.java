package zzy.menu.collector;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.collector.CollectorWindow;

/**
 * A home menu for collector
 * 
 * @author Zhaoyi
 */
public class HomeMenu extends BasicMenu {
	private static final long serialVersionUID = -1753266645449136508L;

	/**
	 * Construct a menu
	 * 
	 * @param parent - the parent window of the menu
	 */
	public HomeMenu(CollectorWindow parent) {
		super(parent);
		setText("Home");
		setMnemonic('h');

		JMenuItem hide = new JMenuItem("Hide", KeyEvent.VK_H);
		setALTAccelerator(hide, KeyEvent.VK_F4);

		addMenuItems(this, hide);
	}

	/**
	 * Determine which menu item is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Hide":
				parent.setVisible(false);
				break;
		}
	}
}
