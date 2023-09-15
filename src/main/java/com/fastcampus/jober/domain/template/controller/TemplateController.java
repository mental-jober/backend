package com.fastcampus.jober.domain.template.controller;

import com.fastcampus.jober.domain.template.dto.TemplateResponseDto.ListDto;
import com.fastcampus.jober.domain.template.service.TemplateService;
import com.fastcampus.jober.global.util.ApiUtil;
import com.fastcampus.jober.global.util.ApiUtil.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    public ResponseEntity<Response> templateList(
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false, defaultValue = "0") int page) {
        page = page == 0 ? 0 : page - 1;

        Page<ListDto> templates = templateService.findTemplates(type, keyword, page, 10);

        return ResponseEntity.ok(
            ApiUtil.result(HttpStatus.OK.value(), "정상적으로 처리되었습니다.", templates));
    }
}
