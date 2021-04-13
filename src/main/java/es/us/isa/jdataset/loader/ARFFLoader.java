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
import java.util.Set;

/**
 *
 * @author José Antonio Parejo
 */
public class ARFFLoader extends AbstractFileTypeLoader{

    public final static Set<String> SUPPORTED_EXTENSIONS=Sets.newHashSet("arff");
    
    @Override
    public Set<String> getSupportedExtensions() {
        return SUPPORTED_EXTENSIONS;
    }

    
    @Override
    public DataSet load(InputStream is,  String extension)  throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
