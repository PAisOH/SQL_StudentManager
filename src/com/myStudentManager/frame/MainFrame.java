package com.myStudentManager.frame;
/**
 * 选修课程管理系统 广东石油化工学院 计算机07-1班
 * @author 陈罗志 
 * @version 1.2.3
 * @data 03/24/2010
 * @last update 06/02/2011
 * @Email 646749375@qq.com
 * 本程序可以由任何人自由发表、修改、传播，请保留此注视，谢谢！
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
	private TrayIcon trayicon = new TrayIcon(image, "选课管理系统", createMenu());

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
		this.setSize(640, 432);// 主界面大小
		this.setTitle("学生选课管理系统");
//		imgURL = this.getClass().getResource("/com/chenluozhi/images/icon.png");
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
//		this.setLayout(new FlowLayout());


		this.addWindowListener(new WindowAdapter()// 系统关闭事件
		{
			public void windowClosing(WindowEvent e) {
				SystemTrayInitial();//初始化托盘图标
			}
		});

		ta_sql_search = new JTextArea();
		jMenuItem_relogin = new JMenuItem();
		jMenuItem_relogin.setText("重新登录");
		jMenuItem_operate_log = new JMenuItem();
		jMenuItem_operate_log.setText("操作日志");
		jMenuItem_change_password = new JMenuItem();
		jMenuItem_change_password.setText("修改密码");
		jMenuItem_user_manage = new JMenuItem();
		jMenuItem_user_manage.setText("用户管理");
		jMenuItem_initDB = new JMenuItem();
		jMenuItem_initDB.setText("初始化数据库");
		jMenuItem_hang_system = new JMenuItem();
		jMenuItem_exit = new JMenuItem();
		jMenuItem_exit.setText("退出");
		jMenuItem_student_manage = new JMenuItem();
		jMenuItem_student_manage.setText("学生信息管理");
		jMenuItem_teacher_manage = new JMenuItem();
		jMenuItem_teacher_manage.setText("教师信息管理");
		jMenuItem_course_manage = new JMenuItem();
		jMenuItem_course_manage.setText("课程信息管理");
		jMenuItem_grade_class = new JMenuItem();
		jMenuItem_grade_class.setText("年级信息管理");
		jMenuItem_class = new JMenuItem();
		jMenuItem_class.setText("班级信息管理");
		jMenuItem_ccourse_add = new JMenuItem();
		jMenuItem_ccourse_add.setText("选课录入");
		jMenuItem_ccourse = new JMenuItem();
		jMenuItem_ccourse.setText("选课总览");
		
		jMenuItem_mark_add = new JMenuItem();
		jMenuItem_mark_add.setText("成绩录入");
		jMenuItem_mark_statistics = new JMenuItem();
		jMenuItem_mark_statistics.setText("成绩统计");
		
		
		
		jMenuItem_about = new JMenuItem();
		jMenuItem_about.setText("关于系统");
		jMenuItem_sys_info = new JMenuItem();
		jMenuItem_sys_info.setText("系统说明");
		
		jMenuItem_online_update = new JMenuItem();
		jMenuItem_online_update.setText("在线升级");
		
		
		jMenu_start = new JMenu();
		jMenu_start.setText("开始菜单");
		jMenu_start.add(jMenuItem_relogin);
		jMenu_start.add(jMenuItem_change_password);
		jMenu_start.add(jMenuItem_user_manage);
		jMenu_start.add(jMenuItem_operate_log);
		jMenu_start.add(jMenuItem_initDB);
		jMenu_start.addSeparator();// 分割线
		//jMenu_start.add(jMenuItem_hang_system);
		jMenu_start.add(jMenuItem_exit);
		jMenu_backstage  = new JMenu();
		jMenu_backstage .setText("后台管理");
		jMenu_backstage .add(jMenuItem_student_manage);
		jMenu_backstage .add(jMenuItem_teacher_manage);
		jMenu_backstage .add(jMenuItem_course_manage);
		jMenu_backstage .add(jMenuItem_grade_class);
		jMenu_backstage .add(jMenuItem_class);

		btn_teacher = new JButton("教师信息" );
//		btn_teacher.setSize(200, 30);
		btn_teacher.setText("教师信息");
		btn_student = new JButton("学生信息");
		btn_course = new JButton("课程信息");
		btn_score = new JButton("分数信息");
		btn_course_add = new JButton("课程管理");
		btn_score_add = new JButton("分数录入");
		btn_send = new JButton("SQL搜索");
		btn_student_ccourse = new JButton("学生课程管理");

//		jMenu_ccourse = new JMenu();
//		jMenu_ccourse.setText("选课情况");
//		jMenu_ccourse.add(jMenuItem_ccourse_add);
//		jMenu_ccourse.add(jMenuItem_ccourse);
//
//		jMenu_mark = new JMenu();
//		jMenu_mark.setText("成绩管理");
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
 * @初始化托盘
 */
	private void SystemTrayInitial() { // 托盘
		if (!SystemTray.isSupported()) // 判断当前系统是否支持系统栏
			return;
				try {
					sysTray.add(trayicon);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				trayicon.displayMessage("学生选课管理系统", "系统已托盘\n", MessageType.INFO);// 窗体托盘时所显示的消息对话
			trayicon.addActionListener(new ActionListener()// 击图标时显示窗体
					{
						public void actionPerformed(ActionEvent e) {
							sysTray.remove(trayicon);
							setVisible(true);
						}
					});
	}
/**
 * @初始化托盘右键
 * @return
 */
	private PopupMenu createMenu() { // 创建系统栏菜单的方法
		PopupMenu menu = new PopupMenu();
		MenuItem exitItem = new MenuItem("退出本系统");
		exitItem.addActionListener(new ActionListener() { // 系统栏退出事件
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		MenuItem openItem = new MenuItem("打开主窗口");
		openItem.addActionListener(new ActionListener() {// 系统栏打开菜单项事件
					public void actionPerformed(ActionEvent e) {
						if (!isVisible()) {
							setVisible(true);
							sysTray.remove(trayicon);
						}
					}
				});
		
		MenuItem viewItem = new MenuItem("访问作者主页【猿来入此】");
		viewItem.addActionListener(new ActionListener() {// 系统栏打开菜单项事件
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
	 * 按钮监听
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jMenuItem_relogin) {
				dispose();
				Login login = new Login();// 调用无参数构造
				login.setVisible(true);
			} else if (e.getSource() == jMenuItem_change_password) {
				if(Login.login_user_type!=0)
					{JOptionPane.showMessageDialog(null, "对不起，非教务处暂不提供修改密码功能");
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
//						JOptionPane.showMessageDialog(null, "【猿来入此】~" + "\n"
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
				System.out.println("更新成功");
			} else {
				System.out.println("请仔细检查MySQL语句!");
			}
		}
	}
	
	
	public static void main(String[] args) {
		MainFrame login = new MainFrame();
		login.setVisible(true);
	}

}
