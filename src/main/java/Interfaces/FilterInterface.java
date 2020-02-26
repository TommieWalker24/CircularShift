package Interfaces;

import java.io.IOException;

public interface FilterInterface {
    void output() throws IOException;
    void run() throws IOException;
    String type = "";
}
