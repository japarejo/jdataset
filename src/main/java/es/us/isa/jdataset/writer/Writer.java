/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.writer;

import es.us.isa.jdataset.DataSet;

/**
 *
 * @author japarejo
 */
public interface Writer {
    String write(DataSet dataSet);
}
