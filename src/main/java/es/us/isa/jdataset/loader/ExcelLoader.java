/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Sets;
import es.us.isa.jdataset.DataSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author José Antonio Parejo
 */
public class ExcelLoader extends AbstractFileTypeLoader {
    public static final Set<String> SUPPORTED_EXTENSIONS=Sets.newHashSet("xls","xlsx");    
    private CSVLoader csvLoader=new CSVLoader(null);
    @Override
    public DataSet load(InputStream is, String extension)  throws IOException{
        try {
            StringBuilder sb=new StringBuilder();
            ExcelToCSV excel2CSV=new ExcelToCSV();
            excel2CSV.convertExcelToCSV(is, sb);
            return csvLoader.load(new ReaderInputStream(new StringReader(sb.toString())),extension);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExcelLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ExcelLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        }
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return SUPPORTED_EXTENSIONS;
    }
    
}
