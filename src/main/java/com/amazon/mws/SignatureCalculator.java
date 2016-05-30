package com.amazon.mws;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

/**
 * Created by wingert on 5/24/2016.
 */
public class SignatureCalculator {

    private static final String CHARACTER_ENCODING = "UTF-8";

    //Need 4 things to make a signature
    //1) MWS Credentials
    //2) Regional endpoint
    //3) API Section
    //4) URL Parameters
    private Credentials credentials;
    private String apiSection = "Products";

    private SignatureCalculator(Credentials credentials) {
        this.credentials = credentials;
    }

    /* If Signature Version is 2, string to sign is based on following:
        *
        *    1. The HTTP Request Method followed by an ASCII newline (%0A)
        *
        *    2. The HTTP Host header in the form of lowercase host,
        *       followed by an ASCII newline.
        *
        *    3. The URL encoded HTTP absolute path component of the URI
        *       (up to but not including the query string parameters);
        *       if this is empty use a forward '/'. This parameter is followed
        *       by an ASCII newline.
        *
        *    4. The concatenation of all query string components (names and
        *       values) as UTF-8 characters which are URL encoded as per RFC
        *       3986 (hex characters MUST be uppercase), sorted using
        *       lexicographic byte ordering. Parameter names are separated from
        *       their values by the '=' character (ASCII character 61), even if
        *       the value is empty. Pairs of parameter and values are separated
        *       by the '&' character (ASCII code 38).
        *
        */
    private String constructStringToSignV2(Map<String, String> parameters) throws SignatureException,
            URISyntaxException {
        // Sort the parameters natural order by storing in TreeMap structure
        Map<String, String> sorted = new TreeMap<String, String>();
        sorted.putAll(parameters);

        // Set endpoint value
        URI endpoint = new URI(credentials.getEndpoint().toLowerCase());

        // Create flattened (String) representation
        StringBuilder data = new StringBuilder();
        data.append("POST\n");
        data.append(credentials.getEndpoint() + "\n");
        data.append("/" + apiSection + "\n");

        Iterator<Map.Entry<String, String>> pairs = sorted.entrySet().iterator();
        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = pairs.next();
            data.append(urlEncode(pair.getKey()) + "=" + urlEncode(pair.getValue() == null ? "" : pair.getValue()));
            // Delimit parameters with ampersand (&)
            if (pairs.hasNext()) {
                data.append("&");
            }
        }

        return data.toString();
    }

    public static SignatureCalculator createSignatureCalculator(Credentials credentials) {
        return new SignatureCalculator(credentials);
    }

    /*
 * Sign the text with the given secret key and convert to base64
 */
    private static String sign(String data, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IllegalStateException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(secretKey.getBytes(CHARACTER_ENCODING),
                ALGORITHM));
        byte[] signature = mac.doFinal(data.getBytes(CHARACTER_ENCODING));
        System.out.println("Siggy:" + Hex.encodeHexString(signature));
        String signatureBase64 = new String(Base64.encodeBase64(signature),
                CHARACTER_ENCODING);
        System.out.println("Base64Siggy: " + signatureBase64);
        //String signatureUrlEncode = urlEncode(signatureBase64);
        //System.out.println("Ency64Siggy: " + signatureUrlEncode);
        return signatureBase64;
    }

    private static String urlEncode(String rawValue) {
        String value = (rawValue == null) ? "" : rawValue;
        String encoded = null;

        try {
            encoded = URLEncoder.encode(value, CHARACTER_ENCODING)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E","~");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unknown encoding: " + CHARACTER_ENCODING);
            e.printStackTrace();
        }

        return encoded;
    }
}
