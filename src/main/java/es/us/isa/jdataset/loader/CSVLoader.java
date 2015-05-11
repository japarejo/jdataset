/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

/**
 *
 * @author José Antonio Parejo
 */
public class CSVLoader<X> extends PlainTextLoader<X>{

    public CSVLoader(Boolean hasColumnHeaders) {
        super(System.getProperty("line.separator"), ";", hasColumnHeaders);
    }
    
}
