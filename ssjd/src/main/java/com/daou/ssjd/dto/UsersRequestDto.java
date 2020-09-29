package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UsersRequestDto {

    @NotNull
    private String nickname;
    private String password;

    @Builder
    public UsersRequestDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
