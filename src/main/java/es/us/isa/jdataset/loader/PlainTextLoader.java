/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.base.Function;
import com.google.common.collect.Sets;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import es.us.isa.jdataset.Column;
import es.us.isa.jdataset.DataSet;
import es.us.isa.jdataset.SimpleDataSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Map<String,Function<String,?>> columnTypeConversors;
    Map<Class<?>,Function<String,?>> perTypeConversors;
    Function<String,X> defaultConversor;            
    Boolean hasColumnHeaders;
    
    transient String[] headers;
    transient Map<String,Integer> headersIndexes;

    public PlainTextLoader(String rowSeparator, String columnSeparator,Boolean hasColumnHeaders)
    {
        this(rowSeparator,columnSeparator,hasColumnHeaders, new HashMap<String, Function<String, ?>>(),null);
        this.defaultConversor=(Function<String,X>)generateDefaultValueConversor(String.class);
    }
            
    
    
    public PlainTextLoader(String rowSeparator, String columnSeparator,Boolean hasColumnHeaders, Map<String, Function<String, ?>> columnTypeConversors, Function<String, X> defaultConversor) {
        this.rowSeparator = rowSeparator;
        this.columnSeparator = columnSeparator;
        this.columnTypeConversors=columnTypeConversors;
        this.perTypeConversors = new HashMap<>();                
        this.hasColumnHeaders = hasColumnHeaders;    
        this.defaultConversor = defaultConversor;
        generateTypeConversors();
    }
    
    private void createColumns(DataSet dataset,String[] firstRow,String[] secondRow) {
        this.headers=firstRow;
        Class<?> columnClass=null;
        headersIndexes=new HashMap<>();
        for(int i=0;i<firstRow.length;i++){   
            headersIndexes.put(firstRow[i].trim(),i);
            columnClass=extractColumnClass(firstRow[i].trim(),secondRow[i].trim());
            dataset.addColumn(columnClass, firstRow[i].trim());
        }
    }

    private Class<?> extractColumnClass(String header,String value) {
        Class<?> result=null;
        /*Function<String,?> function=columnTypeConversors.get(headersIndexes.get(header));
        if(function==null)
            function=defaultConversor;
        if(function!=null){
            result=(Class<?>) ((ParameterizedType) function.getClass()
                            .getGenericSuperclass()).getActualTypeArguments()[1];
        }else*/
            result=inferColumnClass(header,value);
        return result;
    }
    
    private Class<?> inferColumnClass(String header,String value) {        
        return inferClassFromValue(value);
    }
    
    private Class<?> inferClassFromValue(String value)
    {
        Class<?> result=null;
        if(Ints.tryParse(value)!=null)
            result=Integer.class;
        else if(Doubles.tryParse(value)!=null || Doubles.tryParse(value.replace(",", "."))!=null)
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
        if(inequalities==0)
        {
            for(int i=0;i<firstRow.length;i++)
            {
                result[i]=DEFAULT_VAR_NAME_PREFIX+i;                
            }
            this.hasColumnHeaders=Boolean.FALSE;
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
            column=dataset.getColumn(headers[j].trim());
            valueTypeConversor=columnTypeConversors.get(column.getName());
            if(valueTypeConversor==null)
                valueTypeConversor=perTypeConversors.get(column.getType());            
            if(valueTypeConversor==null)                
                valueTypeConversor=defaultConversor;
            if(valueTypeConversor!=null)
                column.set(i, valueTypeConversor.apply(data[j]));
            else
                column.set(i, data[j]);
        }
    }

    @Override
    public DataSet load(InputStream is, String extension) throws IOException {
        DataSet result=new SimpleDataSet();        
        StringWriter writer = new StringWriter();
        IOUtils.copy(is,writer);
        String content=writer.toString();
        String[] lines=content.split(rowSeparator);        
        if(lines.length==1)
            lines=content.split("\n");
        if(lines.length>1){            
            if(hasColumnHeaders!=null && hasColumnHeaders.booleanValue()){
                createColumns(result,lines[0].split(columnSeparator),lines[1].split(columnSeparator));
            }else{
                createColumns(result,generateDefaultColumnNames(lines[0].split(columnSeparator),lines[1].split(columnSeparator)),lines[1].split(columnSeparator));
            }
            int initialIndex=hasColumnHeaders?1:0;
            for(int i=initialIndex;i<lines.length;i++){
                parseLine(lines[i],i-initialIndex,result);
            }
        }
        return result;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return SUPPORTED_EXTENSIONS;
    }        
    
    private static <X> Function<String,X> generateDefaultValueConversor(final Class<X> c)
    {
        
        return new Function<String, X>( ) {
            private final Class imageClass=c;
            
            @Override
            public X apply(String value) {
                X result=null;
                if(Integer.class.equals(imageClass)){
                    Integer intVal=Integer.parseInt(value);
                    result=(X)intVal;
                }else if(Double.class.equals(imageClass)){
                    Double doubleVal=Double.parseDouble(value);
                    result=(X)doubleVal;
                }else if(Boolean.class.equals(imageClass)){
                    Boolean booleanValue=Boolean.parseBoolean(value);
                    result=(X)booleanValue;
                }else if(imageClass.isEnum()){
                    try {
                        Method m=(imageClass.getMethod("", String.class));
                        result=(X)m.invoke(null, value);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(PlainTextLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(PlainTextLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(PlainTextLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(PlainTextLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(PlainTextLoader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(String.class.equals(imageClass)){
                    result=(X)value;
                }
                return result;
            }
        };
    }

    private void generateTypeConversors() {
        Function<String,Integer> integerConversor=new Function<String,Integer>() {
            @Override public Integer apply(String f) {
                return Integer.valueOf(f);
            }
        };
        perTypeConversors.put(Integer.class, integerConversor);
        Function<String,Double> doubleConversor=new Function<String,Double>() {
            @Override  public Double apply(String f) {
                Double result=null;
                result=Doubles.tryParse(f);
                if(result==null)
                    result=Double.parseDouble(f.replace(",", "."));
                return result;
            }
        };
        perTypeConversors.put(Double.class, doubleConversor);
        Function<String,Boolean> booleanConversor=new Function<String, Boolean>() {
            @Override public Boolean apply(String f) {
                return Boolean.valueOf(f);
            }
        };
        perTypeConversors.put(Boolean.class, booleanConversor);
    }
    
}
