package com.example.umc10th.global.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record PageResDTO<T>(
        List<T> data,
        PageInfoDTO pageInfo
) {
    @Builder
    public record PageInfoDTO(
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            Boolean hasNext
    ) {}
}
