package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import beans.CourseBeans;
import dao.CourseDao;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseUpdate {

	private JFrame frame;
	private JTextField tfCname;
	private JTextField tfCduration;
	private JTextField tfCfee;



	/**
	 * Create the application.
	 */
	public CourseUpdate(int id, JTable table) {
		initialize(id, table);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id, JTable table) {
		System.out.println("ID"+id);
		frame = new JFrame();
		frame.setBounds(100, 100, 672, 437);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		/******************************************************************************************************/
		CourseBeans cb = null;
		try {
			cb = CourseDao.getCourseById(id);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		/******************************************************************************************************/

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 102));
		panel.setBounds(0, 0, 656, 398);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCourseUpdateForm = new JLabel("COURSE UPDATE FORM");
		lblCourseUpdateForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseUpdateForm.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblCourseUpdateForm.setBounds(191, 11, 265, 14);
		panel.add(lblCourseUpdateForm);

		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		lblCourseName.setBounds(121, 85, 96, 14);
		panel.add(lblCourseName);

		JLabel lblCourseDuration = new JLabel("Course Duration");
		lblCourseDuration.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		lblCourseDuration.setBounds(121, 126, 124, 14);
		panel.add(lblCourseDuration);

		JLabel lblCourseFee = new JLabel("Course Fee");
		lblCourseFee.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		lblCourseFee.setBounds(121, 174, 96, 14);
		panel.add(lblCourseFee);

		tfCname = new JTextField(cb.getC_name());
		tfCname.setBounds(311, 82, 185, 20);
		panel.add(tfCname);
		tfCname.setColumns(10);

		tfCduration = new JTextField(Integer.toString(cb.getC_duration()));
		tfCduration.setColumns(10);
		tfCduration.setBounds(311, 123, 185, 20);
		panel.add(tfCduration);

		tfCfee = new JTextField(Integer.toString(cb.getC_fee()));
		tfCfee.setColumns(10);
		tfCfee.setBounds(311, 170, 185, 23);
		panel.add(tfCfee);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseBeans cb = new CourseBeans();
				cb.setC_name(tfCname.getText());
				cb.setC_duration(Integer.parseInt(tfCduration.getText()));
				cb.setC_fee(Integer.parseInt(tfCfee.getText()));

				try {
					int updateStatus = CourseDao.updateCourse(cb, id);
					if(updateStatus>0)
					{
						JOptionPane.showMessageDialog(frame, "Data Updated Successfully!");
						ResultSet rs = CourseDao.selectCourse();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(frame, "Data wasn't Updated!");
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnUpdate.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnUpdate.setBounds(342, 229, 114, 39);
		panel.add(btnUpdate);
	}
}
