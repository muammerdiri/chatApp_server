package messages;

import java.nio.ByteBuffer;

public class HelloCA  {



    public byte[] successResponseMessage(byte opcode, int dataLength, byte[] data) {
        return new byte[0];
    }


    public byte[] commantMessage(byte[] data) {
        //Main array = { header +opcode + Length + Data }
        byte [] responseData ={tools.ProtocolName.COMMAND_HEADER,tools.ProtocolName.HELLO,0x00};

        short length = (short)data.length;
        byte[] lengthToByte = ByteBuffer.allocate(2).putShort(length).array();
        responseData =tools.Tools.byteArrayConcatenation(responseData,lengthToByte);

        responseData= tools.Tools.byteArrayConcatenation(responseData,data);
        return responseData;
    }
}
