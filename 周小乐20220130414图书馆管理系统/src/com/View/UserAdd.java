package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.dao.Dao;
import com.Item.MyDocument;
public class UserAdd extends JFrame implements ActionListener {
	JTextField textField_5,textField_4,textField_2, textField;
	JPasswordField textField_6;
	JButton button;
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton radioButton1,radioButton2;
	public UserAdd() {
		setTitle("用户信息添加");
		setBounds(120, 120, 450, 320);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		button = new JButton();
		button.setText("保存");
		panel.add(button);
		button.addActionListener(this);

		JButton button_1 = new JButton();
		button_1.setText("取消");
		panel.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		setVisible(true);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 250));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(10);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(280, 200));
		panel_1.add(panel_2);

		JLabel label = new JLabel();
		label.setText("用户姓名：");
		panel_2.add(label);
		textField = new JTextField();
		panel_2.add(textField);

		JLabel label_1 = new JLabel();
		label_1.setText("性    别：");
		panel_2.add(label_1);

		JPanel label_8 = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_8.setLayout(flowLayout);
		panel_1.add(label_8);

		radioButton1 = new JRadioButton();
		label_8.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		radioButton1.setText("男");
		radioButton1.addActionListener(this);

		radioButton2 = new JRadioButton();
		label_8.add(radioButton2);
		radioButton2.setSelected(true);
		buttonGroup.add(radioButton2);
		radioButton2.setText("女");

		panel_2.add(label_8);

		JLabel label_2 = new JLabel();
		label_2.setText("年    龄：");
		panel_2.add(label_2);
		textField_2 = new JTextField();
		textField_2.setDocument(new MyDocument(2));
		textField_2.setColumns(2);
		panel_2.add(textField_2);

		JLabel label_4 = new JLabel();
		label_4.setText("联系电话：");
		panel_2.add(label_4);
		textField_4 = new JTextField("电话号必须是十一位",11);
		textField_4.setDocument(new MyDocument(11));
		textField_4.setColumns(11);
		panel_2.add(textField_4);

		JLabel label_7 = new JLabel();
		panel_2.add(label_7);
		label_7.setText("身份证：");
		textField_5 = new JTextField();
		panel_2.add(textField_5);
		textField_5.setColumns(20);

		JLabel label_5 = new JLabel();
		label_5.setText("密    码：");
		panel_2.add(label_5);
		textField_6 = new  JPasswordField();
		panel_2.add(textField_6);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(textField.getText().length()==0){
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if(textField.getText().length()>12){
			JOptionPane.showMessageDialog(null, "用户名位数不能大于十二位");
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
		String username=textField.getText();
		int age=Integer.parseInt(textField_2.getText());
		String tel=textField_4.getText();
		String yajin=textField_5.getText();
		String password=textField_6.getText();
		String sex="1";
		if(!radioButton1.isSelected()){
			sex="2";
		}
		int i=Dao.Insertoperator(username,sex,age,yajin,tel,password);
		if(i==1){
			JOptionPane.showMessageDialog(null, "添加成功！");
			dispose();
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
