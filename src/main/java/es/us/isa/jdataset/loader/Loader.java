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
import java.net.URISyntaxException;

/**
 *
 * @author José Antonio Parejo
 */
public interface Loader {
    DataSet load(String url) throws MalformedURLException,IOException;
    DataSet load(InputStream is, String extension) throws IOException;
}
