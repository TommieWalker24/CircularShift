import Context.Context;
import Filters.AlphabetizeFilter;
import Filters.CircularFilter;
import Filters.LineFilter;
import Pipes.SimplePipeline;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class application {
    private JButton execute;
    private JTextArea resultArea;
    private JTextField inputFile;
    private JPanel panel;
    private JLabel locationLabel;

    public application() {
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Read file name with scanner
                String str = inputFile.getText();
                File file = new File(str);
                //todo: replace this with stream reading the file location from front end

                //TODO: specify pipelines
                //pipeline will contain list of filters
                SimplePipeline pipeline1 = new SimplePipeline();
                //TODO: specify filters
                LineFilter lineFilter = new LineFilter();
                CircularFilter circularFilter = new CircularFilter();
                AlphabetizeFilter alphabetizeFilter = new AlphabetizeFilter();
                //TODO: for each filter associated to a specific pipeline. add the filter to pipeline's linkedFilters List
                //todo: add filters in the order you want data to be manipulated
                pipeline1.linkedFilters.add(lineFilter);
                pipeline1.linkedFilters.add(circularFilter);
                pipeline1.linkedFilters.add(alphabetizeFilter);

                try {
                    InputStream fileInput = new FileInputStream(file);

                    //todo: instantiate context
                    Context context = new Context();
                    //todo: place value in context
                    context.putParameter("key",fileInput);
                    resultArea.setText((String) pipeline1.execute(context));
                }
                catch(Error | FileNotFoundException error){
                    System.out.println("IT BROKE");
                    System.out.println(error.getLocalizedMessage());
                    System.out.println(error.getCause());
                }
            }
        });
    }
    public static void main (String[] args){
        JFrame frame = new JFrame("Pipes and Filters");
        frame.setContentPane(new application().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
