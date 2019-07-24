package zzy.menu.processor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.processor.ClipboardMonitor;
import zzy.worker.processor.Processor;

public class RecordMenu extends BasicMenu {
	private static final long serialVersionUID = 6119815660426360109L;

	public RecordMenu(ClipboardMonitor parent) {
		super(parent);
		setText("Record");
		setMnemonic('r');
		JMenuItem showU = new JMenuItem("Show unexported records", KeyEvent.VK_U);
		JMenuItem showF = new JMenuItem("Show failed records", KeyEvent.VK_F);
		JMenuItem showB = new JMenuItem("Show both records", KeyEvent.VK_B);
		JMenuItem clearR = new JMenuItem("Clear records", KeyEvent.VK_C);

		setALTAccelerator(showU, KeyEvent.VK_U);
		setALTAccelerator(showF, KeyEvent.VK_F);
		setALTAccelerator(showB, KeyEvent.VK_B);
		setCTRLAccelerator(clearR, KeyEvent.VK_D);
		addMenuItems(this, showU, showF, showB, clearR);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Processor p = ((ClipboardMonitor) parent).getProcessor();
		switch (e.getActionCommand()) {
			case "Show unexported records":
				p.showU();
				break;
			case "Show failed records":
				p.showF();
				break;
			case "Show both records":
				p.showB();
				break;
			case "Clear records":
				p.clearRecords();
				break;
		}
	}
}
