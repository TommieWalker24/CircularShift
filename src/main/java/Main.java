import Context.Context;
import Filters.AlphabetizeFilter;
import Filters.CircularFilter;
import Filters.LineFilter;
import Filters.NoiseWordFilter;
import Pipes.SimplePipeline;

import java.io.*;
import java.util.ArrayList;
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
        NoiseWordFilter noiseWordFilter = new NoiseWordFilter();
        //TODO: for each filter associated to a specific pipeline. add the filter to pipeline's linkedFilters List
        //todo: add filters in the order you want data to be manipulated
        pipeline1.linkedFilters.add(lineFilter);
        pipeline1.linkedFilters.add(circularFilter);
        pipeline1.linkedFilters.add(alphabetizeFilter);
        pipeline1.linkedFilters.add(noiseWordFilter);


        try {

            InputStream fileInput = new FileInputStream(file);
            System.out.println(file);

            //todo: instantiate context
            Context context = new Context();
            //todo: place value in context
            ArrayList<String> input = construct(fileInput);
            context.putParameter("key",input);
            pipeline1.execute(context);
        }
        catch(Error error){
            System.out.println("IT BROKE");
            System.out.println(error.getLocalizedMessage());
            System.out.println(error.getCause());
        }
    }

     static ArrayList<String> construct(InputStream inputStream) throws IOException {
        ArrayList<String>output = new ArrayList<String>();
        //List of List containing array of chars
        ArrayList <ArrayList> lines = new ArrayList<>();
        int index = 0;
        //gives the amount of individual characters in the file
        int charAmount = inputStream.available();
        while( charAmount != index ){
            char current = (char)inputStream.read();
            //todo: create Character array list
            ArrayList <Character> charArrayList= new ArrayList<Character>();
            while(current != '\n'){
                index++;
                charArrayList.add(current);
                current = (char)inputStream.read();
                if(current =='\n' ){
                    //end list at singular index of lines
                    break;
                }
                else if (0 == inputStream.available()){
                    //add last char
                    charArrayList.add(current);
                    //end list at singular index of lines
                    break;
                }
            }
            index++;
            //add List of character into the list named lines at a singular index
            String line = "";
            for(Character character: charArrayList){
               line =  line.concat(String.valueOf(character));
            }
            output.add(line);
            lines.add(charArrayList);
        }
        //set global list = lines to access in the output in order to be able to write
//        setListToResults(lines);
        //add result to context

        //close connections
        return output;
    }
}
