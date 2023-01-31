package semi_proj.tomatalk;
import java.util.List;

public class MemberVO {
  private String mem_id = null;
  private String mem_pw = null;
  public String mem_name = null;
  public String mem_namec = null; // 바꿀 닉네임을 받아올 변수 선언!!! /////////

  public String getName() {
    return this.mem_name;
  }

  public void setName(String mem_name) {
    this.mem_name = mem_name;
  }
  
  //닉네임 변경 관련////////////////////////////////
  public String getNamec() {
      return this.mem_namec;
  }
  
  public void setNamec(String mem_namec) {
      this.mem_namec = mem_namec;
  }
  //닉네임 변경 관련////////////////////////////////


  public String getId() {
    return this.mem_id;
  }

  public void setId(String mem_id) {
    this.mem_id = mem_id;
  }

  public String getPw() {
    return mem_pw;
  }

  public void setPw(String mem_pw) {
    this.mem_pw = mem_pw;
  }

  public List<MemberVO> getjsonFormat() {
    return null;
  }
}
/*
 * public String getNickName() {
 * return this.getText();
 * }
 * 
 * public void setNickName(String strNickName) {
 * jtf_nickName.setText(strNickName);
 * }
 */