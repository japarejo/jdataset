/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.jdataset.writer;

import es.us.isa.jdataset.Column;
import es.us.isa.jdataset.DataSet;
import java.util.Collection;
import java.util.Iterator;
import static javafx.scene.input.KeyCode.X;

/**
 *
 * @author japarejo
 */
public class JSONArrayWriter implements Writer{
    private boolean writeColumnLabels;

    public JSONArrayWriter(boolean writeColumnLabels) {
        this.writeColumnLabels = writeColumnLabels;
    }

    @Override
    public String write(DataSet dataSet) {
        StringBuilder sb=new StringBuilder("[");
        Collection<Column<?>> columns=dataSet.getColumns();
        int rows=dataSet.getNRows();
        for(int i=0;i<rows;i++)
        {
            sb.append("[");
            writeLine(i,columns,dataSet,sb);
            sb.append("]");
            if(i!=rows-1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private void writeLine(int i, Collection<Column<?>> columns, DataSet dataSet,StringBuilder sb) {
        Iterator<Column<?>> iterator=columns.iterator();
        Column<?> item=iterator.next();
        if(item!=null){
            sb.append(item.get(i));
        }
        while(iterator.hasNext()){
            sb.append(",");
            item=iterator.next();
            sb.append(item.get(i));
        }
    }
    
    
}
