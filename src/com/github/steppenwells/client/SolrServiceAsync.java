package com.github.steppenwells.client;

import com.github.steppenwells.client.model.DismaxField;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface SolrServiceAsync {

    void getFields(AsyncCallback<List<DismaxField>> callback);
}
