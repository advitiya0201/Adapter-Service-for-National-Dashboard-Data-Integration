package com.niua.adapter.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GroupedData {
    private String groupBy;
    private List<Bucket> buckets;
}
