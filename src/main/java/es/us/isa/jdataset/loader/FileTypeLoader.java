/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import java.util.Set;

/**
 *
 * @author José Antonio Parejo
 */
public interface FileTypeLoader extends Loader {
   Set<String> getSupportedExtensions();
}
