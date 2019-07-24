package zzy.menu.collector;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.collector.CollectorWindow;
import zzy.worker.collector.Collector;

public class CollectionMenu extends BasicMenu {
	private static final long serialVersionUID = -8078231079062744263L;

	public CollectionMenu(CollectorWindow parent) {
		super(parent);
		setText("Collection");
		setMnemonic(KeyEvent.VK_T);
		JMenuItem showC = new JMenuItem("Show collection", KeyEvent.VK_C);
		JMenuItem showF = new JMenuItem("Show failed", KeyEvent.VK_F);
		JMenuItem showB = new JMenuItem("Show both", KeyEvent.VK_B);
		JMenuItem clear = new JMenuItem("Clear collection", KeyEvent.VK_D);

		setALTAccelerator(showC, KeyEvent.VK_C);
		setALTAccelerator(showF, KeyEvent.VK_F);
		setALTAccelerator(showB, KeyEvent.VK_B);
		setCTRLAccelerator(clear, KeyEvent.VK_D);
		addMenuItems(this, showC, showF, showB, clear);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Collector c = ((CollectorWindow) parent).getCollector();
		switch (e.getActionCommand()) {
			case "Show collection":
				c.showC();
				break;
			case "Show failed":
				c.showF();
				break;
			case "Show both":
				c.showB();
				break;
			case "Clear collection":
				c.clearCollection();
				break;
		}
	}
}
