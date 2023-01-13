package semi_proj.tomatalk;

public class MemberVO {
  private String mem_id = null;
  private String mem_pw = null;
  public String mem_name = null;

  public String getName() {
    return this.mem_name;
  }

  public void setName(String mem_name) {
    this.mem_name = mem_name;
  }

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
}