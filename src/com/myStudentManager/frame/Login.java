/**
 * 选修课程管理系统 广东石油化工学院 计算机07-1班
 * @author 陈罗志
 * @version 1.2.3
 * @data 03/24/2010
 * @last update 06/02/2011
 * @Email 646749375@qq.com
 *
┏┓         　 ┏┓
┏┛┻━━━┛┻┓
┃　　　　　　　┃
┃　　　━　　　┃
┃　┳┛　┗┳　┃
┃　　　　　　　┃
┃　　　┻　　　┃
┃　　　　　　　┃
┗━┓　　　┏━┛
　　┃　　　┃
　　┃　　　┃
　　┃　　　┗━━━┓
　　┃　　　　　　　┣┓
　　┃　　　　　　　┏┛
　　┗┓┓┏━┳┓┏┛
　　　┃┫┫　┃┫┫
　　　┗┻┛　┗┻┛
 *本程序可以由任何人自由发表、修改、传播，请保留此注视，谢谢！
 *
 * 在此基础上做了一定的SQL和GUI的修改
 */
package com.myStudentManager.frame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Rectangle;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

import com.myStudentManager.dao.UserDao;
import com.myStudentManager.util.DBConnection;

import javax.swing.JComboBox;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton jButton21 = null;
    private JButton jButton22 = null;
    private JTextField jTextField = null;
    private  JPasswordField jPasswordField = null;
    private JLabel jLabel = null;
    static int storeUserId ;// 登录用户名
    public static String storeUserName = null;// 登录用户名
    public  static String storeUserPassword = null;// 登录密码
    static boolean RELOAD = true;// 重新登陆标记
    static int login_user_type ;//0表示管理员，1表示老师，2表示学生
    private JLabel jLabel_User = null;
    private JLabel jLabel_userName = null;
    private JLabel jLabel_password = null;
    private JLabel jLabel_privilege = null;
    private URL imgURL = null;


    private BtnListener btl = null;
    private JComboBox jComboBox = null;
    private JLabel jLabel_tips = null;

    private void initialize() {

        jLabel_tips = new JLabel();
        jLabel_tips.setBounds(new Rectangle(15, 247, 277, 24));
//        jLabel_tips.setText("管理员使用账号登陆，非管理员请使用ID登陆");
//        this.setResizable(false);
        this.setSize(296, 256);
        this.setTitle("欢迎登录");
        this.setLocationRelativeTo(null);
         this.setUndecorated(true);//设置无边框
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jButton21 = new JButton();
        jButton21.setBounds(new Rectangle(15, 195, 78, 26));

/*		InputStream in=this.getClass().getResourceAsStream("");
		Reader data=new InputStreamReader(in);*/

//        imgURL = this.getClass().getResource("/com/chenluozhi/images/icon.png");//解决打包找不到资源的问题

        jButton21.setText("登录");
        jButton21.setBounds(new Rectangle(30, 196, 78, 26));
        getRootPane().setDefaultButton(jButton21);// 回车登录

        jButton22 = new JButton();
        jButton22.setBounds(new Rectangle(180, 196, 78, 26));
        jButton22.setText("退出");

        jTextField = new JTextField(20);
        jTextField.setBounds(new Rectangle(120, 80, 124, 23));

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(new Rectangle(120, 110, 124, 23));

        jLabel = new JLabel();
        jLabel.setText("");
        jLabel.setBounds(new Rectangle(0, -1, 291, 142));
        jLabel_password = new JLabel();
        jLabel_password.setBounds(new Rectangle(29, 110, 71, 19));
        jLabel_password.setText("密 码");
        jLabel_userName = new JLabel();
        jLabel_userName.setBounds(new Rectangle(29, 81, 71, 19));
        jLabel_userName.setText("用户名");
        jLabel_User = new JLabel();
        jLabel_User.setBounds(new Rectangle(10, 47, 275, 98));


        jLabel_privilege = new JLabel();
        jLabel_privilege.setBounds(new Rectangle(18, 152, 101, 19));
        jLabel_privilege.setText("登录类型：");

        jComboBox = new JComboBox();
        jComboBox.setBounds(new Rectangle(109, 152, 123, 23));
        jComboBox.addItem("管理登录");
        jComboBox.addItem("老师登录");
        jComboBox.addItem("学生登录");



//        imgURL = this.getClass().getResource("/com/myStudentManager/images/user.gif");
//        jLabel_User.setIcon(new ImageIcon(imgURL));
//        jLabel_User.setText("User");

        jContentPane = new JPanel();// 新建jPanel面板
        jContentPane.setLayout(null);
        jContentPane.setBackground(new Color(255, 255, 255));
        jContentPane.add(jLabel_userName, null);
        jContentPane.add(jLabel_password, null);
        jContentPane.add(jButton21, null);
        jContentPane.add(jButton22, null);
        jContentPane.add(jTextField, null);
        jContentPane.add(jPasswordField, null);
        jContentPane.add(jLabel, null);
        jContentPane.add(jLabel_User, null);

        jContentPane.add(jComboBox, null);
        jContentPane.add(jLabel_privilege, null);
        jContentPane.add(jLabel_tips, null);
        setContentPane(jContentPane);

        btl = new BtnListener();
        jButton21.addActionListener(btl);
        jButton22.addActionListener(btl);

    }
    /**
     * @监听类
     * @author Administrator
     */
    public class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jButton21) {
                UserDao ud = new UserDao();
                String user = jTextField.getText().trim();
                String password =new String(jPasswordField.getPassword()).trim();//char to String
                storeUserId = Integer.parseInt(user);
                storeUserName = user;
                storeUserPassword = password;
                login_user_type=jComboBox.getSelectedIndex();
                if("".equals(user)){
                    JOptionPane.showMessageDialog(null, "用户名不能为空");return;
                }
                if("".equals(password)){
                    JOptionPane.showMessageDialog(null, "密码不能为空");return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dt = sdf.format(new java.util.Date());

                //如果是管理员，可以直接使用账户登录
                if(login_user_type==0){

                    if(ud.userLogin(login_user_type, storeUserName, storeUserPassword))
                    {
                        dispose();
                        MainFrame mf = new MainFrame();
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "欢迎 "+ user + "登录！", "关于选修课程管理系统",JOptionPane.INFORMATION_MESSAGE);
                        storeUserId = ud.getUserIdByUserName(storeUserName);
                        String log_operate = "["+storeUserName+"]"+"教务处登录系统";
                        DBConnection.update("insert into c_log(login_user,log_operate,log_time) values('"+storeUserName+"','"+log_operate+"','"+dt+"')");
                    }else{JOptionPane.showMessageDialog(null, "登录失败");return;}
                    //教师登录
                }else if(login_user_type==1){
                    boolean hasNonDigit = false;
                    for (int i = 0; i < jTextField.getText().length(); ++i) {
                        if(!Character.isDigit(jTextField.getText().charAt(i))) {
                            hasNonDigit = true;
                            break;
                        }
                    }
                    if(!hasNonDigit) {
                        if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                            dispose();
                            MainFrame mf = new MainFrame();
                            mf.setVisible(true);
                            JOptionPane.showMessageDialog(null, "欢迎 " + storeUserName + "号教师登录！", "关于选修课程管理系统", JOptionPane.INFORMATION_MESSAGE);

                            String log_operate = "[" + storeUserName + "]号教师" + "用户登陆系统";
                            DBConnection.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");

                        } else {JOptionPane.showMessageDialog(null, "登录失败");return;}
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败");return;
                    }
                }else if(login_user_type==2){
                    boolean hasNonDigit = false;
                    for (int i = 0; i < jTextField.getText().length(); ++i) {
                        if(!Character.isDigit(jTextField.getText().charAt(i))) {
                            hasNonDigit = true;
                            break;
                        }
                    }
                    if(!hasNonDigit){
                        if(ud.userLogin(login_user_type, storeUserName, storeUserPassword))
                        {
                            dispose();
                            MainFrame mf = new MainFrame();
                            mf.setVisible(true);
                            JOptionPane.showMessageDialog(null, "欢迎 "+ user + "号学生登录！", "关于选修课程管理系统",JOptionPane.INFORMATION_MESSAGE);
                            String log_operate = "["+storeUserName+"]"+"号学生登陆系统";
                            DBConnection.update("insert into c_log(login_user,log_operate,log_time) values('"+storeUserName+"','"+log_operate+"','"+dt+"')");
                        }else{JOptionPane.showMessageDialog(null, "登录失败");return;}
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败");return;
                    }
                }
            }

            else if (e.getSource() == jButton22) {
                System.exit(0);
            }
        }
    }


    /**
     * @主函数
     * @param args
     */
    public static void main(String[] args) {
        Login login = new Login(RELOAD);
        login.setVisible(true);
    }

    public Login() {
        super();
        initialize();
    }

    public Login(boolean reload) {
        super();
        initialize();
    }
}
