package com.example;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // создаем экземпляр класса CrptApi с ограничением в 10 запросов в минуту
        CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 10);

        // создаем объект документа для ввода в оборот товара
        CrptApi.DocumentInfo documentInfo = new CrptApi.DocumentInfo();
        documentInfo.setDoc_id("123");
        documentInfo.setDoc_status("DRAFT");
        documentInfo.setOwner_inn("1234567890");
        documentInfo.setParticipant_inn("1234567890");
        documentInfo.setProducer_inn("1234567890");
        documentInfo.setProduction_date("2020-01-23");
        documentInfo.setProduction_type("PRODUCTION");

        // создаем объект товара
        CrptApi.ProductInfo productInfo = new CrptApi.ProductInfo();
        productInfo.setCertificate_document("certificate_document");
        productInfo.setCertificate_document_date("2020-01-23");
        productInfo.setCertificate_document_number("certificate_document_number");
        productInfo.setOwner_inn("1234567890");
        productInfo.setProducer_inn("1234567890");
        productInfo.setProduction_date("2020-01-23");
        productInfo.setTnved_code("tnved_code");
        productInfo.setUit_code("uit_code");
        productInfo.setUitu_code("uitu_code");

        // добавляем товар в список товаров документа
        documentInfo.setProducts(new CrptApi.ProductInfo[]{productInfo});

        // создаем подпись
        String signature = "signature";

        try {
            // вызываем метод createDocument() для создания документа
            crptApi.createDocument(documentInfo, signature);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}