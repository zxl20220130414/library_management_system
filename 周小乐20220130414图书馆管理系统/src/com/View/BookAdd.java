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
		setTitle("ͼ����Ϣ���");						// ���ô������
		setBounds(100, 100, 396, 260);					// ���ô���λ�úʹ�С

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		JLabel label_2 = new JLabel();
		label_2.setText("ͼ���ţ�");
		panel.add(label_2);

		ISBN = new JTextField("������13λ���",13);
		ISBN.setDocument(new MyDocument(13)); //��������ı����������ֵΪ13

		ISBN.setColumns(13);
		panel.add(ISBN);

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("���");
		panel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();

		//�����ݿ���ȡ��ͼ�����
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
		label_1.setText("������");
		panel.add(label_1);

		bookName = new JTextField();
		panel.add(bookName);

		JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("���ߣ�");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		JLabel label_2_1 = new JLabel();
		label_2_1.setText("�����磺");
		panel.add(label_2_1);

		publisher = new JComboBox();
		String[]array=new String[]{"�廪��ѧ������","�ӱ߳�����","ͬ�ô�ѧ������","��֪��������"};
		publisher.setModel(new DefaultComboBoxModel(array));
		panel.add(publisher);

		JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("���ߣ�");
		panel.add(label_4);
		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		panel.add(translator);


		JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("���ۣ�");
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
		buttonadd.setText("���");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("�ر�");
		panel_1.add(buttonclose);


		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (ISBN.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
			return;
		}
		if (ISBN.getText().length() != 13) {
			JOptionPane.showMessageDialog(null, "����ı�������λ��Ϊ13λ");
			return;
		}
		if (bookName.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "ͼ�������ı��򲻿���Ϊ��");
			return;
		}
		if (writer.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
			return;
		}

		if (price.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
			return;
		}
		if(!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
			JOptionPane.showMessageDialog(null, "�������ظ���");

		}
			String ISBNs=ISBN.getText().trim();

			//����
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

				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				dispose();
			}
		}

	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
