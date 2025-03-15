package com.rafael.pokemon.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponseDTO<T> {
  private final List<T> content;
  private final long totalElements;
  private final int totalPages;
  private final int size;
  private final int number;
  private final boolean first;
  private final boolean last;

  public PageResponseDTO(Page<T> page) {
    this.content = page.getContent();
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.size = page.getSize();
    this.number = page.getNumber();
    this.first = page.isFirst();
    this.last = page.isLast();
  }
}
