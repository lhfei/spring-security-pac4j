package org.pac4j.springframework.security.authentication;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileHelper;
import org.pac4j.springframework.security.util.SpringSecurityHelper;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.LinkedHashMap;

/**
 * Pac4j authentication token when the user is authenticated.
 *
 * @author Jerome Leleu
 * @since 2.0.0
 */
public class Pac4jAuthenticationToken extends AbstractAuthenticationToken implements Pac4jAuthentication {

    private final LinkedHashMap<String, CommonProfile> profiles;
    private final CommonProfile profile;

    public Pac4jAuthenticationToken(final LinkedHashMap<String, CommonProfile> profiles) {
        super(SpringSecurityHelper.buildAuthorities(profiles));
        this.profiles = profiles;
        this.profile = ProfileHelper.flatIntoOneProfile(profiles).get();
        setAuthenticated(true);
    }

    @Override
    public String getName() {
        return profile.getId();
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return profile;
    }

    @Override
    public LinkedHashMap<String, CommonProfile> getInternalProfilesMap() {
        return this.profiles;
    }
}
