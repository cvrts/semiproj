package semi_proj.tomatalk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TmtClient implements ActionListener {
  // 선언부
  /////////////// 네트워크 관련 부분///////////////
  Socket socket = null;
  ObjectOutputStream oos = null;// 말 하고 싶을 때
  ObjectInputStream ois = null;// 듣기 할 때
  String userID = null;// 유저 아이디 등록
  String userPW = null;// 유저 패스워드 등록
  /////////////// 네트워크 관련 부분///////////////
  JButton loginButton = new JButton("로그인");
  JButton signButton = new JButton("회원가입");
  JButton signconfirmButton = new JButton("가입하기");
  JButton lobbyButton = new JButton("로비");
  JButton logoutButton = new JButton("로그아웃");
  JButton goBackButton = new JButton("뒤로가기");
  JPanel basicPanel = new JPanel();

  JTextArea jta_display = new JTextArea(15, 38);

  // 생성자
  TmtClient() {
    initDisplay();
  }
  // 소켓관련초기화

  // 메인
  public static void main(String[] args) {

  }

  // 화면그리기
  public void initDisplay() {
    loginButton.addActionListener(this);
    signButton.addActionListener(this);

    this.setTitle("토마톡");

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }

}
