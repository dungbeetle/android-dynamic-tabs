package gobyfragment.we.com.gobyfragments;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class MyHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session)
    {
            // TODO Auto-generated method stub
            return true;
    }
}
