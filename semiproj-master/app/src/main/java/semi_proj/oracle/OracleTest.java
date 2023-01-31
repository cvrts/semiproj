package semi_proj.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleTest {
  Oracle oracle = new Oracle();
  Connection con;
  PreparedStatement pstmt;
  ResultSet rs;

  public void insert() {
    StringBuilder sql = new StringBuilder();
    int result = 0;
    sql.append("INSERT INTO MEMBER( USER_ID, USER_PW, USER_NAME) ");
    sql.append("VALUES ( ?, ?, ? ) ");

    try {
      con = oracle.getConnection();
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, "tomato");
      pstmt.setString(2, "123");
      pstmt.setString(3, "토마토");

      result = pstmt.executeUpdate();

    } catch (Exception e) {

    }finally{
      oracle.freeConnection(con, pstmt, rs);
    }
    System.out.println(result);
  }

  public static void main(String[] args) {
    OracleTest test = new OracleTest();
    test.insert();
  }
}
