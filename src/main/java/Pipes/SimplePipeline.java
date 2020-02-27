package Pipes;

import Context.Context;
import Filters.SimpleFilter;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SimplePipeline extends PipedInputStream {
    public InputStream from = new PipedInputStream();
   public OutputStream to = new PipedOutputStream();
   public List<SimpleFilter> linkedFilters = new ArrayList<>();

   public Object execute(final Context context){
        try {
            for (SimpleFilter filter : linkedFilters) {
                filter.run(context);
            }
            return context.getParameter("key");
        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }


    public SimplePipeline() {
    }
}
