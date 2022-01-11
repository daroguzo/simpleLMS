package com.daroguzo.simplelms.admin.model;

import lombok.Data;

@Data
public class MemberParam {

    int pageIndex;
    long pageSize;

    String searchType;
    String searchValue;

    public long getPageStart() {
        init();
        return (pageIndex - 1) * pageSize;
    }

    public long getPageEnd() {
        init();
        return pageSize;
    }

    public void init() {
        // 최소 시작 1
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        // 최소 끝 10
        if (pageSize < 10) {
            pageSize = 10;
        }
    }

    public String getQueryString() {
        init();
        StringBuilder sb = new StringBuilder();

        if (searchType != null && searchType.length() > 0) {
            sb.append(String.format("searchType=%s", searchType));
        }

        if (sb.length() > 0) {
            sb.append("&");
        }

        if (searchType != null && searchType.length() > 0) {
            sb.append(String.format("searchValue=%s", searchValue));
        }

        return sb.toString();
    }
}
