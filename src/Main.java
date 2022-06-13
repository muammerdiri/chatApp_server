import com.sun.net.ssl.internal.ssl.Provider;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.Security;

public class Main {

    public static void main(String[] args) throws IOException {

        Security.addProvider(new Provider());

        //sertifika/genel anahtarı ve özel anahtarı içeren anahtar deposu dosyasını belirtme
        System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");

        //anahtar deposu dosyasının parolasını belirleme
        System.setProperty("javax.net.ssl.keyStorePassword","123456");

        //Sadece el sıkışma sürecinin ayrıntılarının dökümünü göstermek içindir (Zorunlu değil).
        //System.setProperty("javax.net.debug","all");

        //SSLServerSocketFactory, ssl bağlamını kurar ve SSLServerSocket oluşturur
        SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

        //SSLServerSocketFactory tarafından kurulan ssl bağlamını kullanarak SSLServerSocket oluşturur
        SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(1234);

        Server server = new Server(sslServerSocket);
        server.startServer();
    }

}
