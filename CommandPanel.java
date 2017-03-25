package sprint_Three;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class CommandPanel extends JPanel  {
    
    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 14;
    String command;
    
    private JTextField commandField = new JTextField(); 
    private LinkedList<String> commandBuffer = new LinkedList<String>();
    
    CommandPanel () {
        class AddActionListener implements ActionListener {
               public void actionPerformed(ActionEvent event)   {
                   if(commandField.getText().isEmpty())
                   {
                     commandField.setText("");
                   }
                   else
                   {
                     synchronized (commandBuffer) {
                       commandBuffer.add(commandField.getText());
                       commandField.setText("");
                       commandBuffer.notify();
                     }
                   }
               }
           }
        
        ActionListener listener = new AddActionListener();
        commandField.addActionListener(listener);
        commandField.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
        setLayout(new BorderLayout());
        add(commandField, BorderLayout.CENTER);

    setBorder(BorderFactory.createLineBorder(Color.BLACK));
   
    setBounds(800, 630, 500, 50); // specifies the desired coordinates of the panel
                                               // being added to the layered pane
    }
    
    
    
    public String getCommand() {
        synchronized (commandBuffer) {
            while (commandBuffer.isEmpty()) {
                try {
                    commandBuffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            command = commandBuffer.pop();
        }
        
        return command;
    }
    
//    public String getString()
//    {
//      return command;
//    }
     
}