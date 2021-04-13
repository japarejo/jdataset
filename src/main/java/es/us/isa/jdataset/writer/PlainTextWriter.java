/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.writer;

/**
 *
 * @author José Antonio Parejo
 */
public class PlainTextWriter extends CSVWriter{

    public PlainTextWriter(String columnSeparator, String rowSeparator) {
        super();
        this.columnSeparator=columnSeparator;
        this.rowSeparator=rowSeparator;
    }

    public String getColumnSeparator() {
        return columnSeparator;
    }

    public void setColumnSeparator(String columnSeparator) {
        this.columnSeparator = columnSeparator;
    }
    
    

    public String getRowSeparator() {
        return rowSeparator;
    }

    public void setRowSeparator(String rowSeparator) {
        this.rowSeparator = rowSeparator;
    }
                     
}
