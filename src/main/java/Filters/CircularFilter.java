package Filters;

import Context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircularFilter extends SimpleFilter {
    String originalLine = "";
    List<String> shiftedStrings = new ArrayList<String>();

    @Override
    public void run(Context context) throws IOException {
        boolean found = context.findParameterByKey("key");
        if (found == true) {
            try {
                ArrayList<String> input = context.getParameter("key");
                String copy = new String();
                for (String string : input) {
                    string = string.trim();
                    copy = string;
                    originalLine = string;
                    copy = circularShift(copy);
                    shiftedStrings.add(originalLine);
                    while (!originalLine.equals(copy)) {
                        shiftedStrings.add(copy);
                        copy = circularShift(copy);

                    }
                    copy = "";
                    originalLine = "";
                }

                context.putParameter("key", shiftedStrings);
            } catch (Error e) {
                System.out.println(e.getCause() + "\n" + e.getMessage());
            }
        }

    }

    //move the first string of the copied string to the back of the String
    private String circularShift(String copy) {
        List<String> wordList = new ArrayList<String>();
        String newLine = "";
        //placeholder to help move word to the end of the list
        String wordCopy = new String();
        for (String word : copy.split(" ")) {
            wordList.add(word);
        }
        wordCopy = wordList.get(0);
        wordList.remove(0);
        wordList.add(wordCopy);
        for (String newOrderString : wordList) {
            newLine = newLine.concat(newOrderString) + " ";
        }
        newLine = newLine.trim();
        return newLine;

    }
}


