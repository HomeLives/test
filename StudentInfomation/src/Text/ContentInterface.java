package Text;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContentInterface {

	public static void Login(JFrame jf) {

		jf.dispose();
		JFrame jre = new JFrame("学生信息管理系统");
		jre.setSize(800, 400);
		jre.setLocationRelativeTo(null);
		jre.setResizable(false);
		jre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = jre.getContentPane();
		container.setBackground(Color.BLACK);
		container.setLayout(null);

		JButton bt1 = new JButton("全部查询");
		bt1.setBounds(50, 55, 100, 40);
		bt1.setBackground(Color.GRAY);
		bt1.setFocusPainted(false);
		bt1.setFont(new Font("楷体", Font.BOLD, 15));
		container.add(bt1);

		JButton bt2 = new JButton("单个查询");
		bt2.setBounds(50, 155, 100, 40);
		bt2.setBackground(Color.GRAY);
		bt2.setFocusPainted(false);
		bt2.setFont(new Font("楷体", Font.BOLD, 15));
		container.add(bt2);

		JButton bt3 = new JButton("增加信息");
		bt3.setBounds(50, 255, 100, 40);
		bt3.setBackground(Color.GRAY);
		bt3.setFocusPainted(false);
		bt3.setFont(new Font("楷体", Font.BOLD, 15));
		container.add(bt3);

		JTextArea area = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(area);
		area.setEnabled(false);
		area.setFont(new Font("黑体", Font.BOLD, 15));
		scrollPane.setBounds(180, 50, 440, 250);
		container.add(scrollPane);

		JButton bt4 = new JButton("修改信息");
		bt4.setBounds(650, 55, 100, 40);
		bt4.setBackground(Color.GRAY);
		bt4.setFocusPainted(false);
		bt4.setFont(new Font("楷体", Font.BOLD, 15));
		container.add(bt4);

		JButton bt5 = new JButton("删除信息");
		bt5.setBounds(650, 155, 100, 40);
		bt5.setBackground(Color.GRAY);
		bt5.setFocusPainted(false);
		bt5.setFont(new Font("楷体", Font.BOLD, 15));
		container.add(bt5);

		JButton bt6 = new JButton("退出登录");
		bt6.setBounds(650, 255, 100, 40);
		bt6.setBackground(Color.GRAY);
		bt6.setFocusPainted(false);
		bt6.setFont(new Font("楷体", Font.BOLD, 15));
		container.add(bt6);

		Action.LookUp(bt1, area);
		Action.LookInterface(bt2, area, jre);
		Action.AddOrModifyInterface(bt3, area, jre,"添加");
		Action.AddOrModifyInterface(bt4, area, jre,"修改");
		Action.DeleteInterface(bt5, area, jre);
		Action.ReLogin(bt6, jre);
		jre.setVisible(true);

	}

	public static void LoginError(JFrame jf) {
		JOptionPane.showMessageDialog(null, "账号或密码错误!");
	}

	public static void Register(JFrame jf) {
		JOptionPane.showMessageDialog(null, "账号注册成功!");
	}

	public static void RegisterError(JFrame jf) {
		JOptionPane.showMessageDialog(null, "账号已存在!");
	}

}
