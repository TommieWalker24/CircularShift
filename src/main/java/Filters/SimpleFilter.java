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
    PipedInputStream connectIn = new PipedInputStream();
    PipedOutputStream connectOut = new PipedOutputStream();
    List<SimplePipeline> pipelineList = new ArrayList<>();
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
}
