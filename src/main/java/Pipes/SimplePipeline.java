package Pipes;

import Filters.SimpleFilter;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SimplePipeline {
    public PipedInputStream from = new PipedInputStream();
   public List<SimpleFilter> linkedFilters = new ArrayList<>();
   public Boolean execute(){
        try {
            for (SimpleFilter filter : linkedFilters) {
                filter.setConnectIn(this.from);
                filter.run();
            }
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }
}
