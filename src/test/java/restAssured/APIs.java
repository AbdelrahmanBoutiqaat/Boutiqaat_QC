package restAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

import javax.security.auth.callback.ConfirmationCallback;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class APIs {
	String login = "https://staging-wms.boutiqaat.com/api/WMS/USERLOGINFORWEB";
	String homePage_Counts = "https://staging-wms.boutiqaat.com/api/WMS/GetHomeCount";
	String MultiImage_Inquiry = "https://staging-wms.boutiqaat.com/api/WMS/GetEnquiryDataForMultiImage";
	String Item_Expiry_Scan1 = "https://staging-wms.boutiqaat.com/api/WMS/GetItemDimensionDetail";
	String Item_Expiry_Scan2 = "https://staging-wms.boutiqaat.com/api/WMS/GetExpiryDateFromNav";
	String ItemExipryInfo = "https://staging-wms.boutiqaat.com/api/WMS/ItemExpiryInfo";
	String GetItemDimensionDetail = "https://staging-wms.boutiqaat.com/api/WMS/GetItemDimensionDetail";
	String ONBINSCAN = " https://staging-wms.boutiqaat.com/api/WMS/BINDataFromNAV";
	String Confirm = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String Complete = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String ViewList = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String BINDataFromNAV="https://staging-wms.boutiqaat.com/api/WMS/BINDataFromNAV";
	String TokenID;

	@BeforeClass
	public void BeforeClass() {
		String Body = "{\r\n" + "    \"userNM\": \"12511\",\r\n" + "    \"userPWD\": \"wms@1230\",\r\n"
				+ "    \"DeviceID\": \"RT050\",\r\n" + "    \"Tag\": 1\r\n" + "}";
		Response response = given().contentType("application/json").body(Body).post(login);
		System.out.println(response.getBody().asString());
		JsonPath jsonpath = response.jsonPath();
		String UserID = jsonpath.get("result.USERID").toString();
		TokenID = jsonpath.get("result.TOCKEN").toString().replace("[", "").replace("]", "");
		System.out.println("Token ID =" + TokenID);
		assertEquals(UserID, "[12511]");

	}

	// Login
	@Test(priority = 1)
	public void Login() {
		String Body = "{\r\n" + "    \"userNM\": \"12511\",\r\n" + "    \"userPWD\": \"wms@1230\",\r\n"
				+ "    \"DeviceID\": \"RT050\",\r\n" + "    \"Tag\": 1\r\n" + "}";
		Response response = given().contentType("application/json").body(Body).post(login);
		System.out.println(response.getBody().asString());
		JsonPath jsonpath = response.jsonPath();
		String UserID = jsonpath.get("result.USERID").toString();
		String TokenID2 = jsonpath.get("result.TOCKEN").toString();
		System.out.println("Token ID =" + TokenID2);
		assertEquals(UserID, "[12511]");
	}

	// HOME PAGE COUNTS
	@Test(priority = 2)
	public void HomePage_Counts() {

		System.out.println("token Method 2 : " + TokenID);
		String RequestBody = "{\r\n" + "\"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "\"DeviceID\":\"01D90\",\r\n" + "\"Status\":\"100\",\r\n" + "\"Flag\":\"0\",\r\n"
				+ "\"WHNO\":\"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(homePage_Counts);
		System.out.println(response2.getBody().asString());
	}

	// MUTI-IMAGE ENQUIRY
	@Test(priority = 3)
	public void MultiImage_Inquiry() {
		String RequestBody = "{\r\n" + "    \"Flag\": \"2\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DeviceID\": \"01d03\",\r\n" + "\"TokenID\":\"" + TokenID
				+ "\"" + "," + "    \"DocNo\": \"100995\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(MultiImage_Inquiry);
		System.out.println(response2.getBody().asString());
	}

	// ITEMS EXPIRY : ON BARCODE SCAN - 1
	@Test(priority = 4)
	public void Item_Expiry_Scan1() {
		String RequestBody = "{\r\n" + "\"Status\": \"2\",\r\n" + "\"Barcode\":\"100995_BLOT\",\r\n"
				+ "\"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + "," + "\"WHNO\":\"KWI01\"\r\n"
				+ "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Item_Expiry_Scan1);
		System.out.println(response2.getBody().asString());

	}

	// ITEMS EXPIRY : ON BARCODE SCAN - 2
	@Test(priority = 5)
	public void Item_Expiry_Scan2() {
		String RequestBody = "{\r\n" + "\"WHNO\":\"KWI01\",\r\n" + "\"LotNo\":\"BLOT\",\r\n"
				+ "\"ItemNo\":\"100995\",\r\n" + "\"userID\":\"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "\"NavBaseUrl\":\"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Item_Expiry_Scan2);
		System.out.println(response2.getBody().asString());

	}

	// ITEMS EXPIRY : ON BARCODE POST EXPIRY DATE
	@Test
	public void ItemExipryInfo() {
		String RequestBody = "{\r\n" + "    \"WHNO\": \"KWI01\",\r\n" + "    \"Status\": \"3\",\r\n"
				+ "    \"ItemDetailS[DocNo]\": \"IEXP-lduar5tzmalq8wfavpq\",\r\n"
				+ "    \"ItemDetailS[ItemNo]\": \"100995\",\r\n" + "    \"ItemDetailS[LotNo]\": \"BLOT\",\r\n"
				+ "    \"ItemDetailS[ExpiryDate]\": \"2023-02-07\",\r\n" + "    \"ItemDetailS[UserId]\": \"12511\",\r\n"
				+ "    \"ItemDetailS[Barcode]\": \"100995_BLOT\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "   \"TokenID\":\"" + TokenID + "\"" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(ItemExipryInfo);
		System.out.println(response2.getBody().asString());

	}

	@Test(priority = 6)
	// ITEM DIMENSION : ON BARCODE SCAN
	public void GetItemDimensionDetail() {
		String RequestBody = "{\r\n" + "    \"Status\": \"2\",\r\n" + "    \"Barcode\": \"100995_BLOT\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(GetItemDimensionDetail);
		System.out.println(response2.getBody().asString());
	}

	// ONBINSCAN
	@Test(priority = 7)
	public void ONBINSCAN() {
		String RequestBody = "{\r\n" + "\r\n" + "\"WHNO\": \"KWI01\",\r\n" + "\"BinNo\": \"KWI19-G013-L011-03A1\",\r\n"
				+ "\"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "\"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(ONBINSCAN);
		System.out.println(response2.getBody().asString());
	}

	// Confirm
	@Test(priority = 8)
	public void Confirm() {
		String RequestBody = "{\r\n" + "\"Status\": \"11\",\r\n" + "\"userID\": \"12511\",\r\n"
				+ "\"DOCNO\": \"MS/2223/000000003083\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "\"DeviceID\": \"01D90\",\r\n" + "\"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Confirm);
		System.out.println(response2.getBody().asString());
	}

	// Complete -- Not valid data response
	@Test(priority = 9)
	public void Complete() {
		String RequestBody = "{\r\n" + "    \"Status\": \"2\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"DOCNO\": \"MS/2223/000000003083\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01D90\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Complete);
		System.out.println(response2.getBody().asString());
	}

	// ViewList
	@Test(priority = 10)
	public void View_List() {
		String RequestBody = "{\r\n" + "\r\n" + "\"Status\": \"14\",\r\n" + "\"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "\"DOCNO\": \"MS/2223/000000003057\",\r\n"
				+ "\"WHNO\": \"KWI01\",\r\n" + "\"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(ViewList);
		System.out.println(response2.getBody().asString());
	}

	// ON BARCODE SCAN
	@Test(priority = 11)
	public void BINDataFromNAV() {
		String RequestBody = "{\r\n" + "    \"BinNo\": \"KWI19-G007-R015-01A1\",\r\n" + "\"TokenID\":\"" + TokenID
				+ "\"" + "," + "    \"WHNO\": \"KWI01\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(BINDataFromNAV);
		System.out.println(response2.getBody().asString());
	}

}
