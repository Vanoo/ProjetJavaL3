package simulation;

import java.awt.Frame;
import java.util.StringTokenizer;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;
/**
 * 
 * @Javadoc
 * 
 *
 */
public class IPTextFieldVerifier extends InputVerifier 
{
/* (non-Javadoc)
 * @see javax.swing.InputVerifier#verify(javax.swing.JComponent)
 */
public boolean verify(JComponent input) 
   {
      if (input instanceof JFormattedTextField) 
      {
         JFormattedTextField ftf = (JFormattedTextField)input;
         AbstractFormatter formatter = ftf.getFormatter();
         if (formatter != null) 
         {
            String text = ftf.getText();
            StringTokenizer st = new StringTokenizer(text, ".");
            while (st.hasMoreTokens()) 
            {
               int value = Integer.parseInt((String) st.nextToken());
               if (value < 0 || value > 255) 
               {
                  // to prevent recursive calling of the
                  // InputVerifier, set it to null and
                  // restore it after the JOptionPane has
                  // been clicked.
                  input.setInputVerifier(null);
                  JOptionPane.showMessageDialog(new Frame(), "Malformed IP Address!", "Error",JOptionPane.ERROR_MESSAGE);
                  input.setInputVerifier(this); 
                  return false;
               }
            }
            return true;
         }
      }
      return true;
   }
}
 /*
   public boolean shouldYieldFocus(JComponent input) 
   {
      return verify(input);
   }
}

final JTextField normalTf = new JTextField(25);
JButton button = new JButton("Get value");
button.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
      normalTf.setText(""+formattedTf.getValue());
   }
});

getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
getContentPane().add(formattedTf);
getContentPane().add(button);
*/