package semi_proj.tomatalk;

import javax.swing.JDialog;

public class ChatWhisper extends JDialog {
  public ChatWhisper() {
    initDisplay();
  }

  public void initDisplay() {
    this.setTitle("1:1대화창");
    this.setSize(400, 500);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new ChatWhisper();
  }

}
