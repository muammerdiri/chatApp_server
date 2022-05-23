package builders;


import messages.ISignaturePublicKey;

import java.security.PrivateKey;

public class SignatureMessageBuilder {
    private ISignaturePublicKey message;


    public byte[] commantMessage(byte [] clientPublicKey, PrivateKey serverPrivateKey){
        return message.signatureCommantMessage(clientPublicKey,serverPrivateKey);
    }

    public byte[] responseMessage(byte [] signedText){
        return message.successResponseMessage(signedText);
    }

    public ISignaturePublicKey getMessage() {
        return message;
    }

    public void setMessage(ISignaturePublicKey message) {
        this.message = message;
    }
}


