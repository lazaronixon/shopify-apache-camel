package com.koombea.shopifyintegrator.routes;

import com.koombea.shopifyintegrator.models.erp.Product;
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
                .to("direct:fetchProductsERP")
                .to("direct:sendProductsToShopify")
                .to("direct:updateProductsRefreshedAt")
                .log("Aguardando...");
        
        from("direct:fetchProductsERP")
                .toD("{{app.erp.domain}}/products.json?since=${header.LastRefresh}");
        
       from("direct:sendProductsToShopify")
                .split().jsonpathWriteAsString("$")
                .unmarshal().json(Product.class)
                .log("Sending product ${body.id}");

        from("direct:updateProductsRefreshedAt")
                .bean(ParameterService.class, "updateLastRefresh");
    }
}
