package soya.framework.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class FragmentProcessChain {

    private List<FragmentProcessor> processors = new ArrayList<>();

    public FragmentProcessChain(String fragment) {

    }

    public InputStream process(InputStream in) throws IOException {
        byte[] data = StreamUtils.copyToByteArray(in);
        Queue<FragmentProcessor> queue = new LinkedBlockingDeque<>(processors);
        while (queue.isEmpty()) {
            data = queue.poll().process(data);
        }
        return new ByteArrayInputStream(data);

    }
}
