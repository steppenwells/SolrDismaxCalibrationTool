package com.github.steppenwells.client;

import com.github.steppenwells.client.model.DismaxField;
import com.github.steppenwells.client.model.SolrSearchResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface SolrServiceAsync {

    void getFields(String solrUrl, AsyncCallback<List<DismaxField>> callback);

    void getResultsFor(String dismaxQueryString, String query, String solrUrl, AsyncCallback<List<SolrSearchResult>> callback);
}
