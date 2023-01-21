package semi_proj.tomatalk;

import java.util.StringTokenizer;
import java.util.Vector;

public class TmtClientThread extends Thread {
	
//	private static final Logger
  TmtClient tc = null;
  TmtServerThread tst = null;
  TmtChatForm tmtChatForm = null;

  public TmtClientThread(TmtChatForm tmtChatForm) {
    this.tmtChatForm = tmtChatForm;
  }

  public TmtClientThread(TmtClient tc) {
    this.tc = tc;
  }

  // 서버측에서 클라이언트가 접속하면 접속자의 정보를 List<TalkServerThread> add(스레드생성자)했고
  // 메시지를 청취하자 마자 클라이언트측에 반복문을 돌려서 쓰기 한다.(전송함) - broadCastring(String msg)
  @Override
  public void run() {
    boolean isStop = false;
    // run_stop:
    while (!isStop) { // 실시간으로 운영하기 위해 무한 반복
      try {
        // 100#도마도 님 입장하였습니다.
        String msg = ""; // 메세지 변수
        msg = (String) tc.ois.readObject(); // 톡클라이언트가 말하는 것을 청취
        System.out.println("서버에서 전송된 msg : " + msg);
        StringTokenizer st = null;
        int protocol = 0;// 프로토콜 받아올 변수 선언(ex:100 200 300 400 500)
        if (msg != null) { // 메세지를 받아올 때
          st = new StringTokenizer(msg, "#"); // 토크나이저로 #단위로 썰어줌
          protocol = Integer.parseInt(st.nextToken());
        }
        System.out.println("protocol : " + protocol);
        switch (protocol) {
          case Protocol.TALK_IN: {
            String nickName = st.nextToken();
            tc.jta_display.append(nickName + "님이 입장하였습니다.\n");
            // JTable은 양식임, 데이터셋은 DefaultTableModel이니까 거기에 닉네임 출력함
            Vector<String> v = new Vector<>(); // 유저닉네임을 받아올 벡터 생성
            v.add(nickName);
            tc.dtm.addRow(v); // 유저 닉네임(1개로우) 추가
            System.out.println("프로토콜100 / TALK IN");
          }
            break;
          case Protocol.MESSAGE: {
            String nickName = st.nextToken();
            String message = st.nextToken();
            tc.jta_display.append("[" + nickName + "] " + message + "\n"); // [토마토] 안녕하세요 \n < 이런식으로 표기
            tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength()); // setCaretPosition =>> 항상 맨 밑으로
                                                                                       // 스크롤해줌
            System.out.println("프로토콜200 / MESSAGE");
          }
          case Protocol.PROOM_IN: { // //1:1채팅창에 들어감
            String nickName = st.nextToken();// tomato
            String otherName = st.nextToken();// kiwi
            String message = st.nextToken();// 1:1
            // 1:1채팅창을 열어줌
            TmtChatForm tmtChatForm = new TmtChatForm(tc);
            tmtChatForm.initDisplay();
            // DefaultTableModel - 2명
            tc.jta_display.append(nickName + "님과 " + otherName + "님이 대화를 시작했습니다. " + "\n");
            System.out.println("프로토콜210 / PROOM_IN");
            // tc.jta_display.append(nickName + "님이 " + otherName + "님에게 " + message +
            // "\n");
            // tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
          }
            break;
          case Protocol.PROOM_MSG: { // //1:1채팅
            String nickName = st.nextToken();// tomato
            String otherName = st.nextToken();//kiwi
            String message = st.nextToken();// 1:1
            // 1:1채팅창을 열어줌
            // TmtChatForm tcf = new TmtChatForm(tc);
            // tcf.initDisplay();
            // DefaultTableModel - 2명
            tmtChatForm.jta_display.append("[" + nickName + "] " + message + "\n");
            tmtChatForm.jta_display.setCaretPosition(tmtChatForm.jta_display.getDocument().getLength());
            System.out.println("프로토콜220 / PROOM_MSG");
            // tc.jta_display.append(nickName + "님이 " + otherName + "님에게 " + message +
            // "\n");
            // tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
          }
            break;
          // case Protocol.WHISPER: {
          //   String nickName = st.nextToken();
          //   String otherName = st.nextToken();
          //   String message = st.nextToken();
          //   tc.jta_display.append(nickName + "님이 " + otherName + "님에게 " + message + "\n");
          //   tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
          // }
          //   break;
          case Protocol.CHANGE: {
            String nickName = st.nextToken();
            String afterName = st.nextToken();
            String message = st.nextToken();
            // 테이블에 대화명 변경하기
            for (int i = 0; i < tc.dtm.getRowCount(); i++) {
              String imsi = (String) tc.dtm.getValueAt(i, 0);
              if (nickName.equals(imsi)) {
                tc.dtm.setValueAt(afterName, i, 0);
                
              }break;

            }
            // 채팅창에 타이틀바에도 대화명을 변경처리 한다.
            if (nickName.equals(tc.nickName)) {
              tc.setTitle(afterName + "님의 대화창");
              tc.nickName = afterName;
            }
            tc.jta_display.append(message + "\n");
          }
            break;
          case Protocol.TALK_OUT: {
            String nickName = st.nextToken();
            tc.jta_display.append(nickName + "님이 퇴장 하였습니다.\n");
            tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
            for (int i = 0; i < tc.dtm.getRowCount(); i++) {
              String n = (String) tc.dtm.getValueAt(i, 0);
              if (n.equals(nickName)) {
                tc.dtm.removeRow(i);
              }
            }
          }
          default:
            System.out.println("해당하는 프로토콜이 존재하지 않습니다.");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
