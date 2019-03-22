package com.training.util;

import java.util.List;

/**
 * Util class for pagination
 *
 * @author Oleg Dibrov
 */
public class Pagination {

    /**
     * list for pagination field
     */
    private List<?> allEntities;
    /**
     * max records per page field
     */
    private int recordsPerPage;
    /**
     * current page field
     */
    private int page;

    /**
     * constructor for pagination instantiating
     *
     * @param allEntities    list for pagination
     * @param recordsPerPage max records per page
     */
    private Pagination(List<?> allEntities, int recordsPerPage) {
        this.allEntities = allEntities;
        this.recordsPerPage = recordsPerPage;
    }

    /**
     * calculates total number of pages
     *
     * @return number of pages
     */
    public int getNumberOfPages() {
        int noOfRecords = allEntities.size();
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }

    /**
     * @return {@link #page}
     */
    public int getCurrentPage(String page) {
        if (page == null) {
            this.page = 1;
        } else {
            this.page = Integer.parseInt(page);
        }
        return this.page;
    }

    /**
     * paginates list
     *
     * @return paginated list
     */
    public List<?> paginate() {
        int offset = (page - 1) * recordsPerPage;
        if ((offset + recordsPerPage) > allEntities.size()) {
            return allEntities.subList(offset, allEntities.size());
        } else {
            return allEntities.subList(offset, offset + recordsPerPage);
        }
    }
    /**
     * returns paginator instance
     *
     * @param allEntities list for pagination
     * @param recordsPerPage max records per page
     *
     * @return paginator instance
     */
    public static Pagination getPaginator(List<?> allEntities, int recordsPerPage) {
        return new Pagination(allEntities, recordsPerPage);
    }
}
