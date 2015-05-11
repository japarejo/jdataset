/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author José Antonio Parejo
 */
public class SimpleDataSet implements DataSet{
    
    private DataFormatSpecification generalDataSpec;
    Map<String,Column<?>> columns;
    private String rowIdentifier;

    public SimpleDataSet() {
        columns=new HashMap<>();
    }
    
    
    
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
    public <T> Column<T> getColumn(String name) {
        return (Column<T>)columns.get(name);
    }

    @Override
    public <T> void addColumn(Class<T> c, String name, Collection<? extends T> data) throws InvalidParameterException {
        addColumn(c,name);
        Column<T> column=this.<T>getColumn(name);
        int i=0;
        for(T datum:data){
            column.set(i, datum);
            i++;
        }
    }                   

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.generalDataSpec);
        hash = 97 * hash + Objects.hashCode(this.columns);
        hash = 97 * hash + Objects.hashCode(this.rowIdentifier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleDataSet other = (SimpleDataSet) obj;
        if (!Objects.equals(this.generalDataSpec, other.generalDataSpec)) {
            return false;
        }
        if (!Objects.equals(this.columns, other.columns)) {
            return false;
        }
        if (!Objects.equals(this.rowIdentifier, other.rowIdentifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int getNRows() {        
        if(columns.isEmpty())
            return 0;
        return columns.values().iterator().next().size();
    }
    
    
    
}
