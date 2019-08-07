package zzy.menu.processor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.util.LoggableWindow;
import zzy.view.processor.ClipboardMonitor;
import zzy.worker.processor.Processor;

/**
 * A menu to manage export location of clipboard manager
 * 
 * @author Zhaoyi
 */
public class LocationMenu extends BasicMenu {
	private static final long serialVersionUID = -1509559498606660625L;

	/**
	 * Construct a menu
	 * 
	 * @param parent - the parent window of the menu
	 */
	public LocationMenu(LoggableWindow parent) {
		super(parent);
		setText("Location");
		setMnemonic('l');

		JMenuItem changeLoc = new JMenuItem("Change export location", KeyEvent.VK_C);
		JMenuItem showLoc = new JMenuItem("Show export location", KeyEvent.VK_S);
		JMenuItem openLoc = new JMenuItem("Open export location", KeyEvent.VK_O);
		JMenuItem clearLoc = new JMenuItem("Clear records in the current location", KeyEvent.VK_R);

		setCTRLAccelerator(changeLoc, KeyEvent.VK_G);
		setALTAccelerator(showLoc, KeyEvent.VK_T);
		setCTRLAccelerator(openLoc, KeyEvent.VK_O);
		setCTRLAccelerator(clearLoc, KeyEvent.VK_R);
		addMenuItems(this, changeLoc, showLoc, openLoc, clearLoc);
	}

	/**
	 * Determine which menu item is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Processor p = ((ClipboardMonitor) parent).getProcessor();
		switch (e.getActionCommand()) {
			case "Change export location":
				p.changeLocation();
				break;
			case "Show export location":
				p.showLocation();
				break;
			case "Open export location":
				p.openLocation();
				break;
			case "Clear records in the current location":
				p.clearLocation();
				break;
		}
	}
}
