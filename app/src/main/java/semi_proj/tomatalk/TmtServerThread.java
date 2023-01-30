package semi_proj.tomatalk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class TmtServerThread extends Thread {
    TmtChatServer tmtChatServer = null;
    Socket client = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    // 현재 서버에 입장한 클라이언트 스레드의 닉네임 저장
    String talkName = null;

    // 생성자
    public TmtServerThread() {

    }

    public TmtServerThread(TmtChatServer tmtChatServer) {
        this.tmtChatServer = tmtChatServer;
        this.client = tmtChatServer.client; // tmt서버 소켓
        try {
            oos = new ObjectOutputStream(client.getOutputStream());// 말하기
            ois = new ObjectInputStream(client.getInputStream());// 듣기
            String msg = (String) ois.readObject();
            tmtChatServer.jta_log.append(msg + "\n");
            StringTokenizer st = new StringTokenizer(msg, "#");
            st.nextToken();// 100 skip처리
            talkName = st.nextToken();// 닉네임 저장
            tmtChatServer.jta_log.append(talkName + "님이 입장하였습니다.\n");
            for (TmtServerThread tmtServerThread : tmtChatServer.globalList) {
                tmtChatServer.jta_log.append("tst.nickName ==> " + tmtServerThread.talkName + "this: " + this
                        + ", tst : " + tmtServerThread);
                String currentName = tmtServerThread.talkName;
                // this.send(Protocol.TALK_IN + Protocol.separator + tmtServerThread.talkName);
                this.send(Protocol.TALK_IN + Protocol.separator + currentName);
            }

            // 현재 서버에 입장한 클라이언트 스레드 추가하기
            tmtChatServer.globalList.add(this);
            this.broadCasting(msg);
        } catch (Exception e) {
            tmtChatServer.jta_log.append("Exception : " + e.toString() + "\n");
        }
    }

    // 현재 입장해 있는 친구들 모두에게 메시지 전송하기
    public void broadCasting(String msg) {
        synchronized (this) {
            for (TmtServerThread tmtServerThread : tmtChatServer.globalList) {
                tmtServerThread.send(msg);
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
                tmtChatServer.jta_log.append(msg + "\n");
                tmtChatServer.jta_log.setCaretPosition(tmtChatServer.jta_log.getDocument().getLength());
                StringTokenizer st = null;
                int protocol = 0;// 100|200|201|202|500
               log.info(msg);
                // JOptionPane.showMessageDialog(tmtChatServer, msg);
                if (msg != null) {
                    st = new StringTokenizer(msg, "#");
                    protocol = Integer.parseInt(st.nextToken());// 100
                }
                // JOptionPane.showMessageDialog(tmtChatServer, protocol);
                switch (protocol) {
                    case Protocol.MESSAGE: {
                        // JOptionPane.showMessageDialog(tmtChatServer, "200");
                        String talkName = st.nextToken();
                        String message = st.nextToken();
                        broadCasting(Protocol.MESSAGE + Protocol.separator + talkName + Protocol.separator + message);
                    }
                        break;
                    case Protocol.PROOM_IN: { //입장한 사실을 알림
                        String talkName = st.nextToken();// tomato
                        String otherName = st.nextToken();// kiwi
                        String menu = st.nextToken();// 1대1 -> 프로토콜 210|tomato|kiwi|1대1
                        // 귓속말로 보내진 메시지
                       // String msg1 = st.nextToken();

                        // 2023.01.30 수정 부분
                        // 중간 Protocol.separator에서 상대에게 보낸메세지와 나에게 보낸 메세지가
                        // 동일한 메세지로 받아 상대와 나가 이름이 같게되어 문제
                        // 메세지 보낼때 상대에게는 내이름 나에겐 상대이름이 되게 해야해서 otherName, talkName 수정
                        for (TmtServerThread cst : tmtChatServer.globalList) {
                            if (otherName.equals(cst.talkName)) {// 상대에게 보내기
                                cst.send(Protocol.PROOM_IN + Protocol.separator + otherName + Protocol.separator
                                + talkName
                                + Protocol.separator + menu);
                                // cst.send(Protocol.PROOM_IN + Protocol.separator + talkName + Protocol.separator
                                //         + otherName
                                //         + Protocol.separator + menu);
                                // break;
                            }
                        } // end of for
                          // 내가 한 말을 내게 보냄
                      
                        send(Protocol.PROOM_IN + Protocol.separator + talkName + Protocol.separator +otherName+Protocol.separator+ menu);
                    }
                        break;
                    case Protocol.PROOM_MSG: { // 1:1대화
                        String talkName = st.nextToken();// tomato
                        String otherName = st.nextToken();// kiwi
                        // 1:1대화로 보내진 메시지 - TmtChatForm의 actionPerformed에서 날아온 메세지
                        String msg2 = st.nextToken();
                        for (TmtServerThread cst : tmtChatServer.globalList) {
                             // 2023.01.30 수정 부분
                            // talkName.equals(cst.talkName) 추가
                            if (otherName.equals(cst.talkName)|| talkName.equals(cst.talkName)) {// 상대에게 보내기
                                cst.send(Protocol.PROOM_MSG + Protocol.separator + talkName 
                                        + Protocol.separator + msg2);
                            }
                        } // end of for
//                           내가 한 말을 내게 보냄
//                        send(Protocol.PROOM_MSG + Protocol.separator + talkName 
//                                + Protocol.separator + msg2);
                    }
                        break;

                    /*
                     * case Protocol.WHISPER: {
                     * String talkName = st.nextToken();
                     * String otherName = st.nextToken();
                     * // 귓속말로 보내진 메시지
                     * String msg1 = st.nextToken();
                     * for (TmtServerThread cst : tmtChatServer.globalList) {
                     * if (otherName.equals(cst.talkName)) {// 상대에게 보내기
                     * cst.send(Protocol.WHISPER + Protocol.separator + talkName +
                     * Protocol.separator
                     * + otherName
                     * + Protocol.separator + msg1);
                     * break;
                     * }
                     * } // end of for
                     * // 내가 한 말을 내게 보냄
                     * this.send(Protocol.WHISPER + Protocol.separator + talkName +
                     * Protocol.separator + otherName
                     * + Protocol.separator + msg1);
                     * }
                     * break;
                     */
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
                        tmtChatServer.globalList.remove(this);
                        // broadCasting(Protocol.TALK_OUT + Protocol.separator + talkName);
                        String message = Protocol.TALK_OUT + Protocol.separator + talkName;
                        this.broadCasting(message);
                    }
                        break run_start;
                }///////////// end of switch
            } ///////////////// end of while
        } catch (Exception e) {
            log.error("server thread error :", e);
        }
    }
}