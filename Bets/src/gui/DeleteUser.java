package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.objectdb.o.SLV.U;

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

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	private JList<User> userList;
	private JButton deleteUserbtn;
	private BLFacade businessLogic;

	public DeleteUser(BLFacade businessLogic) throws HeadlessException {
		this();	
		this.businessLogic = businessLogic;
		businessLogic.displayUsers().stream().forEach(user -> System.out.println(user.toString()));
		userList.setListData(new Vector<User>(businessLogic.displayUsers()));
	}

	/**
	 * Create the frame.
	 */
	public DeleteUser() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		deleteUserbtn = new JButton("Delete User");
		deleteUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = userList.getSelectedValue();
				
				businessLogic.deleteUser(user);
				userList.setListData(new Vector<User>(businessLogic.displayUsers()));
		
			}
		});
		contentPane.add(deleteUserbtn, BorderLayout.SOUTH);
		
		userList = new JList<User>();
		contentPane.add(userList, BorderLayout.CENTER);
		
		
	}

}
