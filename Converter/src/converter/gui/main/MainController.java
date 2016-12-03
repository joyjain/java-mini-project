package converter.gui.main;

import javax.annotation.PostConstruct;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import converter.UnitTypes;
import converter.converters.*;

@FXMLController(value = "/resources/fxml/Main.fxml", title = "Converter v1.0")
public class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root, mainbody;

    @FXML
    private JFXComboBox types, inputType, outputType;

    @FXML
    private JFXTextField inputText, outputText;

    @FXML
    private JFXButton convert, swap, clear, about, ok;

    @FXML
    private JFXDialog dialog;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        root.getChildren().remove(dialog);

        // init the types combo
        for (String type : UnitTypes.types) {
            types.getItems().add(new Label(type));
        }

        // on changing conversion type
        types.setOnAction((e) -> {
            // remove all children of inputTypes and outoutTypes if present
            inputType.getItems().clear();
            outputType.getItems().clear();
            // add new items according to the current selection index
            for (String unit : UnitTypes.units[types.getSelectionModel().getSelectedIndex()]) {
                inputType.getItems().add(new Label(unit));
                outputType.getItems().add(new Label(unit));
            }
        });

        // validate input and output
        inputText.focusedProperty().addListener((o, oldVal, newVal) -> {
            inputText.validate();
        });

        // convert input to output
        convert.setOnMouseClicked((e) -> {
            Double inputData = 0.0,
                    outputData = 0.0;
            try {
                inputData = Double.parseDouble(inputText.getText());
            } catch (NumberFormatException err) {
                inputText.validate();
            } finally {
                if (inputData != 0.0) {
                    int type1 = 0,
                            type2 = 0,
                            unitofType = types.getSelectionModel().getSelectedIndex(),
                            from = inputType.getSelectionModel().getSelectedIndex(),
                            to = outputType.getSelectionModel().getSelectedIndex();
                    switch (unitofType) {
                        case 0:
                            if (from > 2) {
                                type1 = 1;
                                from -= 3;
                            }
                            if (to > 2) {
                                type2 = 1;
                                to -= 3;
                            }
                            Area a = new Area(from, to, type1, type2, inputData);
                            outputData = a.convert();
                            break;
                        case 1:
                            if (from > 4) {
                                type1 = 1;
                                from -= 5;
                            }
                            if (to > 4) {
                                type2 = 1;
                                to -= 5;
                            }
                            DataTransferRate dtr = new DataTransferRate(from, to, type1, type2, inputData);
                            outputData = dtr.convert();
                            break;
                        case 2:
                            if (from > 5) {
                                type1 = 1;
                                from -= 6;
                            }
                            if (to > 5) {
                                type2 = 1;
                                to -= 6;
                            }
                            DigitalStorage ds = new DigitalStorage(from, to, type1, type2, inputData);
                            outputData = ds.convert();
                            break;
                        case 3:
                            Frequency f = new Frequency(from, to, inputData);
                            outputData = f.convert();
                            break;
                        case 4:
                            if (from > 5) {
                                type1 = 1;
                                from -= 6;
                            }
                            if (to > 5) {
                                type2 = 1;
                                to -= 6;
                            }
                            Length l = new Length(from, to, type1, type2, inputData);
                            outputData = l.convert();
                            break;
                        case 5:
                            Temperature t = new Temperature(from, to, inputData);
                            outputData = t.convert();
                            break;
                        case 6:
                            if (from > 2) {
                                type1 = 1;
                                from -= 3;
                            }
                            if (to > 2) {
                                type2 = 1;
                                to -= 3;
                            }
                            Volume v = new Volume(from, to, type1, type2, inputData);
                            outputData = v.convert();
                            break;
                        default:
                    }
                    outputText.setText(outputData.toString());
                }
            }
        });

        // swap units if swapable
        swap.setOnMouseClicked((e) -> {
            // check if the selected conversion type is in a valid state
            if (types.getSelectionModel().getSelectedIndex() > -1) {
                int from = inputType.getSelectionModel().getSelectedIndex(),
                        to = outputType.getSelectionModel().getSelectedIndex();
                from += to;
                to = from - to;
                from -= to;
                // now set them
                inputType.getSelectionModel().select(from);
                outputType.getSelectionModel().select(to);
            }
        });

        // clear input and output
        clear.setOnMouseClicked((e) -> {
            inputText.clear();
            outputText.clear();
        });

        // show about us dialog
        about.setOnMouseClicked((e) -> {
            dialog.setTransitionType(DialogTransition.CENTER);
            dialog.show(mainbody);
        });

        // hide about us dialog
        ok.setOnMouseClicked((e) -> {
            dialog.close();
        });
    }
}
