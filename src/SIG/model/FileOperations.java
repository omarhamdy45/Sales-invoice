
package SIG.model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import SIG.view.InvoiceFrame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class FileOperations {
    private InvoiceFrame frame;




    public FileOperations(InvoiceFrame frame) {
        this.frame = frame;
    }

   

    

 public ArrayList<sigHeader> readFile(){
     String headerPath;
    //items file name
     String itemPath;
        File headerFile ;
        File itemFile;
        List<String> header_Lines=null;
        List<String> item_Lines=null;
        int result;
        ArrayList<sigHeader> invArray = new ArrayList<>();
  
            JOptionPane.showMessageDialog(frame, "Please insert Headers File then Lines File"); 

            JFileChooser file = new JFileChooser();
            do{//do not close till the user chooses the right file
               result = file.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                headerFile = file.getSelectedFile();
                if(headerFile.getName().contains(".csv")){
                    headerPath=headerFile.getAbsolutePath();
                    break;
                }
                else{//display an error message
                  System.out.println("Wrong Headers File Format");
                 JOptionPane.showMessageDialog(frame, "Wrong Headers File Format please insert the correct file again");  
                }
            }
            }while(true);
            do{//do not close till the user chooses the right file
                result = file.showOpenDialog(frame);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    itemFile = file.getSelectedFile();
                    if(itemFile.getName().contains(".csv")){
                        itemPath= itemFile.getAbsolutePath();
                        break;
                    }
                    else{
                        //display an error message
                    System.out.println("Wrong Items File Format");
                 JOptionPane.showMessageDialog(frame, "Wrong Items File Format");
                }

                }
            }while(true);

            System.out.println(headerPath);
            System.out.println(itemPath);


               
            try {
                header_Lines = Files.lines(Paths.get(headerPath)).collect(Collectors.toList());
            } catch (IOException ex) {
                System.out.println("Wrong Headers File Path");
                 JOptionPane.showMessageDialog(frame, "Wrong Headers File Path");
            }

                
            try {
                item_Lines = Files.lines(Paths.get(itemPath)).collect(Collectors.toList());
            } catch (IOException ex) {
                System.out.println("Wrong Items File Path");
                 JOptionPane.showMessageDialog(frame, "Wrong Items File Patth");
            }
            
               
                
                if(header_Lines!=null && item_Lines !=null){
                for (String headerLine : header_Lines) {

                    String[] arr = headerLine.split(",");
                    String numToString = arr[0];
                    String date = arr[1];
                    String customerName = arr[2];
                    int num = Integer.parseInt(numToString);
                    //create new invoice and add it in  invArray
                    sigHeader invoice = new sigHeader(num, customerName, date);
                    invArray.add(invoice);
                    frame.getInvoices().add(invoice);
                   
                }

                for (String itemLine : item_Lines) {
                    String[] arr = itemLine.split(",");
                    int num = Integer.parseInt(arr[0]);
                    String name1 = arr[1];
                    double unitPrice = Double.parseDouble(arr[2]);
                    int quantity = Integer.parseInt(arr[3]);
                    //create new line and add it inside its invoice
                    sigHeader invoice=getInvoiceByNumber(num);
                    sigItem line = new sigItem(name1,quantity,unitPrice,invoice);
                    invoice.getItems().add(line);

                }
                }

                
                return invArray;        
            //}
        
        
 }

 

 public sigHeader getInvoiceByNumber(int num){
    for(sigHeader inv : frame.getInvoices()){
        if(inv.get_Num()==num){
            return inv;
        }
    } 
        return null;
}

 public void saveFile(ArrayList<sigHeader> headers) {


        String invoices = "";
        String items = "";
        File headerFile;
        File lineFile;
        int result;

        for(sigHeader header: headers){
            String headerLines = header.getInvoicesFromTabel();
            invoices=invoices+headerLines;
            invoices=invoices+"\n";
            
            for(sigItem item : header.getItems()){
                String itemFile = item.getItemsFromTabel();
                items = items+itemFile;
                items = items+"\n";
            }
        }

        
        JOptionPane.showMessageDialog(frame, "Kindly choose the Headers file then Lines file");
        JFileChooser file = new  JFileChooser();
        do{//donot break till the user choose the right format
        result = file.showSaveDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION){
            headerFile = file.getSelectedFile();
            if(headerFile.getName().contains(".csv")){//write only in the correct file
                FileWriter headFileWriter = null;
                try {
                    headFileWriter = new FileWriter(headerFile);
                    headFileWriter.write(invoices);
                    headFileWriter.flush();
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        headFileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }
                else{
                  System.out.println("Wrong Headers File Format");
                 JOptionPane.showMessageDialog(frame, "Wrong Headers File Format");  
                }
        }
        }while(true);
            
           
            do{
            result = file.showSaveDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION){
                lineFile= file.getSelectedFile();
                if(lineFile.getName().contains(".csv")){
                FileWriter lineFileWriter = null;
                    try {
                        lineFileWriter = new FileWriter(lineFile);
                        lineFileWriter.write(items);
                        lineFileWriter.flush();
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            lineFileWriter.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
            }
            else{
                System.out.println("Wrong Lines File Format");
                 JOptionPane.showMessageDialog(frame, "Wrong Lines File Format");   
            }
        }while(true);
       
        
}
}