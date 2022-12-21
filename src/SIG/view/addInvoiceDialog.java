
package SIG.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class addInvoiceDialog extends JDialog{
    private JTextField customer_Name;
    private JTextField invoice_Date;
    private JLabel customer_NameLabel;
    private JLabel date_Label;
    private JButton ok;
    private JButton cancel;

    public addInvoiceDialog(InvoiceFrame frame) {
        customer_NameLabel= new JLabel("Customer Name:");
        customer_Name = new JTextField(30);
        date_Label = new JLabel("Date:");
        invoice_Date = new JTextField(30);
        ok= new JButton("OK");
        cancel = new JButton("Cancel");
        ok.setActionCommand("createInvoice");
        cancel.setActionCommand("cancelInvoice");
        ok.addActionListener(frame.getController());
        cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 4));
        add(customer_NameLabel);
        add(customer_Name);
        add(date_Label);
        add(invoice_Date);
        add(ok);
        add(cancel);
        
        
        pack();
        
        
    }

    public JTextField getCustomerName() {
        return customer_Name;
    }

    public JTextField getInvoiceDate() {
        return invoice_Date;
    }
    
    
}
