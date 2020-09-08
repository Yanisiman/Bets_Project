package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Queue;
import java.util.Vector;

public class TutorialGUI extends JFrame {

	private JPanel contentPane;
	private Vector<String> steps = new Vector<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutorialGUI frame = new TutorialGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TutorialGUI() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{220, 180, 0};
		gbl_contentPane.rowHeights = new int[]{224, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel tutoField = new JLabel("");
		tutoField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_tutoField = new GridBagConstraints();
		gbc_tutoField.fill = GridBagConstraints.BOTH;
		gbc_tutoField.insets = new Insets(0, 0, 5, 5);
		gbc_tutoField.gridx = 0;
		gbc_tutoField.gridy = 0;
		contentPane.add(tutoField, gbc_tutoField);
		
		JButton skipBtn = new JButton("Skip");
		GridBagConstraints gbc_skipBtn = new GridBagConstraints();
		gbc_skipBtn.insets = new Insets(0, 0, 0, 5);
		gbc_skipBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_skipBtn.gridx = 0;
		gbc_skipBtn.gridy = 1;
		contentPane.add(skipBtn, gbc_skipBtn);
		
		JButton nextBtn = new JButton("Next");
		GridBagConstraints gbc_nextBtn = new GridBagConstraints();
		gbc_nextBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nextBtn.gridx = 1;
		gbc_nextBtn.gridy = 1;
		contentPane.add(nextBtn, gbc_nextBtn);
	}

}
