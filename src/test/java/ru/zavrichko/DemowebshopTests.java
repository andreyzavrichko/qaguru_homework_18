package ru.zavrichko;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class DemowebshopTests {
    @Test
    void addToWishListWithCookiesTest() {
        Integer wishListSize = 18;

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e; __utma=78382081.1872251004.1646329600.1646329600.1646329600.1; __utmc=78382081; __utmz=78382081.1646329600.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NOPCOMMERCE.AUTH=8C1165173691941C493DB9FADA8EDE2A132723F5B9CEA3BCA668AA9DFC9409F18216AB75A8EF11C3E5FEF41FE306B81D1E611636076E20DFE805456F289E37ECEB8F0A598D05D1580CEA3B0E950C6C5932D3D51AD23820A2788A4FF7B2F78008122BB96043D4FD443B94D2EED366D10F7EEF6454B048056AE2396C3CB763B159EC7C9D2A3D05E5B7FE363B2C4BDDF2EA; Nop.customer=9345ab71-54a6-468e-a89a-20fd34d3cc66; __utmt=1; nop.CompareProducts=CompareProductIds=2; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=14&RecentlyViewedProductIds=13&RecentlyViewedProductIds=2&RecentlyViewedProductIds=72; __atuvc=5%7C9; __atuvs=622101ba1668df0d004; __utmb=78382081.22.10.1646329600")
                .body("addtocart_14.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/14/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(" + wishListSize + ")"));
    }

    @Test
    void addToWishListTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("addtocart_14.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/14/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(1)"));
    }
}
