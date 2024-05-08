package com.goit.status;

import com.goit.status.app.HttpStatusChecker;
import com.goit.status.app.HttpStatusImageDownloader;
import com.goit.status.exception.StatusInternalErrorException;

import java.util.Scanner;

public class HttpImageStatusCli {
    public int statusCode;
    private HttpStatusImageDownloader downloader;

    public HttpImageStatusCli() {
        this.downloader = new HttpStatusImageDownloader();
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            String imageUrl = checker.getStatusImage(cli.statusCode);
            System.out.println("Image URL: " + imageUrl);
        } catch (Exception e) {
            throw new StatusInternalErrorException("code hasn't checked", e);
        }
    }

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter HTTP status code:");
        if (scanner.hasNextInt()) {
            try {
                statusCode = scanner.nextInt();
                downloader.downloadStatusImage(statusCode);
            } catch (Exception e) {
                throw new StatusInternalErrorException("", e);
            }
        } else {
            System.out.println("Please enter a valid number.");
        }
    }
}