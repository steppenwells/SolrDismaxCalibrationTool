package com.github.steppenwells.server;

import com.github.steppenwells.client.SolrService;
import com.github.steppenwells.client.model.DismaxField;
import com.github.steppenwells.client.model.SolrSearchResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolrServiceImpl extends RemoteServiceServlet implements SolrService {

    @Override
    public List<DismaxField> getFields(String solrUrl) {
        //TODO work out what the fields are properly, currently hard coded to newspaper example

        List<DismaxField> fields = new ArrayList<DismaxField>();

        fields.add(new DismaxField("headline", 1.0, true));
        fields.add(new DismaxField("byline", 1.0, true));
        fields.add(new DismaxField("strap", 1.0, true));
        fields.add(new DismaxField("body", 1.0, true));
        fields.add(new DismaxField("tag-external-names", 1.0, true));
        fields.add(new DismaxField("web-title", 1.0, true));

        return fields;
    }

    @Override
    public List<SolrSearchResult> getResultsFor(String dismaxQueryString, String queryString, String solrUrl) {
        try {
            CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(solrUrl);
            SolrQuery query = new SolrQuery();
            query.setParam("defType", "dismax");
            query.setParam("qf", dismaxQueryString);

            query.setHighlight(true);
            query.setHighlightRequireFieldMatch(true);

            query.setQuery(queryString);

            QueryResponse queryResponse = solrServer.query(query);

            List<SolrSearchResult> resultList = new ArrayList<SolrSearchResult>();

            Map<String, Map<String,List<String>>> highlightResults = queryResponse.getHighlighting();
            for (String highlightKey : highlightResults.keySet()) {
                SolrSearchResult result = new SolrSearchResult();
                result.put("id", highlightKey);

                Map<String, List<String>> highlightFields = highlightResults.get(highlightKey);
                for(String fieldKey : highlightFields.keySet()) {
                    result.put(fieldKey, highlightFields.get(fieldKey));
                }

                resultList.add(result);
            }

            return resultList;

        } catch (Exception e) {
            throw new RuntimeException("failed to query " + solrUrl, e);
        }

    }
}
