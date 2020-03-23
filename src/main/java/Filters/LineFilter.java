package Filters;

import Context.Context;
import java.io.*;
import java.util.*;

public class LineFilter extends SimpleFilter{
    ArrayList<ArrayList> listToResults;
    @Override
    public void run(Context context) throws IOException {
        boolean found = context.findParameterByKey("key");
        if (found == true){
            try{
                ArrayList<String> lines = context.getParameter("key");
                ArrayList<String> output = new ArrayList<String>();
                for(String string: lines){
                    string = string.trim();
                    output.add(string);
                }
                //close connections
                context.putParameter("key",output);

            }
            catch(Error e){
                System.out.println(e.getCause()+ "\n"+ e.getMessage());
            }
        }

       }

    public ArrayList<ArrayList> getListToResults() {
        return listToResults;
    }

    public void setListToResults(ArrayList<ArrayList> listToResults) {
        this.listToResults = listToResults;
    }

    public LineFilter() {
    }

}
