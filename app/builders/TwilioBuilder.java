package builders;

import java.util.HashMap;
import java.util.Map;

public class TwilioBuilder {
    private static final String HTTP_METHOD_GET = "GET";
    private static final String RECORD_DEFAULT = "false";

    public static Map<String, String> buildOutgoingCallParams(String to, String from, String url) {
        Map<String, String> params = new HashMap<String, String>();

        params.put("To", to);
        params.put("From", from);
        params.put("Url", url);
        params.put("Method", HTTP_METHOD_GET);
        params.put("FallbackMethod", HTTP_METHOD_GET);
        params.put("StatusCallbackMethod", HTTP_METHOD_GET);
        params.put("Record", RECORD_DEFAULT);

        return params;
    }
}
