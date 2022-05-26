package protocol;

public enum State {
    HELLO,
    SEND_PUBLIC_KEY,
    SIGNATURE_KEY,
    EXCHANGE_KEY,
    SEND_MESSAGE,
    BYE
}
