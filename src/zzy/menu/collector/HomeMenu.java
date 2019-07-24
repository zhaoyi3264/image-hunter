package zzy.menu.collector;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.collector.CollectorWindow;

public class HomeMenu extends BasicMenu {
	private static final long serialVersionUID = -1753266645449136508L;

	public HomeMenu(CollectorWindow parent) {
		super(parent);
		setText("Home");
		setMnemonic('h');

		JMenuItem hide = new JMenuItem("Hide", KeyEvent.VK_H);
		setALTAccelerator(hide, KeyEvent.VK_F4);

		addMenuItems(this, hide);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Hide":
				parent.setVisible(false);
				break;
		}
	}
}
