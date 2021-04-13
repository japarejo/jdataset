/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Sets;
import es.us.isa.jdataset.DataSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author japarejo
 */
public class CSVLoaderTest extends LoaderTest{
    
    public CSVLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class ExcelLoader.
     */
    @Test
    public void testLoad() throws Exception {
        
        InputStream is = null;
        String baseFileName="src/test/resources/sampleFiles/HeightSample.";        
        CSVLoader instance = new CSVLoader(true);
        Set<String> supporteExtensions = instance.getSupportedExtensions();
        DataSet expResult = createHeightsDataSet();
        File f=null;
        for(String extension:supporteExtensions){
            f=new File(baseFileName+extension);
            DataSet result = instance.load(new FileInputStream(f), extension);
            assertEquals("Found differences between expected and loade datasets for extension "+extension,expResult, result);
        }
        
    }
    
    
    /**
     * Test of getSupportedExtensions method, of class CSVLoader.
     */
    @Test
    public void testGetSupportedExtensions() {        
        CSVLoader instance = new CSVLoader(Boolean.TRUE);
        Set<String> expResult = Sets.newHashSet("csv");
        Set<String> result = instance.getSupportedExtensions();
        assertEquals(expResult, result);       
    }
    
}
