package com.github.steppenwells.client;

import com.github.steppenwells.client.model.DismaxField;
import com.github.steppenwells.client.model.SolrSearchResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("dismaxsolrservice")
public interface SolrService extends RemoteService {

    List<DismaxField> getFields(String solrUrl);

    List<SolrSearchResult> getResultsFor(String dismaxQueryString, String queryString ,String solrUrl);
    
}
