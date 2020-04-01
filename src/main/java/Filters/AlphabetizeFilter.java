package Filters;

import Context.Context;

import java.io.IOException;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphabetizeFilter extends SimpleFilter {
    @Override
    public void run(Context context) throws IOException {
        List<String> sorted = new ArrayList<String>();
        try{
            boolean found = context.findParameterByKey("key");
            if(found == true){
                ArrayList<String> stringsToBeSorted = context.getParameter("key");
                String lowerFirst = "< a < A < b < B < c< C < d< D < e< E < f< F < g< G < h, H < i, I" +
                        "< j< J < k< K < l< L < m< M < n< N < o< O < p< P < q< Q < r< R" +
                        "< s< S < t< T < u< U < v< V < w< W < x< X < y< Y < z< Z" +
                        "aa,bb";
                try {
                    //sets the rules for collection sort algorithm to abide by.
                    RuleBasedCollator myNorwegian = new RuleBasedCollator(lowerFirst);
                    Collections.sort(stringsToBeSorted,myNorwegian);
                    for(String string: stringsToBeSorted){
                        sorted.add(string);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            ArrayList<String> outputList = new ArrayList<String>();
            String output = "";
            int i = 0;
            for(String line: sorted){
                output = "";
                if(i == 0){
                   output =  output.concat(line + "\n");
                }
                else if(i > 0 && i != sorted.size()-1){
                    output = output.concat(line+ "\n");
                }
                else{
                    output = output.concat(line);
                }
               i++;
                outputList.add(output);
                
            }
            setResult(outputList);
           // context.putParameter("key",outputList);
        }
        catch (Error e){
            System.out.println(e.getCause()+ "\n"+ e.getMessage());
        }
    }
}
