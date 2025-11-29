package org.example;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TP5_Punto_3 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(1000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://www.saucedemo.com/");
            page.locator("[data-test=\"username\"]").click();
            page.locator("[data-test=\"username\"]").fill("standard_user");
            page.locator("[data-test=\"username\"]").press("Tab");
            page.locator("[data-test=\"password\"]").fill("secret_sauce");
            page.locator("[data-test=\"login-button\"]").click();

            page.locator("[data-test=\"product-sort-container\"]").selectOption("za");
            page.locator("[data-test=\"product-sort-container\"]").selectOption("lohi");
            page.locator("[data-test=\"product-sort-container\"]").selectOption("hilo");

            // Ordenar productos y verificar

            // Ordenar por Nombre (Z a la A)
            page.locator("[data-test=\"product-sort-container\"]").selectOption("za");
            assertThat(page.locator(".inventory_item_name").first()).hasText("Test.allTheThings() T-Shirt (Red)");
            System.out.println("Ordenado de Z a A");

            // Ordenar por Precio (menor a mayor)
            page.locator("[data-test=\"product-sort-container\"]").selectOption("lohi");
            assertThat(page.locator(".inventory_item_name").first()).hasText("Sauce Labs Onesie");
            System.out.println("Ordenado de menor a mayor precio");


            // Ordenar por Precio (mayor a menor)
            page.locator("[data-test=\"product-sort-container\"]").selectOption("hilo");
            assertThat(page.locator(".inventory_item_name").first()).hasText("Sauce Labs Fleece Jacket");
            System.out.println("Ordenado de mayor a menor precio");


            browser.close();
        }
    }
}