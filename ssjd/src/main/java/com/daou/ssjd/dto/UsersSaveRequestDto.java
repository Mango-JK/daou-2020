package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UsersSaveRequestDto {

    @NotNull
    private String nickname;
    private String password;

    @Builder
    public UsersSaveRequestDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
