
package SIG.controller;

import SIG.model.ShowLineTabel;
import SIG.model.sigHeader;
import SIG.model.sigItem;
import SIG.view.InvoiceFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableAction implements ListSelectionListener{
    private InvoiceFrame frame;

    public TableAction(InvoiceFrame frame) {
        this.frame = frame;
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int invoiceIndex = frame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceIndex!= -1){
             sigHeader selectedRow = frame.getInvoices().get(invoiceIndex);
             ArrayList<sigItem> items = selectedRow.getItems();
             frame.getLabelCustomerName().setText(selectedRow.get_Name());
             frame.getLabelInvoiceNum().setText(""+selectedRow.get_Num());
             frame.getLabelInvoiceDate().setText(selectedRow.get_Date());
             frame.getLabelTostalCost().setText(""+selectedRow.getTotalInvoice());
             ShowLineTabel line = new ShowLineTabel(items);
             frame.getTableInvoiceLines().setModel(line);
             line.fireTableDataChanged();
             
        }
    }
    
}
