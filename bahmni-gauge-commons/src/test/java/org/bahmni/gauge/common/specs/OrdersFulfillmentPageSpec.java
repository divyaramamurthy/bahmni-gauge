package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.clinical.domain.Order;
import org.bahmni.gauge.common.orders.OrdersFulfillmentPage;

import java.util.List;

public class OrdersFulfillmentPageSpec {
    private OrdersFulfillmentPage ordersFulfillmentPage;

    public OrdersFulfillmentPageSpec()
    {
        ordersFulfillmentPage= PageFactorySpec.getOrdersFulfillmentPage();
    }

    @BeforeClassSteps
    public void waitForAppReady(){
        ordersFulfillmentPage.waitForSpinner();
    }

    @Step("Verify orders on orders fulfilment page")
    public void verifyOrders(){
        ordersFulfillmentPage.verifyOrders(ordersFulfillmentPage.getPatientFromSpecStore().getOrders());
    }

    @Step("Fulfill all radiology order details as follow <table>")
    public void fulfilorders(Table table){
        List<Order> orders=ordersFulfillmentPage.getPatientFromSpecStore().getOrders();
        for(TableRow row:table.getTableRows()){
            for(Order order:orders){
                if(row.getCell("name").equals(order.getName())){
                    order.setNote(row.getCell("note"));
                    order.setImage(row.getCell("image"));
                }
            }
        }
        ordersFulfillmentPage.fulfill(ordersFulfillmentPage.getPatientFromSpecStore().getOrders());
    }

    @Step("Verify order details on orders fulfilment page")
    public void verfiyOrderDetails(){
        ordersFulfillmentPage.verifyOrdersDetails(ordersFulfillmentPage.getPatientFromSpecStore().getOrders());
    }
}
