package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestJDialog extends JDialog implements ActionListener {
  // 각 컴포넌트의 크기 및 위치 선정해야함

  LoginForm00 lgf = null;
  JPanel jp = new JPanel();
  JLabel jl_id = new JLabel("아이디 :");
  JTextArea jta_id = new JTextArea();
  JTextField jtf_id = new JTextField();
  JLabel jl_pw = new JLabel("비밀번호 :");
  JTextArea jta_pw = new JTextArea();
  JTextField jtf_pw = new JTextField();
  JButton jbtn_join = new JButton("가입하기"); // 가입하기 누르면 로그인 폼으로 이동구현하기
  boolean isClicked = false; // 로그인 폼에서 클릭하면 회원가입창이 뜰 수 있게 해주는 트리거
  // 생성자

  public TestJDialog() {
    // this.lgf =
    System.out.println("다이얼로그 생성자 호출");
    initDisplay();
  }

  public TestJDialog(LoginForm00 log) {
    System.out.println("다이얼로그 생성자 호출");
    this.lgf = log;
    // initDisplay();
  }

  // 화면처리
  public void initDisplay() {

    System.out.println("디스플레이 호출ok");
    jtf_id.requestFocus(); // jtf에 키이벤트 받을 컴포넌트 설정-디폴트로 할것
    jtf_pw.requestFocus();
    // jbtn.addActionListener(this);//작동하기 위해서 임시로 막아놈
    jp.setLayout(null);
    // 컴포넌트 위치 선정
    jl_id.setBounds(20, 20, 100, 20);
    jtf_id.setBounds(120, 20, 100, 20);
    jl_pw.setBounds(20, 45, 100, 20);
    jtf_pw.setBounds(120, 45, 150, 20);
    jbtn_join.setBounds(220, 20, 120, 20);
    jbtn_join.addActionListener(lgf);
    jp.add(jl_id);
    jp.add(jtf_id);
    jp.add(jl_pw);
    jp.add(jtf_pw);
    jp.add(jbtn_join);
    this.setTitle("회원가입");
    this.setSize(400, 600);
    this.setContentPane(jp);
    this.setVisible(isClicked);
  }

  public static void main(String[] args) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = new Object();
    if(obj == jbtn_join){
      JOptionPane.showConfirmDialog(this, e, getTitle(), ABORT)
    }    
  }
}