/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.util.ArrayList;
import java.util.List;

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
        data.set(rowIdentifier, value);
    }

    
    
}
