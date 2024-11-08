package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.Item.Item;
import com.dao.Dao;
import com.model.Operater;
import com.Item.MyDocument;
import com.model.BookInfo;
public class BookBorrow extends JFrame implements ActionListener {
	Operater user = Login.getUser();
	JTextField price,bookType,bookName,bookISBN,number,readerISBN;
	Map map = Item.getMap();
	DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public BookBorrow() {
		System.out.println(user.getName());
		setTitle("图书借阅管理");
		setBounds(200, 200, 500, 400);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JPanel panel_4 = new JPanel();
		GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(10);
		panel_4.setLayout(gridLayout_1);
		panel_4.setPreferredSize(new Dimension(240, 110));
		panel.setLayout(null);
		getContentPane().add(panel_4);

		JLabel label = new JLabel();
		label.setText("读者编号：");
		panel_4.add(label);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		panel_4.add(readerISBN);

		JLabel label_5 = new JLabel();
		label_5.setText("书籍编号：");
		panel_4.add(label_5);

		bookISBN = new JTextField();
		bookISBN.setDocument(new MyDocument(13));
		panel_4.add(bookISBN);
		bookISBN.addKeyListener(new bookISBNListenerlostFocus());
		JLabel label_6 = new JLabel();
		label_6.setText("书籍名称：");
		panel_4.add(label_6);

		bookName = new JTextField();
		bookName.setEditable(false);
		panel_4.add(bookName);

		JLabel label_7 = new JLabel();
		label_7.setText("书籍类别：");
		panel_4.add(label_7);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_4.add(bookType);

		JLabel label_8 = new JLabel();
		label_8.setText("书籍价格：");
		panel_4.add(label_8);

		price = new JTextField();
		price.setEditable(false);
		panel_4.add(price);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 120));
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 100));
		panel_2.add(panel_8);

		JButton buttonBorrow = new JButton();
		buttonBorrow.setText("借出当前图书");
		buttonBorrow.addActionListener(this);
		panel_8.add(buttonBorrow);

		JButton buttonClear = new JButton();
		buttonClear.setText("清除所有记录");
		panel_8.add(buttonClear);
		setVisible(true);

	}
	class bookISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				if (readerISBN.getText().trim().length()!=0
						&& bookISBN.getText().trim().length()!=0) {
					String ISBNs = bookISBN.getText().trim();
					List list = Dao.selectBookInfo(ISBNs);
					for (int i = 0; i < list.size(); i++) {
						BookInfo book = (BookInfo) list.get(i);
						bookName.setText(book.getBookname());
						bookType.setText(String.valueOf(map.get(book
								.getTypeid())));
						price.setText(String.valueOf(book.getPrice()));
					}
					String readerISBNs = readerISBN.getText().trim();
					List list4 = Dao.selectBookInfo(ISBNs);// 此书是否在bookInfo表中
					if (!readerISBNs.isEmpty()) {
						return;
					}
					if (list4.isEmpty() && !ISBNs.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"本图书馆没有此书，查询输入图书编号是否有误！");
						return;
					}
					number.setText(String.valueOf(Integer.parseInt(number.getText().trim()) - 1));
				}
				else
					JOptionPane.showMessageDialog(null, "请输入读者条形码！");
			}
		}
	}
		public void actionPerformed( ActionEvent e) {
			String bookISBNs=bookISBN.getText().trim();
			String readerISBNs=readerISBN.getText().trim();
			String operatorId=user.getId();
			String borrowDate=myfmt.format(new java.util.Date());
			int i=Dao.InsertBookBorrow(bookISBNs, readerISBNs, operatorId, java.sql.Timestamp.valueOf(borrowDate));
			if(i==1){
				JOptionPane.showMessageDialog(null, "图书借阅完成！");
				dispose();
			}
		}

}