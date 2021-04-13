/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.writer;

import es.us.isa.jdataset.DataSet;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author japarejo
 */
public interface Writer {    
    void write(DataSet dataset, OutputStream output)  throws IOException;
}
