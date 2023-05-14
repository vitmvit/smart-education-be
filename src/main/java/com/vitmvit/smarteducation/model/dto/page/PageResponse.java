package com.vitmvit.smarteducation.model.dto.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse {

    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private long offset;
    private boolean hasPrevious;

    public PageResponse(long totalElements, int totalPages, int pageNumber, int pageSize, long offset, boolean hasPrevious) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        // для смещения от нуля, так мы избавляемся от страницы под номером 0 (нуль)
        this.pageNumber = pageNumber + 1;
        this.pageSize = pageSize;
        this.offset = offset;
        this.hasPrevious = hasPrevious;
    }

    public PageResponse() {
        this.totalElements = 0;
        this.totalPages = 0;
        this.pageNumber = 1;
        this.pageSize = 0;
        this.offset = 0;
        this.hasPrevious = false;
    }

    public void setPageNumber(int pageNumber) {
        // для смещения от нуля, так мы избавляемся от страницы под номером 0 (нуль)
        this.pageNumber = pageNumber + 1;
    }
}
