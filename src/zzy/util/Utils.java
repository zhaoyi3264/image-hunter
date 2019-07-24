package zzy.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Closeable;
import java.io.IOException;

public class Utils {
	public static void close(Closeable... closeable) {
		for (Closeable c : closeable) {
			try {
				if (c != null)
					c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Add an Component to the window using GridBagLayout
	 * 
	 * @param container - the container to add component to
	 * @param gb        - the GridBagLayout
	 * @param gc        - the GridBagConstraints
	 * @param c         - the component to add
	 */
	public static void addComponent(Container container, GridBagLayout gb, GridBagConstraints gc,
			Component c) {
		gb.addLayoutComponent(c, gc);
		container.add(c);
	}
}
