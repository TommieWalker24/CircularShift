package Filters;

import Interfaces.FilterInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class SimpleFilter implements FilterInterface{
    PipedInputStream connectIn;
    PipedOutputStream connectOut;
    @Override
    public abstract void run() throws IOException;

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
}
