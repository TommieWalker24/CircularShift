package Filters;

import Context.Context;
import Interfaces.FilterInterface;
import Pipes.SimplePipeline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SimpleFilter implements FilterInterface{
    SimplePipeline nextPipe = new SimplePipeline();

    @Override
    //todo: make custom alterations for other filter classes
    public abstract void run(Context context) throws IOException;

    public void setNextPipe(SimplePipeline nextPipe) {
        this.nextPipe = nextPipe;
    }
}
