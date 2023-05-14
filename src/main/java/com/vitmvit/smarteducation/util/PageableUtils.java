package com.vitmvit.smarteducation.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PageableUtils {

    private static final int PAGE_SIZE = 50;

    public static Pageable getPageable(int pageNumber) {
        return getPageable(pageNumber, PAGE_SIZE, true, "id");
    }

    public static Pageable getPageable(int pageNumber, int pageSize) {
        return getPageable(pageNumber, pageSize, true, "id");
    }

    public static Pageable getPageable(int pageNumber, int pageSize, boolean asc) {
        return getPageable(pageNumber, pageSize, asc, "id");
    }

    public static Pageable getPageable(int pageNumber, int pageSize, String sortField) {
        return getPageable(pageNumber, pageSize, true, sortField);
    }

    public static Pageable getPageable(int pageNumber, boolean asc) {
        return getPageable(pageNumber, PAGE_SIZE, asc, "id");
    }

    public static Pageable getPageable(int pageNumber, String sortField) {
        return getPageable(pageNumber, PAGE_SIZE, true, sortField);
    }

    public static Pageable getPageable(int pageNumber, boolean asc, String sortField) {
        return getPageable(pageNumber, PAGE_SIZE, asc, sortField);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static Pageable getPageable(int pageNumber, int pageSize, boolean asc, String sortField) {
        return PageRequest.of(pageNumber - 1, pageSize, asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
    }
}