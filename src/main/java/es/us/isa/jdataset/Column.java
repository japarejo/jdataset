/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.util.Objects;

/**
 *
 * @author José Antonio Parejo
 */
public abstract class Column<T extends Object> {
    protected Class<T> type;
    protected DataSet dataSet;
    protected String name;
    private DataFormatSpecification particularDataFormatSpec;

    public Column(Class<T> type,  String name,DataSet dataSet, DataFormatSpecification particularDataFormatSpec) {
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
    
    @Override
    public boolean equals(Object obj) {
        boolean result=false;
        if(obj instanceof Column){
            Column<T> mycolumn=(Column<T>)obj;
            result=name.equals(mycolumn.name);
        }
        return result;
    }

    @Override
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
            
    public abstract T get(int rowIdentifier);
    public abstract int getIndexOf(T object);
    public abstract void set(int rowIdentifier, T value);    
    
    
}
