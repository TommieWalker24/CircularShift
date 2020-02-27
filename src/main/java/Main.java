import Context.Context;
import Filters.AlphabetizeFilter;
import Filters.CircularFilter;
import Filters.LineFilter;
import Pipes.SimplePipeline;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Read file name with scanner
        String str = "C:\\Users\\tball\\Architecture.text";
        File file = new File(str);
        //todo: replace this with stream reading the file location from front end

        //TODO: specify pipelines
        //pipeline will contain list of filters
        SimplePipeline pipeline1 = new SimplePipeline();
        //TODO: specify filters
        LineFilter lineFilter = new LineFilter();
        CircularFilter circularFilter = new CircularFilter();
        AlphabetizeFilter alphabetizeFilter = new AlphabetizeFilter();
        //TODO: for each filter associated to a specific pipeline. add the filter to pipeline's linkedFilters List
        //todo: add filters in the order you want data to be manipulated
        pipeline1.linkedFilters.add(lineFilter);
        pipeline1.linkedFilters.add(circularFilter);
        pipeline1.linkedFilters.add(alphabetizeFilter);

        try {

            InputStream fileInput = new FileInputStream(file);
            System.out.println(file);

            //todo: instantiate context
            Context context = new Context();
            //todo: place value in context
            context.putParameter("key",fileInput);
            pipeline1.execute(context);
        }
        catch(Error error){
            System.out.println("IT BROKE");
            System.out.println(error.getLocalizedMessage());
            System.out.println(error.getCause());
        }
    }
}
