package patdwyer.cs275_happyhour;

import android.util.Log;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.text.ParseException;

/**
 * Created by patrickdwyer on 8/25/15.
 */
public class YelpAPI {

    private static final String API_HOST = "api.yelp.com";
    private static final int SEARCH_LIMIT = 10;
    private static final String SEARCH_PATH = "/v2/search";

    OAuthService service;
    Token accessToken;

    public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service =
                new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                        .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    public String searchForBusinessesByLocation(String term, double lat, double lng) {
        String loc = Double.toString(lat) + "," + Double.toString(lng);
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("category_filter", term);
        request.addQuerystringParameter("ll", loc);
        request.addQuerystringParameter("radius_filter", "1609");
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
        request.addQuerystringParameter("sort", "1");
        return sendRequestAndGetResponse(request);
    }

    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
        return request;
    }

    private String sendRequestAndGetResponse(OAuthRequest request) {
        Log.w("Search Path", "Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

}
