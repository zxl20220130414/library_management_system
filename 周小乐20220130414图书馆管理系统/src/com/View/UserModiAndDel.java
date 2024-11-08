package com.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dao.Dao;
import com.model.user;
import com.Item.MyDocument;
public class UserModiAndDel extends JFrame implements ActionListener {
	 JTextField textField,textField_2,textField_4,textField_5,textField_6,textField_7;
	 JTable table;
	 String[] str;
	 ButtonGroup buttonGroup = new ButtonGroup();
	 JRadioButton JRadioButton1,JRadioButton2;
	 Object[][] getFileStates(List list){
		String[] str = { "用户编号", "用户姓名", "性别", "年龄", "身份证",
				"电话", "密码" };
		Object[][]users=new Object[list.size()][str.length];
		for(int i=0;i<list.size();i++){
			user user=(user)list.get(i);
			users[i][0]=user.getId();
			users[i][1]=user.getName();
			String sex;
			if(user.getSex().equals("1")){
				sex="男";
			}
			else
				sex="女";
			users[i][2]=sex;
			users[i][3]=user.getAge();
			users[i][4]=user.getIdentityCard();
			users[i][5]=user.getTel();
			users[i][6]=user.getPassword();
		}
		return users;

	}

	public UserModiAndDel() {
		setTitle("用户信息修改与删除");
		setBounds(100, 100, 500, 450);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 150));
		getContentPane().add(panel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 120));
		panel.add(scrollPane);

		Object[][] results=getFileStates(Dao.selectuser());
		str = new String[]{"用户编号", "用户姓名", "性别", "年龄", "身份证", "电话", "密码" };
		table = new JTable(results,str);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setHgap(2);
		flowLayout_1.setVgap(9);
		panel_2.setLayout(flowLayout_1);
		getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(300, 110));

		JPanel panel_4 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(8);
		panel_4.setLayout(gridLayout);
		panel_4.setPreferredSize(new Dimension(400, 210));
		panel_2.add(panel_4);
		JLabel label_8 = new JLabel();
		panel_4.add(label_8);
		label_8.setText("    用 户 编 号：");

		textField_7 = new JTextField();
		panel_4.add(textField_7);

		JLabel label = new JLabel();
		panel_4.add(label);
		label.setText("    用 户 姓 名：");

		textField = new JTextField();
		panel_4.add(textField);

		JLabel label_1 = new JLabel();
		panel_4.add(label_1);
		label_1.setText("    性       别：");
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		panel_3.setLayout(flowLayout);

		JRadioButton1 = new JRadioButton();
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel_3.add(JRadioButton1);
		JRadioButton1.setText("男");

		JRadioButton2 = new JRadioButton();
		JRadioButton2.setSelected(true);
		buttonGroup.add(JRadioButton2);
		panel_3.add(JRadioButton2);
		JRadioButton2.setText("女");

		JLabel label_2 = new JLabel();
		panel_4.add(label_2);
		label_2.setText("    年       龄：");

		textField_2 = new JTextField();
		textField_2.setMinimumSize(new Dimension(0, 1));
		panel_4.add(textField_2);
		textField_2.setDocument(new MyDocument(2));
		textField_2.setColumns(2);

		JLabel label_7 = new JLabel();
		panel_4.add(label_7);
		label_7.setText("    身   份  证：");
		textField_5 = new JTextField();
		panel_4.add(textField_5);
		textField_5.setColumns(20);

		JLabel label_4 = new JLabel();
		panel_4.add(label_4);
		label_4.setText("    联 系 电 话：");
		textField_4 = new JTextField("电话号必须是十一位",11);
		panel_4.add(textField_4);
		textField_4.setDocument(new MyDocument(11));
		textField_4.setColumns(11);

		JLabel label_5 = new JLabel();
		panel_4.add(label_5);
		label_5.setText("    密       码：");
		textField_6 = new JTextField();
		panel_4.add(textField_6);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		JButton button = new JButton();
		button.setText("修改");
		panel_1.add(button);
		button.addActionListener(this);
		JButton button_2 = new JButton();
		button_2.setText("删除");
		panel_1.add(button_2);
		button_2.addActionListener(new addUserActionListener());

		JButton button_1 = new JButton();
		button_1.setText("退出");
		panel_1.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked( MouseEvent e) {
			String idd,name,sex,age,IdentityCard,tel,password;
			int selRow = table.getSelectedRow();
			idd = table.getValueAt(selRow, 0).toString().trim();
			name = table.getValueAt(selRow, 1).toString().trim();
			if(table.getValueAt(selRow, 2).toString().trim().equals("男"))
				JRadioButton1.setSelected(true);
			else
				JRadioButton2.setSelected(true);
			age = table.getValueAt(selRow, 3).toString().trim();
			IdentityCard = table.getValueAt(selRow, 4).toString().trim();
			tel = table.getValueAt(selRow, 5).toString().trim();
			password = table.getValueAt(selRow, 6).toString().trim();
			textField_7.setText(idd);
			textField.setText(name);
			textField_2.setText(age);
			textField_5.setText(IdentityCard);
			textField_4.setText(tel);
			textField_6.setText(password);
		}
	}
	class addUserActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			int id=Integer.parseInt(textField_7.getText());
			int i=Dao.Deluser(id);
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				dispose();
				Object[][] results=getFileStates(Dao.selectuser());
				DefaultTableModel model=new DefaultTableModel();
				table.setModel(model);
				model.setDataVector(results, str);
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(textField.getText().length()==0){
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}

		if(textField_2.getText().length()==0){
			JOptionPane.showMessageDialog(null, "年龄不能为空");
			return;
		}

		if(textField_4.getText().length()==0){
			JOptionPane.showMessageDialog(null, "电话不能为空");
			return;
		}
		if(textField_4.getText().length()!=11){
			JOptionPane.showMessageDialog(null, "电话号必须是十一位");
			return;
		}
		if(textField_5.getText().length()==0){
			JOptionPane.showMessageDialog(null, "身份证不能为空");
			return;
		}
		if(textField_6.getText().length()==0){
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(textField_6.getText().length()>15){
			JOptionPane.showMessageDialog(null, "密码不能大于十五位");
			return;
		}

		int id=Integer.parseInt(textField_7.getText());
		String name=textField.getText();
		String sex="1";
		if(JRadioButton2.isSelected()){
			sex="2";}
		int age=Integer.parseInt(textField_2.getText());
		String card=textField_5.getText();
		String tel=textField_4.getText();
		String password=textField_6.getText();
		int i=	Dao.Updateuser(id, name, sex, age, card, tel, password);
		if(i==1){

			JOptionPane.showMessageDialog(null, "修改成功");
			dispose();
			Object[][] results=getFileStates(Dao.selectuser());
			DefaultTableModel model=new DefaultTableModel();
			table.setModel(model);
			model.setDataVector(results, str);

		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			dispose();
		}
	}
}
