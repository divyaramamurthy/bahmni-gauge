package org.bahmni.gauge.possible.specs;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.specs.RegistrationFirstPageSpec;
import org.bahmni.gauge.possible.registration.PossibleRegistrationFirstPage;

public class PossibleRegistrationFirstPageSpec extends RegistrationFirstPageSpec {

    public PossibleRegistrationFirstPageSpec() {
        super();
        this.registrationFirstPage = PageFactory.get(PossibleRegistrationFirstPage.class);
    }

    @Step("Create the following patient <table>")
    public void createPatients(Table table) throws Exception {
        this.registrationFirstPage.createPatients(table);
    }
}
