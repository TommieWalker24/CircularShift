package Filters;

import Interfaces.FilterInterface;
import Pipes.SimplePipeline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class SimpleFilter implements FilterInterface{
    String type;
    PipedInputStream connectIn = new PipedInputStream();
    PipedOutputStream connectOut = new PipedOutputStream();
    SimplePipeline nextPipe = new SimplePipeline();
   public List<SimplePipeline> pipelineList = new ArrayList<>();


    @Override
    //todo: make custom alterations for other filter classes
    public abstract void run() throws IOException;

    @Override
    //todo: write to next connection
    public abstract void output() throws IOException;

    public PipedInputStream getConnectIn() {
        return connectIn;
    }

    public void setConnectIn(PipedInputStream connectIn) {
        this.connectIn = connectIn;
    }

    public PipedOutputStream getConnectOut() {
        return connectOut;
    }

    public void setConnectOut(PipedOutputStream connectOut) {
        this.connectOut = connectOut;
    }

    public List<SimplePipeline> getPipelineList() {
        return pipelineList;
    }

    public void setPipelineList(List<SimplePipeline> pipelineList) {
        this.pipelineList = pipelineList;
    }

    public SimplePipeline getNextPipe() {
        return nextPipe;
    }

    public void setNextPipe(SimplePipeline nextPipe) {
        this.nextPipe = nextPipe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public SimpleFilter generateFilter(String type){
        SimpleFilter determinedType;
        switch (type){
            case "Line":
                determinedType =  (new LineFilter());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return determinedType;
    }
}
