package com.skip.challenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PagedContent<T> {

    @JsonProperty("recordCount")
    public long recordCount;

    @JsonProperty("data")
    public List<T> data;

    public PagedContent(long recordCount, List<T> data) {
        this.recordCount = recordCount;
        this.data = data;
    }

}
