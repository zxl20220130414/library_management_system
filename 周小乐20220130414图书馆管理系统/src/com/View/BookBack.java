package com.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.Item.Item;
import com.dao.Dao;
import com.model.Back;
import com.model.Operater;
import com.Item.MyDocument;
public class BookBack extends JFrame implements ActionListener {
	Operater user = Login.getUser();
	JTable table;
	JTextField operator,todaydate,readerISBN;
	String[] columnNames = {"图书名称", "图书条形码", "图书类别", "读者姓名", "读者条形码"};
	DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String bookISBNs;
	String readerISBNs;
	int id;
	public void add() {
		readerISBNs = readerISBN.getText().trim();
		List list = Dao.selectBookBack(readerISBNs);
		for (int i = 0; i < list.size(); i++) {
			Back back = (Back) list.get(i);
			id = back.getId();
			String str[] = new String[5];
			str[0] = back.getBookname();
			str[1] = back.getBookISBN();
			str[2] = String.valueOf(Item.getMap().get(back.getTypeId() + ""));
			str[3] = back.getReaderName();
			str[4] = back.getReaderISBN();
			model.addRow(str);
		}
	}
	public BookBack() {
		super();
		setTitle("图书归还管理");
		setBounds(100, 100, 600, 480);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "基本信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(0, 200));
		getContentPane().add(panel, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(5);
		panel_5.setLayout(gridLayout_1);
		panel_5.setPreferredSize(new Dimension(400, 20));
		panel.add(panel_5);

		JLabel label_4 = new JLabel();
		label_4.setText("读者编号：");
		panel_5.add(label_4);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		readerISBN.addKeyListener(new readerISBNListenerlostFocus());
		panel_5.add(readerISBN);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new FlowLayout());
		panel_4.setPreferredSize(new Dimension(450, 130));
		panel.add(panel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 120));
		panel_4.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.addMouseListener(new TableListener());


		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		GridLayout gridLayout_2 = new GridLayout(0, 2);
		gridLayout_2.setVgap(20);
		panel_2.setLayout(gridLayout_2);
		panel_2.setPreferredSize(new Dimension(250, 230));
		panel_1.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "系统信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_3.setPreferredSize(new Dimension(280, 230));
		panel_1.add(panel_3);

		JPanel panel_7 = new JPanel();
		GridLayout gridLayout_3 = new GridLayout(0, 2);
		gridLayout_3.setVgap(35);
		panel_7.setLayout(gridLayout_3);
		panel_7.setPreferredSize(new Dimension(260, 90));
		panel_3.add(panel_7);

		JLabel label_10_1 = new JLabel();
		label_10_1.setText("当前时间：");
		panel_7.add(label_10_1);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		todaydate.addActionListener(new TimeActionListener());
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		JLabel label_11_1 = new JLabel();
		label_11_1.setText("操作员：");
		panel_7.add(label_11_1);

		operator = new JTextField(user.getName());
		operator.setEditable(false);
		panel_7.add(operator);

		JButton buttonback = new JButton();
		buttonback.setText("图书归还");
		buttonback.addActionListener(this);
		panel_3.add(buttonback);

		JButton buttonExit = new JButton();
		buttonExit.setText("退出");
		buttonExit.addActionListener(new CloseActionListener());
		panel_3.add(buttonExit);
		setVisible(true);

	}

	class readerISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				add();
			}
		}
	}

	class TimeActionListener implements ActionListener {
		public TimeActionListener() {
			Timer t = new Timer(1000, this);
			t.start();
		}
		public void actionPerformed(ActionEvent e) {
			todaydate.setText(myfmt.format(new java.util.Date()).toString());
		}
	}

	class TableListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			java.util.Date date = new java.util.Date();
			int selRow = table.getSelectedRow();
			bookISBNs = table.getValueAt(selRow, 1).toString().trim();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成方法存根
		if (readerISBNs == null) {
			JOptionPane.showMessageDialog(null, "请先按回车键");
			return;
		}
		System.out.println(bookISBNs == null);

		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "请选择所要归还的图书！");
			return;
		}
		int i = Dao.UpdateBookBack(bookISBNs, readerISBNs, id);
		System.out.print(i);
		if (i == 1) {
			int selectedRow = table.getSelectedRow();
			model.removeRow(selectedRow);
			JOptionPane.showMessageDialog(null, "还书操作完成！");
		}
	}
	class CloseActionListener implements ActionListener {            // 添加关闭按钮的事件监听器
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
