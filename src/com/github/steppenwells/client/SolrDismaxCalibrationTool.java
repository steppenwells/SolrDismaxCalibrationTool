package com.github.steppenwells.client;

import com.github.steppenwells.client.model.DismaxField;
import com.github.steppenwells.client.model.SolrSearchResult;
import com.github.steppenwells.client.ui.DismaxFieldControl;
import com.github.steppenwells.client.ui.ResultDisplayControl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SolrDismaxCalibrationTool implements EntryPoint {

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final SolrServiceAsync solrService = GWT.create(SolrService.class);

    private TextBox solrServerUrlEntryField;
    private final List<DismaxFieldControl> fieldControls = new ArrayList<DismaxFieldControl>();
    private TextBox queryStringEntryField;
    //TODO temp result display solution
    private List<ResultDisplayControl> resultsDisplayControls = new ArrayList<ResultDisplayControl>();

    private VerticalPanel fieldsPanel;
    private VerticalPanel queryReportingPanel;
    private VerticalPanel resultsPanel;

    private final DismaxToolState dismaxToolState;
    private Label queryDisplayLabel;

    public SolrDismaxCalibrationTool() {
        dismaxToolState = new DismaxToolState(this, solrService);
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        final VerticalPanel toolLayoutPanel = new VerticalPanel();

        HorizontalPanel solrUrlPanel = createSolrUrlPanel();
        toolLayoutPanel.add(solrUrlPanel);

        fieldsPanel = new VerticalPanel();
        queryReportingPanel = initQueryReportingPanel();
        resultsPanel = initResultsPanel();

        HorizontalPanel calibrationControls = new HorizontalPanel();
        calibrationControls.add(fieldsPanel);
        calibrationControls.add(queryReportingPanel);
        toolLayoutPanel.add(calibrationControls);

        toolLayoutPanel.add(resultsPanel);

        RootPanel.get("fieldsContainer").add(toolLayoutPanel);

    }

    private VerticalPanel initResultsPanel() {
        VerticalPanel panel = new VerticalPanel();

        return panel;
    }

    private VerticalPanel initQueryReportingPanel() {
        VerticalPanel panel = new VerticalPanel();

        this.queryDisplayLabel = new Label();
        panel.add(queryDisplayLabel);

        panel.add(new Label("search for:"));
        this.queryStringEntryField = new TextBox();
        panel.add(queryStringEntryField);

        return panel;
    }

    private void showFields(String solrUrl) {
        dismaxToolState.getFieldsFor(solrUrl);

        for (DismaxFieldControl fieldControl : fieldControls) {
            fieldsPanel.remove(fieldControl);
        }
        fieldControls.clear();

        for (DismaxField dismaxField : dismaxToolState.getFields()) {
            DismaxFieldControl dismaxFieldControl = new DismaxFieldControl(dismaxField, dismaxToolState);
            fieldControls.add(dismaxFieldControl);
            fieldsPanel.add(dismaxFieldControl);
        }

    }

    private HorizontalPanel createSolrUrlPanel() {
        HorizontalPanel solrUrlPanel = new HorizontalPanel();
        solrUrlPanel.add(new Label("Root url of the solr server"));
        solrServerUrlEntryField = new TextBox();
        solrServerUrlEntryField.setTitle("Root url of the solr server");
        solrUrlPanel.add(solrServerUrlEntryField);

        Button connectButton = new Button("Connect");

        connectButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                showFields(solrServerUrlEntryField.getValue());
            }
        });

        solrUrlPanel.add(connectButton);
        return solrUrlPanel;
    }

    public List<DismaxFieldControl> getFieldControls() {
        return fieldControls;
    }

    public TextBox getSolrServerUrlEntryField() {
        return solrServerUrlEntryField;
    }

    public void displayQueryString(String dismaxQueryString) {
        queryDisplayLabel.setText(dismaxQueryString);
    }

    public void displayResults(List<SolrSearchResult> results) {

        for (ResultDisplayControl control : resultsDisplayControls) {
            resultsPanel.remove(control);
        }
        resultsDisplayControls.clear();

        for (SolrSearchResult result : results) {
            ResultDisplayControl control = new ResultDisplayControl(result);
            resultsDisplayControls.add(control);
            resultsPanel.add(control);
        }
    }

    public String getQueryString() {
        return queryStringEntryField.getValue();
    }
}
