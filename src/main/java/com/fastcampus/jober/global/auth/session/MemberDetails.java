package com.fastcampus.jober.global.auth.session;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class MemberDetails implements UserDetails {

    private final Member member;
    private final Long spaceWallId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getSpaceWallMember() == null) return null;
        if (this.spaceWallId == null) return null;

        for (SpaceWallMember spaceWallMember : member.getSpaceWallMember()) {
            if (this.spaceWallId == spaceWallMember.getSpaceWall().getId()) {
                authorities.add(
                        new SimpleGrantedAuthority(spaceWallMember.getSpaceWallPermissions().getAuths().name())
                );
                break;
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
