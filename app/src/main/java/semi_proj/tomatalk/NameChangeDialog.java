package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameChangeDialog extends JDialog implements ActionListener {
    // 이름 변경 선언
    JDialog NameChangeDial = new JDialog();
    JButton jbtn_nameConfirm = new JButton("확인");
    JPanel jp_name = new JPanel();
    boolean isVisible = false;
    EditDialog editDialog = null;

    public NameChangeDialog(EditDialog ed) {
        this.editDialog = ed;
    }

    NameChangeDialog() {
        System.out.println("이름 변경 생성자 호출");
        initDisplay();
    }

    public void initDisplay() {
        System.out.println("이름 변경 화면 호출");
        this.setLayout(null);
        this.setVisible(isVisible);
        this.setSize(300, 500);
        this.add(jbtn_nameConfirm);
        jbtn_nameConfirm.setBounds(50, 280, 200, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == jbtn_nameConfirm) {
            System.out.println("확인 버튼 클릭됨");
        }
    }
}