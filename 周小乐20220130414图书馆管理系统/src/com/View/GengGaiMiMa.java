package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.dao.Dao;
import com.model.Operater;
import com.Item.MyDocument;

public class GengGaiMiMa extends JFrame implements ActionListener {
	JPasswordField oldPass,newPass2,newPass1;
	JTextField username;
	Operater user = Login.getUser();

	public GengGaiMiMa() {
		setTitle("更改密码");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 300, 228);

		JLabel label_5 = new JLabel();
		label_5.setFont(new Font("", Font.PLAIN, 14));
		label_5.setText("登  录  名：");
		GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.gridy = 2;
		gridBagConstraints_11.gridx = 0;
		getContentPane().add(label_5, gridBagConstraints_11);

		username  =new JTextField(user.getName());
		GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.gridy = 2;
		gridBagConstraints_12.gridx = 1;
		gridBagConstraints_12.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(username, gridBagConstraints_12);

		username.setEditable(false);

		JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 14));
		label_1.setText("旧  密  码：");
		GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridy = 3;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(label_1, gridBagConstraints_2);

		oldPass = new JPasswordField();
		oldPass.setDocument(new MyDocument(6));
		GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.weighty = 1.0;
		gridBagConstraints_3.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_3.gridwidth = 3;
		gridBagConstraints_3.gridy = 3;
		gridBagConstraints_3.gridx = 1;
		getContentPane().add(oldPass, gridBagConstraints_3);

		JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));
		label_2.setText("新  密  码：");
		GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 4;
		gridBagConstraints_4.gridx = 0;
		getContentPane().add(label_2, gridBagConstraints_4);

		newPass1 = new JPasswordField();
		newPass1.setDocument(new MyDocument(6));
		newPass1.setFont(new Font("", Font.PLAIN, 14));
		GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.weighty = 1.0;
		gridBagConstraints_5.ipadx = 30;
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_5.gridwidth = 3;
		gridBagConstraints_5.gridy = 4;
		gridBagConstraints_5.gridx = 1;
		getContentPane().add(newPass1, gridBagConstraints_5);

		JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 14));
		label_3.setText("确认新密码：");
		GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.gridy = 5;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(label_3, gridBagConstraints_6);

		newPass2 = new JPasswordField();
		newPass2.setDocument(new MyDocument(6));
		newPass2.setFont(new Font("", Font.PLAIN, 14));
		GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.weighty = 1.0;
		gridBagConstraints_7.ipadx = 30;
		gridBagConstraints_7.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_7.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_7.weightx = 1.0;
		gridBagConstraints_7.gridwidth = 3;
		gridBagConstraints_7.gridy = 5;
		gridBagConstraints_7.gridx = 1;
		getContentPane().add(newPass2, gridBagConstraints_7);

		JButton button = new JButton();
		button.addActionListener(this);
		button.setText("确认");
		GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.weighty = 1.0;
		gridBagConstraints_8.anchor = GridBagConstraints.EAST;
		gridBagConstraints_8.gridy = 6;
		gridBagConstraints_8.gridx = 1;
		getContentPane().add(button, gridBagConstraints_8);

		JButton button_1 = new JButton();
		button_1.addActionListener(this);
		button_1.setText("重写");
		GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.gridwidth = 2;
		gridBagConstraints_9.weighty = 1.0;
		gridBagConstraints_9.gridy = 6;
		gridBagConstraints_9.gridx = 2;
		getContentPane().add(button_1, gridBagConstraints_9);

		setVisible(true);
	}

	public void actionPerformed( ActionEvent e) {
		if (oldPass.getText().equals(user.getPassword())) {
			if (newPass1.getText().equals(newPass2.getText())) {
				user.setPassword(newPass1.getText());
				Dao.Updatepass(user.getPassword(),user.getName());
				oldPass.setText(null);
				newPass1.setText(null);
				newPass2.setText(null);
				JOptionPane.showMessageDialog(getContentPane(), "密码修改成功。");
				dispose();
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "两次输入的密码不一致，请重新输入。");
			}
		}else {
			JOptionPane.showMessageDialog(getContentPane(), "旧密码输入错误，请确认密码。");
		}
	}
}
