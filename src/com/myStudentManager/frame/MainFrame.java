package com.myStudentManager.frame;
/**
 * ѡ�޿γ̹���ϵͳ �㶫ʯ�ͻ���ѧԺ �����07-1��
 * @author ����־ 
 * @version 1.2.3
 * @data 03/24/2010
 * @last update 06/02/2011
 * @Email 646749375@qq.com
 * ������������κ������ɷ����޸ġ��������뱣����ע�ӣ�лл��
 */
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;

import com.myStudentManager.util.DBConnection;
import com.myStudentManager.util.DBInit;

import static com.myStudentManager.frame.Login.storeUserName;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenu jMenu_start = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_backstage  = null;

	private JButton btn_teacher = null;
	private JButton btn_student = null;
	private JButton btn_course = null;
	private JButton btn_course_add = null;
	private JButton btn_score = null;
	private JButton btn_score_add = null;
	private JTextArea ta_sql_search = null;
	private JButton btn_send = null;
	private JButton btn_student_ccourse = null;

//	private JMenu jMenu_ccourse = null;
//	private JMenu jMenu_mark = null;
//	private JMenu jMenu_help = null;
	private JMenuItem jMenuItem_relogin = null;
	public JMenuItem jMenuItem_initDB = null;
	private JMenuItem jMenuItem_change_password = null;
	public JMenuItem jMenuItem_user_manage = null;
	private JMenuItem jMenuItem_hang_system = null;
	private JMenuItem jMenuItem_exit = null;
	private JMenuItem jMenuItem_student_manage = null;
	private JMenuItem jMenuItem_teacher_manage = null;
	private JMenuItem jMenuItem_course_manage = null;
	private JMenuItem jMenuItem_grade_class = null;
	private JMenuItem jMenuItem_class = null;
	private JMenuItem jMenuItem_about = null;
	private JMenuItem jMenuItem_online_update = null;
	
	
	private JMenuItem jMenuItem_sys_info = null;
	public JMenuItem jMenuItem_operate_log = null;
	private JMenuItem jMenuItem_ccourse = null;
	private JMenuItem jMenuItem_ccourse_add = null;
	private JMenuItem jMenuItem_mark_add = null;
	private JMenuItem jMenuItem_mark_statistics = null;
	private JLabel jLabel = null;
	private URL imgURL = null;
	private SystemTray sysTray = SystemTray.getSystemTray();
	
	Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/myStudentManager/images/icon.png"));
	private TrayIcon trayicon = new TrayIcon(image, "ѡ�ι���ϵͳ", createMenu());

	public MainFrame() {
		super();
		initialize();
		btn_teacher.setBounds(50, 50, 120, 40);
		btn_student.setBounds(180, 50, 120, 40);
		btn_course.setBounds(310, 50, 120, 40);
		btn_score.setBounds(440, 50, 120, 40);
		btn_course_add.setBounds(310, 150, 120, 40);
		btn_score_add.setBounds(440, 150, 120, 40);
		ta_sql_search.setBounds(100, 200, 300, 100);
		btn_send.setBounds(420, 200, 120, 40);
		btn_student_ccourse.setBounds(50, 150, 120, 40);
		initPrivilege();
		this.add(btn_teacher);
		this.add(btn_student);
		this.add(btn_course);
		this.add(btn_score);
		this.add(btn_course_add);
		this.add(btn_score_add);
		this.add(ta_sql_search);
		this.add(btn_send);
		this.add(btn_student_ccourse);
	}

	private void initialize() {		
		this.setSize(640, 432);// �������С
		this.setTitle("ѧ��ѡ�ι���ϵͳ");
//		imgURL = this.getClass().getResource("/com/chenluozhi/images/icon.png");
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
//		this.setLayout(new FlowLayout());


		this.addWindowListener(new WindowAdapter()// ϵͳ�ر��¼�
		{
			public void windowClosing(WindowEvent e) {
				SystemTrayInitial();//��ʼ������ͼ��
			}
		});

		ta_sql_search = new JTextArea();
		jMenuItem_relogin = new JMenuItem();
		jMenuItem_relogin.setText("���µ�¼");
		jMenuItem_operate_log = new JMenuItem();
		jMenuItem_operate_log.setText("������־");
		jMenuItem_change_password = new JMenuItem();
		jMenuItem_change_password.setText("�޸�����");
		jMenuItem_user_manage = new JMenuItem();
		jMenuItem_user_manage.setText("�û�����");
		jMenuItem_initDB = new JMenuItem();
		jMenuItem_initDB.setText("��ʼ�����ݿ�");
		jMenuItem_hang_system = new JMenuItem();
		jMenuItem_exit = new JMenuItem();
		jMenuItem_exit.setText("�˳�");
		jMenuItem_student_manage = new JMenuItem();
		jMenuItem_student_manage.setText("ѧ����Ϣ����");
		jMenuItem_teacher_manage = new JMenuItem();
		jMenuItem_teacher_manage.setText("��ʦ��Ϣ����");
		jMenuItem_course_manage = new JMenuItem();
		jMenuItem_course_manage.setText("�γ���Ϣ����");
		jMenuItem_grade_class = new JMenuItem();
		jMenuItem_grade_class.setText("�꼶��Ϣ����");
		jMenuItem_class = new JMenuItem();
		jMenuItem_class.setText("�༶��Ϣ����");
		jMenuItem_ccourse_add = new JMenuItem();
		jMenuItem_ccourse_add.setText("ѡ��¼��");
		jMenuItem_ccourse = new JMenuItem();
		jMenuItem_ccourse.setText("ѡ������");
		
		jMenuItem_mark_add = new JMenuItem();
		jMenuItem_mark_add.setText("�ɼ�¼��");
		jMenuItem_mark_statistics = new JMenuItem();
		jMenuItem_mark_statistics.setText("�ɼ�ͳ��");
		
		
		
		jMenuItem_about = new JMenuItem();
		jMenuItem_about.setText("����ϵͳ");
		jMenuItem_sys_info = new JMenuItem();
		jMenuItem_sys_info.setText("ϵͳ˵��");
		
		jMenuItem_online_update = new JMenuItem();
		jMenuItem_online_update.setText("��������");
		
		
		jMenu_start = new JMenu();
		jMenu_start.setText("��ʼ�˵�");
		jMenu_start.add(jMenuItem_relogin);
		jMenu_start.add(jMenuItem_change_password);
		jMenu_start.add(jMenuItem_user_manage);
		jMenu_start.add(jMenuItem_operate_log);
		jMenu_start.add(jMenuItem_initDB);
		jMenu_start.addSeparator();// �ָ���
		//jMenu_start.add(jMenuItem_hang_system);
		jMenu_start.add(jMenuItem_exit);
		jMenu_backstage  = new JMenu();
		jMenu_backstage .setText("��̨����");
		jMenu_backstage .add(jMenuItem_student_manage);
		jMenu_backstage .add(jMenuItem_teacher_manage);
		jMenu_backstage .add(jMenuItem_course_manage);
		jMenu_backstage .add(jMenuItem_grade_class);
		jMenu_backstage .add(jMenuItem_class);

		btn_teacher = new JButton("��ʦ��Ϣ" );
//		btn_teacher.setSize(200, 30);
		btn_teacher.setText("��ʦ��Ϣ");
		btn_student = new JButton("ѧ����Ϣ");
		btn_course = new JButton("�γ���Ϣ");
		btn_score = new JButton("������Ϣ");
		btn_course_add = new JButton("�γ̹���");
		btn_score_add = new JButton("����¼��");
		btn_send = new JButton("SQL����");
		btn_student_ccourse = new JButton("ѧ���γ̹���");

//		jMenu_ccourse = new JMenu();
//		jMenu_ccourse.setText("ѡ�����");
//		jMenu_ccourse.add(jMenuItem_ccourse_add);
//		jMenu_ccourse.add(jMenuItem_ccourse);
//
//		jMenu_mark = new JMenu();
//		jMenu_mark.setText("�ɼ�����");
//		jMenu_mark.add(jMenuItem_mark_add);
//		jMenu_mark.add(jMenuItem_mark_statistics);
		

		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(10, 25));
		jJMenuBar.add(jMenu_start);
		jJMenuBar.add(jMenu_backstage );
//		jJMenuBar.add(jMenu_ccourse);
//		jJMenuBar.add(jMenu_mark);
		
		setJMenuBar(jJMenuBar);

		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(1, -2, 800, 544));
//		imgURL = this.getClass().getResource("/com/myStudentManager/images/main.jpg");
//		jLabel.setIcon(new ImageIcon(imgURL));

		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel, null);
		setContentPane(jContentPane);
		
		btnListener btn = new btnListener();
		btn_teacher.addActionListener(btn);
		btn_student.addActionListener(btn);
		btn_course.addActionListener(btn);
		btn_score.addActionListener(btn);
		btn_score_add.addActionListener(btn);
		btn_course_add.addActionListener(btn);
		btn_send.addActionListener(btn);
		btn_student_ccourse.addActionListener(btn);

		jMenuItem_relogin.addActionListener(btn);
		jMenuItem_change_password.addActionListener(btn);
		jMenuItem_user_manage.addActionListener(btn);
		jMenuItem_initDB.addActionListener(btn);
		jMenuItem_operate_log.addActionListener(btn);
//		jMenuItem_hang_system.addActionListener(btn);
		jMenuItem_exit.addActionListener(btn);
		jMenuItem_student_manage.addActionListener(btn);
		jMenuItem_teacher_manage.addActionListener(btn);
		jMenuItem_course_manage.addActionListener(btn);
		jMenuItem_grade_class.addActionListener(btn);
		jMenuItem_class.addActionListener(btn);
		jMenuItem_ccourse_add.addActionListener(btn);
		jMenuItem_about.addActionListener(btn);
		jMenuItem_mark_add.addActionListener(btn);
		jMenuItem_mark_statistics.addActionListener(btn);
		jMenuItem_ccourse.addActionListener(btn);
		jMenuItem_sys_info.addActionListener(btn);
		
	}
/**
 * @��ʼ������
 */
	private void SystemTrayInitial() { // ����
		if (!SystemTray.isSupported()) // �жϵ�ǰϵͳ�Ƿ�֧��ϵͳ��
			return;
				try {
					sysTray.add(trayicon);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				trayicon.displayMessage("ѧ��ѡ�ι���ϵͳ", "ϵͳ������\n", MessageType.INFO);// ��������ʱ����ʾ����Ϣ�Ի�
			trayicon.addActionListener(new ActionListener()// ��ͼ��ʱ��ʾ����
					{
						public void actionPerformed(ActionEvent e) {
							sysTray.remove(trayicon);
							setVisible(true);
						}
					});
	}
/**
 * @��ʼ�������Ҽ�
 * @return
 */
	private PopupMenu createMenu() { // ����ϵͳ���˵��ķ���
		PopupMenu menu = new PopupMenu();
		MenuItem exitItem = new MenuItem("�˳���ϵͳ");
		exitItem.addActionListener(new ActionListener() { // ϵͳ���˳��¼�
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		MenuItem openItem = new MenuItem("��������");
		openItem.addActionListener(new ActionListener() {// ϵͳ���򿪲˵����¼�
					public void actionPerformed(ActionEvent e) {
						if (!isVisible()) {
							setVisible(true);
							sysTray.remove(trayicon);
						}
					}
				});
		
		MenuItem viewItem = new MenuItem("����������ҳ��Գ����ˡ�");
		viewItem.addActionListener(new ActionListener() {// ϵͳ���򿪲˵����¼�
					public void actionPerformed(ActionEvent e) {
						try {
							Runtime.getRuntime().exec("explorer http://programmer.ischoolbar.com");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
		
		
		menu.add(openItem);
		menu.add(viewItem);
		menu.addSeparator();
		menu.add(exitItem);
		return menu;
	}

	public void initPrivilege() {
		if(Login.login_user_type==0){
			btn_student_ccourse.setVisible(false);
		}
		if (Login.login_user_type==1) {
			jMenuItem_change_password.setEnabled(false);
			jMenuItem_operate_log.setEnabled(false);
			jMenuItem_initDB.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenu_backstage .setEnabled(false);
			btn_student_ccourse.setVisible(false);
		}
		if(Login.login_user_type==2){

			jMenuItem_change_password.setEnabled(false);
			jMenuItem_operate_log.setEnabled(false);
			jMenuItem_initDB.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenuItem_sys_info.setEnabled(false);

			jMenu_backstage.setEnabled(false);
			btn_teacher.setVisible(false);
			btn_student.setVisible(false);
			btn_send.setVisible(false);
			btn_course.setVisible(false);
			btn_course_add.setVisible(false);
			btn_score.setVisible(false);
			btn_score_add.setVisible(false);

		}
	}

	/**
	 * ��ť����
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jMenuItem_relogin) {
				dispose();
				Login login = new Login();// �����޲�������
				login.setVisible(true);
			} else if (e.getSource() == jMenuItem_change_password) {
				if(Login.login_user_type!=0)
					{JOptionPane.showMessageDialog(null, "�Բ��𣬷ǽ����ݲ��ṩ�޸����빦��");
					return;
					}
				UserChangePassword cp = new UserChangePassword();
				cp.setVisible(true);
			} else if (e.getSource() == jMenuItem_user_manage) {
			
				UserManage um = new UserManage();
				um.setVisible(true);
			} else if (e.getSource() == jMenuItem_operate_log) {
				LogManage lm = new LogManage();
				lm.setVisible(true);
			}
			else if (e.getSource() == jMenuItem_initDB) {
				DBInit di = new DBInit();
				di.setVisible(true);
			} else if (e.getSource() == jMenuItem_exit) {

				System.exit(0);
			} else if (e.getSource() == jMenuItem_student_manage) {
				StudentManage sm = new StudentManage();
				sm.setVisible(true);
			} else if (e.getSource() == jMenuItem_teacher_manage) {
		
				TeacherManage tm = new TeacherManage();
				tm.setVisible(true);
			}
			else if (e.getSource() == btn_teacher) {
				TeacherManage tm = new TeacherManage();
				tm.setVisible(true);
			} else if (e.getSource() == btn_student) {
				StudentManage sm = new StudentManage();
				sm.setVisible(true);
			}  else if (e.getSource() == btn_course) {
				CourseManage cm = new CourseManage();
				cm.setVisible(true);
			} else if (e.getSource() == btn_score) {
				CCourseMarkStatistic ccs = new CCourseMarkStatistic();
				ccs.setVisible(true);
			} else if (e.getSource() == btn_score_add) {
				CCourseMarkAdd ccma = new CCourseMarkAdd();
				ccma.setVisible(true);
			} else if (e.getSource() == btn_course_add) {
				CCourseAdd cm = new CCourseAdd();
				cm.setVisible(true);
			}
			else if(e.getSource() == btn_send) {
				sendToSQL(ta_sql_search.getText().toString());

				ta_sql_search.setText("");
			}

			else if (e.getSource() == jMenuItem_grade_class) {
				GradeManage gm = new GradeManage();
				gm.setVisible(true);
			} else if(e.getSource() == jMenuItem_class){
				ClassManage cm= new ClassManage();
				cm.setVisible(true);
			}else if (e.getSource() == jMenuItem_course_manage) {
			
				CourseManage cm = new CourseManage();
				cm.setVisible(true);
			} else if (e.getSource() == jMenuItem_ccourse_add) {
			
				CCourseAdd cm = new CCourseAdd();
				cm.setVisible(true);
			} else if (e.getSource() == jMenuItem_ccourse) {
				CCourseManage cci = new CCourseManage();
				cci.setVisible(true);
			}else if (e.getSource() == jMenuItem_mark_add) {
				
				CCourseMarkAdd ccma = new CCourseMarkAdd();
				ccma.setVisible(true);
			}else if (e.getSource() == jMenuItem_mark_statistics) {
				CCourseMarkStatistic ccs = new CCourseMarkStatistic();
				ccs.setVisible(true);
			} else if (e.getSource() == jMenuItem_about) {
				About ab = new About();
				ab.setVisible(true);
			} else if (e.getSource() == jMenuItem_sys_info) {
				try {
					String fp = System.getProperty("user.dir") + "\\readme.txt";
					File f = new File(fp);
					if (f.exists()) {
						Runtime.getRuntime().exec(" notepad.exe " + fp);
					}
//					else {
//						JOptionPane.showMessageDialog(null, "��Գ����ˡ�~" + "\n"
//								+ "http://programmer.ischoolbar.com");
//					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			if (e.getSource() == btn_student_ccourse) {
				StudentCourseManage scm = new StudentCourseManage();
				scm.setVisible(true);
			}
		}

		private void sendToSQL(String sql) {
			if (DBConnection.update(sql)){
				System.out.println("���³ɹ�");
			} else {
				System.out.println("����ϸ���MySQL���!");
			}
		}
	}
	
	
	public static void main(String[] args) {
		MainFrame login = new MainFrame();
		login.setVisible(true);
	}

}
