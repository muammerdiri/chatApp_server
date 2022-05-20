package messages;

public interface IHelloCA  {

        byte[] successResponseMessage(byte opcode, int dataLength, byte[] data);
        byte[] commantMessage(byte opcode, int dataLength, byte[] data);



}
