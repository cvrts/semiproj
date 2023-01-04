package semi_proj.tomatalk;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;
import java.awt.BorderLayout;

public class TmtChatForm extends JFrame implements ActionListener {

  // 선언부
  // ActionListener actionListener = new TmtChatForm();

  // JTextAreaUILogic jtaUI = new JTextAreaUILogic(this);
  JPanel jp = new JPanel();
  JPanel jp_south = new JPanel();
  JTextField jtf_msg = new JTextField(20);// south속지 center - 한줄 입력창 만들기
  JButton jbtn_send = new JButton("전송");// south속지 east
  JTextArea jta_display = new JTextArea(15, 38);
  JScrollPane jsp_display = new JScrollPane(jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

  // 생성자

  // 메인
  public static void main(String[] args) {
    TmtChatForm tmtChatForm = new TmtChatForm();
    tmtChatForm.initDisplay();

    // tmtChatForm.init();
  }

  // 화면그리기
  public void initDisplay() {
    jtf_msg.addActionListener(this);
    jbtn_send.addActionListener(this);
    this.setLayout(new GridLayout(1, 2));
    jp.setLayout(new BorderLayout());
    jp.add("Center", jsp_display);
    jp.add("South", jp_south);
    jp_south.setLayout(new BorderLayout());
    jp_south.add("Center", jtf_msg);
    jp_south.add("East", jbtn_send);
    jta_display.setLineWrap(true);// 자동줄바꾸기
    jta_display.setEnabled(false);// JTextArea 입력불가하게 만들기

    this.add(jp);
    this.setSize(400, 550);
    this.setVisible(true);
    this.setTitle("tomatalk_chat_form");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (jtf_msg == obj ||jbtn_send == obj ) {
      String msg = jtf_msg.getText();
      jta_display.append(msg + "\n");
      jtf_msg.setText("");
    } 
  }
}
