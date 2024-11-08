package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.Item.Item;
import com.dao.Dao;
import com.model.BookType;
import com.Item.MyDocument;

public class BookAdd extends JFrame implements ActionListener{
	JComboBox publisher,bookType;
	JTextField price,translator,writer,ISBN,bookName;
	JButton buttonadd,buttonclose;
	DefaultComboBoxModel bookTypeModel;
	public BookAdd() {
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setTitle("图书信息添加");						// 设置窗体标题
		setBounds(100, 100, 396, 260);					// 设置窗体位置和大小

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		JLabel label_2 = new JLabel();
		label_2.setText("图书编号：");
		panel.add(label_2);

		ISBN = new JTextField("请输入13位书号",13);
		ISBN.setDocument(new MyDocument(13)); //设置书号文本框最大输入值为13

		ISBN.setColumns(13);
		panel.add(ISBN);

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("类别：");
		panel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();

		//从数据库中取出图书类别
		List list=Dao.selectBookCategory();
		for(int i=0;i<list.size();i++){
			BookType booktype=(BookType)list.get(i);
			Item item=new Item();
			item.setId((String)booktype.getId());
			item.setName((String)booktype.getTypeName());
			bookTypeModel.addElement(item);
		}
		panel.add(bookType);

		JLabel label_1 = new JLabel();
		label_1.setText("书名：");
		panel.add(label_1);

		bookName = new JTextField();
		panel.add(bookName);

		JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("作者：");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		JLabel label_2_1 = new JLabel();
		label_2_1.setText("出版社：");
		panel.add(label_2_1);

		publisher = new JComboBox();
		String[]array=new String[]{"清华大学出版社","延边出版社","同济大学出版社","不知名出版社"};
		publisher.setModel(new DefaultComboBoxModel(array));
		panel.add(publisher);

		JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("译者：");
		panel.add(label_4);
		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		panel.add(translator);


		JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("单价：");
		panel.add(label_3_1);
		price=new JTextField();
		price.setDocument(new MyDocument(10));
		panel.add(price);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(this);
		buttonadd.setText("添加");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("关闭");
		panel_1.add(buttonclose);


		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (ISBN.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
			return;
		}
		if (ISBN.getText().length() != 13) {
			JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
			return;
		}
		if (bookName.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "图书名称文本框不可以为空");
			return;
		}
		if (writer.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
			return;
		}

		if (price.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
			return;
		}
		if(!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
			JOptionPane.showMessageDialog(null, "添加书号重复！");

		}
			String ISBNs=ISBN.getText().trim();

			//分类
			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			String bookTypes=item.getId();
			String translators=translator.getText().trim();
			String bookNames=bookName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=(String)publisher.getSelectedItem();
			String prices=price.getText().trim();
			int i=Dao.Insertbook(ISBNs,bookTypes, bookNames, writers, translators, publishers,Double.parseDouble(prices));

			if(i==1){

				JOptionPane.showMessageDialog(null, "添加成功");
				dispose();
			}
		}

	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
