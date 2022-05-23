package messages;

import java.security.PrivateKey;

public interface ISignaturePublicKey {
    byte[] signatureCommantMessage(byte [] clientPublicKey, PrivateKey serverPrivateKey);
    byte[] successResponseMessage();

}
