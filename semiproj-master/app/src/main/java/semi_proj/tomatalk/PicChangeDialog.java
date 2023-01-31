package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PicChangeDialog extends JDialog implements ActionListener {
    // 사진 변경 선언
    JDialog    picChangeDial  = new JDialog();
    JButton    jbtn_pcConfirm = new JButton( "확인" );
    JPanel     jp_pic         = new JPanel();
    boolean    isVisible      = false;
    EditDialog editDialog     = null;
    
    public PicChangeDialog( EditDialog ed ) {
        this.editDialog = ed;
    }
    
    PicChangeDialog() {
        System.out.println( "사진 변경 생성자 호출" );
        initDisplay();
    }
    
    public void initDisplay() {
        System.out.println( "사진 변경 화면 호출" );
        this.setLayout( null );
        this.setVisible( isVisible );
        this.setSize(300, 500 );
        this.add( jbtn_pcConfirm );
        jbtn_pcConfirm.setBounds( 50, 280, 200, 50 );
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object obj = e.getSource();
        
        if ( obj == jbtn_pcConfirm ) {
            System.out.println( "확인 버튼 클릭됨" );
        }
    }
}