package com.assignment6.service.dto;

import java.io.Serializable;
import java.util.List;

public class UserDetailsDTO implements Serializable {

    private Integer totalCount;

    private Boolean incompleteResults;

    private List<ItemsDTO> items;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<ItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemsDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "totalCount=" + totalCount +
                ", incompleteResults=" + incompleteResults +
                ", items=" + items +
                '}';
    }
}
