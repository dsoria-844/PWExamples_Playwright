package org.example;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class TP5_Punto_1 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(500));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            //Login exitoso, agregar un producto al carrito, ingresar al carrito y verificar que el producto coincida con lo seleccionado, screenshot y logout.
            page.navigate("https://www.saucedemo.com/");
            page.locator("[data-test=\"username\"]").click();
            page.locator("[data-test=\"username\"]").fill("standard_user");
            page.locator("[data-test=\"password\"]").click();
            page.locator("[data-test=\"password\"]").fill("secret_sauce");
            page.locator("[data-test=\"login-button\"]").click();
            System.out.println("Login realizado exitosamente."); // Verificar que el login sea exitoso.


            String firstProduct = page.textContent(".inventory_item_name");

            page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click();
            page.locator("[data-test=\"shopping-cart-link\"]").click();

            Locator Product_in_car = page.locator(".cart_list .inventory_item_name").first();
            assertThat(Product_in_car).hasText(firstProduct);


            System.out.println("El producto en el carrito coincide correctamente con:" + firstProduct); // Verificar que el carrito contenga el producto correcto.
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("CarritoCorrecto.png"))); // Verificar que se genere la captura de pantalla


            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu")).click();
            page.locator("[data-test=\"logout-sidebar-link\"]").click();
            page.locator("form").click();

            // Verificar que al hacer logout se regrese al formulario de login
            assertThat(page).hasURL("https://www.saucedemo.com/"); // URL volvió al login
            assertThat(page.locator("[data-test=\"login-button\"]")).isVisible(); // login visible nuevamente

            System.out.println("Test realizado correctamente.");


        }
    }
}