package entities;

import javax.servlet.http.HttpServletRequest;

/**
 * This class will create an HTTPS-based URL from a relative URL by using the
 * local host name, context name, and port number from the request.
 *
 * It is assumed that during testing the port numbers 8084 and 8443 are being
 * used instead of the standard ports 80 and 443.
 *
 * @author Dr. Grove used by Team03
 */
public class UrlRewriter {

    private final static String TEST_HTTP_PORT = ":8084";
    private final static String TEST_HTTPS_PORT = ":8443";

    /**
     * Rewrites a nonsecure url to a secure url
     *
     * @param relativeUrl
     * @param request
     * @return
     */
    public String rewriteUrl(String relativeUrl, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String localName = request.getLocalName();
        String requestURL = request.getRequestURL().toString();
        String newUrl = "https://" + localName + ":8443";
        // if (requestURL.contains(TEST_HTTP_PORT)) { // for NetBeans testing
        //   newUrl += TEST_HTTPS_PORT;
        // }
        newUrl += contextPath + "/" + relativeUrl;
        return newUrl;
    }
}
