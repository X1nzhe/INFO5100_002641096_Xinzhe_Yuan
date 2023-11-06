module com.info5100.fourfunccalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.info5100.fourfunccalculator to javafx.fxml;
    exports com.info5100.fourfunccalculator;
}