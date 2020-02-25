package Filters;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LineFilter extends SimpleFilter{

    @Override
    public void run() throws IOException {
        System.out.println("This is from the lineFilter class!");
        System.out.println("--------------------------------------------------------------");
        // Use of read(byte[] buffer, int offset, int maxlen) :
        byte[] buffer = new byte[6];
        //Array of Strings
        ArrayList <ArrayList> lines = new ArrayList<>();
        //Array of characters

        int index = 0;
        while(this.connectIn.available() != index ){
            char current = (char)this.connectIn.read();
            //todo: Character array list
            ArrayList <Character> charArrayList= new ArrayList<Character>();
           while(current != '\n'){
                index++;
               charArrayList.add(current);
               current = (char)this.connectIn.read();
               if(current =='\n' ){
                   System.out.println("broke from next line char");
                   int checksize = this.connectIn.available();
                   break;

               } else if (0== this.connectIn.available()){
                   break;
               }

              //String currentIndexString =  lines.get(index).concat(String.valueOf(index));
           }
           index++;
           lines.add(charArrayList);
           System.out.println("WE got out of the loop");
          // index++;
           //hex value for newline is 96  //ascii for space is 32
     }
    }
}
