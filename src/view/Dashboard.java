package view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import beans.CourseBeans;
import dao.CourseDao;
import dao.DBConnection;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Dashboard {

	private JFrame frame;
	private JTextField tfcname;
	private JTextField tfcduration;
	private JTextField tfcfee;
	private JTable table;



	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 153, 51));
		frame.setBounds(100, 100, 807, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblDashboard = new JLabel("DASHBOARD");
		lblDashboard.setForeground(new Color(0, 0, 0));
		lblDashboard.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblDashboard.setBounds(353, 11, 132, 40);
		frame.getContentPane().add(lblDashboard);

		JButton btnAddStudents = new JButton("Add Student");
		btnAddStudents.setFont(new Font("Sitka Heading", Font.BOLD, 12));
		btnAddStudents.setIcon(new ImageIcon("C:\\Users\\Saroj\\Downloads\\icons8-plus-16 (1).png"));
		btnAddStudents.setBounds(32, 198, 172, 40);
		frame.getContentPane().add(btnAddStudents);

		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.setFont(new Font("Sitka Display", Font.BOLD, 12));
		btnAddCourse.setIcon(new ImageIcon("C:\\Users\\Saroj\\Downloads\\icons8-plus-16 (1).png"));
		btnAddCourse.setBounds(32, 143, 172, 40);
		frame.getContentPane().add(btnAddCourse);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(256, 116, 502, 291);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Course", null, panel, null);
		panel.setLayout(null);

		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		lblCourseName.setBounds(10, 17, 109, 23);
		panel.add(lblCourseName);

		tfcname = new JTextField();
		tfcname.setBounds(129, 11, 341, 34);
		panel.add(tfcname);
		tfcname.setColumns(10);

		JLabel lblCourseDuration = new JLabel("Course Duration");
		lblCourseDuration.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		lblCourseDuration.setBounds(10, 62, 109, 23);
		panel.add(lblCourseDuration);

		tfcduration = new JTextField();
		tfcduration.setColumns(10);
		tfcduration.setBounds(129, 56, 341, 34);
		panel.add(tfcduration);

		JLabel lblCourseFee = new JLabel("Course Fee");
		lblCourseFee.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		lblCourseFee.setBounds(10, 105, 109, 23);
		panel.add(lblCourseFee);

		tfcfee = new JTextField();
		tfcfee.setColumns(10);
		tfcfee.setBounds(129, 101, 341, 34);
		panel.add(tfcfee);

		JButton btnAddNewCourse = new JButton("Add Course");
		btnAddNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cname = tfcname.getText();
				String cduration = tfcduration.getText();
				String cfee = tfcfee.getText();

				CourseBeans cb = new CourseBeans();
				cb.setC_name(cname);
				cb.setC_duration(Integer.parseInt(cduration));
				cb.setC_fee(Integer.parseInt(cfee));

				try {

					int insertionStatus = CourseDao.addCourse(cb);
					if(insertionStatus>0)
					{
						JOptionPane.showMessageDialog(null, "Course Added Successfully!");
						tfcname.setText("");
						tfcduration.setText("");
						tfcfee.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Course not added!");
					}

				} catch (SQLException e) {

					e.printStackTrace();
				}




			}
		});
		btnAddNewCourse.setFont(new Font("Sitka Display", Font.BOLD, 14));
		btnAddNewCourse.setBounds(254, 146, 109, 34);
		panel.add(btnAddNewCourse);

		JPanel panel_1 = new JPanel();
		panel_1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {

				try {

					ResultSet rs = CourseDao.selectCourse();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});



		tabbedPane.addTab("Course List", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 477, 181);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				Object o = table.getValueAt(row, col);
				int id = (int)o;

				//call
				try {
					int delStatus = CourseDao.deleteCourse(id);
					ResultSet rs = CourseDao.selectCourse();
					table.setModel(DbUtils.resultSetToTableModel(rs));

					if(delStatus>0) {
						JOptionPane.showMessageDialog(frame, "Data Deleted!");
					}else {
						JOptionPane.showMessageDialog(frame, "Data Not Deleted");
					}


				} catch (SQLException e) {

					e.printStackTrace();
				}

				//System.out.println(table.getValueAt(row, col));

			}
		});
		btnDelete.setBounds(10, 11, 89, 37);
		panel_1.add(btnDelete);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				Object o = table.getValueAt(row, col);
				int id = (int)o;
				new CourseUpdate(id, table);

			}
		});
		btnUpdate.setBounds(97, 11, 89, 37);
		panel_1.add(btnUpdate);
	}
}
