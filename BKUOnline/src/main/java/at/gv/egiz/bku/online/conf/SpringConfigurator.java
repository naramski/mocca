package at.gv.egiz.bku.online.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import at.gv.egiz.bku.slexceptions.SLRuntimeException;

public class SpringConfigurator extends Configurator implements
		ResourceLoaderAware {

	private final static Log log = LogFactory.getLog(SpringConfigurator.class);

	private ResourceLoader resourceLoader;

	public void setResource(Resource resource) {
		log.debug("Loading config from: " + resource);
		if (resource != null) {
			Properties props = new Properties();
			try {
				props.load(resource.getInputStream());
				super.setConfiguration(props);
			} catch (IOException e) {
				log.error("Cannot load config", e);
			}
		}
	}

	public void configure() {
		super.configure();
		configureSSL();
	}

	private Set<TrustAnchor> getCACerts() throws IOException,
			CertificateException {
		Set<TrustAnchor> caCerts = new HashSet<TrustAnchor>();
		String caDirectory = getProperty("SSL.caDirectory");
		if (caDirectory != null) {
			Resource caDirRes = resourceLoader.getResource(caDirectory);

			File caDir = caDirRes.getFile();
			if (!caDir.isDirectory()) {
				log.error("Expecting directory as SSL.caDirectory parameter");
				throw new SLRuntimeException(
						"Expecting directory as SSL.caDirectory parameter");
			}
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			for (File f : caDir.listFiles()) {
				try {
					FileInputStream fis = new FileInputStream(f);
					X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
					fis.close();
					log.debug("Adding trusted cert " + cert.getSubjectDN());
					caCerts.add(new TrustAnchor(cert, null));
				} catch (Exception e) {
					log.error("Cannot add trusted ca", e);
				}
			}
			return caCerts;

		} else {
			log.warn("No CA certificates configured");
		}
		return null;
	}

	private CertStore getCertstore() throws IOException, CertificateException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException {
		String certDirectory = getProperty("SSL.certDirectory");
		if (certDirectory != null) {
			Resource certDirRes = resourceLoader.getResource(certDirectory);

			File certDir = certDirRes.getFile();
			if (!certDir.isDirectory()) {
				log.error("Expecting directory as SSL.certDirectory parameter");
				throw new SLRuntimeException(
						"Expecting directory as SSL.certDirectory parameter");
			}
			List<X509Certificate> certCollection = new LinkedList<X509Certificate>();
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			for (File f : certDir.listFiles()) {
				try {
					FileInputStream fis = new FileInputStream(f);
					X509Certificate cert =(X509Certificate) cf.generateCertificate(fis);
					certCollection.add(cert);
					fis.close();
					log.trace("Added following cert to certstore: "+cert.getSubjectDN());
				} catch (Exception ex) {
					log.error("Cannot add certificate", ex);
				}
			}
			CollectionCertStoreParameters csp = new CollectionCertStoreParameters(
					certCollection);
			return CertStore.getInstance("Collection", csp);

		} else {
			log.warn("No certstore configured");
		}
		return null;
	}

	public void configureSSL() {
		Set<TrustAnchor> caCerts = null;
		try {
			caCerts = getCACerts();
		} catch (Exception e1) {
			log.error("Cannot load CA certificates", e1);
		}
		CertStore certStore = null;
		try {
			certStore = getCertstore();
		} catch (Exception e1) {
			log.error("Cannot load certstore certificates", e1);
		}
		System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
		try {
			X509CertSelector selector = new X509CertSelector();
			PKIXBuilderParameters pkixParams;
			pkixParams = new PKIXBuilderParameters(caCerts, selector);
			if ((getProperty("SSL.doRevocationChecking") != null)
					&& (Boolean.valueOf(getProperty("SSL.doRevocationChecking")))) {
				log.info("Enable revocation checking");
				pkixParams.setRevocationEnabled(true);
				System.setProperty("com.sun.security.enableCRLDP", "true");
				Security.setProperty("ocsp.enable", "true");
			} else {
				log.warn("Revocation checking disabled");
				pkixParams.setRevocationEnabled(false);
			}
			pkixParams.addCertStore(certStore);
			ManagerFactoryParameters trustParams = new CertPathTrustManagerParameters(
					pkixParams);
			TrustManagerFactory trustFab;
			try {
				trustFab = TrustManagerFactory.getInstance("PKIX");
				trustFab.init(trustParams);
				KeyManager[] km = null;
				SSLContext sslCtx = SSLContext
						.getInstance(getProperty("SSL.sslProtocol"));
				sslCtx.init(km, trustFab.getTrustManagers(), null);
				HttpsURLConnection
						.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());
			} catch (Exception e) {
				log.error("Cannot configure SSL", e);
			}

		} catch (InvalidAlgorithmParameterException e) {
			log.error("Cannot configure SSL", e);
		}
	}

	@Override
	public void setResourceLoader(ResourceLoader loader) {
		this.resourceLoader = loader;
	}
}