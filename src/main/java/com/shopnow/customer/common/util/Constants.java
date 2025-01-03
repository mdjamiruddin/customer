package com.shopnow.customer.common.util;

public class Constants {
    // API Response Messages
    public static final String CUSTOMER_NOT_FOUND = "Customer not found with ID: ";
    public static final String ADDRESS_NOT_FOUND = "Address not found with ID: ";
    public static final String BANK_NOT_FOUND = "Bank details not found with ID: ";
    public static final String PREFERENCE_NOT_FOUND = "Preferences not found for Customer ID: ";

    // Regex Patterns
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Default Values
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 0;

    // Other Constants
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
}
