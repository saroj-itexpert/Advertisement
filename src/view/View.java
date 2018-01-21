package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;


import beans.LoginBeans;
import dao.LoginDao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private final JLabel lblForgotPassword = new JLabel("forgot password?");
	Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 102));
		frame.setBounds(100, 100, 585, 236);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblLogIn.setForeground(Color.DARK_GRAY);
		lblLogIn.setBounds(416, 9, 43, 25);
		frame.getContentPane().add(lblLogIn);

		JLabel lblNepalTelevision = new JLabel("NEPAL TELEVISION");
		lblNepalTelevision.setForeground(Color.DARK_GRAY);
		lblNepalTelevision.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
		lblNepalTelevision.setBounds(10, 0, 178, 44);
		frame.getContentPane().add(lblNepalTelevision);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		lblUsername.setBounds(239, 48, 92, 25);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		lblPassword.setBounds(239, 94, 72, 25);
		frame.getContentPane().add(lblPassword);

		tfUsername = new JTextField();
		tfUsername.setBounds(337, 45, 222, 34);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);

		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String username = tfUsername.getText();	//to take uesrname from username field
				char pass[] = pfPassword.getPassword();
				String password = new String(pass);



				LoginBeans lb = new LoginBeans();
				lb.setUsername(username);
				lb.setPassword(password);


				try {


					ResultSet rs = LoginDao.loginUser();
                   int count=0;
					while(rs.next())
					{
					if(username.equals(rs.getString(2)) && password.equals(rs.getString(4)))
					{
					  count++;
					  break;
					}

					}
					if(count>0)
					{
						JOptionPane.showMessageDialog(frame, "sucess");
						new Dashboard();
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(frame, "failed");
					}



				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});
		btnLogIn.setBackground(Color.LIGHT_GRAY);
		btnLogIn.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
		btnLogIn.setForeground(Color.DARK_GRAY);
		btnLogIn.setBounds(337, 135, 82, 38);
		frame.getContentPane().add(btnLogIn);

		JLabel lblDevelopedBySaroj = new JLabel("Designed & Developed By: ");
		lblDevelopedBySaroj.setForeground(new Color(51, 102, 51));
		lblDevelopedBySaroj.setFont(new Font("Segoe UI Historic", Font.ITALIC, 12));
		lblDevelopedBySaroj.setBounds(10, 159, 143, 14);
		frame.getContentPane().add(lblDevelopedBySaroj);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(View.class.getResource("/images/ntvv logo.jpg")));
		label.setBounds(10, 35, 112, 113);
		frame.getContentPane().add(label);

		JLabel lblSarojMaharjan = new JLabel("SAROJ MAHARJAN");
		lblSarojMaharjan.setForeground(new Color(51, 102, 51));
		lblSarojMaharjan.setFont(new Font("Segoe UI Historic", Font.ITALIC, 12));
		lblSarojMaharjan.setBounds(10, 179, 143, 14);
		frame.getContentPane().add(lblSarojMaharjan);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(337, 92, 222, 32);
		frame.getContentPane().add(pfPassword);

		JButton button = new JButton("SIGN UP");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Signup();
				frame.dispose();
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(447, 135, 112, 38);
		frame.getContentPane().add(button);

		lblForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblForgotPassword.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Signup();

			}
		});
		lblForgotPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblForgotPassword.setBounds(393, 180, 166, 14);
		frame.getContentPane().add(lblForgotPassword);
	}
}
