/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.logging.Logger;

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
    public T set(int rowIdentifier, T value) {
        while(rowIdentifier>data.size()-1)
            data.add(null);
        data.set(rowIdentifier, value);
        return value;
    }    

    @Override
    public int size() {
        return data.size();
    }

    

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return data.toArray(a);
    }

    @Override
    public boolean add(T e) {
        return data.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return data.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return data.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return data.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return data.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return data.retainAll(c);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public void add(int index, T element) {
        data.add(index, element);
    }

    @Override
    public T remove(int index) {
        return data.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return data.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return data.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return data.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return data.listIterator();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return data.subList(fromIndex, toIndex);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListColumn<?> other = (ListColumn<?>) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.data)+super.hashCode();
        return hash;
    }
    
    
    
    
}
