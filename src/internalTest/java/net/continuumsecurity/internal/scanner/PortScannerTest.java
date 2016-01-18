package net.continuumsecurity.internal.scanner;

import net.continuumsecurity.Port;
import net.continuumsecurity.scanner.PortScanner;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PortScannerTest {
    PortScanner scanner;

    @Test
    public void testPortOpen() throws ExecutionException, InterruptedException {
        scanner = new PortScanner("www.google.com",80,80,1,500);
        List<Port> results = scanner.scan();
        assert results.size() == 1;
        assert results.get(0).getNumber() == 80;
        assert results.get(0).getState() == Port.State.OPEN;
    }


    @Test
    public void testPortClosed() throws ExecutionException, InterruptedException {
        scanner = new PortScanner("127.0.0.1",65531,65531,1,500);
        List<Port> results = scanner.scan();
        assert results.size() == 1;
        assert results.get(0).getNumber() == 65531;
        assert results.get(0).getState() == Port.State.CLOSED;
    }

    @Test
    public void testPortTimedOut() throws ExecutionException, InterruptedException {
        scanner = new PortScanner("www.google.com",83,83,1,500);
        List<Port> results = scanner.scan();
        assert results.size() == 1;
        assert results.get(0).getNumber() == 83;
        assert results.get(0).getState() == Port.State.TIMEDOUT;
    }

}
