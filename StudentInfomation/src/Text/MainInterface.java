package Text;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainInterface {

	public static JButton login;
	public static JButton cancle;
	public static JFrame jf;

	public static void initLogin() {
		jf = new JFrame("ѧ������ϵͳ");
		jf.setSize(500, 300);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container pane = jf.getContentPane();
		pane.setLayout(null);

		Label label1 = new Label("�˺�:");
		label1.setFont(new Font("����", Font.BOLD, 25));
		label1.setBounds(60, 25, 100, 100);
		pane.add(label1);

		Label label2 = new Label("����:");
		label2.setFont(new Font("����", Font.BOLD, 25));
		label2.setBounds(60, 90, 100, 100);
		pane.add(label2);

		JTextField account = new JTextField();
		account.setBounds(160, 60, 250, 30);
		account.setFont(new Font("����", Font.BOLD, 25));
		pane.add(account);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(160, 120, 250, 30);
		passwordField.setFont(new Font("΢���ź�", Font.BOLD, 25));
		pane.add(passwordField);

		login = new JButton("��¼");
		login.setFont(new Font("΢���ź�", Font.BOLD, 20));
		login.setBounds(160, 180, 80, 30);
		login.setBackground(Color.WHITE);
		login.setFocusPainted(false);
		pane.add(login);

		cancle = new JButton("ע��");
		cancle.setFont(new Font("΢���ź�", Font.BOLD, 20));
		cancle.setBounds(330, 180, 80, 30);
		cancle.setBackground(Color.WHITE);
		cancle.setFocusPainted(false);
		pane.add(cancle);

		login(account, passwordField);
		register(account, passwordField);
		jf.setVisible(true);
	}

	// ����¼������
	public static void login(JTextField act, JPasswordField psd) {

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String account = act.getText();
				String password = new String(psd.getPassword());
				Database.login(account, password, jf);
			}

		});
	}

	// ��ע�ᡱ����
	public static void register(JTextField act, JPasswordField psd) {

		cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String account = act.getText();
				String password = new String(psd.getPassword());
				Database.register(account, password, jf);
			}

		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainInterface::initLogin);
	}
}
