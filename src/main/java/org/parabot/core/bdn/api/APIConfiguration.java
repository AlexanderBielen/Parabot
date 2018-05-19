package org.parabot.core.bdn.api;

/**
 * @author JKetelaar
 */
public class APIConfiguration {

    public static final String NIGHTLY_PARAMETER = "nightly=%s";

    // Live
//    public static final String OAUTH_CLIENT_ID = "2_163spniuypxc44kcg8wgwg4gso8ocw484sss8osskw840c4g8c";
//    public static final String ENDPOINT              = "http://v3.bdn.parabot.org/app_dev.php/";

    // Test
    public static final String OAUTH_CLIENT_ID = "4_wdg0tnpp7lwg4ck0wk4w4kooss8gow4cwoogsg4g8wckwgsgg";
    public static final String ENDPOINT        = "http://v3.bdn.parabot.test/";

    public static final String API_ENDPOINT          = ENDPOINT + "api/";
    public static final String CLOSE_PAGE            = ENDPOINT + "close";
    public static final String USERS_LOGIN           = API_ENDPOINT + "users/log_in?after_login_redirect=%s";
    public static final String INTERNAL_ROUTE        = ENDPOINT + "internal/route/oauth/v2/token";
    public static final String INTERNAL_ROUTE_CLIENT = INTERNAL_ROUTE + "/client";
    public static final String CREATE_COPY_LOGIN     = API_ENDPOINT + "users/oauth/v2/create_copy?clientId=%s";
    public static final String COPY_LOGIN            = API_ENDPOINT + "users/oauth/v2/copy";
    public static final String VALIDATE_ACCESS_TOKEN = API_ENDPOINT + "users/oauth/v2/valid?access_token=%s";

    public static final String DOWNLOAD_SERVER_PROVIDER = API_ENDPOINT + "bot/download/%s?" + NIGHTLY_PARAMETER;
    public static final String DOWNLOAD_BOT             = API_ENDPOINT + "bot/download/client?" + NIGHTLY_PARAMETER;
    public static final String DOWNLOAD_RANDOMS         = API_ENDPOINT + "bot/download/randoms?" + NIGHTLY_PARAMETER;

}
