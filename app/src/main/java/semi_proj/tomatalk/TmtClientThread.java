package semi_proj.tomatalk;

import java.util.StringTokenizer;
import java.util.Vector;

public class TmtClientThread extends Thread {
  TmtClient tc = null;
  TmtServerThread tst = null;

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
          default:
            System.out.println("해당하는 프로토콜이 존재하지 않습니다.");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}