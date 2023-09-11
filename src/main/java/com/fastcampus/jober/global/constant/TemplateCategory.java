package com.fastcampus.jober.global.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TemplateCategory {
    RECENT("최근"), PERSONAL("개인"), COMPANY("회사"), SURVEY("설문"),
    CONTRACT("계약"), LAW("법률");

    private String name;
}
