/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import es.us.isa.jdataset.Column;
import es.us.isa.jdataset.DataSet;
import es.us.isa.jdataset.SimpleDataSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author José Antonio Parejo
 */
public class PlainTextLoader<X> extends AbstractFileTypeLoader{
    public static final String DEFAULT_VAR_NAME_PREFIX="Var";
    public static final Set<String> SUPPORTED_EXTENSIONS=Sets.newHashSet("txt");
    private String rowSeparator;
    private String columnSeparator;    
    Map<Integer,Function<String,?>> columnTypeConversors;
    Function<String,X> defaultConversor;            
    Boolean hasColumnHeaders;
    
    transient String[] headers;
    transient Map<String,Integer> headersIndexes;

    public PlainTextLoader(String rowSeparator, String columnSeparator,Boolean hasColumnHeaders)
    {
        this(rowSeparator,columnSeparator,hasColumnHeaders, new HashMap<Integer, Function<String, ?>>(),null);
    }
            
    
    
    public PlainTextLoader(String rowSeparator, String columnSeparator,Boolean hasColumnHeaders, Map<Integer, Function<String, ?>> columnTypeConversors, Function<String, X> defaultConversor) {
        this.rowSeparator = rowSeparator;
        this.columnSeparator = columnSeparator;
        this.columnTypeConversors = columnTypeConversors;        
        this.defaultConversor = defaultConversor;        
        this.hasColumnHeaders = hasColumnHeaders;    
    }
    
    private void createColumns(DataSet dataset,String[] firstRow,String[] secondRow) {
        this.headers=firstRow;
        Class<?> columnClass=null;
        headersIndexes=new HashMap<>();
        for(int i=0;i<firstRow.length;i++){   
            headersIndexes.put(firstRow[i],i);
            columnClass=extractColumnClass(firstRow[i],secondRow[i]);
            dataset.addColumn(columnClass, firstRow[i]);
        }
    }

    private Class<?> extractColumnClass(String header,String value) {
        Class<?> result=null;
        Function<String,?> function=columnTypeConversors.get(headersIndexes.get(header));
        if(function==null)
            function=defaultConversor;
        if(function!=null){
            result=(Class<?>) ((ParameterizedType) getClass()
                            .getGenericSuperclass()).getActualTypeArguments()[1];
        }else
            result=inferColumnClass(header,value);
        return result;
    }
    
    private Class<?> inferColumnClass(String header,String value) {        
        return inferClassFromValue(value);
    }
    
    private Class<?> inferClassFromValue(String value)
    {
        Class<?> result=null;
        if(Ints.tryParse(value)==null)
            result=Integer.class;
        else if(StringUtils.isNumeric(value))
            result=Double.class;
        else if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))
            result=Boolean.class;
        else
            result=String.class;
        return result;
    }

    private String[] generateDefaultColumnNames(String [] firstRow, String [] secondRow) {
        String [] result=new String[firstRow.length];
        boolean [] equality=new boolean[firstRow.length];
        int inequalities=0;
        for(int i=0;i<firstRow.length;i++)
        {
            equality[i]=(inferClassFromValue(firstRow[i]).equals(inferClassFromValue(secondRow[i])));
            if(!equality[i])
                inequalities++;
        }
        if(inequalities>0)
        {
            for(int i=0;i<firstRow.length;i++)
            {
                result[i]=DEFAULT_VAR_NAME_PREFIX+i;
            }
        }else{
            result=firstRow;
            this.hasColumnHeaders=Boolean.TRUE;
        }
        return result;
    }    

    private void parseLine(String line,int i,DataSet dataset) {
        String[] data=line.split(columnSeparator);
        Column column=null;
        Function<String,?> valueTypeConversor=null;        
        for(int j=0;j<data.length;j++)
        {
            column=dataset.getColumn(headers[j]);
            valueTypeConversor=columnTypeConversors.get(j);            
            if(valueTypeConversor==null){                
                valueTypeConversor=defaultConversor;
            }
            if(valueTypeConversor!=null)
                column.set(i, valueTypeConversor.apply(data[j]));
            else
                column.set(i, data[j]);
        }
    }

    @Override
    public DataSet load(InputStream is, String extension) throws IOException {
        DataSet result=new SimpleDataSet();        
        String content=IOUtils.toString(is);
        String[] lines=content.split(rowSeparator);        
        if(lines.length>1){            
            if(hasColumnHeaders!=null && hasColumnHeaders.booleanValue()){
                createColumns(result,lines[0].split(columnSeparator),lines[1].split(columnSeparator));
            }else{
                createColumns(result,generateDefaultColumnNames(lines[0].split(columnSeparator),lines[1].split(columnSeparator)),lines[1].split(columnSeparator));
            }
            int initialIndex=hasColumnHeaders?1:0;
            for(int i=initialIndex;i<lines.length;i++){
                parseLine(lines[i],i,result);
            }
        }
        return result;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return SUPPORTED_EXTENSIONS;
    }        
    
    
    
}
