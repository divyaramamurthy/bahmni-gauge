package org.bahmni.gauge.endtb.specs;

import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.endtb.clinical.EndTBBacteriologyPage;

public class EndTBPageFactorySpec extends PageFactory {


    private static final String EndTB_BACTERIOLOGY_PAGE = "endtb.clinical.bacteriology";

    public static EndTBBacteriologyPage getEndTbBacteriologyPage() {
        return (EndTBBacteriologyPage) get(EndTB_BACTERIOLOGY_PAGE);
    }

}
