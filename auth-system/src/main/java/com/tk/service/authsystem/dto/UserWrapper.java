package com.tk.service.authsystem.dto;

import com.tk.service.donner.user.UserDto;

public class UserWrapper {
    public static UserDto from(PersistedUser user) {
        return new UserDto(user.getUsername(), user.getPassword());
    }

    public static PersistedUser from (UserDto user) {
        return new PersistedUser(user.getUsername(), user.getPassword());
    }
}
