/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author José Antonio Parejo
 */
public class ListColumn<T> extends Column<T>{

    List<T> data;

    public ListColumn(Class<T> type, String name, DataSet dataSet)
    {
        this(type,name,dataSet,null);
    }
    public ListColumn(Class<T> type, String name, DataSet dataSet, DataFormatSpecification particularDataFormatSpec) {
        super(type, name, dataSet, particularDataFormatSpec);
        data=new ArrayList<>();
    }          
    
    @Override
    public T get(int i) {
        return data.get(i);
    }        

    @Override
    public int getIndexOf(T object) {
        return data.indexOf(object);
    }

    @Override
    public void set(int rowIdentifier, T value) {
        while(rowIdentifier>data.size()-1)
            data.add(null);
        data.set(rowIdentifier, value);
        
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public int size() {
        return data.size();
    }

    
    
}
