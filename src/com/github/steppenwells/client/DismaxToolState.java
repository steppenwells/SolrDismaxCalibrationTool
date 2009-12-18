package com.github.steppenwells.client;

import com.github.steppenwells.client.model.DismaxField;
import com.github.steppenwells.client.model.SolrSearchResult;
import com.github.steppenwells.client.ui.DismaxFieldControl;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

public class DismaxToolState {
    private SolrDismaxCalibrationTool solrDismaxCalibrationTool;
    private SolrServiceAsync solrService;

    private List<DismaxField> fields = new ArrayList<DismaxField>();
    private List<SolrSearchResult> results = new  ArrayList<SolrSearchResult>();

    private String dismaxQueryString;

    public DismaxToolState(SolrDismaxCalibrationTool solrDismaxCalibrationTool, SolrServiceAsync solrService) {
        this.solrDismaxCalibrationTool = solrDismaxCalibrationTool;
        this.solrService = solrService;
    }

    public void weightingsChanged() {

        updateFieldStateFromControls();
        recalculateDismaxQueryString();
        solrDismaxCalibrationTool.displayQueryString(dismaxQueryString);

        solrService.getResultsFor(dismaxQueryString,
                solrDismaxCalibrationTool.getQueryString(),
                solrDismaxCalibrationTool.getSolrServerUrlEntryField().getValue(),
                new AsyncCallback<List<SolrSearchResult>>() {

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<SolrSearchResult> solrSearchResults) {
                results = solrSearchResults;
                solrDismaxCalibrationTool.displayResults(results);
            }
        });

    }

    private void recalculateDismaxQueryString() {

        StringBuilder queryBuilder = new StringBuilder();
        String delimit = "";
        for (DismaxField field : fields) {
            if (field.isInUse()) {
                queryBuilder.append(delimit)
                        .append(field.getFieldName())
                        .append("^")
                        .append(field.getWeight());

                delimit = " ";
            }
        }
        dismaxQueryString = queryBuilder.toString();
    }

    private void updateFieldStateFromControls() {
        List<DismaxFieldControl> fieldControlList = solrDismaxCalibrationTool.getFieldControls();
        List<DismaxField> updatedDismaxFields = new ArrayList<DismaxField>();
        for (DismaxFieldControl fieldControl : fieldControlList) {
            updatedDismaxFields.add(fieldControl.getDismaxField());
        }
        fields = updatedDismaxFields;
    }

    public void getFieldsFor(String solrUrl) {
        solrService.getFields(solrUrl, new AsyncCallback<List<DismaxField>>() {


            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<DismaxField> dismaxFields) {
                fields.clear();
                fields.addAll(dismaxFields);
            }
        });
    }

    public List<DismaxField> getFields() {
        return fields;
    }

    public String getDismaxQueryString() {
        return dismaxQueryString;
    }
}
