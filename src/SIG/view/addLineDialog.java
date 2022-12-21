
package SIG.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class addLineDialog extends JDialog {
    private JTextField item_Name;
    private JTextField Unit_Price;
    private JTextField Quantity;
    private JLabel item_NameLabel;
    private JLabel Unit_PriceLabel;
    private JLabel Quantity_Label;
    private JButton ok;
    private JButton cancel;

    public addLineDialog(InvoiceFrame frame) {
        item_NameLabel= new JLabel("Item Name:");
        item_Name = new JTextField(40);
        Unit_PriceLabel = new JLabel("Unit Price:");
        Unit_Price = new JTextField(40);
        Quantity_Label = new JLabel("Quantity:");
        Quantity = new JTextField(40);
        ok= new JButton("OK");
        cancel = new JButton("Cancel");
        ok.setActionCommand("createLine");
        cancel.setActionCommand("cancelLine");
        ok.addActionListener(frame.getController());
        cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 4));
        add(item_NameLabel);
        add(item_Name);
        add(Unit_PriceLabel);
        add(Unit_Price);
        add(Quantity_Label);
        add(Quantity);
        add(ok);
        add(cancel);
        
        pack();
    }

    public JTextField getItemName() {
        return item_Name;
    }

    public JTextField getUnitPrice() {
        return Unit_Price;
    }

    public JTextField getQuantity() {
        return Quantity;
    }
    
}
