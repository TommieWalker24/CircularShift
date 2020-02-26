package Pipes;

import Filters.CircularFilter;
import Filters.LineFilter;
import Filters.SimpleFilter;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SimplePipeline extends PipedInputStream {
    public PipedInputStream from = new PipedInputStream();
   // public PipedOutputStream to = new PipedOutputStream();
   public List<SimpleFilter> linkedFilters = new ArrayList<>();
   public String to;

   public Boolean execute(){
        try {
            for (SimpleFilter filter : linkedFilters) {
                filter.setConnectIn(from);
                PipedOutputStream pipedOutputStream = new PipedOutputStream();
                filter.setConnectOut(pipedOutputStream);
                filter.run();
                filter.output();
            }
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }


    public SimplePipeline() {
    }

    public SimpleFilter makeFilter() {
        SimpleFilter determinedType;
        if(!linkedFilters.isEmpty()){
           String type = linkedFilters.get(0).getType();
           switch (type){
               case "Line":
                   determinedType =  (new LineFilter());
                   break;
               case "Circular":
                   determinedType = new CircularFilter();
               default:
                   throw new IllegalStateException("Unexpected value: " + type);
           }
           return determinedType;
       }
        else {
            System.out.println("Next pipeline linked filter array is empty");
            return null;
        }
    }
}
