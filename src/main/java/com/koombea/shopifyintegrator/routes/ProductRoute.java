package com.koombea.shopifyintegrator.routes;

import com.koombea.shopifyintegrator.services.ParameterService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("scheduler:fetchProductsTimer?delay={{app.delay}}")
                .setHeader("LastRefresh", method(ParameterService.class, "getLastRefresh"))
                .log("Searching for new products since ${header.LastRefresh}")
                .to("direct:updateProductsLastRefresh")
                .log("Aguardando...");

        from("direct:updateProductsLastRefresh")
                .bean(ParameterService.class, "updateLastRefresh");
    }
}
