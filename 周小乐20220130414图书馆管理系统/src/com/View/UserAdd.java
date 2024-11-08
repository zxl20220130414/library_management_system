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
		setTitle("�û���Ϣ���");
		setBounds(120, 120, 450, 320);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		button = new JButton();
		button.setText("����");
		panel.add(button);
		button.addActionListener(this);

		JButton button_1 = new JButton();
		button_1.setText("ȡ��");
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
		label.setText("�û�������");
		panel_2.add(label);
		textField = new JTextField();
		panel_2.add(textField);

		JLabel label_1 = new JLabel();
		label_1.setText("��    ��");
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
		radioButton1.setText("��");
		radioButton1.addActionListener(this);

		radioButton2 = new JRadioButton();
		label_8.add(radioButton2);
		radioButton2.setSelected(true);
		buttonGroup.add(radioButton2);
		radioButton2.setText("Ů");

		panel_2.add(label_8);

		JLabel label_2 = new JLabel();
		label_2.setText("��    �䣺");
		panel_2.add(label_2);
		textField_2 = new JTextField();
		textField_2.setDocument(new MyDocument(2));
		textField_2.setColumns(2);
		panel_2.add(textField_2);

		JLabel label_4 = new JLabel();
		label_4.setText("��ϵ�绰��");
		panel_2.add(label_4);
		textField_4 = new JTextField("�绰�ű�����ʮһλ",11);
		textField_4.setDocument(new MyDocument(11));
		textField_4.setColumns(11);
		panel_2.add(textField_4);

		JLabel label_7 = new JLabel();
		panel_2.add(label_7);
		label_7.setText("���֤��");
		textField_5 = new JTextField();
		panel_2.add(textField_5);
		textField_5.setColumns(20);

		JLabel label_5 = new JLabel();
		label_5.setText("��    �룺");
		panel_2.add(label_5);
		textField_6 = new  JPasswordField();
		panel_2.add(textField_6);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(textField.getText().length()==0){
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
			return;
		}
		if(textField.getText().length()>12){
			JOptionPane.showMessageDialog(null, "�û���λ�����ܴ���ʮ��λ");
			return;
		}
		if(textField_2.getText().length()==0){
			JOptionPane.showMessageDialog(null, "���䲻��Ϊ��");
			return;
		}
		if(textField_4.getText().length()==0){
			JOptionPane.showMessageDialog(null, "�绰����Ϊ��");
			return;
		}
		if(textField_4.getText().length()!=11){
			JOptionPane.showMessageDialog(null, "�绰�ű�����ʮһλ");
			return;
		}
		if(textField_5.getText().length()==0){
			JOptionPane.showMessageDialog(null, "���֤����Ϊ��");
			return;
		}
		if(textField_6.getText().length()==0){
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
			return;
		}
		if(textField_6.getText().length()>15){
			JOptionPane.showMessageDialog(null, "���벻�ܴ���ʮ��λ");
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
			JOptionPane.showMessageDialog(null, "��ӳɹ���");
			dispose();
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
