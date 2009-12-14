package com.github.steppenwells.client.ui;

import com.github.steppenwells.client.model.DismaxField;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;

public class DismaxFieldControl extends Composite {

    private DismaxField dismaxField;

    private CheckBox enabledCheckbox;
    private TextBox weightTextBox;

    public DismaxFieldControl(DismaxField dismaxField) {
        this.dismaxField = dismaxField;
        initUi();
    }

    private void initUi() {
        HorizontalPanel panel = new HorizontalPanel();

        enabledCheckbox = new CheckBox(dismaxField.getFieldName());
        enabledCheckbox.setEnabled(dismaxField.isInUse());
        enabledCheckbox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> booleanValueChangeEvent) {
                dismaxField.setInUse(booleanValueChangeEvent.getValue());
            }
        });
        panel.add(enabledCheckbox);

        // TODO: is there a more appropriate control than a straight textbox?
        weightTextBox = new TextBox();
        weightTextBox.setValue(String.valueOf(dismaxField.getWeight()));
        weightTextBox.addValueChangeHandler(new ValueChangeHandler<String> () {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                String weightString = stringValueChangeEvent.getValue();
                try {
                    double weight = Double.parseDouble(weightString);
                    dismaxField.setWeight(weight);
                } catch (NumberFormatException nfe) {
                    //TODO put error label here.
                }
            }
        });
        panel.add(weightTextBox);

        initWidget(panel);

    }

    public DismaxField getDismaxField() {
        return dismaxField;
    }
}
