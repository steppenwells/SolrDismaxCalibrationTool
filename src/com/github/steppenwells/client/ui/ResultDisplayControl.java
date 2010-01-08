package com.github.steppenwells.client.ui;

import com.github.steppenwells.client.model.SolrSearchResult;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ResultDisplayControl extends com.google.gwt.user.client.ui.Composite {

    private final SolrSearchResult result;

    public ResultDisplayControl(SolrSearchResult result) {

        this.result = result;

        initUI();
    }

    private void initUI() {
        VerticalPanel panel = new VerticalPanel();

        List<String> fieldNames = new ArrayList(result.getFieldValueMap().keySet());
        Collections.sort(fieldNames);

        for(String fieldName : fieldNames) {
            HorizontalPanel fieldPanel = new HorizontalPanel();
            fieldPanel.add(new Label(fieldName + ":"));
            fieldPanel.add(new Label(result.getFieldValueMap().get(fieldName).toString()));
            panel.add(fieldPanel);
        }


        initWidget(panel);
    }
}
