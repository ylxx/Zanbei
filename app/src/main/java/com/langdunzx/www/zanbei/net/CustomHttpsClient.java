package com.langdunzx.www.zanbei.net;

import android.content.Context;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class CustomHttpsClient extends DefaultHttpClient {

	
	private Context context;
	
	public CustomHttpsClient(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected ClientConnectionManager createClientConnectionManager() {
		// TODO Auto-generated method stub 
			
			try {
				KeyStore  trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				CustomSSLSocketFactory mySSlSocketFactory = new CustomSSLSocketFactory(trustStore);
				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", PlainSocketFactory
						.getSocketFactory(), 80));
				registry.register(new Scheme("https", mySSlSocketFactory, 443));
				
				return new SingleClientConnManager(getParams(), registry);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        
			return null;
	}
	
	 
	private class CustomSSLSocketFactory extends SSLSocketFactory{
		SSLContext sslContext = SSLContext.getInstance("TLS");
		

		public CustomSSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException,
				KeyManagementException, KeyStoreException,
				UnrecoverableKeyException {
			super(truststore);
			// TODO Auto-generated constructor stub
			sslContext.init(null, new TrustManager[]{new CustomTrustManager()}, null);
		}

		@Override
		public Socket createSocket() throws IOException {
			// TODO Auto-generated method stub
			return sslContext.getSocketFactory().createSocket();
		}


		@Override
		public Socket createSocket(Socket socket, String host, int port,
				boolean autoClose) throws IOException, UnknownHostException {
			// TODO Auto-generated method stub
			return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
		}
	}
	
	private class CustomTrustManager implements X509TrustManager{

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
//			Certificate cer = null;
//			InputStream ins;
//			try {
//				ins = context.getAssets().open("api.etongdai.cer");
//				CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
//				cer = cerFactory.generateCertificate(ins);
////				Logger.LogD("cer:"+cer.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			for (int i = 0; i < chain.length; i++) {
//				Certificate certificate = chain[i];
//				Logger.LogI( i+">>>>>>certificate:"+certificate.toString());
//				if(cer.equals(certificate)){
//					Logger.LogE( "success----->");
//					return;
//				}
//			}
//			Logger.LogE( "no peer cer----->");
//			throw new CertificateException();
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	


}
