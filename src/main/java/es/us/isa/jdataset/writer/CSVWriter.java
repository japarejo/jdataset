/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.writer;

import es.us.isa.jdataset.Column;
import es.us.isa.jdataset.DataSet;

/**
 *
 * @author José Antonio Parejo
 */
public class CSVWriter extends AbstractTextWriter{

    protected String rowSeparator;
    protected String columnSeparator;
    
    public CSVWriter()
    {
        this.columnSeparator=";";
        this.rowSeparator=System.getProperty("line.separator");
    }
    
    
    @Override
    public String write(DataSet dataSet) {
        StringBuilder sb=new StringBuilder();
        writeHeaders(dataSet,sb);
        writeData(dataSet,sb);
        return sb.toString();
                
    }

    private void writeHeaders(DataSet dataSet, StringBuilder sb) {
        int i=0;
        int nColumns=dataSet.getColumns().size();
        for(Column<?> column:dataSet.getColumns())
        {
            sb.append(column.getName());
            if(i!=nColumns-1)
                sb.append(columnSeparator);
            i++;
        }
        sb.append(rowSeparator);
    }

    private void writeData(DataSet dataSet, StringBuilder sb) {
        for(int i=0;i<dataSet.getNRows();i++){
            writeLine(i,dataSet,sb);
            
        }
    }

    private void writeLine(int index, DataSet dataSet, StringBuilder sb) {
        int i=0;
        int nColumns=dataSet.getColumns().size();
        for(Column<?> column:dataSet.getColumns())
        {
            sb.append(column.get(index));
            if(i!=nColumns-1)
                sb.append(";");
            i++;
        }
        sb.append(System.getProperty("line.separator"));;
    }
    
}
