package zzy.worker.processor;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import zzy.dialog.MessageDialog;
import zzy.util.Showable;
import zzy.util.Timer;
import zzy.util.Utils;
import zzy.view.processor.AutoScrollPane;
import zzy.view.processor.ClipboardMonitor;

/**
 * A processor that can process URLs depending on the mode
 * 
 * @author Zhaoyi
 */
public abstract class Processor implements Showable{ // TODO: use multiple-thread to process maybe
	public final static File DEFAULT_LOCATION = new File("exported_records.txt");
	public final static String[] options = new String[] { "Yes", "No" };
	protected final static int MAX_TRIALS = 1;

	private final String stars = "******************";
	private final SimpleDateFormat format;
	private AutoScrollPane console;
	private ClipboardMonitor parent;

	protected File path;
	protected Set<String> unexported;
	protected List<String> failed;

	/**
	 * Construct a processor with given unexported and failed records
	 * 
	 * @param records       - unexported records
	 * @param failedRecords - failed records
	 * @param parent        - clipboard monitor window
	 * @param console       - the console
	 */
	public Processor(Set<String> records, List<String> failedRecords, ClipboardMonitor parent,
			AutoScrollPane console) {
		this.format = new SimpleDateFormat("  yyyy-MM-dd HH:mm:ss  ");
		this.path = DEFAULT_LOCATION;
		if (records == null)
			this.unexported = new HashSet<String>();
		else
			this.unexported = records;
		if (failedRecords == null)
			this.failed = new LinkedList<String>();
		else
			this.failed = failedRecords;
		this.parent = parent;
		this.console = console;
	}

	/**
	 * Add a record to the collection
	 * 
	 * @param record - record to add
	 * @return true if there are no dulpicates
	 */
	public boolean add(String record) {
		return unexported.add(record);
	}

	/**
	 * Return unported records
	 * 
	 * @return unexported records
	 */
	public Set<String> getRecords() {
		return unexported;
	}

	/**
	 * Return failed records
	 * 
	 * @return failed records
	 */
	public List<String> getFailedRecords() {
		return failed;
	}

	/**
	 * Return successfully exported records
	 * 
	 * @return successfully exported records
	 */
	public int getSuccessed() {
		return unexported.size() - failed.size();
	}

	/**
	 * Process the unexported records
	 */
	private void process() { // TODO: show the process
		failed.clear();
		Iterator<String> itr = unexported.iterator();
		String url = null;
		BufferedWriter writer = null;
		try {
			// TODO: create a window at the beginning xxx
			writer = new BufferedWriter(new FileWriter(path, true));
			writer.append(getHeader()).write(System.lineSeparator());
			while (itr.hasNext()) {
				url = itr.next().trim();
				try {
					check(url);
					writer.append(getTrueURL(url, 1)).write(System.lineSeparator());
				} catch (Exception e) {
					failed.add(url);
				}
			}
			writer.append(getFooter()).write(System.lineSeparator());
		} catch (Exception e) {
			console.log("IOException");
		} finally {
			Utils.close(writer);
		}
	}

	// ------------------------------------------------------------------------------------------//

	// export
	/**
	 * Export the unexported records
	 */
	public void export() {
		List<String> temp = null;
		if (failed.size() > 0)
			temp = new LinkedList<String>(failed);
		if (!hasRecords(unexported, parent))
			return;

		Timer.start();
		process();
		Timer.stop();
		console.log(stars + stars);
		console.log("Export complete");
		int total = unexported.size();
		int successed = getSuccessed();
		console.log(successed + " out of " + total + " completed");
		if (successed < total) {
			console.log("Failed URLs:");
			for (String f : failed)
				console.log("    " + f);
		}
		console.log(String.format("Time spent: %ss", Timer.returnStringTime()));
		console.log(stars + stars);
		unexported.clear();
		if (temp != null)
			failed.addAll(temp);
	}

	/**
	 * Export unexported records to a certain file
	 */
	public void exportTo() {
		if (!hasRecords(unexported, parent))
			return;
		File temp = path;
		changeLocation(); // change the location
		export();
		this.path = temp; // change the location back
	}

	/**
	 * Export unexported records and exit
	 */
	public void exportAndExit() {
		export();
		parent.exit();
	}

	/**
	 * Export failed records
	 */
	public void exportFailed() {
		if (hasRecords(failed, parent)) {
			Collection<String> temp = unexported;
			unexported.clear();
			unexported.addAll(failed);
			failed.clear();
			export();
			unexported.addAll(temp);
		}
	}

	/**
	 * Export failed records to a certain location
	 */
	public void exportFailedTo() {
		if (hasRecords(failed, parent)) {
			File temp = path;
			changeLocation(); // change the location
			exportFailed();
			this.path = temp; // change the location back
		}
	}

	// location
	/**
	 * Change the export location
	 */
	public void changeLocation() { // TODO: BasicFileChooserUI
		JDialog dialog = new JDialog(parent, "Change export location to...", true);
		JFileChooser chooser = new JFileChooser(".", FileSystemView.getFileSystemView());
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));
		chooser.setSelectedFile(path);

		chooser.addActionListener((ActionEvent e) -> {
			if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
				path = chooser.getSelectedFile();
				new MessageDialog(parent, "New export location", path.getAbsolutePath());
			}
			dialog.dispose();
		});
		dialog.add(chooser);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		dialog.setVisible(true);
	}

	public void showLocation() {
		new MessageDialog(parent, "Current export location", path.getAbsolutePath());
	}

	public void openLocation() {
		try {
			Runtime.getRuntime().exec("explorer " + path.getAbsolutePath());
		} catch (IOException e) {
			new MessageDialog(parent, "Error", "Cannot open\r\n" + path.getAbsolutePath());
		}
	}

	public void clearLocation() {
		if (JOptionPane.showOptionDialog(parent,
				"Do you want to clear the file at\r\n" + path.getAbsolutePath(), "WARNING",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]) == 0) {
			if (path.exists())
				path.delete();
			try {
				path.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new MessageDialog(parent, "Message", "File cleared");
		}
	}

	// records, simplified by Showable
	public void showU() {
		showByType(unexported, "Unexported", parent);
	}

	public void showF() {
		showByType(failed, "Failed", parent);
	}

	public void showB() {
		showBoth(unexported, failed, "Unexported", "Failed", parent);
	}

	public void clearRecords() {
		clearRecords(unexported, failed, "Unexported", "Failed", parent, console);
	}

	// ------------------------------------------------------------------------------------------//

	private String getHeader() {
		return stars + this.getClass().getSimpleName() + format.format(new Date()) + stars;
	}

	private String getFooter() {
		return "Successed records: " + getSuccessed();
	}

	protected abstract void check(String url) throws Exception;

	protected abstract String getTrueURL(String url, int trial) throws IOException;
}
