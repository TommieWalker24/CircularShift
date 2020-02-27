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
                InputStream fileInput = context.getParameter("key");
                //List of List containing array of chars
                ArrayList <ArrayList> lines = new ArrayList<>();
                int index = 0;
                //gives the amount of individual characters in the file
                int charAmount = fileInput.available();
                while( charAmount != index ){
                    char current = (char)fileInput.read();
                    //todo: create Character array list
                    ArrayList <Character> charArrayList= new ArrayList<Character>();
                    while(current != '\n'){
                        index++;
                        charArrayList.add(current);
                        current = (char)fileInput.read();
                        if(current =='\n' ){
                            //end list at singular index of lines
                            break;
                        }
                        else if (0 == fileInput.available()){
                            //add last char
                            charArrayList.add(current);
                            //end list at singular index of lines
                            break;
                        }
                        context.putParameter("file",context);
                    }
                    index++;
                    //add List of character into the list named lines at a singular index
                    lines.add(charArrayList);
                    //add a new pipeline to pipeline list so functions can be separated.
                }
                //set global list = lines to access in the output in order to be able to write
                setListToResults(lines);
                //add result to context

                //close connections
                context.putParameter("key",lines );

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
