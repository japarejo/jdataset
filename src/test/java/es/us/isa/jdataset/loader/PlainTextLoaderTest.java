/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import es.us.isa.jdataset.DataSet;
import es.us.isa.jdataset.SimpleDataSet;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author José Antonio Parejo
 */
public class PlainTextLoaderTest extends LoaderTest {
    
    public PlainTextLoaderTest(){
    }

    /**
     * Test of load method, of class PlainTextLoader.
     */
    @Test
    public void testLoad() throws Exception {
        PlainTextLoader ptl=new PlainTextLoader(System.getProperty("line.separator"), ",", true);       
        DataSet expectedResult=createHeightsDataSet();
        File f=new File("src/test/resources/sampleFiles/HeightSample.txt");
        DataSet result=ptl.load(new FileInputStream(f), ".txt");
        assertEquals(expectedResult,result);
        
    }

    /**
     * Test of getSupportedExtensions method, of class PlainTextLoader.
     */
    @Test
    public void testGetSupportedExtensions() {
        Set<String> expectedExtensions=Sets.newHashSet("txt");
        PlainTextLoader ptl=new PlainTextLoader(null, null, true);
        assertEquals(expectedExtensions,ptl.getSupportedExtensions());
    }   

    
    
}
