/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.writer;

import es.us.isa.jdataset.DataSet;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author José Antonio Parejo
 */
public abstract class AbstractTextWriter implements TextWriter{    

    @Override
    public void write(DataSet dataset, OutputStream out) throws IOException{        
            String result=write(dataset);
            IOUtils.write(result, out);        
    }
    
}
