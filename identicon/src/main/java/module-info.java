module dev.ikm.komet.identicon {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.ikm.komet.identicon to javafx.fxml;
    exports dev.ikm.komet.identicon;
}