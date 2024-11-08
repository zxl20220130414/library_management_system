package com.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import com.Item.Item;
import com.dao.Dao;
import com.model.BookInfo;

public class BookSearch extends JFrame implements ActionListener {
	JTextField textField_1;
	JTable table_1, table_2;
	JComboBox choice;
	JScrollPane scrollPane, scrollPane_1;
	String[] booksearch = { "���", "����", "����", "����", "����","������", "����" };
	public Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			BookInfo book = (BookInfo) list.get(i);
			s[i][0] = book.getISBN();
			String booktypename=String.valueOf(Item.getMap().get(book.getTypeid()));
			s[i][1] = booktypename;
			s[i][2] = book.getBookname();
			s[i][3] = book.getWriter();
			s[i][4] = book.getTranslator();
			s[i][5] = book.getPublisher();
			s[i][6] = book.getPrice();

		}
		return s;

	}

	public BookSearch() {
		setTitle("ͼ���ѯ");
		setBounds(100, 100, 500, 400);
		setVisible(true);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("������ѯ", null, panel_1, null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "��ѡ���ѯ��Ŀ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		choice=new JComboBox();
		String[] array={"ͼ������","ͼ������"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);

		}
		panel_1_1.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_1_1.add(textField_1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollPane_1);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		JButton button = new JButton();
		button.setText("��ѯ");
		panel_2_1.add(button);
		button.addActionListener(this);

		JButton button_1 = new JButton();
		button_1.setText("�˳�");
		panel_2_1.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		setVisible(true);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("��ʾͼ��ȫ����Ϣ", null, panel_2, null);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		panel_2.add(scrollPane);
		Object[][] results=getselect(Dao.selectbookserch());
		String [] booksearch = { "���", "����", "����", "����", "����","������", "����" };
		table_1 = new JTable(results,booksearch);
		scrollPane.setViewportView(table_1);
	}
		public void actionPerformed( ActionEvent e) {
			String name=(String)choice.getSelectedItem();
			if(name.equals("ͼ������")){
				Object[][] results=getselect(Dao.selectbookmohu(textField_1.getText()));
				table_2 = new JTable(results,booksearch);
				scrollPane_1.setViewportView(table_2);
			}
			else if(name.equals("ͼ������")){
				Object[][] results=getselect(Dao.selectbookmohuwriter(textField_1.getText()));
				table_2 = new JTable(results,booksearch);
				scrollPane_1.setViewportView(table_2);
			}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed( ActionEvent e) {
			dispose();
		}
	}
}
