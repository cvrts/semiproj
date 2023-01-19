package semi_proj.tomatalk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import semi_proj.oracle.DBConnectionMgr;

//DB연동, 회원가입 로그인
public class ChatDao {
  int result = 0;
  DBConnectionMgr dMgr = new DBConnectionMgr();
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null; //
  String sql = "";

  // TestJDialog jd = new TestJDialog();
  // 회원가입
  public int insertData(MemberVO mVO) {
    try {
      con = dMgr.getConnection();
      sql = "insert into Member(mem_id, mem_pw, mem_name) ";// 가입날자 sysdate 넣기
      sql += "values(?,?,?)";
      pstmt = con.prepareStatement(sql.toString());
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, mVO.getId());
      pstmt.setString(2, mVO.getPw());
      pstmt.setString(3, mVO.getName());
      result = pstmt.executeUpdate();
      System.out.println(mVO.toString());
      System.out.println("result ==> " + result);
      if (result == 1) {
        System.out.println("회원가입되었습니다.");
        JOptionPane.showMessageDialog(null, "회원가입성공"); // null값을 기본으로 주어 띄움
      } else if (result == 0) {
        System.out.println("회원가입 실패했다 ㅡㅡ");
        // JOptionPane.showMessageDialog(null, "회원가입실패");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.toString());
    } finally {
      dMgr.freeConnection(con, pstmt);
    }
    return result;
  }
	//로그인
  public String login(String mem_id, String mem_pw) {
    String a_name = null;
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT mem_name     ");
    sql.append("  FROM member       ");
    sql.append("  WHERE mem_id =? ");
    sql.append("    AND mem_pw= ?  ");
    try {
      con = dMgr.getConnection();
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, mem_id);
      pstmt.setString(2, mem_pw);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        a_name = rs.getString("mem_name");
      } else {
        a_name = "없음";
      }
      System.out.println(a_name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a_name; // db오류
  }
	//저장된 멤버리스트
 public List<MemberVO> listMembers(){
    List<MemberVO> memberlist = new ArrayList<MemberVO>();
    try {
      con = dMgr.getConnection();
      String query = "select * from member ";
      System.out.println(query);
      pstmt = con.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        String mem_id = rs.getString("mem_id");
        String mem_pw = rs.getString("mem_pw");
        String mem_name = rs.getString("mem_name");
        //Date joinDate = rs.getDate("insert");

        MemberVO vo = new MemberVO();
        vo.setId(mem_id);
        vo.setPw(mem_pw);
        vo.setName(mem_name);
        memberlist.add(vo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      dMgr.freeConnection(con, pstmt, rs);
    }
    return memberlist;
  } 
  //id 중복
  public int idCheck(String mem_id){
    int value = 0;
    try {
      String sql = "select mem_id from member where mem_id = ? ";
      con = dMgr.getConnection();
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, mem_id);
      rs = pstmt.executeQuery();
      if(rs.next()){ 
        value = 1;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      dMgr.freeConnection(con, pstmt, rs);
    }
    return value;
  }
}