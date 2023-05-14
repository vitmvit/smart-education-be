package com.vitmvit.smarteducation.model.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PageContentResponse<T> {

    PageResponse page;
    List<T> content;
}