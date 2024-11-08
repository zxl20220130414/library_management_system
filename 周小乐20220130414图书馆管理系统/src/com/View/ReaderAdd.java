package com.View;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.dao.Dao;
import com.Item.MyDocument;

public class ReaderAdd extends JFrame implements ActionListener {
	JTextField ISBN;
	ButtonGroup buttonGroup = new ButtonGroup();
	JTextField tel,zjnumber,zy,age,readername;
	JComboBox comboBox;
	JFormattedTextField maxnumber;
	String [] array;

	public ReaderAdd() {
		setTitle("读者相关信息添加");
		setBounds(100, 100, 500, 350);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(450, 200));
		panel.add(panel_1);

		JLabel label_2 = new JLabel();
		label_2.setText("姓    名：");
		panel_1.add(label_2);

		readername = new JTextField();
		readername.setDocument(new MyDocument(10));
		panel_1.add(readername);

		JLabel label_3 = new JLabel();
		label_3.setText("性    别：");
		panel_1.add(label_3);

		JPanel label_13 = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_13.setLayout(flowLayout);
		panel_1.add(label_13);

		JRadioButton radioButton1 = new JRadioButton();
		label_13.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		radioButton1.setText("男");

		JRadioButton radioButton2 = new JRadioButton();
		label_13.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("女");

		JLabel label_4 = new JLabel();
		label_4.setText("年    龄：");
		panel_1.add(label_4);

		age = new JTextField();
		age.setDocument(new MyDocument(2));//设置书号文本框最大输入值为2
		panel_1.add(age);

		JLabel label_5 = new JLabel();
		label_5.setText("职    业：");
		panel_1.add(label_5);

		zy = new JTextField();
		zy.setDocument(new MyDocument(30));
		panel_1.add(zy);

		JLabel label_6 = new JLabel();
		label_6.setText("有效证件：");
		panel_1.add(label_6);

		comboBox = new JComboBox();
		array=new String[]{"身份证","学生证","工作证"};
		comboBox.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			comboBox.setSelectedIndex(i);
			comboBox.setSelectedItem(array);
		}
		panel_1.add(comboBox);

		JLabel label_7 = new JLabel();
		label_7.setText("证件号码：");
		panel_1.add(label_7);

		zjnumber = new JTextField();
		zjnumber.setDocument(new MyDocument(13));
		panel_1.add(zjnumber);

		JLabel label_9 = new JLabel();
		label_9.setText("最大借书量：");
		panel_1.add(label_9);

		maxnumber = new JFormattedTextField();
		maxnumber.setDocument(new MyDocument(2));
		panel_1.add(maxnumber);

		JLabel label_11 = new JLabel();
		label_11.setText("电    话：");
		panel_1.add(label_11);
		tel = new JTextField();
		tel.setDocument(new MyDocument(11));

		panel_1.add(tel);

		JLabel label_1 = new JLabel();
		label_1.setText("读者编号：");
		panel_1.add(label_1);

		ISBN = new JTextField();
		ISBN.setDocument(new MyDocument(13));
		panel_1.add(ISBN);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);

		JButton save = new JButton();
		panel_2.add(save);
		save.setText("保存");
		save.addActionListener(this);

		JButton back = new JButton();
		panel_2.add(back);
		back.setText("返回");
		back.addActionListener(new CloseActionListener());
		setVisible(true);
	}
		public void actionPerformed( ActionEvent e) {

			if(readername.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
				return;
			}
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者年龄文本框不可为空");
				return;
			}

			if(zjnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "证件号码文本框不可为空");
				return;
			}
			if(zjnumber.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "证件号码位数为13");
				return;
			}

			if(zy.getText().length()==0){
				JOptionPane.showMessageDialog(null, "职业文本框不可为空");
				return;
			}
			if(zy.getText().length()>20){
				JOptionPane.showMessageDialog(null, "职业文本框位数为20");
				return;
			}
			if(ISBN.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者条形码文本框不可为空");
				return;
			}
			if(ISBN.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "读者条形码文本框为13位");
				return;
			}
			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "电话号码文本框不可为空");
				return;
			}
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "电话号码位数小于11位");
				return;
			}
			if(maxnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大借书量文本框不可为空");
				return;
			}
			if(maxnumber.getText().length()>2||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "最大借书量为两位数字");
				return;
			}
			String sex="1";
			String zj=String.valueOf(comboBox.getSelectedIndex());
			System.out.println(comboBox.getSelectedIndex());
			int i=Dao.InsertReader(readername.getText().trim(),sex.trim(),age.getText().trim(),zjnumber.getText().trim(),maxnumber.getText().trim(),tel.getText().trim(),zj,zy.getText().trim(),ISBN.getText().trim());
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