package com.martin.ecommerce.domain.mapper;

import com.martin.ecommerce.domain.dto.common.PaginationResponse;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Page;

public class PaginationMapper {

  public static <T, R> PaginationResponse<R> toPaginationResponse(
      List<T> content, Page<T> page, Function<T, R> mapper) {
    if (content == null || page == null || mapper == null) {
      return null;
    }

    List<R> mappedContent = content.stream()
        .map(mapper)
        .toList();

    return new PaginationResponse<>(
        mappedContent,
        page.getNumber(),
        page.getSize(),
        (int) page.getTotalElements(),
        page.getTotalPages()
    );
  }
}
