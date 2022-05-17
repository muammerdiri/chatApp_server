package opcodes.abstracts;

import java.security.PrivateKey;

public interface OperationCodeService {

    String signaturePk(String message, PrivateKey key) throws  Exception;
    byte [] successResponse(byte[]data,byte opcode)throws Exception;

}
