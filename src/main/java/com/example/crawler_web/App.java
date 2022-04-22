package com.example.crawler_web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	public static void main(String[] args) throws Exception {

		// Gerekli değişkenlerin tanımlanması

		String url = "https://www.ilan.gov.tr/ilan/kategori/9/ihale-duyurulari";
		int count = 0;
		List<WebElement> searchPageResult;
		List<String> allLinkText = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver", "tools/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window();
		driver.get(url);

		// Sayfalardaki ilanların adreslerinin ArrayList'e aktarılması

		for (int i = 1; i <= 30; i++) {
			driver.get("https://www.ilan.gov.tr/ilan/kategori/9/ihale-duyurulari?txv=9&currentPage=" + i);
			Thread.sleep(2000);
			searchPageResult = driver
					.findElements(By.xpath("//div[@class='col ng-tns-c146-3']//igt-ad-single-list/ng-component/a"));
			for (WebElement link : searchPageResult) {
				allLinkText.add(link.getAttribute("href"));
				count++;
			}
		}
		// İlanların adreslerine tek tek giderek gerekli bilgilerin değişkenlere aktarılması

		for (String eachLinkHref : allLinkText) {
			driver.get(eachLinkHref);
			Thread.sleep(1500);
			List<WebElement> adTitle;
			adTitle = driver.findElements(By.className("list-title"));
			Thread.sleep(1500);

			String registrationNumber = null, type = null, location = null, typeDescription = null;

			for (WebElement row : adTitle) {
				switch (row.getText()) {
				case "İhale Kayıt No":
					registrationNumber = driver.findElement(By.xpath(
							"//*[@id=\"print-section\"]/div[2]/div[2]/div/div[1]/ul/li/div[text()='İhale Kayıt No']/following::div"))
							.getText();
					break;
				case "İlan Türü":
					type = driver.findElement(By.xpath(
							"//*[@id=\"print-section\"]/div[2]/div[2]/div/div[1]/ul/li/div[text()='İlan Türü']/following::div"))
							.getText();
					break;
				case "Şehir":
					location = driver.findElement(By.xpath(
							"//*[@id=\"print-section\"]/div[2]/div[2]/div/div[1]/ul/li/div[text()='Şehir']/following::div"))
							.getText();
					break;
				case "İhale Türü":
					typeDescription = driver.findElement(By.xpath(
							"//*[@id=\"print-section\"]/div[2]/div[2]/div/div[1]/ul/li/div[text()='İhale Türü']/following::div"))
							.getText();
					break;
				}

			}
			AdDescription description = new AdDescription(registrationNumber, type, location, typeDescription);
			writeOutput(description.toString());
		}
		System.out.println("total " + count + "kayıt.");
	}

	//Nesneye aktarılan bilgilerin txt'ye yazdırılması

	public static void writeOutput(String toString) {
		File file = new File("tools//data.txt");
		try {
			String value = toString;
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(value);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
