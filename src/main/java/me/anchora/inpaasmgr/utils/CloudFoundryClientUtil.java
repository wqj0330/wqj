package me.anchora.inpaasmgr.utils;

import java.util.UUID;

import me.anchora.inpaasmgr.entry.Accesstokens;
import me.anchora.inpaasmgr.entry.ccdb.Organizations;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudEntity;
import org.cloudfoundry.client.lib.domain.CloudOrganization;
import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;

public class CloudFoundryClientUtil {
    public static void setToken(CloudFoundryClient cloudFoundryClient, Accesstokens accessTokens) {
        DefaultOAuth2AccessToken auth = new DefaultOAuth2AccessToken(accessTokens.getAccessToken());
        DefaultOAuth2RefreshToken reauth = new DefaultOAuth2RefreshToken(accessTokens.getRefreshToken());
        auth.setRefreshToken(reauth);
        CloudCredentials credentials = new CloudCredentials(auth);
        cloudFoundryClient.setCloudCredentials(credentials);
    }

    public static CloudSpace getCloudSpace(Spaces spaces, Organizations organizations) {
        CloudEntity.Meta coMeta = new CloudEntity.Meta(UUID.fromString(organizations.getGuid()), organizations.getCreatedAt(), organizations.getUpdatedAt());
        CloudOrganization co = new CloudOrganization(coMeta, organizations.getName().toString(), organizations.getBillingEnabled());

        CloudEntity.Meta csMeta = new CloudEntity.Meta(UUID.fromString(spaces.getGuid()), spaces.getCreatedAt(), spaces.getUpdatedAt());
        CloudSpace cs = new CloudSpace(csMeta, spaces.getName().toString(), co);
        return cs;
    }
}
