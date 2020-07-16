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

	// ȫ������
	public static void LookUp(JButton bt, JTextArea area) {

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				area.append("��ȡ��..." + "\r\n"
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
						area.append("ѧ�ţ�" + sno + " ������" + sname + " ���䣺"
								+ sage + " רҵ��" + major + "\r\n");
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

	// ѧ�Ų��ҽ���
	public static void LookInterface(JButton bt, JTextArea area, JFrame jre) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JDialog log = new JDialog(jre, "����", true);
				log.setSize(400, 200);
				log.setLocationRelativeTo(null);
				log.setResizable(false);
				Container container = log.getContentPane();
				container.setLayout(null);

				Label label = new Label("ѧ��:");
				label.setFont(new Font("����", Font.BOLD, 25));
				label.setBounds(25, 25, 80, 50);
				container.add(label);

				JTextField field = new JTextField();
				field.setFont(new Font("����", Font.BOLD, 25));
				field.setBounds(110, 25, 200, 40);
				container.add(field);

				JButton button = new JButton("����");
				button.setFont(new Font("����", Font.BOLD, 20));
				button.setBounds(150, 90, 80, 40);
				button.setFocusPainted(false);
				button.setBackground(Color.WHITE);
				container.add(button);

				Serach(button, field, area, log);
				log.setVisible(true);
			}
		});
	}

	// ѧ�Ų���
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
					JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ��!");
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
								area.append(account + "����:" + "\r\n");
								area.append("ѧ�ţ�" + sno + " ������" + sname
										+ " ���䣺" + sage + " רҵ��" + major
										+ "\r\n");
								break;
							}
						}
						if (flag == false) {
							area.append(account + "����:" + "\r\n");
							area.append("�޽��" + "\r\n");
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

	// (���/�޸�)��Ϣ����
	public static void AddOrModifyInterface(JButton bt, JTextArea area,
			JFrame jre, String name) {

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog log = new JDialog(jre, name + "��Ϣ", true);
				log.setSize(400, 330);
				log.setResizable(false);
				log.setLocationRelativeTo(null);

				Container container = log.getContentPane();
				container.setLayout(null);

				Label a = new Label("ѧ��:");
				a.setFont(new Font("����", Font.BOLD, 25));
				a.setBounds(25, 25, 80, 50);
				container.add(a);

				JTextField f1 = new JTextField();
				f1.setFont(new Font("����", Font.BOLD, 25));
				f1.setBounds(110, 25, 200, 40);
				container.add(f1);

				Label b = new Label("����:");
				b.setFont(new Font("����", Font.BOLD, 25));
				b.setBounds(25, 75, 80, 50);
				container.add(b);

				JTextField f2 = new JTextField();
				f2.setFont(new Font("����", Font.BOLD, 25));
				f2.setBounds(110, 75, 200, 40);
				container.add(f2);

				Label c = new Label("����:");
				c.setFont(new Font("����", Font.BOLD, 25));
				c.setBounds(25, 125, 80, 50);
				container.add(c);

				JTextField f3 = new JTextField();
				f3.setFont(new Font("����", Font.BOLD, 25));
				f3.setBounds(110, 125, 200, 40);
				container.add(f3);

				Label d = new Label("רҵ:");
				d.setFont(new Font("����", Font.BOLD, 25));
				d.setBounds(25, 175, 80, 50);
				container.add(d);

				JTextField f4 = new JTextField();
				f4.setFont(new Font("����", Font.BOLD, 25));
				f4.setBounds(110, 175, 200, 40);
				container.add(f4);

				JButton button = new JButton(name);
				button.setFont(new Font("����", Font.BOLD, 20));
				button.setBounds(150, 235, 80, 40);
				button.setFocusPainted(false);
				button.setBackground(Color.WHITE);
				container.add(button);

				if (name.equals("���")) {
					Add(button, f1, f2, f3, f4, area, log);
				}
				if (name.equals("�޸�")) {
					Modify(button, f1, f2, f3, f4, area, log);
				}

				log.setVisible(true);
			}
		});
	}

	// ������Ϣ
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
					JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ��!");
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
							area.append(sno + "ѧ����Ϣ��ӳɹ�!" + "\r\n");
						} else {
							area.append(sno + "ѧ����Ϣ�Ѵ��ڣ����ʧ�ܣ�" + "\r\n");
						}
						log.dispose();
					}

				}
			}
		});

	}

	// �޸���Ϣ
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
					JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ��!");
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
								area.append(sno + "ѧ����Ϣ�޸ĳɹ�!" + "\r\n");
							} else {
								area.append(sno + "ѧ����Ϣ�����ڣ��޸�ʧ��!" + "\r\n");
							}
							log.dispose();
						}

					}
				}
			}
		});
	}

	// ɾ����Ϣ����
	public static void DeleteInterface(JButton bt, JTextArea area, JFrame jre) {
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog log = new JDialog(jre, "ɾ��", true);
				log.setSize(400, 200);
				log.setLocationRelativeTo(null);
				log.setResizable(false);
				Container container = log.getContentPane();
				container.setLayout(null);

				Label label = new Label("ѧ��:");
				label.setFont(new Font("����", Font.BOLD, 25));
				label.setBounds(25, 25, 80, 50);
				container.add(label);

				JTextField field = new JTextField();
				field.setFont(new Font("����", Font.BOLD, 25));
				field.setBounds(110, 25, 200, 40);
				container.add(field);

				JButton button = new JButton("ɾ��");
				button.setFont(new Font("����", Font.BOLD, 20));
				button.setBounds(150, 90, 80, 40);
				button.setFocusPainted(false);
				button.setBackground(Color.WHITE);
				container.add(button);

				Delete(button, field, area, log);
				log.setVisible(true);
			}
		});
	}

	// ɾ����Ϣ
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
					JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ��!");
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
								area.append(account + "ѧ����Ϣɾ���ɹ���" + "\r\n");
							} else {
								area.append(account + "ѧ����Ϣ�����ڣ�ɾ��ʧ�ܣ�" + "\r\n");
							}
							log.dispose();
						}
					}
				}

			}
		});
	}

	// �˳���¼
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
