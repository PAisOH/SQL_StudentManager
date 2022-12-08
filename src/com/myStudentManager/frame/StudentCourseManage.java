package com.myStudentManager.frame;

import com.myStudentManager.dao.CCourseDao;
import com.myStudentManager.dao.CourseDao;
import com.myStudentManager.dao.StudentDao;
import com.myStudentManager.dao.TeacherDao;
import com.myStudentManager.model.CCourseModel;
import com.myStudentManager.model.CourseModel;
import com.myStudentManager.model.TeacherModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.myStudentManager.frame.Login.storeUserId;
import static com.myStudentManager.frame.Login.storeUserName;

public class StudentCourseManage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButton_course_add = null;
//	private JButton jButton_course_edit = null;
	private JButton jButton_course_query = null;
	private JButton jButton_course_delete = null;
	private JButton jButton_course_flash = null;
	private JLabel jLabel_course_counts = null;
	private int counts=0;

	private List<CourseModel> course_lists ;

	private List<CourseModel> teacher_lists;  //  @jve:decl-index=0:

	DefaultTableModel model = new DefaultTableModel();
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	//	private JTextArea jTextField_course_info = null;
	private JPanel jPanel = null;
	private JLabel jLabel_course_name = null;
//	private JLabel jLabel_course_credit = null;
//	private JLabel jLabel_teach_name = null;
//	private JTextField jTextField_course_name = null;
//	private JTextField jTextField_course_credit = null;
	private JComboBox jComboBox_teach_name = null;
//	private JTextField jTextField_course_begin_time = null;
	private JButton jButton_ok = null;
	private JButton jButton_cancel = null;

	public StudentCourseManage() {
		super();
		initialize();
		initData();
		StudentCourseManage.btnListener btn = new StudentCourseManage.btnListener();
		jButton_course_add.addActionListener(btn);
//		jButton_course_edit.addActionListener(btn);
		jButton_course_query.addActionListener(btn);
		jButton_course_delete.addActionListener(btn);
		jButton_course_flash.addActionListener(btn);
		jButton_ok.addActionListener(btn);
		jButton_cancel.addActionListener(btn);

		jTable.getSelectionModel().addListSelectionListener(new StudentCourseManage.tableListener());
	}

	private void initialize() {
		jLabel_course_name = new JLabel();
		jLabel_course_name.setText("课程名称：");
		jLabel_course_name.setBounds(new Rectangle(23, 25, 88, 28));
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(478, 71, 96, 31));
//		jLabel1.setText("课程介绍：");
		jLabel = new JLabel();
		jLabel.setText("开课时间：");
		jLabel.setBounds(new Rectangle(250, 63, 73, 30));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(680, 479);
		this.setTitle("课程管理");
		//this.setModal(true);
		this.setLocationRelativeTo(null);
		jButton_course_add = new JButton();
		jButton_course_add.setText("添加");
		jButton_course_query = new JButton();
		jButton_course_query.setText("查询");
		jButton_course_delete = new JButton();
		jButton_course_delete.setText("删除");
		jButton_course_flash = new JButton();
		jButton_course_flash.setText("刷新");


		jButton_ok = new JButton();
		jButton_ok.setBounds(new Rectangle(496, 17, 114, 29));
		jButton_ok.setText("确定");

		jButton_cancel=new JButton();
		jButton_cancel.setBounds(new Rectangle(496, 63, 115, 27));
		jButton_cancel.setText("取消");



		jLabel_course_counts = new JLabel();
		jLabel_course_counts.setSize(20, 20);
		jJToolBarBar = new JToolBar();
		jJToolBarBar.setBounds(new Rectangle(5, 21, 714, 34));
		jJToolBarBar.add(jButton_course_add);
		jJToolBarBar.add(jButton_course_query);
		jJToolBarBar.add(jButton_course_delete);
		jJToolBarBar.add(jButton_course_flash);
		jJToolBarBar.add(jLabel_course_counts);

		jTable = new JTable();
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(20, 71, 450, 250));
		jScrollPane.setViewportView(jTable);




		jComboBox_teach_name = new JComboBox();
		jComboBox_teach_name.setBounds(new Rectangle(326, 18, 148, 30));


		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBounds(new Rectangle(22, 332, 626, 106));
		jPanel.setBorder(BorderFactory.createTitledBorder("课程开设"));
		jPanel.add(jLabel_course_name, null);

		jPanel.add(jComboBox_teach_name, null);
		jPanel.add(jLabel, null);
		jPanel.add(jButton_ok, null);
		jPanel.add(jButton_cancel, null);

		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jScrollPane, null);
		jContentPane.add(jJToolBarBar, null);
		jContentPane.add(jLabel1, null);
//				jContentPane.add(jTextField_course_info, null);
		jContentPane.add(jPanel, null);
		this.setContentPane(jContentPane);
		jContentPane.add(jScrollPane, null);

		this.setContentPane(jContentPane);




	}

	/**
	 * @初始化界面
	 */
	public void initData() {

		String heads[] = { "课程ID", "课程名称", "课程学分", "授课教师","开课时间"};
		model = new DefaultTableModel(null, heads);
		CourseDao cd = new CourseDao();
		CCourseDao ccd = new CCourseDao();
//		course_lists = cd.getLists(false, -1);

		int  student_id = storeUserId;
		course_lists = new ArrayList<>();
		List<CCourseModel> cCourseModelList = ccd.getListsByStuId(student_id);
		cCourseModelList.stream().forEach(cCourseModel -> {
			Integer course_id = cCourseModel.getCourse_id();
			List<CourseModel> courseList = cd.getLists(true, course_id);
			course_lists.addAll(courseList);
		});



		jButton_ok.setEnabled(false);
		jButton_ok.setText("确定");
//		TeacherDao td = new TeacherDao();

		teacher_lists = cd.getLists(false, -1);
		List<CourseModel> restCourseList = new ArrayList<>();
		teacher_lists.stream().forEach(course -> {
			if(!course_lists.contains(course)) {
				restCourseList.add(course);
			}
		});

		for(int i=0;i<restCourseList.size();i++){
			jComboBox_teach_name.addItem(restCourseList.get(i).getCourse_name());
		}

		flashData();


	}

	public void flashData(){
//		CourseDao cd = new CourseDao();
//		CCourseDao ccd = new CCourseDao();
//
//		int student_id = storeUserId;
//		try {
//			student_id = Integer.parseInt(JOptionPane.showInputDialog("按学号查询，请输入要查询的学号："));
//		} catch (Exception e2) {
//			// TODO: handle exception
//			return;
//		}
//		course_lists = new ArrayList<>();
//		List<CCourseModel> cCourseModelList = ccd.getListsByStuId(student_id);
//		cCourseModelList.stream().forEach(cCourseModel -> {
//			Integer course_id = cCourseModel.getCourse_id();
//			List<CourseModel> courseList = cd.getLists(true, course_id);
//			course_lists.addAll(courseList);
//		});
//

		counts = course_lists.size();
		model.setRowCount(course_lists.size());// 设置行数
		for(int i=0;i<counts;i++){
			model.setValueAt(course_lists.get(i).getCourse_id(), i, 0);
			model.setValueAt(course_lists.get(i).getCourse_name(), i, 1);
			model.setValueAt(course_lists.get(i).getCourse_credit(), i, 2);
			model.setValueAt(course_lists.get(i).getTeach_name(), i, 3);
			model.setValueAt(course_lists.get(i).getCourse_begin_time(), i, 4);

		}
		jLabel_course_counts.setText("记录数:" + counts + "");
		jTable.setModel(model);
		jTable.setAutoCreateRowSorter(true);//为JTable设置排序器
	}

	/**
	 * 内部类监听器模块
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CourseDao cd = new CourseDao();
			CCourseDao ccd = new CCourseDao();

			if (e.getSource() == jButton_course_add) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String course_begin_time = sdf.format(new Date());
//				jTextField_course_info.setEditable(true);
				jButton_ok.setEnabled(true);
				jButton_ok.setText("确定");
			}
			else if (e.getSource() == jButton_course_delete) {
				if (jTable.getSelectedRow() != -1) {
					int course_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					cd.deleteListByCourseId(course_id);
					JOptionPane.showMessageDialog(null, "删除成功！");
					model.removeRow(jTable.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的行！");
				}
			}
			else if (e.getSource() == jButton_course_query) {
				int  student_id;
				try {
					student_id = Integer.parseInt(JOptionPane.showInputDialog("按学号查询，请输入要查询的学号："));
				} catch (Exception e2) {
					// TODO: handle exception
					return;
				}
				course_lists = new ArrayList<>();
				List<CCourseModel> cCourseModelList = ccd.getListsByStuId(student_id);
				cCourseModelList.stream().forEach(cCourseModel -> {
					Integer course_id = cCourseModel.getCourse_id();
					List<CourseModel> courseList = cd.getLists(true, course_id);
					course_lists.addAll(courseList);
				});
//				course_lists = cd.getLists(true, course_id);
				flashData();
			}
			else if (e.getSource() == jButton_course_flash) {
				initData();
			}
			else if(e.getSource()==jButton_ok){
				if("确认修改".equals(jButton_ok.getText())){
//					int course_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
//					int course_credit = Integer.parseInt(jTextField_course_credit.getText().toString());
//					int teach_id = teacher_lists.get(jComboBox_teach_name.getSelectedIndex()).getTeach_id();
//					String course_name = jTextField_course_name.getText();
//					String course_begin_time = jTextField_course_begin_time.getText();
////					String course_info = jTextField_course_info.getText();
//					if(cd.modifyCourse(course_id, course_name, course_credit, teach_id, course_begin_time)){
//						JOptionPane.showMessageDialog(null, "修改成功！");
//						initData();
//					}else{
//						JOptionPane.showMessageDialog(null, "修改失败！");
//					}
					return;
				}
//				String course_name = jTextField_course_name.getText();
//				int course_credit = Integer.parseInt(jTextField_course_credit.getText());
//				String course_begin_time = jTextField_course_begin_time.getText();
////				String course_info = jTextField_course_info.getText();
//				int teach_id = teacher_lists.get(jComboBox_teach_name.getSelectedIndex()).getTeach_id();
//				if(cd.addCourse(course_name, course_credit, course_begin_time, teach_id)) {
//					JOptionPane.showMessageDialog(null, "添加成功");
//				} else
//					JOptionPane.showMessageDialog(null, "添加失败");
//
//				course_lists = cd.getLists(false, -1);
				flashData();
			}
			else if(e.getSource()==jButton_cancel){
				initData();

			}


		}
	}

	public class tableListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (jTable.getSelectedRow() != -1) {
				int index = jTable.getSelectedRow();
//				jTextField_course_info.setText(course_lists.get(index).getCourse_info());

			}
		}
	}


}
