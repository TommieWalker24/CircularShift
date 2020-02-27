package Filters;

import Context.Context;

import java.io.Console;
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
                        System.out.println(string);
                        sorted.add(string);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } context.putParameter("key",sorted);
        }
        catch (Error e){
            System.out.println(e.getCause()+ "\n"+ e.getMessage());
        }
    }
}
