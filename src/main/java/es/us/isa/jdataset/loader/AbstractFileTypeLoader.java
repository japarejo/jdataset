/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import es.us.isa.jdataset.DataSet;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio Parejo
 */
public abstract class AbstractFileTypeLoader implements FileTypeLoader {
    
    @Override
    public DataSet load(String url) throws MalformedURLException,IOException {
        String extension=url.substring(url.lastIndexOf(".")+1);        
        URL uri= new URL(url);                
        return load(new BufferedInputStream(uri.openStream()),extension);        
    }
    
}
