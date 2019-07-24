package zzy.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import zzy.dialog.MessageDialog;

public class ContinueMessage {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		JButton b = new JButton("invoke");
		b.addActionListener((ActionEvent e) -> {
			invoke(f);
		});
		f.add(b);
		f.pack();
		f.setVisible(true);
	}

	public static void invoke(JFrame j) {
		EventQueue.invokeLater(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				new MessageDialog(j, "" + i, "" + i).repaint();
			}
		});
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i + 10);
		}

	}
}
