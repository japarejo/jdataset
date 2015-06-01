/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.util.Objects;
import java.util.List;

/**
 *
 * @author José Antonio Parejo
 * @param <T> Type of the objects contained in the column
 */
public abstract class Column<T extends Object> implements List<T>{
    protected Class<T> type;
    protected DataSet dataSet;
    protected String name;
    private DataFormatSpecification<T> particularDataFormatSpec;

    public Column(Class<T> type,  String name,DataSet dataSet, DataFormatSpecification<T> particularDataFormatSpec) {
        this.type = type;
        this.dataSet = dataSet;
        this.name = name;
        this.particularDataFormatSpec = particularDataFormatSpec;
    }
    
    
    
    protected Column(Class<T> type, String name, DataSet dataset)
    {
        this(type,name,dataset,null);
    }
    
    public Class<T> getType(){
        return type;
    }
    
    public boolean equals(Object obj) {
        boolean result=false;
        if(obj instanceof Column){
            Column<T> mycolumn=(Column<T>)obj;
            result=name.equals(mycolumn.name);
        }
        return result;
    }

    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
            
    @Override
    public abstract T get(int rowIdentifier);
    public abstract int getIndexOf(T object);
    @Override
    public abstract T set(int rowIdentifier, T value);    
    @Override
    public abstract int size();
    
    
}
