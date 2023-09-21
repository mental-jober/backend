package com.fastcampus.jober.global.auth.session;

/**
 * spaceWallId를 MemberDetails로 전달하기 위한 ContextHolder입니다.
 * 현재 예상되는 문제는, 로그인 후 새로 공유스페이스에 멤버 등록 시 해당 권한이 세션에 갱신되는가 입니다.
 */
public class SpaceWallContextHolder {

    private static final ThreadLocal<Long> spaceIdHolder = new ThreadLocal<>();

    public static Long getSpaceWallId() {
        return spaceIdHolder.get();
    }

    public static void setSpaceWallId(Long spaceWallId) {
        spaceIdHolder.set(spaceWallId);
    }

    public static void clearSpaceWallId() {
        spaceIdHolder.remove();
    }
}
