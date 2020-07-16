package Text;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Action {

	private static String sno = "";
	private static String sname = "";
	private static String sage = "";
	private static String major = "";

	// 全部查找
	public static void LookUp(JButton bt, JTextArea area) {

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				area.append("读取中..." + "\r\n"
						+ "==========================================="
						+ "\r\n");
				Connection connect = Database.connect();
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				try {
					statement = connect
							.prepareStatement("select * from stuinfo");
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						sno = resultSet.getString("sno");
						sname = resultSet.getString("sname");
						sage = resultSet.getString("sage");
						major = resultSet.getString("major");
						area.append("学号：" + sno + " 姓名：" + sname + " 年龄："
								+ sage + " 专业：" + major + "\r\n");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						resultSet.close();
						statement.close();
						connect.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
	}

	// 学号查找界面
	public static void LookInterface(JButton bt, JTextArea area, JFrame jre) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JDialog log = new JDialog(jre, "查找", true);
				log.setSize(400, 200);
				log.setLocationRelativeTo(null);
				log.setResizable(false);
				Container container = log.getContentPane();
				container.setLayout(null);

				Label label = new Label("学号:");
				label.setFont(new Font("黑体", Font.BOLD, 25));
				label.setBounds(25, 25, 80, 50);
				container.add(label);

				JTextField field = new JTextField();
				field.setFont(new Font("黑体", Font.BOLD, 25));
				field.setBounds(110, 25, 200, 40);
				container.add(field);

				JButton button = new JButton("搜索");
				button.setFont(new Font("黑体", Font.BOLD, 20));
				button.setBounds(150, 90, 80, 40);
				button.setFocusPainted(false);
				button.setBackground(Color.WHITE);
				container.add(button);

				Serach(button, field, area, log);
				log.setVisible(true);
			}
		});
	}

	// 学号查找
	public static void Serach(JButton bt, JTextField field, JTextArea area,
			JDialog log) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				Connection connection = Database.connect();
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				String account = field.getText();
				if (account.equals("")) {
					JOptionPane.showMessageDialog(null, "所填信息不能为空!");
				} else {
					try {
						Boolean flag = false;
						statement = connection
								.prepareStatement("select * from stuinfo");
						resultSet = statement.executeQuery();
						while (resultSet.next()) {
							sno = resultSet.getString("sno");
							if (account.equals(sno)) {
								flag = true;
								sname = resultSet.getString("sname");
								sage = resultSet.getString("sage");
								major = resultSet.getString("major");
								area.append(account + "搜索:" + "\r\n");
								area.append("学号：" + sno + " 姓名：" + sname
										+ " 年龄：" + sage + " 专业：" + major
										+ "\r\n");
								break;
							}
						}
						if (flag == false) {
							area.append(account + "搜索:" + "\r\n");
							area.append("无结果" + "\r\n");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							resultSet.close();
							statement.close();
							connection.close();
							log.dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
	}

	// (添加/修改)信息界面
	public static void AddOrModifyInterface(JButton bt, JTextArea area,
			JFrame jre, String name) {

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog log = new JDialog(jre, name + "信息", true);
				log.setSize(400, 330);
				log.setResizable(false);
				log.setLocationRelativeTo(null);

				Container container = log.getContentPane();
				container.setLayout(null);

				Label a = new Label("学号:");
				a.setFont(new Font("黑体", Font.BOLD, 25));
				a.setBounds(25, 25, 80, 50);
				container.add(a);

				JTextField f1 = new JTextField();
				f1.setFont(new Font("黑体", Font.BOLD, 25));
				f1.setBounds(110, 25, 200, 40);
				container.add(f1);

				Label b = new Label("姓名:");
				b.setFont(new Font("黑体", Font.BOLD, 25));
				b.setBounds(25, 75, 80, 50);
				container.add(b);

				JTextField f2 = new JTextField();
				f2.setFont(new Font("黑体", Font.BOLD, 25));
				f2.setBounds(110, 75, 200, 40);
				container.add(f2);

				Label c = new Label("年龄:");
				c.setFont(new Font("黑体", Font.BOLD, 25));
				c.setBounds(25, 125, 80, 50);
				container.add(c);

				JTextField f3 = new JTextField();
				f3.setFont(new Font("黑体", Font.BOLD, 25));
				f3.setBounds(110, 125, 200, 40);
				container.add(f3);

				Label d = new Label("专业:");
				d.setFont(new Font("黑体", Font.BOLD, 25));
				d.setBounds(25, 175, 80, 50);
				container.add(d);

				JTextField f4 = new JTextField();
				f4.setFont(new Font("黑体", Font.BOLD, 25));
				f4.setBounds(110, 175, 200, 40);
				container.add(f4);

				JButton button = new JButton(name);
				button.setFont(new Font("黑体", Font.BOLD, 20));
				button.setBounds(150, 235, 80, 40);
				button.setFocusPainted(false);
				button.setBackground(Color.WHITE);
				container.add(button);

				if (name.equals("添加")) {
					Add(button, f1, f2, f3, f4, area, log);
				}
				if (name.equals("修改")) {
					Modify(button, f1, f2, f3, f4, area, log);
				}

				log.setVisible(true);
			}
		});
	}

	// 增加信息
	public static void Add(JButton bt, JTextField f1, JTextField f2,
			JTextField f3, JTextField f4, JTextArea area, JDialog log) {

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				area.setText("");
				sno = f1.getText();
				sname = f2.getText();
				sage = f3.getText();
				major = f4.getText();

				if (sno.equals("") || sname.equals("") || sage.equals("")
						|| major.equals("")) {
					JOptionPane.showMessageDialog(null, "所填信息不能为空!");
				} else {
					Connection connection = Database.connect();
					PreparedStatement statement = null;
					int update = 0;
					try {
						statement = connection
								.prepareStatement("insert into stuinfo values(?,?,?,?)");
						statement.setString(1, sno);
						statement.setString(2, sname);
						statement.setString(3, sage);
						statement.setString(4, major);
						update = statement.executeUpdate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							statement.close();
							connection.close();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (update == 1) {
							area.append(sno + "学生信息添加成功!" + "\r\n");
						} else {
							area.append(sno + "学生信息已存在，添加失败！" + "\r\n");
						}
						log.dispose();
					}

				}
			}
		});

	}

	// 修改信息
	public static void Modify(JButton bt, JTextField f1, JTextField f2,
			JTextField f3, JTextField f4, JTextArea area, JDialog log) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				sno = f1.getText();
				sname = f2.getText();
				sage = f3.getText();
				major = f4.getText();

				if (sno.equals("") || sname.equals("") || sage.equals("")
						|| major.equals("")) {
					JOptionPane.showMessageDialog(null, "所填信息不能为空!");
				} else {
					Connection connection = Database.connect();
					PreparedStatement statement = null;
					int update = 0;
					try {
						statement = connection
								.prepareStatement("update stuinfo set sname='"
										+ sname + "',sage=" + sage + ",major='"
										+ major + "' where sno='"+ sno+"'");
						update = statement.executeUpdate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							statement.close();
							connection.close();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							System.out.println(update);
							if (update == 1) {
								log.dispose();
								area.append(sno + "学生信息修改成功!" + "\r\n");
							} else {
								area.append(sno + "学生信息不存在，修改失败!" + "\r\n");
							}
							log.dispose();
						}

					}
				}
			}
		});
	}

	// 删除信息界面
	public static void DeleteInterface(JButton bt, JTextArea area, JFrame jre) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog log = new JDialog(jre, "删除", true);
				log.setSize(400, 200);
				log.setLocationRelativeTo(null);
				log.setResizable(false);
				Container container = log.getContentPane();
				container.setLayout(null);

				Label label = new Label("学号:");
				label.setFont(new Font("黑体", Font.BOLD, 25));
				label.setBounds(25, 25, 80, 50);
				container.add(label);

				JTextField field = new JTextField();
				field.setFont(new Font("黑体", Font.BOLD, 25));
				field.setBounds(110, 25, 200, 40);
				container.add(field);

				JButton button = new JButton("删除");
				button.setFont(new Font("黑体", Font.BOLD, 20));
				button.setBounds(150, 90, 80, 40);
				button.setFocusPainted(false);
				button.setBackground(Color.WHITE);
				container.add(button);

				Delete(button, field, area, log);
				log.setVisible(true);
			}
		});
	}

	// 删除信息
	public static void Delete(JButton bt, JTextField field, JTextArea area,
			JDialog log) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				Connection connection = Database.connect();
				PreparedStatement statement = null;
				String account = field.getText();
				int update = 0;
				if (account.equals("")) {
					JOptionPane.showMessageDialog(null, "所填信息不能为空!");
				} else {
					try {
						statement = connection
								.prepareStatement("delete from stuinfo where sno="
										+ account);
						update = statement.executeUpdate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							statement.close();
							connection.close();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							if (update == 1) {
								area.append(account + "学生信息删除成功！" + "\r\n");
							} else {
								area.append(account + "学生信息不存在，删除失败！" + "\r\n");
							}
							log.dispose();
						}
					}
				}

			}
		});
	}

	// 退出登录
	public static void ReLogin(JButton bt, JFrame jre) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jre.dispose();
				MainInterface.initLogin();
			}
		});
	}
}
