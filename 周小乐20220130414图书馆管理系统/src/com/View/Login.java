package com.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.dao.Dao;
import com.model.Operater;
public class Login extends JFrame implements ActionListener{
        JPasswordField password;
        JTextField username;
        JButton login,reset;
        static Operater user;
        ImageIcon image=new ImageIcon("image/background.jpg");
        JLabel jLabel=new JLabel(image);
        public Login() {
            jLabel.setBounds(0,0,800,500);

            Font f=new Font("����",Font.BOLD,20);
            int x=260,y=190;
            BorderLayout borderLayout = new BorderLayout();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(borderLayout);
            setTitle("ͼ��ݹ���ϵͳ��¼");
            setBounds(500, 230, 800, 500);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            getContentPane().add(panel);

            JLabel label1 = new JLabel("ͼ��ݹ���ϵͳ");
            label1.setBounds(x+60,y-200,500,200);
            label1.setFont(new Font("����",Font.BOLD,30));
            panel.add(label1);

            JLabel label = new JLabel("�˻�:");
            label.setBounds(x,y,80,40);
            label.setFont(f);
            panel.add(label);
            username = new JTextField();
            username.setBounds(x+80,y+8,150,30);
            panel.add(username);

            JLabel label_1 = new JLabel("���룺");
            label_1.setBounds(x,y+60,110,40);
            label_1.setFont(f);
            panel.add(label_1);
            password = new JPasswordField();
            password.setBounds(x+80,y+66,150,30);
            panel.add(password);


            login = new JButton("��¼");
            login.setFont(f);
            login.setBounds(x+30,y+150,80,40);
            login.addActionListener(this);
            panel.add(login);
            reset = new JButton("����");
            reset.setFont(f);
            reset.setBounds(x+150,y+150,80,40);
            reset.addActionListener(this);
            panel.add(reset);
            panel.add(jLabel);
            setVisible(true);
            setResizable(false);

        }
        public void actionPerformed( ActionEvent e) {
            if (e.getSource() == login) {
                user = Dao.check(username.getText(), password.getText());
                if (user.getName() != null) {

                    try {
                        Library frame = new Library();
                        frame.setVisible(true);
                        Login.this.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ֻ�й���Ա�ſ��Ե�¼��");
                    username.setText("");
                    password.setText("");
                }
                username.setText("");
                password.setText("");
            }
            else if(e.getSource()==reset)
            {
                System.exit(0);
            }
        }
        public static Operater getUser() {
            return user;
        }
}