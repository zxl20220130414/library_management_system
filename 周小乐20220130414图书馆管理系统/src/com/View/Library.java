package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Library extends JFrame implements ActionListener {
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13,item14,item15;
	public JDesktopPane DESKTOP_PANE = new JDesktopPane();
	public static void main(String[] args) {
		try {
			new Login();//��¼����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public Library() {

		    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		    setLocationByPlatform(true);
		    setSize(1000, 600);
		    setTitle("ͼ��ݹ���ϵͳ");
		    JMenuBar menuBar=new JMenuBar();
		    setJMenuBar(menuBar);

		    JLabel label = new JLabel();
		    label.setBounds(0, 0, 0, 0);
		    label.setIcon(null); // ���屳��
		    getContentPane().add(DESKTOP_PANE);

			JMenu readerManager= new JMenu("������Ϣ����");
			readerManager.setFont(new Font("����",Font.PLAIN,16));
			item1=new JMenuItem("������Ϣ���");
		    item1.setFont(new Font("����",Font.PLAIN,16));
			item2=new JMenuItem("������Ϣ�޸���ɾ��");
			item2.setFont(new Font("����",Font.PLAIN,16));
			item7=new JMenuItem("������������Ϣ��ʾ");
			item7.setFont(new Font("����",Font.PLAIN,16));
			item1.addActionListener(this);
			item2.addActionListener(this);
			item7.addActionListener(this);
			readerManager.add(item1);
			readerManager.add(item2);
			readerManager.add(item7);

			JMenu bookTypeManage = new JMenu("ͼ��������");
		    bookTypeManage.setFont(new Font("����",Font.PLAIN,16));
			item12=new JMenuItem("ͼ��������");
			item12.setFont(new Font("����",Font.PLAIN,16));
			item3=new JMenuItem("ͼ������޸���ɾ��");
		    item3.setFont(new Font("����",Font.PLAIN,16));
			item12.addActionListener(this);
			item3.addActionListener(this);
			bookTypeManage.add(item12);
			bookTypeManage.add(item3);

			JMenu bookManage = new JMenu("ͼ����Ϣ����");
			bookManage.setFont(new Font("����",Font.PLAIN,16));
			item4=new JMenuItem("ͼ����Ϣ���");
		    item4.setFont(new Font("����",Font.PLAIN,16));
			item5=new JMenuItem("ͼ����Ϣ�޸���ɾ��");
		    item5.setFont(new Font("����",Font.PLAIN,16));
		    item8=new JMenuItem("ͼ����������Ϣ��ʾ");
		    item8.setFont(new Font("����",Font.PLAIN,16));
			item4.addActionListener(this);
			item5.addActionListener(this);
		    item8.addActionListener(this);
			bookManage.add(item4);
			bookManage.add(item5);
		    bookManage.add(item8);

			JMenu borrowManage = new JMenu("���Ĺ���"); // ���Ĺ���
		    borrowManage.setFont(new Font("����",Font.PLAIN,16));
			item6=new JMenuItem("ͼ�����");
		    item6.setFont(new Font("����",Font.PLAIN,16));
			item13=new JMenuItem("ͼ��黹");
			item13.setFont(new Font("����",Font.PLAIN,16));
			item6.addActionListener(this);
			item13.addActionListener(this);
			borrowManage.add(item6);
			borrowManage.add(item13);


			JMenu userManage = new JMenu("�û�����");
		    userManage.setFont(new Font("����",Font.PLAIN,16));
			item9=new JMenuItem("�û����");
		    item9.setFont(new Font("����",Font.PLAIN,16));
		    item10=new JMenuItem("�û��޸���ɾ��");
		    item10.setFont(new Font("����",Font.PLAIN,16));
			item11=new JMenuItem("�޸�����");
		    item11.setFont(new Font("����",Font.PLAIN,16));
			item11.addActionListener(this);
			item10.addActionListener(this);
			item9.addActionListener(this);
			userManage.add(item9);
			userManage.add(item10);
			userManage.add(item11);

			JMenu xitong=new JMenu("ϵͳ����");
			xitong.setFont(new Font("����",Font.PLAIN,16));
			item14=new JMenuItem("�˳�ϵͳ");
			item14.setFont(new Font("����",Font.PLAIN,16));
			item15=new JMenuItem("���µ�¼");
			item15.setFont(new Font("����",Font.PLAIN,16));
			item14.addActionListener(this);
			item15.addActionListener(this);
			xitong.add(item14);
			xitong.add(item15);
			//�������ӵ�������
			menuBar.add(bookManage);
			menuBar.add(readerManager);
			menuBar.add(bookTypeManage);
			menuBar.add(borrowManage);
			menuBar.add(userManage);
			menuBar.add(xitong);
		    setJMenuBar(menuBar);
	}

		@Override
		public void actionPerformed (ActionEvent e){
		if(e.getSource()==item1){
			new ReaderAdd();
		}
		if(e.getSource()==item2){
				new ReaderModiAndDel();
			}
			if(e.getSource()==item3){
				new BookTypeModiAndDel();
			}
			if(e.getSource()==item4){
				new BookAdd();
			}
			if(e.getSource()==item5){
				new BookModiAndDel();
			}
			if(e.getSource()==item6){
				new BookBorrow();
			}
            if(e.getSource()==item7){
				new ReaderSearch();
			}
			if(e.getSource()==item8){
				new BookSearch();
			}
			if(e.getSource()==item9){
				new UserAdd();
			}
			if(e.getSource()==item10){
				new UserModiAndDel();
			}
			if(e.getSource()==item11){
				new GengGaiMiMa();
			}
			if(e.getSource()==item12){
				new BookTypeAdd();
			}
			if(e.getSource()==item13){
				new BookBack();
			}
			if(e.getSource()==item14){
				dispose();
				System.exit(0);
			}
			if(e.getSource()==item15){
				dispose();
				new Login();
			}
		}
	}

