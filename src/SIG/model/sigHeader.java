

package SIG.model;

import java.util.ArrayList;
import java.util.Iterator;

public class sigHeader {
    private int num;
    private String date;
    private String name;
    private ArrayList<sigItem> items;

    public sigHeader(int num, String name, String date) {
        this.num = num;
        this.name = name;
        this.date = date;
    }

    public double getTotalInvoice() {
        double total = 0.0;

        sigItem item;
        for(Iterator var3 = this.getItems().iterator(); var3.hasNext(); total += item.getTotalLine()) {
            item = (sigItem)var3.next();
        }

        return total;
    }

    public int get_Num() {
        return this.num;
    }


    public String get_Name() {
        return this.name;
    }

    public void set_Name(String name) {
        this.name = name;
    }

    public String get_Date() {
        return this.date;
    }



    public ArrayList<sigItem> getItems() {
        if (this.items == null) {
            this.items = new ArrayList();
        }

        return this.items;
    }

    public String toString() {
        return "sigHeader{num=" + this.num + ", date=" + this.date + ", name=" + this.name + ", items=" + this.items + "}";
    }



    public String getInvoicesFromTabel() {
        return this.num + "," + this.date + "," + this.name;
    }
}
