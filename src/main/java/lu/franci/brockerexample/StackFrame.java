package lu.franci.brockerexample;

import java.io.Serializable;
import java.util.Optional;

public class StackFrame implements Serializable {
    private Integer size;
    private String webpage;

    public StackFrame(int size, String webpage) {
        this.size = size;
        this.webpage = webpage;
    }

    public int getSize() {
        return size;
    }

    public Optional<String> getWebpage() {
        return Optional.of(webpage);
    }
}

