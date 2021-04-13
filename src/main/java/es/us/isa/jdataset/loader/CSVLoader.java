/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 *
 * @author José Antonio Parejo
 */
public class CSVLoader<X> extends PlainTextLoader<X>{

    public static Set<String> SUPPORTED_EXTENSIONS=Sets.newHashSet("csv");
    public CSVLoader(Boolean hasColumnHeaders) {
        super(System.getProperty("line.separator"), ";", hasColumnHeaders);
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return SUPPORTED_EXTENSIONS;
    }
    
    
}
