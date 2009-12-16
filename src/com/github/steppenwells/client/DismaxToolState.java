package com.github.steppenwells.client;

import com.github.steppenwells.client.model.DismaxField;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

public class DismaxToolState {
    private SolrDismaxCalibrationTool solrDismaxCalibrationTool;
    private SolrServiceAsync solrService;

    private final List<DismaxField> fields = new ArrayList<DismaxField>();

    private String dismaxQueryString;

    public DismaxToolState(SolrDismaxCalibrationTool solrDismaxCalibrationTool, SolrServiceAsync solrService) {
        this.solrDismaxCalibrationTool = solrDismaxCalibrationTool;
        this.solrService = solrService;
    }

    public void weightingsChanged() {

        recalculateDismaxQueryString();
        solrDismaxCalibrationTool.displayQueryString();

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
