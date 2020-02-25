package Pipes;

import Filters.SimpleFilter;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SimplePipeline {
    public PipedInputStream from = new PipedInputStream();
   public List<SimpleFilter> linkedFilters = new ArrayList<>();
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
}
