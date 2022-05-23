package messages;

import tools.Tools;

import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class SignaturePublicKey implements ISignaturePublicKey {


    @Override
    public byte [] signatureCommantMessage(byte [] clientPublicKey, PrivateKey serverPrivateKey)  {

        byte[] signature = null;
        try {
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(serverPrivateKey);
            privateSignature.update(clientPublicKey);
            signature = privateSignature.sign();
            byte[] lengthToByte = ByteBuffer.allocate(2).putShort((short)signature.length).array();
            byte [] responseData ={tools.ProtocolName.COMMAND_HEADER,tools.ProtocolName.HELLO,0x00};
            responseData = Tools.byteArrayConcatenation(responseData,lengthToByte);
            signature = Tools.byteArrayConcatenation(responseData,signature);

        }catch (Exception e){
            e.printStackTrace();
        }
        return signature;

    }

    @Override
    public byte[] successResponseMessage() {
        return new byte[0];
    }

}
