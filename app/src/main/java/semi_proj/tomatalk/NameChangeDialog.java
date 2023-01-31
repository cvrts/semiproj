package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class NameChangeDialog extends JDialog implements ActionListener, MouseListener {
    // 이름 변경 선언
    JDialog    NameChangeDial   = new JDialog();
    JButton    jbtn_nameConfirm = new JButton( "확인" );
    JPanel     jp_name          = new JPanel();
    boolean    isVisible        = false;
    EditDialog editDialog       = null;
    JTextField jtf_changeInput  = new JTextField( "여기에 변경할 닉네임을 입력" );
    MemberVO   memVO            = new MemberVO();
    ChatDao    dao              = new ChatDao();

    public NameChangeDialog( EditDialog ed ) {
        this.editDialog = ed;
    }
    
    NameChangeDialog() {
        System.out.println( "이름 변경 생성자 호출" );
        initDisplay();
    }
    
    public void initDisplay() {
        System.out.println( "이름 변경 화면 호출" );
        this.setLayout( null );
        this.setVisible( isVisible );
        this.setSize( 300, 170 );
        this.add( jbtn_nameConfirm );
        this.add( jtf_changeInput );
        jbtn_nameConfirm.addActionListener(this);
        jtf_changeInput.addMouseListener( this );
        jtf_changeInput.setBounds( 50, 30, 200, 30 );
        jbtn_nameConfirm.setBounds( 50, 70, 200, 40 );
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object obj = e.getSource();
        
        if ( obj == jbtn_nameConfirm ) {
            System.out.println( "확인 버튼 클릭됨(NameChange)" ); //확인버튼 동작 확인
            if(dao.nameCheck(memVO.getNamec()) == 1) { //닉네임이 중복되는지 확인함!
                System.out.println( "닉네임 변경 진입 성공 / 입력한 닉네임 => " + memVO.getNamec() ); //잘 들어갔나 확인하기 위해 출력
                memVO.setNamec( jtf_changeInput.getText() ); //바꿀 닉네임을 memVO에 넣어줌
                dao.nameChange(memVO.getNamec(), memVO.getName()); //nameChange(변경할 닉네임, 기존닉네임) 으로 닉네임 변경 메소드 실행
            } else {
                System.out.println( "닉네임 진입 실패" );
//                JOptionPane.showConfirmDialog( this, "닉네임 변경에 실패했습니다." );
            }
            dispose();//위 과정 끝나고 창 꺼짐.                
        }
    }
    
    @Override
    public void mouseClicked( MouseEvent e ) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void mousePressed( MouseEvent e ) {
        Object obj = e.getSource();
        
        if ( obj == jtf_changeInput ) {
            jtf_changeInput.setText( "" );
        }
    }
    
    @Override
    public void mouseReleased( MouseEvent e ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void mouseEntered( MouseEvent e ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void mouseExited( MouseEvent e ) {
        // TODO Auto-generated method stub
        
    }
}