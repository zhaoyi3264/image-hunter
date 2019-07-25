package zzy.menu.processor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.processor.ClipboardMonitor;
import zzy.worker.processor.Processor;

/**
 * A menu to help to export records for clipboard monitor
 * 
 * @author Zhaoyi
 */
public class ExportMenu extends BasicMenu {
	private static final long serialVersionUID = 1396809839286717248L;

	/**
	 * Construct a menu
	 * 
	 * @param parent - the parent window of the menu
	 */
	public ExportMenu(ClipboardMonitor parent) {
		super(parent);
		setText("Export");
		setMnemonic('e');
		JMenuItem ex = new JMenuItem("Export", KeyEvent.VK_X);
		JMenuItem exTo = new JMenuItem("Export to...", KeyEvent.VK_T);
		JMenuItem exExit = new JMenuItem("Export and exit", KeyEvent.VK_N);
		JMenuItem exF = new JMenuItem("Export failed", KeyEvent.VK_F);
		JMenuItem exFTo = new JMenuItem("Export failed to...", KeyEvent.VK_O);

		setCTRLAccelerator(ex, KeyEvent.VK_E);
		setCTRLAccelerator(exExit, KeyEvent.VK_BACK_SPACE);
		setCTRLAccelerator(exF, KeyEvent.VK_F);
		addMenuItems(this, ex, exTo, exExit);
		addSeparator();
		addMenuItems(this, exF, exFTo);
	}

	/**
	 * Determine which menu item is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Processor p = ((ClipboardMonitor) parent).getProcessor();
		switch (e.getActionCommand()) {
			case "Export":
				p.export();
				break;
			case "Export to...":
				p.exportTo();
				break;
			case "Export and exit":
				p.exportAndExit();
				break;
			case "Export failed":
				p.exportFailed();
				break;
			case "Export failed to...":
				p.exportFailedTo();
				break;
		}
	}
}
