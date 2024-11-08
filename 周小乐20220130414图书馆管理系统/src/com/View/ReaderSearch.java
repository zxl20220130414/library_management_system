package com.View;

import com.dao.Dao;
import com.model.Reader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReaderSearch extends JFrame implements ActionListener {
    JTextField textField_1;
    JTable table_1, table_2;
    JComboBox choice;
    JScrollPane scrollPane, scrollPane_1;
    String readersearch[]={ "��������", "�����Ա�", "��������", "֤������",
            "��������", "�绰","֤��","ְҵ","���߱��" };
    String[] array=new String[]{"���֤","ѧ��֤","����֤"};

    public Object[][] getselect(List list){
        Object[][]results=new Object[list.size()][9];
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


    public ReaderSearch() {
        setTitle("���߲�ѯ");
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
        String[] array={"���߱��","��������"};
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
        tabbedPane.addTab("��ʾȫ��������Ϣ", null, panel_2, null);
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(450, 250));
        panel_2.add(scrollPane);
        Object[][] results=getselect(Dao.selectreaderserch());
        String readersearch[]={ "��������", "�����Ա�", "��������", "֤������",
                "��������", "�绰","֤��","ְҵ","���߱��" };
        table_1 = new JTable(results,readersearch);
        scrollPane.setViewportView(table_1);
    }
    public void actionPerformed( ActionEvent e) {
        String ISBN=(String)choice.getSelectedItem();
        if(ISBN.equals("ISBN")){
            Object[][] results=getselect(Dao.selectreadermohu(textField_1.getText()));
            table_2 = new JTable(results,readersearch);
            scrollPane_1.setViewportView(table_2);
        }
        else if(ISBN.equals("��������")){
            Object[][] results=getselect(Dao.selectreadermohuname(textField_1.getText()));
            table_2 = new JTable(results,readersearch);
            scrollPane_1.setViewportView(table_2);
        }
    }
    class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
        public void actionPerformed( ActionEvent e) {
            dispose();
        }
    }
}
