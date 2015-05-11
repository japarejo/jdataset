/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author José Antonio Parejo
 */
public class SimpleDataSet implements DataSet{
    private DataFormatSpecification generalDataSpec;
    Map<String,Column<?>> columns;
    private String rowIdentifier;
    /**
     * @return the generalDataSpec
     */
    public DataFormatSpecification getGeneralDataSpec() {
        return generalDataSpec;
    }

    /**
     * @param generalDataSpec the generalDataSpec to set
     */
    public void setGeneralDataSpec(DataFormatSpecification generalDataSpec) {
        this.generalDataSpec = generalDataSpec;
    }
    
    @Override
    public Collection<Column<?>> getColumns()
    {
        return columns.values();
    }
    
    @Override
    public <T> void addColumn(Class<T> type, String columnName) throws InvalidParameterException
    {
        
        if(columnName==null || columnName.equals(""))
            throw new InvalidParameterException("A columns must have a valid name");
        if(!columns.containsKey(columnName)){
            Column<T> column=(Column<T>)new ListColumn<T>(type,columnName,this);                            
            columns.put(columnName, column);
        }else
            throw new InvalidParameterException("The dataset already contains a column with name '"+columnName+"'");
    }
        
    @Override
    public <T> T get(int row, String columnName) {
        Column<T> column=(Column<T>)getColumn(columnName);
        return column.get(row);
    }

    @Override
    public <T> T get(int row, Column<T> column) {
       return column.get(row);
    }

    @Override
    public <T> void set(int row, Column<T> column, T value) {
        column.set(row,value);
    }

    @Override
    public Column<?> getColumn(String name) {
        return columns.get(name);
    }

    
       
    
    
    
}
