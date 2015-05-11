/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset;

import java.security.InvalidParameterException;
import java.util.Collection;





/**
 *
 * @author José Antonio Parejo
 */
public interface DataSet {
    <T> T get(int row, String column);
    <T> T get(int row, Column<T> column);
    <T> void set(int row, Column<T> column, T value);
    Collection<Column<?>> getColumns();
    <T> Column<T> getColumn(String name);    
    <T> void addColumn(Class<T> c,String name) throws InvalidParameterException;
    <T> void addColumn(Class<T> c,String name,Collection<? extends T> data) throws InvalidParameterException;
    DataFormatSpecification getGeneralDataSpec();    

    int getNRows();
}
