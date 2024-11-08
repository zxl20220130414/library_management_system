package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.dao.Dao;
import com.model.Reader;
import com.Item.MyDocument;

public class ReaderModiAndDel extends JFrame implements ActionListener {
	ButtonGroup buttonGroup = new ButtonGroup();
	JTable table;
	JTextField ISBN,zy,tel,maxnumber,zjnumber,age,readername;
	JComboBox comboBox;
	JRadioButton JRadioButton1,JRadioButton2;
	String[] columnNames={ "��������", "�����Ա�", "��������", "֤������",
			"��������", "�绰","֤��","ְҵ","���߱��" };
	String[] array=new String[]{"���֤","ѧ��֤","����֤"};

	public Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Reader reader=(Reader)list.get(i);
			results[i][0]=reader.getName();
			String sex;
			if(reader.getSex().equals("1")){
				sex="��";
			}
			else
				sex="Ů";
			results[i][1]=sex;
			results[i][2]=reader.getAge();
			results[i][3]=reader.getIdentityCard();
			results[i][4]=reader.getMaxNum();
			results[i][5]=reader.getTel();
			results[i][6]=array[reader.getZj()];
			results[i][7]=reader.getZy();
			results[i][8]=reader.getISBN();
		}
		return results;
	}
	public ReaderModiAndDel() {
		setTitle("������Ϣ�޸���ɾ��");
		setBounds(100, 100, 600, 420);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 100));
		panel_1.add(scrollPane, BorderLayout.NORTH);


		DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectReader());
		model.setDataVector(results,columnNames);

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());

		JPanel panel_2 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(9);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(0, 200));
		panel_1.add(panel_2, BorderLayout.SOUTH);

		JLabel label_1 = new JLabel();
		label_1.setText("  ��    ����");
		panel_2.add(label_1);

		readername = new JTextField();
		readername.setDocument(new MyDocument(10));
		panel_2.add(readername);

		JLabel label_2 = new JLabel();
		label_2.setText("  ��    ��");
		panel_2.add(label_2);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		JRadioButton1 = new JRadioButton();
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel_3.add(JRadioButton1);
		JRadioButton1.setText("��");

		JRadioButton2 = new JRadioButton();
		buttonGroup.add(JRadioButton2);
		panel_3.add(JRadioButton2);
		JRadioButton2.setText("Ů");

		JLabel label_3 = new JLabel();
		label_3.setText("  ��    �䣺");
		panel_2.add(label_3);

		age = new JTextField();
		age.setDocument(new MyDocument(2));

		panel_2.add(age);

		JLabel label_5 = new JLabel();
		label_5.setText("  ְ    ҵ��");
		panel_2.add(label_5);

		zy = new JTextField();
		zy.setDocument(new MyDocument(30));
		panel_2.add(zy);

		JLabel label = new JLabel();
		label.setText("  ��Ч֤����");
		panel_2.add(label);

		comboBox = new JComboBox();

		comboBox.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			comboBox.setSelectedIndex(i);
			comboBox.setSelectedItem(array);
		}
		panel_2.add(comboBox);

		JLabel label_6 = new JLabel();
		label_6.setText("  ֤�����룺");
		panel_2.add(label_6);

		zjnumber = new JTextField();
		zjnumber.setDocument(new MyDocument(13));

		panel_2.add(zjnumber);

		JLabel label_9 = new JLabel();
		label_9.setText("  ����������");
		panel_2.add(label_9);

		maxnumber = new JTextField();
		panel_2.add(maxnumber);

		JLabel label_8 = new JLabel();
		label_8.setText("  ��    ����");
		panel_2.add(label_8);

		tel = new JFormattedTextField();
		tel.setDocument(new MyDocument(11));
		panel_2.add(tel);

		JLabel label_4 = new JLabel();
		label_4.setText("  ���߱�ţ�");
		panel_2.add(label_4);

		ISBN = new JTextField();
		ISBN.setEditable(false);
		ISBN.setDocument(new MyDocument(13));
		panel_2.add(ISBN);

		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(0, 0));
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(4);
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4);

		JButton button = new JButton();
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_4.add(button);
		button.setText("�޸�");
		button.addActionListener(this);

		JButton buttonDel = new JButton();
		panel_4.add(buttonDel);
		buttonDel.setText("ɾ��");
		buttonDel.addActionListener(new DelButtonListener(model));
		dispose();
		setVisible(true);

	}

	class TableListener extends MouseAdapter {
		public void mouseClicked( MouseEvent e) {

			int selRow = table.getSelectedRow();
			readername.setText(table.getValueAt(selRow, 0).toString().trim());
			if(table.getValueAt(selRow, 1).toString().trim().equals("��"))
				JRadioButton1.setSelected(true);
			else
				JRadioButton2.setSelected(true);
			age.setText(table.getValueAt(selRow, 2).toString().trim());
			zjnumber.setText(table.getValueAt(selRow, 3).toString().trim());
			maxnumber.setText(table.getValueAt(selRow, 4).toString().trim());
			tel.setText(table.getValueAt(selRow, 5).toString().trim());
			comboBox.setSelectedItem(table.getValueAt(selRow, 6).toString().trim());
			zy.setText(table.getValueAt(selRow, 7).toString().trim());
			ISBN.setText(table.getValueAt(selRow, 8).toString().trim());

		}
	}

	private  class DelButtonListener implements ActionListener {
		private DefaultTableModel model;
		public DelButtonListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(ActionEvent e) {
			int i=Dao.DelReader(ISBN.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				dispose();
				Object[][] results=getFileStates(Dao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}

		public void actionPerformed(ActionEvent e) {
			if (readername.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if (age.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if (zjnumber.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "֤�������ı��򲻿�Ϊ��");
				return;
			}
			if (zy.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "ְҵ�ı��򲻿�Ϊ��");
				return;
			}
			if (ISBN.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�����������ı��򲻿�Ϊ��");
				return;
			}
			if (ISBN.getText().length() != 13) {
				JOptionPane.showMessageDialog(null, "�����������ı���Ϊ13λ");
				return;
			}

			if (tel.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�绰�����ı��򲻿�Ϊ��");
				return;
			}
			if (tel.getText().length() > 11 || tel.getText().length() < 0) {
				JOptionPane.showMessageDialog(null, "�绰����λ��С��11λ");
				return;
			}
			if (maxnumber.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if (maxnumber.getText().length() > 2 || tel.getText().length() < 0) {
				JOptionPane.showMessageDialog(null, "��������Ϊ��λ����");
				return;
			}
			String sex = "1";
			if (!JRadioButton1.isSelected()) {
				sex = "2";
			}
			String zj = String.valueOf(comboBox.getSelectedIndex());
			System.out.println(comboBox.getSelectedIndex());
			DefaultTableModel model=new DefaultTableModel();
			int i = Dao.UpdateReader(readername.getText().trim(), sex, age.getText().trim(), zjnumber.getText().trim(), maxnumber.getText().trim(), tel.getText().trim(), zj, zy.getText().trim(), ISBN.getText().trim());
			System.out.println(i);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				Object[][] results = getFileStates(Dao.selectReader());
				model.setDataVector(results, columnNames);
				table.setModel(model);
			}
		}
}
