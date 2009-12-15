package com.github.steppenwells.server;

import com.github.steppenwells.client.SolrService;
import com.github.steppenwells.client.model.DismaxField;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;

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
}
