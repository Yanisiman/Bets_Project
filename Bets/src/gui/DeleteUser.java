package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
/*
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
*/
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DeleteUser extends JFrame {

	private BLFacade businessLogic;

	public DeleteUser(BLFacade businessLogic) throws HeadlessException {
	this();	
		this.businessLogic = businessLogic;
		businessLogic.displayUsers().stream().forEach(user -> System.out.println(user.toString()));
		userList.setListData(new Vector(businessLogic.displayUsers()));
	}

	private JPanel contentPane;
	private JList<User> userList;
	private JButton deleteUserbtn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{71, 351, 0};
		gbl_contentPane.rowHeights = new int[]{218, 25, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		 
		 deleteUserbtn = new JButton("Delete User");
		 deleteUserbtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		User user = userList.getSelectedValue();
		 		
		 		businessLogic.deleteUser(user);
		 		userList.setListData(new Vector(businessLogic.displayUsers()));
		 
		 	}
		 });
		 
		  userList = new JList();
		  GridBagConstraints gbc_userList = new GridBagConstraints();
		  gbc_userList.gridwidth = 2;
		  gbc_userList.fill = GridBagConstraints.BOTH;
		  gbc_userList.insets = new Insets(0, 0, 5, 0);
		  gbc_userList.gridx = 0;
		  gbc_userList.gridy = 0;
		  contentPane.add(userList, gbc_userList);
		 GridBagConstraints gbc_deleteUserbtn = new GridBagConstraints();
		 gbc_deleteUserbtn.gridwidth = 2;
		 gbc_deleteUserbtn.anchor = GridBagConstraints.NORTH;
		 gbc_deleteUserbtn.fill = GridBagConstraints.HORIZONTAL;
		 gbc_deleteUserbtn.gridx = 0;
		 gbc_deleteUserbtn.gridy = 1;
		 contentPane.add(deleteUserbtn, gbc_deleteUserbtn);
		
		
	}

}
