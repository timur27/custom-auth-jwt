package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;

public class UserWrapper {
    public static UserDto from(PersistedUser user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword());
    }

    public static PersistedUser from (UserDto user) {
        return new PersistedUser(user.getEmail(), user.getPassword());
    }
}
