/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.loader;

import com.google.common.collect.Lists;
import es.us.isa.jdataset.DataSet;
import es.us.isa.jdataset.SimpleDataSet;
import java.util.List;

/**
 *
 * @author japarejo
 */
public abstract class LoaderTest {
    protected DataSet createHeightsDataSet() {
        List<String> rowId=Lists.newArrayList("Data1","Data2","Data3","Data4","Data5","Data6","Data7",
            "Data8","Data9","Data10","Data11","Data12","Data13","Data14","Data15","Data16","Data17","Data18",
            "Data19","Data20","Data21","Data22","Data23","Data24","Data25");
        List<Double> heightMen=Lists.newArrayList(67.489439,69.48316,70.561353,74.84632,69.469678,71.959434,
            68.360909,72.777127,73.612962,74.591664,65.93332,70.154467,73.060535,
            66.321518,72.125492,72.61502,67.630836,70.996237,70.616807,69.491898,
            69.044748,69.113072,71.566874,63.306848,70.582437);
        List<Double> heightWomen=Lists.newArrayList(63.463062,63.880407,64.539034,63.841551,65.692283,
            64.963393,66.325883,65.102038,66.229205,62.041943,63.663395,67.989878,
            69.852506,69.211567,63.448222,58.165974,61.652194,64.82155,63.396557,
            63.592375,63.476537,64.693599,65.66029,64.927502,66.915061);
        DataSet result=new SimpleDataSet();
        result.addColumn(String.class,"Data",rowId);
        result.addColumn(Double.class,"Men",heightMen);
        result.addColumn(Double.class,"Women",heightWomen);                
        return result;
                
                
    }
}
