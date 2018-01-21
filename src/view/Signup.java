package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import beans.UserBeans;
import dao.UsersDao;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup {

	private JFrame frame;
	private JTextField tf_uname;
	private JTextField tf_contact;
	private JPasswordField pf_pass;
	private JPasswordField pf_repeatpass;

	int xx, xy;



	/**
	 * Create the application.
	 */
	public Signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 720, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 355, 420);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();

			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				//Signup.this.setLocation(x - xx, y - xy);

			}
		});
		label.setBounds(-30, 0, 420, 275);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Signup.class.getResource("/images/bg.jpg")));
		panel.add(label);

		JLabel lblNewLabel = new JLabel("NEPAL TELEVISION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 302, 196, 14);
		panel.add(lblNewLabel);

		JLabel lblCommunicationForDevelopment = new JLabel("Communication For Development");
		lblCommunicationForDevelopment.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommunicationForDevelopment.setForeground(new Color(240, 255, 240));
		lblCommunicationForDevelopment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCommunicationForDevelopment.setBounds(90, 327, 196, 14);
		panel.add(lblCommunicationForDevelopment);

		Button btn_signup = new Button("Sign Up");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tf_uname.getText();
				long contact = Long.parseLong(tf_contact.getText());
				char pass[] = pf_pass.getPassword();
				String password= new String(pass);
				char re_pass[] = pf_repeatpass.getPassword();
				String re_password = new String(re_pass);


				UserBeans ub = new UserBeans();
				ub.setUsername(username);
				ub.setContact(contact);
				ub.setPassword(password);
				ub.setRe_password(re_password);

				try {

					int user_status = UsersDao.addUser(ub);
					if(user_status>0)
					{
						JOptionPane.showMessageDialog(null, "User Added Successfully!");
						tf_uname.setText("");
						tf_contact.setText("");
						pf_pass.setText("");
						pf_repeatpass.setText("");

						new View();
						frame.dispose();
					}

				} catch (SQLException e1) {


					e1.printStackTrace();
				}





			}
		});
		btn_signup.setForeground(Color.WHITE);
		btn_signup.setBackground(new Color(241, 57, 83));
		btn_signup.setBounds(399, 304, 246, 33);
		frame.getContentPane().add(btn_signup);

		tf_uname = new JTextField();
		tf_uname.setBounds(399, 51, 246, 28);
		frame.getContentPane().add(tf_uname);
		tf_uname.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(399, 26, 76, 14);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("CONTACT");
		lblPassword.setBounds(399, 90, 76, 14);
		frame.getContentPane().add(lblPassword);

		tf_contact = new JTextField();
		tf_contact.setColumns(10);
		tf_contact.setBounds(399, 115, 246, 28);
		frame.getContentPane().add(tf_contact);

		JLabel lblAddress = new JLabel("PASSWORD");
		lblAddress.setBounds(399, 154, 76, 14);
		frame.getContentPane().add(lblAddress);

		JLabel lblPassword_1 = new JLabel("REPEAT PASSWORD");
		lblPassword_1.setBounds(399, 223, 121, 14);
		frame.getContentPane().add(lblPassword_1);

		pf_pass = new JPasswordField();
		pf_pass.setBounds(399, 179, 246, 33);
		frame.getContentPane().add(pf_pass);

		pf_repeatpass = new JPasswordField();
		pf_repeatpass.setBounds(399, 244, 246, 30);
		frame.getContentPane().add(pf_repeatpass);

		JLabel lbl_close = new JLabel("X");
		lbl_close.setToolTipText("Close");

		lbl_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.exit(0);

			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(694, 0, 26, 14);
		frame.getContentPane().add(lbl_close);
	}
}
