package Filters;

import Pipes.SimplePipeline;

import java.io.*;
import java.util.*;

public class LineFilter extends SimpleFilter{
    ArrayList<ArrayList> listToResults;
    @Override
    public void run() throws IOException {
        System.out.println("This is from the lineFilter class!");
        System.out.println("----------------------------------------------------");
        //List of List containing array of chars
        ArrayList <ArrayList> lines = new ArrayList<>();


        int index = 0;
        //gives the amount of individual characters in the file
        int charAmount = this.connectIn.available();
        while( charAmount != index ){
            char current = (char)this.connectIn.read();
            //todo: create Character array list
            ArrayList <Character> charArrayList= new ArrayList<Character>();
           while(current != '\n'){
                index++;
               charArrayList.add(current);
               current = (char)this.connectIn.read();
               if(current =='\n' ){
                   //end list at singular index of lines
                   break;
               }
               else if (0== this.connectIn.available()){
                   //end list at singular index of lines
                   break;
               }
           }
           index++;
           //add List of character into the list named lines at a singular index
           lines.add(charArrayList);
           //add a new pipeline to pipeline list so functions can be separated.
           pipelineList.add(new SimplePipeline());
        }
        //set global list = lines to access in the output in order to be able to write
        setListToResults(lines);
        //close connections
       this.connectIn.close();
       this.connectOut.close();
       }

       @Override
       //todo: write to next connection
       public void output() throws IOException {
        int numberIndex = 0;

           for(List<Character> line: listToResults ){
               connectOut = new PipedOutputStream();
               connectOut.connect(pipelineList.get(numberIndex).from);
               for(char character: line){
                   connectOut.write(character);
               }
               connectOut.close();
               numberIndex++;
           }
       }

    public ArrayList<ArrayList> getListToResults() {
        return listToResults;
    }

    public void setListToResults(ArrayList<ArrayList> listToResults) {
        this.listToResults = listToResults;
    }
}
