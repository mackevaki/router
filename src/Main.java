import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    public Main() {
        super("Router");
        setLayout(new GridLayout(3,2,2,2));

        setBounds(550, 250, 800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new JLabel("<html><li><b><font size = 4> Введите команду: (show -all для вывода списка команд)</font></b>"));
        add(textField);
        //add(textArea);
        textArea.setLineWrap(true);
        add(status);
        textField.setCaretColor(Color.MAGENTA);
        textField.setText("$~ ");
        textField.addActionListener(this);


    }

    public JTextArea  textArea = new JTextArea(5,100);
    public JTextField textField = new JTextField(30);
    public JLabel status = new JLabel();
    void message(String msg) {
        status.setText(msg);
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        if (text == "$~ ") {
            message("Nothing to search");
            return;
        }

        textArea.append("ya panel"+ "\n");
        textArea.append("sled stroka"+ "\n");
        message(text);
        message("2 str");
        //textField.selectAll();


        //Make sure the new text is visible, even if there
        //was a selection in the text area.
    }

   /* input.addActionListener(new ButtonEventListener());

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "Button was pressed\n";
            message += "Text is " + input.getText() + "\n";
            message += (radio1.isSelected()?"Radio #1":"Radio #2")
                    + " is selected\n";
            message += "CheckBox is " + ((check.isSelected())
                    ?"checked":"unchecked");
            JOptionPane.showMessageDialog(null,
                    message,
                    "Output",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }*/

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main window = new Main();

                if (translucencySupported())
                    window.setOpacity(0.8f); // прозрачность

                window.setVisible(true);
            }
        });


        String[] incomingIps = {"192.168.0.1/24", "192.168.0.0/20", "192.130.0.1/16", "192.160.0.1/24", "192.168.0.1/24"};

        int j = 0;
        Node node[] = new Node[5];
        Node.nodeInfo[] info = new Node.nodeInfo[5];

        ArrayList[] routs = new ArrayList[5];

        for(Node el : node) {
            el = new Node();
            routs[j] = el.getRoats();
            info[j] = el.getNodeInfo(incomingIps[j]);
            j++;
        }

        RouteTable TABLE = new RouteTable();
        TABLE.putNodeInfo(info[0], routs[0]);
        TABLE.putNodeInfo(info[1], routs[1]);
        TABLE.putNodeInfo(info[2], routs[2]);
        TABLE.putNodeInfo(info[3], routs[3]);
        TABLE.putNodeInfo(info[4], routs[4]);

        String input = "11000000100000100000000000000001";
        System.out.println(input + ":   ");
        System.out.println(TABLE.getMatchingPrefix(input));

        input = "11000000101010000000000000000001";
        System.out.println(input + ":   ");
        System.out.println(TABLE.getMatchingPrefix(input));

        input = "1000010101";
        System.out.println(input + ":   ");
        System.out.println(TABLE.getMatchingPrefix(input));

        input = "basemexz";
        System.out.println(input + ":   ");
        System.out.println(TABLE.getMatchingPrefix(input));

        input = "xyz";
        System.out.println(input + ":   ");
        System.out.println(TABLE.getMatchingPrefix(input));
    }

    public static boolean translucencySupported() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        return gd.isWindowTranslucencySupported(
                GraphicsDevice.WindowTranslucency.TRANSLUCENT);
    }

}