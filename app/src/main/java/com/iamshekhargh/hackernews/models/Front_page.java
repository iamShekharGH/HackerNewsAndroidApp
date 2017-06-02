package com.iamshekhargh.hackernews.models;

/**
 * Created by <<-- iamShekharGH -->>
 * on 03 May 2017
 * at 12:19 PM.
 */


import java.util.List;

/**
 * {
 * "hits":[  ],
 * "nbHits":49,
 * "page":0,
 * "nbPages":3,
 * "hitsPerPage":20,
 * "processingTimeMS":6,
 * "exhaustiveNbHits":true,
 * "query":"",
 * "params":"advancedSyntax=true\u0026analytics=false\u0026tags=front_page"
 * }
 */
public class Front_page {

    int nbHits;
    int page;
    int nbPages;
    int hitsPerPage;
    int processingTimeMS;
    boolean exhaustiveNbHits;
    String query;
    String params;
    List<StoryItem> hits;

    public int getNbHits() {
        return nbHits;
    }

    public void setNbHits(int nbHits) {
        this.nbHits = nbHits;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public int getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(int hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public int getProcessingTimeMS() {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(int processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    public boolean isExhaustiveNbHits() {
        return exhaustiveNbHits;
    }

    public void setExhaustiveNbHits(boolean exhaustiveNbHits) {
        this.exhaustiveNbHits = exhaustiveNbHits;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<StoryItem> getHits() {
        return hits;
    }

    public void setHits(List<StoryItem> hits) {
        this.hits = hits;
    }
}
