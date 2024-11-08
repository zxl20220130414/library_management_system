package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;
import com.dao.Dao;
import com.Item.MyDocument;

public class BookTypeAdd extends JFrame implements ActionListener {
	 JFormattedTextField days;
	 JTextField bookTypeName,fakuan;
	public BookTypeAdd() {
		setTitle("图书类别添加");
		setBounds(100, 100, 500, 500);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 80));
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel label_4 = new JLabel();
		label_4.setPreferredSize(new Dimension(400, 80));
		panel.add(label_4);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 0));
		getContentPane().add(panel_2, BorderLayout.WEST);

		JLabel label = new JLabel();
		panel_2.add(label);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		getContentPane().add(panel_3, BorderLayout.CENTER);

		JLabel label_1 = new JLabel();
		label_1.setPreferredSize(new Dimension(390, 50));
		panel_3.add(label_1);

		JLabel label_2 = new JLabel();
		label_2.setPreferredSize(new Dimension(160, 20));
		label_2.setText("图书类别名称：");
		panel_3.add(label_2);

		bookTypeName = new JTextField();
		bookTypeName.setDocument(new MyDocument(20));
		bookTypeName.setColumns(30);
		panel_3.add(bookTypeName);

		JLabel label_3 = new JLabel();
		label_3.setPreferredSize(new Dimension(160, 20));
		label_3.setText("可 借 天 数：");
		panel_3.add(label_3);

		days = new JFormattedTextField(NumberFormat.getIntegerInstance());
		days.setColumns(30);
		panel_3.add(days);

		JLabel label_5 = new JLabel();
		label_5.setPreferredSize(new Dimension(160, 20));
		label_5.setText("罚款（迟还一天的罚款数）：");
		panel_3.add(label_5);
		fakuan = new JTextField("单位为角");
		fakuan.setColumns(30);
		panel_3.add(fakuan);

		JButton button = new JButton();
		button.setText("保存");
		button.addActionListener(this);
		panel_3.add(button);

		JButton buttonDel = new JButton();
		buttonDel.setText("关闭");
		buttonDel.addActionListener(new CloseActionListener());
		panel_3.add(buttonDel);
		setVisible(true);

	}
	public void actionPerformed(final ActionEvent e) {
		if(bookTypeName.getText().length()==0){
			JOptionPane.showMessageDialog(null, "图书类别文本框不可为空");
			return;
		}
		if(days.getText().length()==0){
			JOptionPane.showMessageDialog(null, "可借天数文本框不可为空");
			return;
		}
		if(fakuan.getText().length()==0||fakuan.getText().trim().equals("单位为角")){
			JOptionPane.showMessageDialog(null, "罚款文本框不可为空");
			return;
		}
		int i=Dao.InsertBookType(bookTypeName.getText().trim(), days.getText().trim(),Double.valueOf(fakuan.getText().trim())/10);
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
