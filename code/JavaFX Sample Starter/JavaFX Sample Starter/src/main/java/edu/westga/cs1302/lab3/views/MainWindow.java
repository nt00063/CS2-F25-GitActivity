package edu.westga.cs1302.lab3.views;

import edu.westga.cs1302.lab3.model.Bill;
import edu.westga.cs1302.lab3.model.BillItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	
	 @FXML
	    private TextArea bill;

	    @FXML
	    private TextField itemname;

	    @FXML
	    private TextField itemprice;
	    
	    private Bill currentBill;
	    private BillView billView;

	    @FXML
	    void additem(ActionEvent event) {
	    	String name = this.itemname.getText();
	    	double price = Double.parseDouble(this.itemprice.getText());
	    	
	    	BillItem item = new BillItem(name, price);
	    	this.currentBill.addItem(item);
	    	
	    	String billText = this.billView.getText(this.currentBill);
	    	this.bill.setText(billText);
	    }
    
    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
    public void initialize() {
    	
    	this.currentBill = new Bill();
    	this.billView = new BillView();
    }
}
