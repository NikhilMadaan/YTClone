package com.youtube.YTClone.config;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audience;
    public AudienceValidator(String audience) {

        this.audience = audience;
    }
    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
       if(token.getAudience().contains(audience))
        return OAuth2TokenValidatorResult.success();
       return OAuth2TokenValidatorResult.failure(new OAuth2Error("Invalid audience for the given token"));
    }

}
