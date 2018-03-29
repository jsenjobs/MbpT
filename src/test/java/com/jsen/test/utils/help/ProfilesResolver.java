package com.jsen.test.utils.help;

import org.springframework.test.context.ActiveProfilesResolver;

public class ProfilesResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> aClass) {
        String activeProfiles = System.getProperty("spring.profiles.active");
        System.out.println(activeProfiles);
        return new String[] {activeProfiles != null ? activeProfiles : "local"};
    }
}


