package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersResponseDto {

    @NotNull
    private String nickname;
    private String token;

    @Builder
    public UsersResponseDto(String nickname, String token) {
        this.nickname = nickname;
        this.token = token;
    }
}
