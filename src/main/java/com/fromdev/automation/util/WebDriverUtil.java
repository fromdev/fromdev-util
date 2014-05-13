package com.fromdev.automation.util;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Strings;

public class WebDriverUtil {

	public static void scrollDown(WebDriver driver, int by) {
		try {
			JavascriptExecutor jsx = (JavascriptExecutor) driver;
			jsx.executeScript("window.scrollBy(0," + by + ")", "");
		} catch (Exception e) {
			System.out.println("Scroll down failed " + by);
		}

	}

	public static boolean doClick(WebDriver driver, List elmentXpathList,
			WebDriverWait wait) {
		if (elmentXpathList != null && elmentXpathList.size() > 0) {
			for (Iterator iterator = elmentXpathList.iterator(); iterator
					.hasNext();) {
				String elementXpath = (String) iterator.next();
				if ((!Strings.isNullOrEmpty(elementXpath))
						&& doClick(driver, elementXpath, wait)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean doClick(WebDriver driver, String elementXpath,
			WebDriverWait wait) {

		boolean status = false;
		try {
			WebElement element = findElement(driver, elementXpath, wait);
			if (element != null) {
				element.click();
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Cant find elementXpath: " + elementXpath);
		}
		return status;
	}

	public static WebElement findElement(WebDriver driver,
			List elmentXpathList, WebDriverWait wait) {
		WebElement element = null;
		if (elmentXpathList != null && elmentXpathList.size() > 0) {
			for (Iterator iterator = elmentXpathList.iterator(); iterator
					.hasNext();) {
				String elementXpath = (String) iterator.next();
				if ((!Strings.isNullOrEmpty(elementXpath))) {
					element = findElement(driver, elementXpath, wait);
				}
				if(element != null) {
					break;
				}
			}
		}
		return element;
	}

	public static WebElement findElement(WebDriver driver, String elementXpath,
			WebDriverWait wait) {
		WebElement element = null;
		if (driver.findElements(By.xpath(elementXpath)).size() != 0) {
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(elementXpath)));

		}
		return element;
	}

}
