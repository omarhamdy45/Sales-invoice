package SIG.controller;

import SIG.model.FileOperations;
import SIG.model.ShowInvTabel;
import SIG.model.ShowLineTabel;
import SIG.model.sigHeader;
import SIG.model.sigItem;
import SIG.view.InvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import SIG.view.addInvoiceDialog;
import SIG.view.addLineDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener {

    private InvoiceFrame frame;
    private addInvoiceDialog inv_Dialog;
    private addLineDialog item_Dialog;

    public Controller(InvoiceFrame frame) {
        this.frame = frame;

    }

    @Override

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "New Invoice":
                newInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "New Line":
                NewLine();
                break;
            case "Delete Line":
                DeleteLine();
                break;
                   
            case "createInvoice":
                AddInvOk();
                break;
                
            case "cancel Invoice":
                CancelInvoice();
                break;
            case "create Line":
                CreateLine();
                break;
            case "cancel Line":
                CancelLine();
                break;  
                
            case "Open File":

                frame.setInvoices(null);
                FileOperations fileOperations = new FileOperations(frame);
                ArrayList<sigHeader> inv= fileOperations.readFile();
                frame.setInvoices(inv);
                ShowInvTabel invoiceTable = new ShowInvTabel(inv);
                frame.setHeaderTabel(invoiceTable);
                frame.getTableInvoiceHeader().setModel(invoiceTable);
                frame.getHeaderTabel().fireTableDataChanged();
           
                break;

            case "Save File":

                FileOperations fileOperations1 = new FileOperations(frame);

            
                fileOperations1.saveFile(frame.getInvoices());
            
            
                break;

                
        }
    }

    private void newInvoice() {
        inv_Dialog = new addInvoiceDialog(frame);
        inv_Dialog.setVisible(true);

    }

    private void deleteInvoice() {
         int row = frame.getTableInvoiceHeader().getSelectedRow();
        if(row!= -1){
            frame.getInvoices().remove(row);
            frame.getHeaderTabel().fireTableDataChanged();
            
        }
    }

    private void NewLine() {
        item_Dialog = new addLineDialog(frame);
        item_Dialog.setVisible(true);
        
    }

    private void DeleteLine() {
        int invoiceSelected= frame.getTableInvoiceHeader().getSelectedRow();
          int row = frame.getTableInvoiceLines().getSelectedRow();

        if((invoiceSelected!=-1) && (row!= -1)){
            sigHeader invoice = frame.getInvoices().get(invoiceSelected);
            invoice.getItems().remove(row);
            frame.getHeaderTabel().fireTableDataChanged();
            ShowLineTabel line = new ShowLineTabel(invoice.getItems());
            frame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();
    }
    }


    public void AddInvOk() {
      String date= inv_Dialog.getInvoiceDate().getText();
      String customer = inv_Dialog.getCustomerName().getText();
      //get the total invoices number
      int number= frame.getTotalInvNum();
      number++;
        sigHeader newInvoice = new sigHeader(number,customer,date);
        frame.getInvoices().add(newInvoice);
        frame.getHeaderTabel().fireTableDataChanged();

        inv_Dialog.setVisible(false);
        inv_Dialog.dispose();
        inv_Dialog=null;
        
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void CancelInvoice() {
        inv_Dialog.setVisible(false);
        inv_Dialog.dispose();
        inv_Dialog=null;
    }

    private void CreateLine() {
        
      int invoiceSelected= frame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceSelected!=-1){
            sigHeader invoice = frame.getInvoices().get(invoiceSelected);
            String item= item_Dialog.getItemName().getText();
            String unitPrice = item_Dialog.getUnitPrice().getText();
            String quantity = item_Dialog.getQuantity().getText();
            double itemUnitPrice = Double.parseDouble(unitPrice);
            int itemQuantity = Integer.parseInt(quantity);
            sigItem newLine = new sigItem(item,itemQuantity,itemUnitPrice,invoice);
            invoice.getItems().add(newLine);
            ShowLineTabel line = new ShowLineTabel(invoice.getItems());
            frame.getHeaderTabel().fireTableDataChanged();
            frame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();

        }
        item_Dialog.setVisible(false);
        item_Dialog.dispose();
        item_Dialog=null;
        
    }

    private void CancelLine() {
        item_Dialog.setVisible(false);
        item_Dialog.dispose();
        item_Dialog=null;
    }
}
