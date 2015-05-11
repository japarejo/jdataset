/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author José Antonio Parejo
 */
public class PlainTextLoaderTest {
    
    public PlainTextLoaderTest() {
    }

    /**
     * Test of load method, of class PlainTextLoader.
     */
    @Test
    public void testLoad() throws Exception {
        
    }

    /**
     * Test of getSupportedExtensions method, of class PlainTextLoader.
     */
    @Test
    public void testGetSupportedExtensions() {
        Set<String> expectedExtensions=Sets.newHashSet("txt");
        PlainTextLoader ptl=new PlainTextLoader(null, null, true);
        asserEquals(expectedExtensions,ptl.getSupportedExtensions());
    }

    private void asserEquals(Set<String> expectedExtensions, Set supportedExtensions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
