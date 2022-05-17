package opcodes.concrete;

import opcodes.abstracts.OperationCodeService;
import opcodes.constant.ProtocolName;

import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;



public class OperationCodeManager implements OperationCodeService {

    @Override
    public String signaturePk(String message, PrivateKey key) throws Exception  {

            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(key);
            privateSignature.update(message.getBytes());
            byte[] signature = privateSignature.sign();

            return Base64.getEncoder().encodeToString(signature);
    }

    @Override
    public byte [] successResponse(byte[]data,byte opcode) throws Exception  {
        byte[] dataLengthBytes = ByteBuffer.allocate(2).putShort((short)data.length).array();
        byte[] statusLengthBytes = ByteBuffer.allocate(2).putShort((short)ProtocolName.SUCCESS_STATUS).array();
        byte [] responseData ={ProtocolName.RESPONSE_HEADER,opcode};

//
        responseData = byteArrayConcatenation(responseData,dataLengthBytes);
        responseData = byteArrayConcatenation(responseData,statusLengthBytes);
        responseData = byteArrayConcatenation(responseData,data);



        return responseData;
    }

    //! Array concatenation function
    private static byte[] byteArrayConcatenation(byte[]arr1,byte[]arr2){
        byte[] fullText = new byte [arr1.length+ arr2.length];
        for (int i=0; i<arr1.length + arr2.length; i++)
        {
            if (i<arr1.length)
                fullText[i]=arr1[i];
            else
                fullText[i] = arr2[i-arr1.length];
        }
        return fullText;
    }

}
