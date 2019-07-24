package zzy.menu.collector;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import zzy.menu.BasicMenu;
import zzy.view.collector.CollectorWindow;
import zzy.worker.collector.Collector;

public class CollectMenu extends BasicMenu {
	private static final long serialVersionUID = -1493845725816661735L;

	public CollectMenu(CollectorWindow parent) {
		super(parent);
		setText("Collect");
		setMnemonic('l');

		JMenuItem collect = new JMenuItem("Collect", KeyEvent.VK_C);
		JMenuItem collectF = new JMenuItem("Collect from failed", KeyEvent.VK_F);

		setCTRLAccelerator(collect, KeyEvent.VK_L);
		setCTRLAccelerator(collectF, KeyEvent.VK_F);
		addMenuItems(this, collect, collectF);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Collector c = ((CollectorWindow) parent).getCollector();
		switch (e.getActionCommand()) {
			case "Collect":
				c.collect();
				break;
			case "Collect from failed":
				c.collectF();
				break;
		}
	}
}
