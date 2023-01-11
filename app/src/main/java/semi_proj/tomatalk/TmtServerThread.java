package semi_proj.tomatalk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class TmtServerThread extends Thread {
    TmtChatServer ts = null;
    Socket client = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    // 현재 서버에 입장한 클라이언트 스레드의 닉네임 저장
    String talkName = null;

    // 생성자
    public TmtServerThread() {

    }

    public TmtServerThread(TmtChatServer ts) {
        this.ts = ts;
        this.client = ts.socket; // tmt서버 소켓
        try {
            oos = new ObjectOutputStream(client.getOutputStream());// 말하기
            ois = new ObjectInputStream(client.getInputStream());// 듣기
            String msg = (String) ois.readObject();
            ts.jta_log.append(msg + "\n");
            StringTokenizer st = new StringTokenizer(msg, "#");
            st.nextToken();// 100 skip처리
            talkName = st.nextToken();// 닉네임 저장
            ts.jta_log.append(talkName + "님이 입장하였습니다.\n");
            for (TmtServerThread tst : ts.globalList) {
                ts.jta_log.append("tst.nickName ==> " + tst.talkName + "this: " + this + ", tst : " + tst);
                String currentName = tst.talkName;
                this.send(Protocol.TALK_IN + Protocol.separator + tst.talkName);
            }
            // 현재 서버에 입장한 클라이언트 스레드 추가하기
            ts.globalList.add(this);
            this.broadCasting(msg);
        } catch (Exception e) {
            ts.jta_log.append("Exception : " + e.toString() + "\n");
        }
    }

    // 현재 입장해 있는 친구들 모두에게 메시지 전송하기
    public void broadCasting(String msg) {
        synchronized (this) {
            for (TmtServerThread tst : ts.globalList) {
                tst.send(msg);
            }
        }
    }

    // 클라이언트에게 말하기
    public void send(String msg) {
        try {
            oos.writeObject(msg);
        } catch (Exception e) {
            e.printStackTrace();// stack에 쌓여 있는 에러메시지 이력 출력
        }
    }

    @Override
    public void run() {
        String msg = null;
        boolean isStop = false;
        try {
            run_start: while (!isStop) {
                msg = (String) ois.readObject();
                ts.jta_log.append(msg + "\n");
                ts.jta_log.setCaretPosition(ts.jta_log.getDocument().getLength());
                StringTokenizer st = null;
                int protocol = 0;// 100|200|201|202|500
                if (msg != null) {
                    st = new StringTokenizer(msg, "#");
                    protocol = Integer.parseInt(st.nextToken());// 100
                }
                switch (protocol) {
                    case Protocol.MESSAGE: {
                        String talkName = st.nextToken();
                        String message = st.nextToken();
                    }
                        break;
                    case Protocol.WHISPER: {
                        String talkName = st.nextToken();
                        String otherName = st.nextToken();
                        // 귓속말로 보내진 메시지
                        String msg1 = st.nextToken();
                        for (TmtServerThread cst : ts.globalList) {
                            if (otherName.equals(cst.talkName)) {// 상대에게 보내기
                                cst.send(Protocol.WHISPER + Protocol.separator + talkName + Protocol.separator
                                        + otherName
                                        + Protocol.separator + msg1);
                                break;
                            }
                        } // end of for
                          // 내가 한 말을 내게 보냄
                        this.send(Protocol.WHISPER + Protocol.separator + talkName + Protocol.separator + otherName
                                + Protocol.separator + msg1);
                    }
                        break;
                    case Protocol.CHANGE: {
                        String talkName = st.nextToken();
                        String afterName = st.nextToken();
                        String msg1 = st.nextToken();
                        this.talkName = afterName;// 초기화
                        broadCasting(Protocol.CHANGE + Protocol.separator + talkName + Protocol.separator + afterName
                                + Protocol.separator + msg1);
                    }
                        break;
                    // 종료하기 구현
                    case Protocol.TALK_OUT: {
                        String talkName = st.nextToken();
                        // 메시지를 전송한 스레드를 chatList에서 제거 한다.
                        ts.globalList.remove(this);
                        // broadCasting(Protocol.TALK_OUT + Protocol.separator + talkName);
                        String message = Protocol.TALK_OUT + Protocol.separator + talkName;
                        this.broadCasting(message);
                    }
                        break run_start;
                }///////////// end of switch
            } ///////////////// end of while
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}