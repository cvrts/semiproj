package semi_proj.tomatalk;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
// 추가하고 싶은 기능 : 텍스트필드 사이즈 넉넉하게 하기
//
public class TmtChatForm extends JFrame implements ActionListener {

  // 선언부

  JPanel jp = new JPanel();
  JPanel jp_south = new JPanel();
  JPanel jp_south_first = new JPanel();//메세지 입력, 전송 부분
  JPanel jp_south_second = new JPanel();//메인,개인,단체버튼 부분
  JTextField jtf_msg = new JTextField(25);// south속지 center - 한줄 입력창 만들기
 
  JButton jbtn_send = new JButton("전송");// south속지 east

  JButton jbtn_main = new JButton("메인");
  JButton jbtn_personal = new JButton("개인");
  JButton jbtn_everyone = new JButton("단체");

  JTextArea jta_display = new JTextArea(25, 40);
  JScrollPane jsp_display = new JScrollPane(jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

  // 생성자
  public void TmtChatForm() {

  }

  // 메인
  public static void main(String[] args) {
    TmtChatForm tmtChatForm = new TmtChatForm();
    tmtChatForm.initDisplay();
  }

  // 화면그리기
  public void initDisplay() {

    jtf_msg.addActionListener(this);
    jbtn_send.addActionListener(this);
    
    jp.setLayout(new BorderLayout());
    jp.add("Center", jsp_display);
    jp.add("South", jp_south);
  
    jp_south.setLayout(new GridLayout());
    jp_south.setLayout(new GridLayout(2,1));
    jp_south.add(jp_south_first);
    jp_south.add(jp_south_second);
    jp_south_first.add("Center",jtf_msg);
    jp_south_first.add("West",jbtn_send);
   
    jp_south_second.setLayout(new GridLayout(1,3));
    jp_south_second.add( jbtn_main);
    jp_south_second.add(jbtn_personal);
    jp_south_second.add(jbtn_everyone);

    jta_display.setLineWrap(true);// 자동줄바꾸기
    jta_display.setEnabled(false);// JTextArea 입력불가하게 만들기

    this.setLayout(new GridLayout(1, 2));// 메세지창 , 전송버튼
    this.add(jp);
    this.setSize(400, 550);
    this.setVisible(true);
    this.setTitle("tomatalk_chat_form");
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (jtf_msg == obj || jbtn_send == obj) {
      String msg = jtf_msg.getText();
      jta_display.append(msg + "\n");
      jtf_msg.setText("");
      // jtf_msg.requestFocus();
    }
  }
}