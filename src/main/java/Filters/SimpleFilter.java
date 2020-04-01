package Filters;

import Context.Context;
import Interfaces.FilterInterface;
import Pipes.SimplePipeline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SimpleFilter implements FilterInterface{

    List<String> result;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    @Override
    //todo: make custom alterations for other filter classes
    public abstract void run(Context context) throws IOException;


}
