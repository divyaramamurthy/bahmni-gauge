package org.bahmni.gauge.endtb.specs;

import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.endtb.clinical.endTBBacteriologyPage;

public class endTBPageFactory extends PageFactory {


    private static final String EndTB_BACTERIOLOGY_PAGE = "endtb.clinical.bacteriology";

    public static endTBBacteriologyPage getEndTB_BACTERIOLOGY_PAGE() {
        return (endTBBacteriologyPage) get(EndTB_BACTERIOLOGY_PAGE);
    }

}
