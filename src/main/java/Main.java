import Filters.LineFilter;
import Pipes.SimplePipeline;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Read file name with scanner
        //todo: replace this with stream reading the file location from front end
        //Scanner fileScanner = new Scanner(System.in);
        System.out.println("please enter a file path to a text file. \nexample: C:\\Users\\user1\\Architecture.text");
        //String str = fileScanner.nextLine();
        String str = "C:\\Users\\tball\\Architecture.text";
        //Create file object with file path grabbed by scanner
        File file = new File(str);
        //TODO: specify datasource as PippedOutputStream
        PipedOutputStream dataSource = new PipedOutputStream();
        //TODO: specify pipelines
        SimplePipeline pipeline1 = new SimplePipeline();
        SimplePipeline pipeline2 = new SimplePipeline();
       // SimplePipeline pipeline3 = new SimplePipeline();
        //TODO: specify filters
        LineFilter lineFilter = new LineFilter();
        LineFilter lineFilter1 = new LineFilter();
        //TODO: specify the nextPipe for each created filter above
        lineFilter.setNextPipe(pipeline2);
        //new filter
        //TODO: for each filter associated to a specific pipeline. add the filter to pipeline's linkedFilters List
        pipeline1.linkedFilters.add(lineFilter);
        pipeline2.linkedFilters.add(lineFilter1);
        //TODO: specify the pipeline linked to the output of every filter
        lineFilter.setNextPipe(pipeline2);

        try {
            FileInputStream fileInput = new FileInputStream(file);

            //TODO: specify all connections ----------------------------------------------
            //link where to get data from to specific pipeline
            dataSource.connect(pipeline1.from);
            //sets connection between line filter and pipeline 2
           // lineFilter.getConnectOut().connect(pipeline2.from);
            //---------------------------------------------------------------

            //todo: transfer the file into datasource
            fileInput.transferTo(dataSource);
            //todo: pipeline1 is linked to datasource above the rest should be self running
            pipeline1.execute();
        }
        catch(Error error){
            System.out.println("IT BROKE");
            System.out.println(error.getLocalizedMessage());
            System.out.println(error.getCause());
        }
    }
}
