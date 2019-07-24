package zzy.menu.collector;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import zzy.menu.BasicMenu;
import zzy.util.LoggableWindow;

public class AdvancedMenu extends BasicMenu {
	private static final long serialVersionUID = -1230887912920031978L;

	public AdvancedMenu(LoggableWindow parent) {
		super(parent);
		setText("Advanced");
		setMnemonic(KeyEvent.VK_A);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
