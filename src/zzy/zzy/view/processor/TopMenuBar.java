package zzy.view.processor;

import javax.swing.JMenuBar;

import zzy.menu.ConsoleMenu;
import zzy.menu.processor.ExportMenu;
import zzy.menu.processor.HomeMenu;
import zzy.menu.processor.LocationMenu;
import zzy.menu.processor.RecordMenu;

/**
 * The top menu bar for the clipboard monitor window.
 * 
 * @author Zhaoyi
 */
public class TopMenuBar extends JMenuBar {
	private static final long serialVersionUID = -3305784445647644202L;

	/**
	 * Construct a menu bar that contains Home, Export, Record, and Console menu
	 * items
	 * 
	 * @param parent - the clipboard monitor window
	 * @param p      - the Processor
	 */
	public TopMenuBar(ClipboardMonitor parent) {
		add(new HomeMenu(parent));
		add(new ExportMenu(parent));
		add(new RecordMenu(parent));
		add(new LocationMenu(parent));
		add(new ConsoleMenu(parent));
	}
}
