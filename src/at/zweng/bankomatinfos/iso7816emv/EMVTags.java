/*
 * Copyright 2010 sasc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.zweng.bankomatinfos.iso7816emv;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 
 * source: https://code.google.com/p/javaemvreader/
 * 
 * http://www.emvlab.org/emvtags/all/
 * 
 * The coding of primitive context-specific class data objects in the ranges
 * '80' to '9E' and '9F00' to '9F4F' is reserved for EMV specification. The
 * coding of primitive context-specific class data objects in the range '9F50'
 * to '9F7F' is reserved for the payment systems.
 * 
 * @author sasc
 */
public class EMVTags {

	private static LinkedHashMap<ByteArrayWrapper, EmvTag> tags = new LinkedHashMap<ByteArrayWrapper, EmvTag>();
	// One byte tags
	public static final EmvTag ISSUER_IDENTIFICATION_NUMBER = new TagImpl(
			"42",
			TagValueType.NUMERIC,
			"Issuer Identification Number (IIN)",
			"The number that identifies the major industry and the card issuer and that forms the first part of the Primary Account Number (PAN)");
	public static final EmvTag AID_CARD = new TagImpl("4f",
			TagValueType.BINARY, "Application Identifier (AID) - card",
			"Identifies the application as described in ISO/IEC 7816-5");
	public static final EmvTag APPLICATION_LABEL = new TagImpl("50",
			TagValueType.TEXT, "Application Label",
			"Mnemonic associated with the AID according to ISO/IEC 7816-5");
	public static final EmvTag PATH = new TagImpl("51", TagValueType.BINARY,
			"ISO-7816 Path", "");
	public static final EmvTag TRACK_2_EQV_DATA = new TagImpl(
			"57",
			TagValueType.BINARY,
			"Track 2 Equivalent Data",
			"Contains the data elements of track 2 according to ISO/IEC 7813, excluding start sentinel, end sentinel, and Longitudinal Redundancy Check (LRC)");
	public static final EmvTag PAN = new TagImpl("5a", TagValueType.NUMERIC,
			"Application Primary Account Number (PAN)",
			"Valid cardholder account number");
	public static final EmvTag APPLICATION_TEMPLATE = new TagImpl(
			"61",
			TagValueType.BINARY,
			"Application Template",
			"Contains one or more data objects relevant to an application directory entry according to ISO/IEC 7816-5");
	public static final EmvTag FCI_TEMPLATE = new TagImpl("6f",
			TagValueType.BINARY, "File Control Information (FCI) Template",
			"Identifies the FCI template according to ISO/IEC 7816-4");
	public static final EmvTag RECORD_TEMPLATE = new TagImpl("70",
			TagValueType.BINARY, "Record Template (EMV Proprietary)",
			"Template proprietary to the EMV specification");
	public static final EmvTag ISSUER_SCRIPT_TEMPLATE_1 = new TagImpl(
			"71",
			TagValueType.BINARY,
			"Issuer Script Template 1",
			"Contains proprietary issuer data for transmission to the ICC before the second GENERATE AC command");
	public static final EmvTag ISSUER_SCRIPT_TEMPLATE_2 = new TagImpl(
			"72",
			TagValueType.BINARY,
			"Issuer Script Template 2",
			"Contains proprietary issuer data for transmission to the ICC after the second GENERATE AC command");
	public static final EmvTag DD_TEMPLATE = new TagImpl("73",
			TagValueType.BINARY, "Directory Discretionary Template",
			"Issuer discretionary part of the directory according to ISO/IEC 7816-5");
	public static final EmvTag RESPONSE_MESSAGE_TEMPLATE_2 = new TagImpl(
			"77",
			TagValueType.BINARY,
			"Response Message Template Format 2",
			"Contains the data objects (with tags and lengths) returned by the ICC in response to a command");
	public static final EmvTag RESPONSE_MESSAGE_TEMPLATE_1 = new TagImpl(
			"80",
			TagValueType.BINARY,
			"Response Message Template Format 1",
			"Contains the data objects (without tags and lengths) returned by the ICC in response to a command");
	public static final EmvTag AMOUNT_AUTHORISED_BINARY = new TagImpl("81",
			TagValueType.BINARY, "Amount, Authorised (Binary)",
			"Authorised amount of the transaction (excluding adjustments)");
	public static final EmvTag APPLICATION_INTERCHANGE_PROFILE = new TagImpl(
			"82",
			TagValueType.BINARY,
			"Application Interchange Profile",
			"Indicates the capabilities of the card to support specific functions in the application");
	public static final EmvTag COMMAND_TEMPLATE = new TagImpl("83",
			TagValueType.BINARY, "Command Template",
			"Identifies the data field of a command message");
	public static final EmvTag DEDICATED_FILE_NAME = new TagImpl("84",
			TagValueType.BINARY, "Dedicated File (DF) Name",
			"Identifies the name of the DF as described in ISO/IEC 7816-4");
	public static final EmvTag ISSUER_SCRIPT_COMMAND = new TagImpl("86",
			TagValueType.BINARY, "Issuer Script Command",
			"Contains a command for transmission to the ICC");
	public static final EmvTag APPLICATION_PRIORITY_INDICATOR = new TagImpl(
			"87",
			TagValueType.BINARY,
			"Application Priority Indicator",
			"Indicates the priority of a given application or group of applications in a directory");
	public static final EmvTag SFI = new TagImpl(
			"88",
			TagValueType.BINARY,
			"Short File Identifier (SFI)",
			"Identifies the SFI to be used in the commands related to a given AEF or DDF. The SFI data object is a binary field with the three high order bits set to zero");
	public static final EmvTag AUTHORISATION_CODE = new TagImpl("89",
			TagValueType.BINARY, "Authorisation Code",
			"Value generated by the authorisation authority for an approved transaction");
	public static final EmvTag AUTHORISATION_RESPONSE_CODE = new TagImpl("8a",
			TagValueType.TEXT, "Authorisation Response Code",
			"Code that defines the disposition of a message");
	public static final EmvTag CDOL1 = new TagImpl(
			"8c",
			TagValueType.DOL,
			"Card Risk Management Data Object List 1 (CDOL1)",
			"List of data objects (EmvTag and length) to be passed to the ICC in the first GENERATE AC command");
	public static final EmvTag CDOL2 = new TagImpl(
			"8d",
			TagValueType.DOL,
			"Card Risk Management Data Object List 2 (CDOL2)",
			"List of data objects (EmvTag and length) to be passed to the ICC in the second GENERATE AC command");
	public static final EmvTag CVM_LIST = new TagImpl(
			"8e",
			TagValueType.BINARY,
			"Cardholder Verification Method (CVM) List",
			"Identifies a method of verification of the cardholder supported by the application");
	public static final EmvTag CA_PUBLIC_KEY_INDEX_CARD = new TagImpl(
			"8f",
			TagValueType.BINARY,
			"Certification Authority Public Key Index - card",
			"Identifies the certification authority’s public key in conjunction with the RID");
	public static final EmvTag ISSUER_PUBLIC_KEY_CERT = new TagImpl("90",
			TagValueType.BINARY, "Issuer Public Key Certificate",
			"Issuer public key certified by a certification authority");
	public static final EmvTag ISSUER_AUTHENTICATION_DATA = new TagImpl("91",
			TagValueType.BINARY, "Issuer Authentication Data",
			"Data sent to the ICC for online issuer authentication");
	public static final EmvTag ISSUER_PUBLIC_KEY_REMAINDER = new TagImpl("92",
			TagValueType.BINARY, "Issuer Public Key Remainder",
			"Remaining digits of the Issuer Public Key Modulus");
	public static final EmvTag SIGNED_STATIC_APP_DATA = new TagImpl("93",
			TagValueType.BINARY, "Signed Static Application Data",
			"Digital signature on critical application parameters for SDA");
	public static final EmvTag APPLICATION_FILE_LOCATOR = new TagImpl(
			"94",
			TagValueType.BINARY,
			"Application File Locator (AFL)",
			"Indicates the location (SFI, range of records) of the AEFs related to a given application");
	public static final EmvTag TERMINAL_VERIFICATION_RESULTS = new TagImpl(
			"95", TagValueType.BINARY, "Terminal Verification Results (TVR)",
			"Status of the different functions as seen from the terminal");
	public static final EmvTag TDOL = new TagImpl(
			"97",
			TagValueType.BINARY,
			"Transaction Certificate Data Object List (TDOL)",
			"List of data objects (tag and length) to be used by the terminal in generating the TC Hash Value");
	public static final EmvTag TC_HASH_VALUE = new TagImpl("98",
			TagValueType.BINARY, "Transaction Certificate (TC) Hash Value",
			"Result of a hash function specified in Book 2, Annex B3.1");
	public static final EmvTag TRANSACTION_PIN_DATA = new TagImpl("99",
			TagValueType.BINARY,
			"Transaction Personal Identification Number (PIN) Data",
			"Data entered by the cardholder for the purpose of the PIN verification");
	public static final EmvTag TRANSACTION_DATE = new TagImpl("9a",
			TagValueType.NUMERIC, "Transaction Date",
			"Local date that the transaction was authorised");
	public static final EmvTag TRANSACTION_STATUS_INFORMATION = new TagImpl(
			"9b", TagValueType.BINARY, "Transaction Status Information",
			"Indicates the functions performed in a transaction");
	public static final EmvTag TRANSACTION_TYPE = new TagImpl(
			"9c",
			TagValueType.NUMERIC,
			"Transaction Type",
			"Indicates the type of financial transaction, represented by the first two digits of ISO 8583:1987 Processing Code");
	public static final EmvTag DDF_NAME = new TagImpl("9d",
			TagValueType.BINARY, "Directory Definition File (DDF) Name",
			"Identifies the name of a DF associated with a directory");
	public static final EmvTag FCI_PROPRIETARY_TEMPLATE = new TagImpl(
			"a5",
			TagValueType.BINARY,
			"File Control Information (FCI) Proprietary Template",
			"Identifies the data object proprietary to this specification in the FCI template according to ISO/IEC 7816-4");
	// Two byte tags
	public static final EmvTag CARDHOLDER_NAME = new TagImpl("5f20",
			TagValueType.TEXT, "Cardholder Name",
			"Indicates cardholder name according to ISO 7813");
	public static final EmvTag APP_EXPIRATION_DATE = new TagImpl("5f24",
			TagValueType.NUMERIC, "Application Expiration Date",
			"Date after which application expires");
	public static final EmvTag APP_EFFECTIVE_DATE = new TagImpl("5f25",
			TagValueType.NUMERIC, "Application Effective Date",
			"Date from which the application may be used");
	public static final EmvTag ISSUER_COUNTRY_CODE = new TagImpl("5f28",
			TagValueType.NUMERIC, "Issuer Country Code",
			"Indicates the country of the issuer according to ISO 3166");
	public static final EmvTag TRANSACTION_CURRENCY_CODE = new TagImpl("5f2a",
			TagValueType.TEXT, "Transaction Currency Code",
			"Indicates the currency code of the transaction according to ISO 4217");
	public static final EmvTag LANGUAGE_PREFERENCE = new TagImpl(
			"5f2d",
			TagValueType.TEXT,
			"Language Preference",
			"1–4 languages stored in order of preference, each represented by 2 alphabetical characters according to ISO 639");
	public static final EmvTag SERVICE_CODE = new TagImpl("5f30",
			TagValueType.NUMERIC, "Service Code",
			"Service code as defined in ISO/IEC 7813 for track 1 and track 2");
	public static final EmvTag PAN_SEQUENCE_NUMBER = new TagImpl("5f34",
			TagValueType.NUMERIC,
			"Application Primary Account Number (PAN) Sequence Number",
			"Identifies and differentiates cards with the same PAN");
	public static final EmvTag TRANSACTION_CURRENCY_EXP = new TagImpl(
			"5f36",
			TagValueType.NUMERIC,
			"Transaction Currency Exponent",
			"Indicates the implied position of the decimal point from the right of the transaction amount represented according to ISO 4217");
	public static final EmvTag ISSUER_URL = new TagImpl("5f50",
			TagValueType.TEXT, "Issuer URL",
			"The URL provides the location of the Issuer’s Library Server on the Internet");
	public static final EmvTag IBAN = new TagImpl(
			"5f53",
			TagValueType.BINARY,
			"International Bank Account Number (IBAN)",
			"Uniquely identifies the account of a customer at a financial institution as defined in ISO 13616");
	public static final EmvTag BANK_IDENTIFIER_CODE = new TagImpl("5f54",
			TagValueType.MIXED, "Bank Identifier Code (BIC)",
			"Uniquely identifies a bank as defined in ISO 9362");
	public static final EmvTag ISSUER_COUNTRY_CODE_ALPHA2 = new TagImpl(
			"5f55",
			TagValueType.TEXT,
			"Issuer Country Code (alpha2 format)",
			"Indicates the country of the issuer as defined in ISO 3166 (using a 2 character alphabetic code)");
	public static final EmvTag ISSUER_COUNTRY_CODE_ALPHA3 = new TagImpl(
			"5f56",
			TagValueType.TEXT,
			"Issuer Country Code (alpha3 format)",
			"Indicates the country of the issuer as defined in ISO 3166 (using a 3 character alphabetic code)");
	public static final EmvTag ACQUIRER_IDENTIFIER = new TagImpl("9f01",
			TagValueType.NUMERIC, "Acquirer Identifier",
			"Uniquely identifies the acquirer within each payment system");
	public static final EmvTag AMOUNT_AUTHORISED_NUMERIC = new TagImpl("9f02",
			TagValueType.NUMERIC, "Amount, Authorised (Numeric)",
			"Authorised amount of the transaction (excluding adjustments)");
	public static final EmvTag AMOUNT_OTHER_NUMERIC = new TagImpl(
			"9f03",
			TagValueType.NUMERIC,
			"Amount, Other (Numeric)",
			"Secondary amount associated with the transaction representing a cashback amount");
	public static final EmvTag AMOUNT_OTHER_BINARY = new TagImpl(
			"9f04",
			TagValueType.NUMERIC,
			"Amount, Other (Binary)",
			"Secondary amount associated with the transaction representing a cashback amount");
	public static final EmvTag APP_DISCRETIONARY_DATA = new TagImpl("9f05",
			TagValueType.BINARY, "Application Discretionary Data",
			"Issuer or payment system specified data relating to the application");
	public static final EmvTag AID_TERMINAL = new TagImpl("9f06",
			TagValueType.BINARY, "Application Identifier (AID) - terminal",
			"Identifies the application as described in ISO/IEC 7816-5");
	public static final EmvTag APP_USAGE_CONTROL = new TagImpl(
			"9f07",
			TagValueType.BINARY,
			"Application Usage Control",
			"Indicates issuer’s specified restrictions on the geographic usage and services allowed for the application");
	public static final EmvTag APP_VERSION_NUMBER_CARD = new TagImpl("9f08",
			TagValueType.BINARY, "Application Version Number - card",
			"Version number assigned by the payment system for the application");
	public static final EmvTag APP_VERSION_NUMBER_TERMINAL = new TagImpl(
			"9f09", TagValueType.BINARY,
			"Application Version Number - terminal",
			"Version number assigned by the payment system for the application");
	public static final EmvTag CARDHOLDER_NAME_EXTENDED = new TagImpl(
			"9f0b",
			TagValueType.TEXT,
			"Cardholder Name Extended",
			"Indicates the whole cardholder name when greater than 26 characters using the same coding convention as in ISO 7813");
	public static final EmvTag ISSUER_ACTION_CODE_DEFAULT = new TagImpl(
			"9f0d",
			TagValueType.BINARY,
			"Issuer Action Code - Default",
			"Specifies the issuer’s conditions that cause a transaction to be rejected if it might have been approved online, but the terminal is unable to process the transaction online");
	public static final EmvTag ISSUER_ACTION_CODE_DENIAL = new TagImpl(
			"9f0e",
			TagValueType.BINARY,
			"Issuer Action Code - Denial",
			"Specifies the issuer’s conditions that cause the denial of a transaction without attempt to go online");
	public static final EmvTag ISSUER_ACTION_CODE_ONLINE = new TagImpl(
			"9f0f",
			TagValueType.BINARY,
			"Issuer Action Code - Online",
			"Specifies the issuer’s conditions that cause a transaction to be transmitted online");
	public static final EmvTag ISSUER_APPLICATION_DATA = new TagImpl(
			"9f10",
			TagValueType.BINARY,
			"Issuer Application Data",
			"Contains proprietary application data for transmission to the issuer in an online transaction");
	public static final EmvTag ISSUER_CODE_TABLE_INDEX = new TagImpl(
			"9f11",
			TagValueType.NUMERIC,
			"Issuer Code Table Index",
			"Indicates the code table according to ISO/IEC 8859 for displaying the Application Preferred Name");
	public static final EmvTag APP_PREFERRED_NAME = new TagImpl("9f12",
			TagValueType.TEXT, "Application Preferred Name",
			"Preferred mnemonic associated with the AID");
	public static final EmvTag LAST_ONLINE_ATC_REGISTER = new TagImpl("9f13",
			TagValueType.BINARY,
			"Last Online Application Transaction Counter (ATC) Register",
			"ATC value of the last transaction that went online");
	public static final EmvTag LOWER_CONSEC_OFFLINE_LIMIT = new TagImpl(
			"9f14",
			TagValueType.BINARY,
			"Lower Consecutive Offline Limit",
			"Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal with online capability");
	public static final EmvTag MERCHANT_CATEGORY_CODE = new TagImpl(
			"9f15",
			TagValueType.NUMERIC,
			"Merchant Category Code",
			"Classifies the type of business being done by the merchant, represented according to ISO 8583:1993 for Card Acceptor Business Code");
	public static final EmvTag MERCHANT_IDENTIFIER = new TagImpl(
			"9f16",
			TagValueType.TEXT,
			"Merchant Identifier",
			"When concatenated with the Acquirer Identifier, uniquely identifies a given merchant");
	public static final EmvTag PIN_TRY_COUNTER = new TagImpl("9f17",
			TagValueType.BINARY,
			"Personal Identification Number (PIN) Try Counter",
			"Number of PIN tries remaining");
	public static final EmvTag ISSUER_SCRIPT_IDENTIFIER = new TagImpl("9f18",
			TagValueType.BINARY, "Issuer Script Identifier",
			"Identification of the Issuer Script");
	public static final EmvTag TERMINAL_COUNTRY_CODE = new TagImpl("9f1a",
			TagValueType.TEXT, "Terminal Country Code",
			"Indicates the country of the terminal, represented according to ISO 3166");
	public static final EmvTag TERMINAL_FLOOR_LIMIT = new TagImpl("9f1b",
			TagValueType.BINARY, "Terminal Floor Limit",
			"Indicates the floor limit in the terminal in conjunction with the AID");
	public static final EmvTag TERMINAL_IDENTIFICATION = new TagImpl("9f1c",
			TagValueType.TEXT, "Terminal Identification",
			"Designates the unique location of a terminal at a merchant");
	public static final EmvTag TERMINAL_RISK_MANAGEMENT_DATA = new TagImpl(
			"9f1d", TagValueType.BINARY, "Terminal Risk Management Data",
			"Application-specific value used by the card for risk management purposes");
	public static final EmvTag INTERFACE_DEVICE_SERIAL_NUMBER = new TagImpl(
			"9f1e", TagValueType.TEXT, "Interface Device (IFD) Serial Number",
			"Unique and permanent serial number assigned to the IFD by the manufacturer");
	public static final EmvTag TRACK1_DISCRETIONARY_DATA = new TagImpl("9f1f",
			TagValueType.TEXT, "[Magnetic Stripe] Track 1 Discretionary Data",
			"Discretionary part of track 1 according to ISO/IEC 7813");
	public static final EmvTag TRACK2_DISCRETIONARY_DATA = new TagImpl("9f20",
			TagValueType.TEXT, "[Magnetic Stripe] Track 2 Discretionary Data",
			"Discretionary part of track 2 according to ISO/IEC 7813");
	public static final EmvTag TRANSACTION_TIME = new TagImpl("9f21",
			TagValueType.NUMERIC, "Transaction Time (HHMMSS)",
			"Local time that the transaction was authorised");
	public static final EmvTag CA_PUBLIC_KEY_INDEX_TERMINAL = new TagImpl(
			"9f22",
			TagValueType.BINARY,
			"Certification Authority Public Key Index - Terminal",
			"Identifies the certification authority’s public key in conjunction with the RID");
	public static final EmvTag UPPER_CONSEC_OFFLINE_LIMIT = new TagImpl(
			"9f23",
			TagValueType.BINARY,
			"Upper Consecutive Offline Limit",
			"Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal without online capability");
	public static final EmvTag APP_CRYPTOGRAM = new TagImpl("9f26",
			TagValueType.BINARY, "Application Cryptogram",
			"Cryptogram returned by the ICC in response of the GENERATE AC command");
	public static final EmvTag CRYPTOGRAM_INFORMATION_DATA = new TagImpl(
			"9f27",
			TagValueType.BINARY,
			"Cryptogram Information Data",
			"Indicates the type of cryptogram and the actions to be performed by the terminal");
	public static final EmvTag ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_CERT = new TagImpl(
			"9f2d", TagValueType.BINARY,
			"ICC PIN Encipherment Public Key Certificate",
			"ICC PIN Encipherment Public Key certified by the issuer");
	public static final EmvTag ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_EXP = new TagImpl(
			"9f2e", TagValueType.BINARY,
			"ICC PIN Encipherment Public Key Exponent",
			"ICC PIN Encipherment Public Key Exponent used for PIN encipherment");
	public static final EmvTag ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_REM = new TagImpl(
			"9f2f", TagValueType.BINARY,
			"ICC PIN Encipherment Public Key Remainder",
			"Remaining digits of the ICC PIN Encipherment Public Key Modulus");
	public static final EmvTag ISSUER_PUBLIC_KEY_EXP = new TagImpl(
			"9f32",
			TagValueType.BINARY,
			"Issuer Public Key Exponent",
			"Issuer public key exponent used for the verification of the Signed Static Application Data and the ICC Public Key Certificate");
	public static final EmvTag TERMINAL_CAPABILITIES = new TagImpl("9f33",
			TagValueType.BINARY, "Terminal Capabilities",
			"Indicates the card data input, CVM, and security capabilities of the terminal");
	public static final EmvTag CVM_RESULTS = new TagImpl("9f34",
			TagValueType.BINARY, "Cardholder Verification (CVM) Results",
			"Indicates the results of the last CVM performed");
	public static final EmvTag TERMINAL_TYPE = new TagImpl(
			"9f35",
			TagValueType.NUMERIC,
			"Terminal Type",
			"Indicates the environment of the terminal, its communications capability, and its operational control");
	public static final EmvTag APP_TRANSACTION_COUNTER = new TagImpl(
			"9f36",
			TagValueType.BINARY,
			"Application Transaction Counter (ATC)",
			"Counter maintained by the application in the ICC (incrementing the ATC is managed by the ICC)");
	public static final EmvTag UNPREDICTABLE_NUMBER = new TagImpl("9f37",
			TagValueType.BINARY, "Unpredictable Number",
			"Value to provide variability and uniqueness to the generation of a cryptogram");
	public static final EmvTag PDOL = new TagImpl(
			"9f38",
			TagValueType.DOL,
			"Processing Options Data Object List (PDOL)",
			"Contains a list of terminal resident data objects (tags and lengths) needed by the ICC in processing the GET PROCESSING OPTIONS command");
	public static final EmvTag POINT_OF_SERVICE_ENTRY_MODE = new TagImpl(
			"9f39",
			TagValueType.NUMERIC,
			"Point-of-Service (POS) Entry Mode",
			"Indicates the method by which the PAN was entered, according to the first two digits of the ISO 8583:1987 POS Entry Mode");
	public static final EmvTag AMOUNT_REFERENCE_CURRENCY = new TagImpl("9f3a",
			TagValueType.BINARY, "Amount, Reference Currency",
			"Authorised amount expressed in the reference currency");
	public static final EmvTag APP_REFERENCE_CURRENCY = new TagImpl(
			"9f3b",
			TagValueType.NUMERIC,
			"Application Reference Currency",
			"1–4 currency codes used between the terminal and the ICC when the Transaction Currency Code is different from the Application Currency Code; each code is 3 digits according to ISO 4217");
	public static final EmvTag TRANSACTION_REFERENCE_CURRENCY_CODE = new TagImpl(
			"9f3c",
			TagValueType.NUMERIC,
			"Transaction Reference Currency Code",
			"Code defining the common currency used by the terminal in case the Transaction Currency Code is different from the Application Currency Code");
	public static final EmvTag TRANSACTION_REFERENCE_CURRENCY_EXP = new TagImpl(
			"9f3d",
			TagValueType.NUMERIC,
			"Transaction Reference Currency Exponent",
			"Indicates the implied position of the decimal point from the right of the transaction amount, with the Transaction Reference Currency Code represented according to ISO 4217");
	public static final EmvTag ADDITIONAL_TERMINAL_CAPABILITIES = new TagImpl(
			"9f40", TagValueType.BINARY, "Additional Terminal Capabilities",
			"Indicates the data input and output capabilities of the terminal");
	public static final EmvTag TRANSACTION_SEQUENCE_COUNTER = new TagImpl(
			"9f41",
			TagValueType.NUMERIC,
			"Transaction Sequence Counter",
			"Counter maintained by the terminal that is incremented by one for each transaction");
	public static final EmvTag APPLICATION_CURRENCY_CODE = new TagImpl("9f42",
			TagValueType.NUMERIC, "Application Currency Code",
			"Indicates the currency in which the account is managed according to ISO 4217");
	public static final EmvTag APP_REFERENCE_CURRECY_EXPONENT = new TagImpl(
			"9f43",
			TagValueType.NUMERIC,
			"Application Reference Currency Exponent",
			"Indicates the implied position of the decimal point from the right of the amount, for each of the 1–4 reference currencies represented according to ISO 4217");
	public static final EmvTag APP_CURRENCY_EXPONENT = new TagImpl(
			"9f44",
			TagValueType.NUMERIC,
			"Application Currency Exponent",
			"Indicates the implied position of the decimal point from the right of the amount represented according to ISO 4217");
	public static final EmvTag DATA_AUTHENTICATION_CODE = new TagImpl(
			"9f45",
			TagValueType.BINARY,
			"Data Authentication Code",
			"An issuer assigned value that is retained by the terminal during the verification process of the Signed Static Application Data");
	public static final EmvTag ICC_PUBLIC_KEY_CERT = new TagImpl("9f46",
			TagValueType.BINARY, "ICC Public Key Certificate",
			"ICC Public Key certified by the issuer");
	public static final EmvTag ICC_PUBLIC_KEY_EXP = new TagImpl(
			"9f47",
			TagValueType.BINARY,
			"ICC Public Key Exponent",
			"ICC Public Key Exponent used for the verification of the Signed Dynamic Application Data");
	public static final EmvTag ICC_PUBLIC_KEY_REMAINDER = new TagImpl("9f48",
			TagValueType.BINARY, "ICC Public Key Remainder",
			"Remaining digits of the ICC Public Key Modulus");
	public static final EmvTag DDOL = new TagImpl(
			"9f49",
			TagValueType.DOL,
			"Dynamic Data Authentication Data Object List (DDOL)",
			"List of data objects (tag and length) to be passed to the ICC in the INTERNAL AUTHENTICATE command");
	public static final EmvTag SDA_TAG_LIST = new TagImpl(
			"9f4a",
			TagValueType.BINARY,
			"Static Data Authentication EmvTag List",
			"List of tags of primitive data objects defined in this specification whose value fields are to be included in the Signed Static or Dynamic Application Data");
	public static final EmvTag SIGNED_DYNAMIC_APPLICATION_DATA = new TagImpl(
			"9f4b", TagValueType.BINARY, "Signed Dynamic Application Data",
			"Digital signature on critical application parameters for DDA or CDA");
	public static final EmvTag ICC_DYNAMIC_NUMBER = new TagImpl("9f4c",
			TagValueType.BINARY, "ICC Dynamic Number",
			"Time-variant number generated by the ICC, to be captured by the terminal");
	public static final EmvTag LOG_ENTRY = new TagImpl("9f4d",
			TagValueType.BINARY, "Log Entry",
			"Provides the SFI of the Transaction Log file and its number of records");
	public static final EmvTag MERCHANT_NAME_AND_LOCATION = new TagImpl("9f4e",
			TagValueType.TEXT, "Merchant Name and Location",
			"Indicates the name and location of the merchant");
	public static final EmvTag LOG_FORMAT = new TagImpl(
			"9f4f",
			TagValueType.DOL,
			"Log Format",
			"List (in tag and length format) of data objects representing the logged data elements that are passed to the terminal when a transaction log record is read");
	// '9F50' to '9F7F' are reserved for the payment systems (proprietary)

	public static final EmvTag CUMULATIVE_TOTAL_TRANSACTION_AMOUNT_UPPER_LIMIT = new TagImpl(
			"9f5c", TagValueType.BINARY,
			"Cumulative Total Transaction Amount Upper Limit",
			"Cumulative Total Transaction Amount Upper Limit");
	public static final EmvTag CUSTOMER_EXCLUSIVE_DATA = new TagImpl("9f7c",
			TagValueType.BINARY, "Customer Exclusive Data ",
			"Customer Exclusive Data ");

	// '9f66' specified in EMV Contactless
	public static final EmvTag TERMINAL_TRANSACTION_QUALIFIERS = new TagImpl(
			"9f66",
			TagValueType.BINARY,
			"Terminal Transaction Qualifiers",
			"Provided by the reader in the GPO command and used by the card to determine processing choices based on reader functionality");

	public static final EmvTag FCI_ISSUER_DISCRETIONARY_DATA = new TagImpl(
			"bf0c", TagValueType.BINARY,
			"File Control Information (FCI) Issuer Discretionary Data",
			"Issuer discretionary part of the FCI (e.g. O/S Manufacturer proprietary data)");
	public static final EmvTag VISA_LOG_ENTRY = new TagImpl("df60",
			TagValueType.BINARY, "VISA Log Entry ??", "");

	// these tags are MASTERCARD specific
	public static final EmvTag MASTERCARD_UPPER_OFFLINE_AMOUNT = new TagImpl(
			"9f52",
			TagValueType.BINARY,
			"Upper Cumulative Domestic Offline Transaction Amount",
			"Issuer specified data element indicating the required maximum cumulative offline amount allowed for the application before the transaction goes online.");

	// Global Platform
	// "73" Security Domain Management Data
	public static final EmvTag MAXIMUM_COMMAND_LENGTH = new TagImpl("9f65",
			TagValueType.BINARY,
			"Maximum length of data field in command message",
			"Global Platform");
	public static final EmvTag APP_LIFE_CYCLE_DATA = new TagImpl("9f6e",
			TagValueType.BINARY, "Application production life cycle data",
			"Global Platform");

	public static final EmvTag ISO7816_TAG_II_CARD_SERVICE = new TagImpl("43",
			TagValueType.BINARY, "ISO 7816 Card Service", "");
	public static final EmvTag ISO7816_TAG_II_INITIAL_ACCESS_DATA = new TagImpl(
			"44", TagValueType.BINARY, "ISO 7816 Initial Access Data", "");
	public static final EmvTag ISO7816_TAG_II_CARD_ISSUER_DATA = new TagImpl(
			"45", TagValueType.BINARY, "ISO 7816 Card Issuer Data", "");
	public static final EmvTag ISO7816_TAG_II_PRE_ISSUING = new TagImpl("46",
			TagValueType.BINARY, "ISO 7816 Pre Issuing", "");
	public static final EmvTag ISO7816_TAG_II_CARD_CAPABILITIES = new TagImpl(
			"47", TagValueType.BINARY, "ISO 7816 Card Capabilities", "");

	/**
	 * If the tag is not found, this method returns the "[UNHANDLED TAG]"
	 * containing 'tagBytes'
	 * 
	 * @param tagBytes
	 * @return
	 */
	public static EmvTag getNotNull(byte[] tagBytes) {
		EmvTag tag = find(tagBytes);
		if (tag == null) {
			tag = new TagImpl(tagBytes, TagValueType.BINARY, "[UNHANDLED TAG]",
					"");
		}
		return tag;
	}

	/**
	 * Returns null if Tag not found
	 */
	public static EmvTag find(byte[] tagBytes) {
		return tags.get(ByteArrayWrapper.wrapperAround(tagBytes));
	}

	private static void addTag(EmvTag tag) {
		// Use 'wrapper around', since the underlaying byte-array will not be
		// changed in this case
		ByteArrayWrapper baw = ByteArrayWrapper
				.wrapperAround(tag.getTagBytes());
		if (tags.containsKey(baw)) {
			throw new IllegalArgumentException("Tag already added " + tag);
		}
		tags.put(baw, tag);
	}

	static {
		Field[] fields = EMVTags.class.getFields();
		for (Field f : fields) {
			if (f.getType() == EmvTag.class) {
				try {
					EmvTag t = (EmvTag) f.get(null);
					addTag(t);
				} catch (IllegalAccessException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(find(new byte[] { (byte) 0x42 })); // IIN
		System.out.println(find(new byte[] { (byte) 0x5f, (byte) 0x20 })); // CARDHOLDER_NAME
		System.out.println(getNotNull(new byte[] { (byte) 0x5f, (byte) 0x21 })); // UNDEFINED
	}

	public static Iterator<EmvTag> iterator() {
		return tags.values().iterator();
	}

	private EMVTags() {
		// Do not instantiate
	}
}
