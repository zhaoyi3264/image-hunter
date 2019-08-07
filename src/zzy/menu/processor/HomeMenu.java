package zzy.menu.processor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.processor.ClipboardMonitor;

/**
 * A home menu for clipboard manager
 * 
 * @author Zhaoyi
 */
public class HomeMenu extends BasicMenu {
	private static final long serialVersionUID = -801326250393125057L;
	private JMenuItem resize;

	/**
	 * Construct a menu
	 * 
	 * @param parent - the parent window of the menu
	 */
	public HomeMenu(ClipboardMonitor parent) {
		super(parent);
		setText("Home");
		setMnemonic('h');
		JMenuItem collector = new JMenuItem("Open collector", KeyEvent.VK_O);
		resize = new JCheckBoxMenuItem("Resizable", parent.isResizable());
		JMenuItem res_pau = new JMenuItem("Resume/Pause", KeyEvent.VK_P);
		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);

		setCTRLAccelerator(collector, KeyEvent.VK_F1);
		setCTRLAccelerator(resize, KeyEvent.VK_Z);
		setCTRLAccelerator(res_pau, KeyEvent.VK_P);
		setALTAccelerator(exit, KeyEvent.VK_F4);

		addMenuItems(this, collector, resize, res_pau, exit);
	}

	/**
	 * Determine which menu item is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ClipboardMonitor p = (ClipboardMonitor) parent;
		switch (e.getActionCommand()) {
			case "Open collector":
				p.pause();
				p.openCollector();
				break;
			case "Resizable":
				p.setResizable(resize.isSelected());
				break;
			case "Resume/Pause":
				p.changeStatus();
				break;
			case "Exit":
				p.exit();
				break;
		}
	}
}