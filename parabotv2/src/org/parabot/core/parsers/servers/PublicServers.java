package org.parabot.core.parsers.servers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.parabot.core.Configuration;
import org.parabot.core.desc.ServerDescription;
import org.parabot.core.forum.AccountManager;
import org.parabot.core.forum.AccountManagerAccess;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.servers.executers.PublicServerExecuter;

import java.io.BufferedReader;
import java.net.URL;

/**
 * Parses servers hosted on Parabot
 *
 * @author Paradox, Everel
 */
public class PublicServers extends ServerParser {

    private static AccountManager manager;

    public static final AccountManagerAccess MANAGER_FETCHER = new AccountManagerAccess() {

        @Override
        public final void setManager(AccountManager manager) {
            PublicServers.manager = manager;
        }

    };

    @Override
    public void execute() {
        try {
            BufferedReader br = WebUtil.getReader(new URL(
                    Configuration.GET_SERVER_PROVIDERS_JSON), manager.getAccount().getURLUsername(), manager.getAccount().getURLPassword());
            String line;

            JSONParser parser = new JSONParser();
            while ((line = br.readLine()) != null) {

                JSONObject jsonObject = (JSONObject) parser.parse(line);
                String name = String.valueOf(jsonObject.get("name"));
                String author = String.valueOf(jsonObject.get("author"));
                double version = Double.parseDouble(String.valueOf(jsonObject.get("version")));
                int serverID = Integer.parseInt(String.valueOf(jsonObject.get("id")));

                ServerDescription desc = new ServerDescription(name,
                        author, version);
                SERVER_CACHE.put(desc, new PublicServerExecuter(name, serverID));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
