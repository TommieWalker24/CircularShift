import Filters.LineFilter;
import Filters.SimpleFilter;
import Pipes.SimplePipeline;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Read file name with scanner
        //Scanner fileScanner = new Scanner(System.in);
        String str = "C:\\Users\\tball\\Architecture.text";
        //Create file object with file path grabbed by scanner
        File file = new File(str);
        PipedOutputStream dataSource= new PipedOutputStream();
        SimplePipeline pipeline1 = new SimplePipeline();
        System.out.println("please enter a file path to a text file. \nexample: C:\\Users\\user1\\Architecture.text");
        LineFilter lineFilter = new LineFilter();
        try{
                FileInputStream fileInput = new FileInputStream(file);
                dataSource.connect(pipeline1.from);
                fileInput.transferTo(dataSource);
                //dataSource.write(fileInput);
                pipeline1.linkedFilters.add(lineFilter);
                pipeline1.execute();
        }
        catch(Error error){
            System.out.println("IT BROKE");
            System.out.println(error.getLocalizedMessage());
            System.out.println(error.getCause());
        }






    }
}
