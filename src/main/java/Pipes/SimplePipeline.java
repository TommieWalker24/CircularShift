package Pipes;

import Context.Context;
import Filters.SimpleFilter;
import org.apache.commons.lang3.time.StopWatch;

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
       StopWatch stopWatch = new StopWatch();
       stopWatch.start();
        try {
            for (SimpleFilter filter : linkedFilters) {
                filter.run(context);
                context.putParameter("key",filter.getResult());
            }
            String result = listToString(context.getParameter("key"));
            stopWatch.stop();
            long time = stopWatch.getTime();
            System.out.println(time);
            return result;

        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }


    }

    public String listToString(List<String> list){
       String output = new String();
       for(String string: list){
           output = output.concat(string);
       }
       return output;
    }

    public SimplePipeline() {
    }
}
