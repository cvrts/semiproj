package semi_proj.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionMgr {
  public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
  // 물리적으로 떨어져 있는 오라클 서버에 URL정보 추가
  // 물리적으로 떨어져 있는 오라클 서버에 접속하는 방식으로 thin방식과 oci방식지원
  // 멀티티어 환경에서는 thin 방식이다 서버의 DNS, 포트번호-1521, SID이름-물리적인 저장소참조
  public static final String _URL = "jdbc:oracle:thin:@192.168.10.89:1521:orcl11";
  public static String _USER = "DAKOO";
  public static String _PW = "dakoo2";

  public DBConnectionMgr() {
    // 파라미터가 있는 생성자가 하나라도 있으면 디폴트생성자가 자동으로 제공 되지X
  }

  public DBConnectionMgr(String ID, String PW) {
    // this대한 어려움으로 리액트훅(함수형 프로그래밍, 자바:람다식, 익명클래스)
    // 새로운 방식 제안함 (ver16.8부터 현재18.2)
    // 웹브라우저에서는 this가 왜 문제인가?
    _USER = ID; // static으로 선언된 변수는 this, super 같은 예약어 사용 불가
    _PW = PW;
  }

  public Connection getConnection() {
    Connection con = null;
    try {
      Class.forName(_DRIVER);
      con = DriverManager.getConnection(_URL, _USER, _PW);
    } catch (ClassNotFoundException ce) {
      System.out.println("드라이버 클래스를 찾을 수 없습니다.");
    } catch (Exception e) {// 멀티 블럭 작성시 더 넓은 클래스가 뒤에 와야 함
      System.out.println("오라클 서버와 커넥션 실패!!");
    }
    return con;
  }

  // 사용한 자원을 반납하는 코드는 명시적으로 하는 것을 원칙으로 하고 있음
  // 반납하는 순서는 생성된 역순으로 진행할 것 - 자바튜닝팀 지적사항
  // 사용한 자원 반납하기 - INSERT , UPDATE, DELETE
  public void freeConnection(Connection con, Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (con != null) {
      try {
        con.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }

  // 오라클서버와 연계에 필요한 객체, 사용후에 반드시 자원 반납할 것-명식적으로
  // (묵시적으로 언젠가는 처리됨)
  public void freeConnection(Connection con, CallableStatement cstmt) {
    if (cstmt != null) {
      try {
        cstmt.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (con != null) {
      try {
        con.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }

  // 사용한 자원 반납하기 - SELECT - 커서 조작이 필요함
  public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (pstmt != null) {
      try {
        pstmt.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (con != null) {
      try {
        con.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }

  public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (stmt != null) {
      try {
        stmt.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (con != null) {
      try {
        con.close();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }

}