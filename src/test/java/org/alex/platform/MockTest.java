package org.alex.platform;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.mock.Expectation;
import org.mockserver.model.KeyMatchStyle;
import org.mockserver.model.Parameters;

import static org.mockserver.model.ConnectionOptions.connectionOptions;
import static org.mockserver.model.HttpError.error;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.NottableSchemaString.schemaString;
import static org.mockserver.model.NottableString.string;
import static org.mockserver.model.Parameter.param;
import static org.mockserver.model.Parameter.schemaParam;
import static org.mockserver.model.StringBody.subString;
import static org.mockserver.model.XPathBody.xpath;

public class MockTest {

    public static void main(String[] args) {

        // restful 风格的url
        // key word:match request by path parameter regex value

        ClientAndServer server = new ClientAndServer(1080);

        server.when(
                request()
                        .withPath("/some/path")
                        .withPathParameters(
                                param("cartId", "055CA455-1DF7-45BB-8535-4F83E7266092")
                        )
                        .withQueryStringParameters(
                                param("type", "[A-Z0-9\\-]+")
                        )
        ).respond(
                response()
                        .withBody("mock successfully")
        );

//
//        server = new ClientAndServer(7777);
//
//        server.when(
//                request()
//                        .withMethod("GET")
//                        .withPath("/hello/good")
//        ).respond(
//                response()
//                        .withBody("mock successfully")
//        );
    }
}
