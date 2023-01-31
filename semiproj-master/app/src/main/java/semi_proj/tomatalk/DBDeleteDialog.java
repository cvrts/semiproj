package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBDeleteDialog extends JDialog implements ActionListener {
  // 회원탈퇴 선언
  JDialog deleteDial = new JDialog();
  JButton jbtn_delete = new JButton("회원탈퇴");
  JPanel jp_pic = new JPanel();
  boolean isVisible = false;
  EditDialog editDialog = null;
  String id = null;
  Statement stmt = null;
  ResultSet rs = null;
  // 오라클 포트번호1521/@이후에는 IP주소
  String _URL = "jdbc:oracle:thin:@192.168.10.89:1521:orcl11";
  String sql = null;
  String sql2 = null;
  Properties info = null;
  Connection cnn = null;

  // id를 받아와서 그것의 정보로 pw/name/birth 삭제
  int InfoDel(String id) {
    int result = 0;
    this.id = id;
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver"); // 알아서 들어간다..conn로
      info = new Properties();
      info.setProperty("user", "DAKOO");
      info.setProperty("password", "dakoo2");
      cnn = DriverManager.getConnection(_URL, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
      stmt = cnn.createStatement();

      sql = "delete from joinDB where id='" + id + "'";
      stmt.executeUpdate(sql);

      sql = "select * from joinDB where id='" + id + "'";
      rs = stmt.executeQuery(sql);
      if (rs.next() == true) { // 다음값의
        result = 0; // 실패
      } else {
        result = 1; // 성공
      }
    } catch (Exception e) {
      System.out.println("문제있음");
      e.printStackTrace();
    }

    return result;
  }

  // 생성자
  DBDeleteDialog() {
    System.out.println("회원탈퇴 생성자 호출");
    initDisplay();
  }

  public DBDeleteDialog(EditDialog ed) {
    this.editDialog = ed;
  }

  // 화면
  public void initDisplay() {
    System.out.println("회원탈퇴 화면 호출");
    this.setLayout(null);
    this.setVisible(isVisible);
    this.setSize(300, 500);
    this.add(jbtn_delete);
    jbtn_delete.setBounds(50, 280, 200, 50);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();

    if (obj == jbtn_delete) {
      System.out.println("회원탈퇴 버튼 클릭됨");
    }
  }
}
