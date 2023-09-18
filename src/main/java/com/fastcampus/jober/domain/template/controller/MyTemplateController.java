package com.fastcampus.jober.domain.template.controller;

import com.fastcampus.jober.core.auth.session.MemberDetails;
import com.fastcampus.jober.domain.template.dto.TemplateResponseDto.ListDto;
import com.fastcampus.jober.domain.template.service.MyTemplateService;
import com.fastcampus.jober.global.util.ApiUtil;
import com.fastcampus.jober.global.util.ApiUtil.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-templates")
@RequiredArgsConstructor
public class MyTemplateController {

    private final MyTemplateService myTemplateService;

    //@TODO: 예외처리
    @GetMapping
    public ResponseEntity<Response> myTemplateList(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestParam(required = false, defaultValue = "0") int page) {
        page = page == 0 ? 0 : page - 1;

        Page<ListDto> myTemplates = myTemplateService.findMyTemplates(page, 10,
            memberDetails.getMember());

        return ResponseEntity.ok(ApiUtil.result(HttpStatus.OK.value(), "정상적으로 처리되었습니다.",
            myTemplates));
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<Response> myTemplateRemove(
        @AuthenticationPrincipal MemberDetails memberDetails, @RequestParam Long templateId) {
        int result = myTemplateService.removeMyTemplate(memberDetails.getMember(), templateId);

        return ResponseEntity.ok(ApiUtil.result(HttpStatus.OK.value(), "정상적으로 처리되었습니다.", result));
    }
}
