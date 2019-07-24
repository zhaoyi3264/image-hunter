package zzy.view.collector;

import javax.swing.JMenuBar;

import zzy.menu.ConsoleMenu;
import zzy.menu.collector.CollectMenu;
import zzy.menu.collector.CollectionMenu;
import zzy.menu.collector.HomeMenu;

public class TopMenuBar extends JMenuBar {
	private static final long serialVersionUID = 152096610333519847L;

	public TopMenuBar(CollectorWindow parent) {
		add(new HomeMenu(parent));
		add(new CollectMenu(parent));
		add(new CollectionMenu(parent));
		add(new ConsoleMenu(parent));
	}
}
