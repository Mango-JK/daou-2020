package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersUpdateRequestDto {

    @NotNull
    private String nickname;
    private String newNickname;

    @Builder
    public UsersUpdateRequestDto (String nickname, String newNickname) {
        this.nickname = nickname;
        this.newNickname = newNickname;
    }
}
