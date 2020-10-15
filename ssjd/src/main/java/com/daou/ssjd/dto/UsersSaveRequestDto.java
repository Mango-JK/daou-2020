package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersSaveRequestDto {

    @NotNull
    private String nickname;
    private String password;
    private String salt;

    @Builder
    public UsersSaveRequestDto(String nickname, String password, String salt) {
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
    }
}
