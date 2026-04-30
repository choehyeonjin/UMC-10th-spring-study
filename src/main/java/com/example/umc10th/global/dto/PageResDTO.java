package com.example.umc10th.global.dto;

import lombok.Builder;

public class PageResDTO {

    @Builder
    public record PageInfoDTO(
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            Boolean hasNext)
    {}
}
