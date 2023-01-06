package soya.framework.io;

public interface ResourceProcessor {
    byte[] process(byte[] data) throws ResourceException;
}
