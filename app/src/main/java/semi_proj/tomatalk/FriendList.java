package semi_proj.tomatalk;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;//두개 이상의 마우스 이벤트처리를 위해 필요함
//

public class FriendList extends JFrame implements ActionListener, MouseListener {
  // 선언부
  TmtMultiChat tMulti = null; // 단체 채팅방
  TmtChatForm tChatForm = null; // 1:1 채팅창
  public JList friendList;
  public DefaultListModel model; // JList에서 목록수가 많아지면 자동으로 스크롤바 생김
  JPanel jp_north = new JPanel();
  JPanel jp_south = new JPanel();
  JButton jbtn_edit = new JButton("설정");
  JButton jbtn_multi = new JButton("단체 채팅");
  JButton jbtn_exit = new JButton("로그 아웃");
  JScrollPane jsp_display = new JScrollPane(
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  // 필요할때만 스크롤바가 보이도록 함, 스크롤바를 숨김
  Font f = new Font("Paryrus", Font.BOLD, 30);// 글자 크기
  Boolean isClicked = false;

  // 생성부
  public FriendList() {
    System.out.println("생성자 호출ok");
    initDisplay();
  }

  public FriendList(TmtChatForm tChatForm) {
    this.tChatForm = tChatForm;
  }

  // 화면처리기
  public void initDisplay() {
    setDefaultCloseOperation(EXIT_ON_CLOSE); // 화면 끄면 작동도 꺼진다.

    model = new DefaultListModel();
    model.addElement("윤성준");
    model.addElement("박상준");
    model.addElement("권양아");
    model.addElement("김성훈");
    model.addElement("정다희");
    friendList = new JList(model);
    // 디폴트리스트 모델은 변경할 수 없기 때문에
    // 새로운 디폴트리스트 모델 인스턴스를 생성하여
    // 생성자의 매개변수로 전달해야한다.
    add(new JScrollPane(friendList));
    // 친구리스트 만드는 메소드 추가해야함

    jp_north.add(jbtn_edit);
    jp_north.add(jbtn_multi);
    jp_north.add(jbtn_exit);
    jp_south.setLayout(new GridLayout(1, 3));
    // this.add("Center", jtf_display);
    this.add("South", jp_south);
    this.add("North", jp_north);
    this.setTitle("친구목록");
    this.setSize(400, 600);
    this.setVisible(isClicked);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == jbtn_multi) {
      // 단체방 버튼 이벤트 처리
      tMulti.isClicked = true;
      tMulti.initDisplay();
      System.out.println("단체방 버튼 OK");
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Object obj = e.getButton();
    if (obj == model) {
      // boolean isClicked->TmtChatForm 선언부에 추가해야함
      tChatForm.isClicked = true;
      tChatForm.initDisplay();
      System.out.println("귓속말 OK");
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // 필요는 없지만 지우면 안됨
    // 마우스 버튼을 꾹 누름
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // 필요는 없지만 지우면 안됨
    // 마우스버튼을 놓음
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // 필요는 없지만 지우면 안됨
    // 마우스가 윈도우 안에 들어옴
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // 필요는 없지만 지우면 안됨
    // 마우스가 윈도우 밖으로 나감
    // TODO Auto-generated method stub

  }

}