/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import es.us.isa.jdataset.DataSet;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio Parejo
 */
public class CompositeFileTypeLoader implements FileTypeLoader {

    Set<FileTypeLoader> loaders;
            
    @Override
    public Set<String> getSupportedExtensions() {
        HashSet<String> result=new HashSet<String>();
        for(FileTypeLoader loader:loaders)
            result.addAll(loader.getSupportedExtensions());
        return result;
    }

    

    @Override
    public DataSet load(InputStream is, String extension) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSet load(String url) throws MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
