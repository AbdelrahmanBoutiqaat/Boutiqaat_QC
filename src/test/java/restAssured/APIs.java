package restAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
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
	String BINDataFromNAV = "https://staging-wms.boutiqaat.com/api/WMS/BINDataFromNAV";
	String MVMNT_Pick_Lot_On_Load = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String On_barcode_Scan = "https://staging-wms.boutiqaat.com/api/WMS/BINDataFromNAV";
	String OnConfirm = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String OnPost = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String VieWlist = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String DocList = "https://staging-wms.boutiqaat.com/api/WMS/Movement";
	String Sorting_Putaway_ON_LOAD = "https://staging-wms.boutiqaat.com/api/WMS/SortingItemBin";
	String Sorting_Putaway_On_Item_Scan = "https://staging-wms.boutiqaat.com/api/WMS/SortingItemBin";
	String Sorting_Putaway_On_Confirm = "https://staging-wms.boutiqaat.com/api/WMS/SortingItemBin";
	String Sorting_Pegion_Hole_Release = "https://staging-wms.boutiqaat.com/api/WMS/FreeSortingBinToNAV";
	String Cycle_Count = "https://staging-wms.boutiqaat.com/api/WMS/GetHomeCount";
	String Manual_Cycle_Count = "https://staging-wms.boutiqaat.com/api/WMS/CycleCount";
	String Manual_Cycle_Count_ONBIN_Scan = "https://staging-wms.boutiqaat.com/api/WMS/BINDataFromNAV";
	String Manual_Cycle_Count_OnBarcode_Scan = "https://staging-wms.boutiqaat.com/api/WMS/GetItemDetail";
	String Manual_Cycle_Count_OnBinScan = "https://staging-wms.boutiqaat.com/api/WMS/BINDataFromNAV";
	String Manual_Cycle_OnBarcode_Scan = "https://staging-wms.boutiqaat.com/api/WMS/GetItemDetail";
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
		System.out.println("--------------------------------------------------------------");

	}
	/* ---------------------------Login Screen------------------- */
	/*
	 * // Login
	 * 
	 * @Test(priority = 1) public void Login() { String Body = "{\r\n" +
	 * "    \"userNM\": \"12511\",\r\n" + "    \"userPWD\": \"wms@1230\",\r\n" +
	 * "    \"DeviceID\": \"RT050\",\r\n" + "    \"Tag\": 1\r\n" + "}"; Response
	 * response = given().contentType("application/json").body(Body).post(login);
	 * System.out.println(response.getBody().asString()); JsonPath jsonpath =
	 * response.jsonPath(); String UserID =
	 * jsonpath.get("result.USERID").toString(); String TokenID2 =
	 * jsonpath.get("result.TOCKEN").toString(); System.out.println("Token ID =" +
	 * TokenID2); assertEquals(UserID, "[12511]"); System.out.println(
	 * "--------------------------------------------------------------"); }
	 */

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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*---------------------------Support Screen -------------------------------*/
	// MUTI-IMAGE ENQUIRY
	@Test(priority = 3)
	public void MultiImage_Inquiry() {
		String RequestBody = "{\r\n" + "    \"Flag\": \"2\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DeviceID\": \"01d03\",\r\n" + "\"TokenID\":\"" + TokenID
				+ "\"" + "," + "    \"DocNo\": \"100995\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(MultiImage_Inquiry);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*-------------------------------Others Screen------------------------------*/
	// ITEMS EXPIRY : ON BARCODE SCAN - 1
	@Test(priority = 4)
	public void Item_Expiry_Scan1() {
		String RequestBody = "{\r\n" + "\"Status\": \"2\",\r\n" + "\"Barcode\":\"100995_BLOT\",\r\n"
				+ "\"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + "," + "\"WHNO\":\"KWI01\"\r\n"
				+ "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Item_Expiry_Scan1);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");

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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");

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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");

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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*------------------------------------Movement Pick Single Lot --------------------------*/
	// ONBINSCAN
	@Test(priority = 7)
	public void ONBINSCAN() {
		String RequestBody = "{\r\n" + "\r\n" + "\"WHNO\": \"KWI01\",\r\n" + "\"BinNo\": \"KWI19-G013-L011-03A1\",\r\n"
				+ "\"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "\"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(ONBINSCAN);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
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
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*-------------------------Movement Pick Lot Wise screen--------------------------*/
	@Test(priority = 12)
	public void onLoad() {
		String RequestBody = "{\r\n" + "    \"Status\": \"0\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DeviceID\": \"01D86\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DOCTYPE\": \"600\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(MVMNT_Pick_Lot_On_Load);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");

	}

	@Test(priority = 13)
	public void ON_BARCODE_SCAN() {
		String RequestBody = "{\r\n" + "    \"BinNo\": \"KWI19-G007-R015-01A1\",\r\n" + "\"TokenID\":\"" + TokenID
				+ "\"" + "," + "    \"WHNO\": \"KWI01\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(On_barcode_Scan);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 14)
	public void OnConfirm() {
		String RequestBody = "{\r\n" + "    \"Status\": \"1\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"BinNo\": \"KWI19-G007-R015-01A1\",\r\n"
				+ "    \"Barcode\": \"SC-00006810_LOT00055559\",\r\n" + "    \"ItemNo\": \"SC-00006810\",\r\n"
				+ "    \"ItemDesc\": \"\",\r\n" + "    \"LOTNO\": \"LOT00055559\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"DOCNO\": \"\",\r\n" + "    \"Qty\": \"1\",\r\n" + "    \"DOCTYPE\": \"600\",\r\n"
				+ "    \"STAGEBINNO\": \"STAGEMOVEBIN\",\r\n" + "    \"SYSBINNO\": \"KWI19-G009-L017-05A1\",\r\n"
				+ "    \"SYSZONEID\": \"ZONE16\",\r\n" + "    \"BINNO_1\": \"ZONE14  KWI19-G007-R014-05A1\",\r\n"
				+ "    \"BINNO_2\": \"ZONE08  KWI01-G024-R001-01A1\",\r\n"
				+ "    \"BINNO_3\": \"G038  KWI01-G038-Y001-01A3\",\r\n" + "    \"BINNO_4\": \"\",\r\n"
				+ "    \"DeviceID\": \"01D90\",\r\n" + "    \"Misc\": \"0\",\r\n" + "    \"SYSQTY\": \"10\",\r\n"
				+ "    \"Tag\":\"\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(OnConfirm);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 15)
	public void OnPost() {
		String RequestBody = "{\r\n" + "    \"Status\": \"2\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"DOCNO\": \"BL/2223/000000021928\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01D90\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(OnPost);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 16)
	public void ViewList() {
		String RequestBody = "{\r\n" + "    \"Status\": \"14\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DOCNO\": \"BL/2223/000000021928\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(VieWlist);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*----------------------------MOVEMENT SINGLE LOT PUT Screen---------------------*/
	@Test(priority = 17)
	public void DOCLIST() {
		String RequestBody = "{\r\n" + "\"Status\": \"3\",\r\n" + "\"userID\": \"12511\",\r\n"
				+ "\"ItemType\": \"2\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + "," + "\"WHNO\": \"KWI01\",\r\n"
				+ "\"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 18)
	public void Page_ON_Load() {
		String RequestBody = "{\r\n" + "\"Status\": \"4\",\r\n" + "\"userID\": \"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "\"DOCNO\": \"MS/2223/000000003057\",\r\n" + "\"WHNO\": \"KWI01\",\r\n"
				+ "\"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 19)
	public void BARCODE_SCAN() {
		String RequestBody = "{\r\n" + "\"Status\":\"5\",\r\n" + "\"userID\":\"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "\"DOCNO\":\"MS/2223/000000003058\",\r\n" + "\"Barcode\": \"100995_BLOT\",\r\n"
				+ "\"WHNO\":\"KWI01\",\r\n" + "\"DeviceID\":\"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 20)
	public void ConFirm() {
		String RequestBody = "{\r\n" + "    \"Status\": \"6\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DOCNO\": \"MS/2223/000000003059\",\r\n"
				+ "    \"BinNo\": \"KWI19-G013-L011-03A1\",\r\n" + "    \"Barcode\": \"100995_BLOT\",\r\n"
				+ "    \"ItemNo\": \"\",\r\n" + "    \"Qty\": \"1\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"ItemType\": \"UPK\",\r\n" + "    \"DeviceID\": \"01D90\",\r\n" + "    \"Misc\": \"0\"\r\n"
				+ "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 21)
	public void ViEwList() {
		String RequestBody = "{\r\n" + "\"status\": \"7\",\r\n" + "\"DOCNO\": \"MS/2223/000000003059\",\r\n"
				+ "\"userID\":\"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + "," + "\"Tag\": \"0\",\r\n"
				+ "\"WHNO\": \"KWI01\",\r\n" + "\"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*--------------------------------Movement Put Lot Wise-------------------------------*/
	@Test(priority = 21)
	public void DOCLiST() {
		String RequestBody = "{\r\n" + "    \"Status\": \"3\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"ItemType\": \"2\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 22)
	public void Page_ON_load() {
		String RequestBody = "{\r\n" + "\"Status\": \"4\",\r\n" + "\"userID\": \"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "\"DOCNO\": \"MS/2223/000000003057\",\r\n" + "\"WHNO\": \"KWI01\",\r\n"
				+ "\"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 23)
	public void BARCODE_sCAN() {
		String RequestBody = "{\r\n" + "\"Status\":\"5\",\r\n" + "\"userID\":\"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "\"DOCNO\":\"MS/2223/000000003058\",\r\n" + "\"Barcode\": \"100995_BLOT\",\r\n"
				+ "\"WHNO\":\"KWI01\",\r\n" + "\"DeviceID\":\"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 24)
	public void ConfIrm() {
		String RequestBody = "{\r\n" + "    \"Status\": \"6\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DOCNO\": \"MS/2223/000000003059\",\r\n"
				+ "    \"BinNo\": \"KWI19-G013-L011-03A1\",\r\n" + "    \"Barcode\": \"100995_BLOT\",\r\n"
				+ "    \"ItemNo\": \"\",\r\n" + "    \"Qty\": \"1\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"ItemType\": \"UPK\",\r\n" + "    \"DeviceID\": \"01D90\",\r\n" + "    \"Misc\": \"0\"\r\n"
				+ "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 25)
	public void ViEwLIst() {
		String RequestBody = "{\r\n" + "\"status\": \"7\",\r\n" + "\"DOCNO\": \"MS/2223/000000003059\",\r\n"
				+ "\"userID\":\"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + "," + "\"Tag\": \"0\",\r\n"
				+ "\"WHNO\": \"KWI01\",\r\n" + "\"DeviceID\": \"01D90\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(DocList);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*------------------------------------Sorting Putaway Scree-----------------*/
	// OnLoad
	@Test(priority = 26)
	public void On_load() {
		String RequestBody = "{\r\n" + "    \"Status\": \"9\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DeviceID\": \"01D90\",\r\n"
				+ "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Sorting_Putaway_ON_LOAD);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	@Test(priority = 27)
	public void On_Item_Scan() {
		String RequestBody = "{\r\n" + "    \"Status\":\"1\",\r\n" + "\"userID\":\"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "\"DeviceID\":\"222\",\r\n" + "\"WHNO\":\"KWI01\",\r\n"
				+ "\"Barcode\":\"16597_20072859\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Sorting_Putaway_ON_LOAD);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ON Confirm
	@Test(priority = 28)
	public void On_Confirm() {
		String RequestBody = "{\r\n" + "\"Status\": \"3\",\r\n" + "\"userID\": \"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "\"WHNO\": \"KWI01\",\r\n" + "\"DeviceID\": \"222\",\r\n"
				+ "\"BinNo\": \"BPNA001\",\r\n" + "\"Barcode\": \"16783_20004997\",\r\n"
				+ "\"DOCNO\":\"9981892722\"\r\n" + "\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Sorting_Putaway_ON_LOAD);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// Reset
	@Test(priority = 29)
	public void Reset() {
		String RequestBody = "{\r\n" + "    \"Status\": \"9\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DeviceID\": \"222\",\r\n"
				+ "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Sorting_Putaway_ON_LOAD);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}
	/*---------------------------Sorting Pegion Hole Release ---------------------*/

	@Test(priority = 30)
	public void ON_LOAD() {
		String RequestBody = "{\r\n" + "    \"Status\": \"7\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DeviceID\": \"01D90\",\r\n"
				+ "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Sorting_Putaway_ON_LOAD);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// BIN SCAN
	@Test(priority = 31)
	public void BIN_SCAN() {
		String RequestBody = "{\r\n" + "    \"Status\": \"6\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DeviceID\": \"222\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"binno\": \"ZH-093\",\r\n"
				+ "    \"DocNo\": \"100515429\",\r\n"
				+ "    \"OfsBaseUrl\":\"https://staging-ofsapi.boutiqaat.com\"\r\n" + "\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody)
				.post(Sorting_Pegion_Hole_Release);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}
	/*-------------------------Cycle Count screen ---------------------*/

	// Cycle Count
	@Test(priority = 32)
	public void Cycle_count() {
		String RequestBody = "{\r\n" + "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\",\r\n" + "    \"Status\": \"200\",\r\n" + "    \"Flag\": \"4\",\r\n"
				+ "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*-------------------------------MANUAL CycleCount ------------------------*/

	// ONLOAD CHECK ISMANUAL CC
	@Test(priority = 33)
	public void Onload_Check() {
		String RequestBody = "{\r\n" + "    \"Status\": \"7\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\",\r\n" + "    \"DOCTYPE\": \"1\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ONLOAD DOC GENERATION
	@Test(priority = 34)
	public void OnLoad_Doc_Gene() {
		String RequestBody = "{\r\n" + "    \"Status\": \"0\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ONLOAD PREV SCANNED DOC
	@Test(priority = 35)
	public void OnLoad_Prev() {
		String RequestBody = "{\r\n" + "    \"Status\": \"5\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"DOCNO\": \"CC/2223/000000028724\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ON BINSCAN
	@Test(priority = 36)
	public void OnBin_Scan() {
		String RequestBody = "{\r\n" + "    \"DOCNO\": \"CC/2223/000000028771\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"BinNo\": \"KWI19-G049-L001-01A1\",\r\n" + "    \"userID\": \"97458870\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody)
				.post(Manual_Cycle_Count_ONBIN_Scan);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ON BARCODE SCAN
	@Test(priority = 37)
	public void OnBarcode_Scan() {
		String RequestBody = "{\r\n" + "    \"Status\": \"2\",\r\n" + "    \"Barcode\": \"005835_BLOT\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n"
				+ "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody)
				.post(Manual_Cycle_Count_OnBarcode_Scan);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// Confirm
	@Test(priority = 38)
	public void ConFIrm() {
		String RequestBody = "{\r\n" + "    \"Status\": \"1\",\r\n" + "    \"DOCQTY\": \"10\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"BinNo\": \"KWI01-G003-Y025-07A4\",\r\n" + "    \"Barcode\": \"ORL-00000677_LOT00012346\",\r\n"
				+ "    \"ItemNo\": \"ORL-0000067\",\r\n" + "    \"ItemDesc\": \"\",\r\n"
				+ "    \"LOTNO\": \"LOT00012346\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"DOCNO\": \"CC/2223/000000028731\",\r\n" + "    \"Qty\": \"1\",\r\n"
				+ "    \"DeviceID\": \"02C01\",\r\n" + "    \"SYSQTY\": \"5\",\r\n"
				+ "    \"SYSZONEID\": \"ZONE11\",\r\n" + "    \"UserNM\": \"BILAL\",\r\n"
				+ "    \"STAGEBINNO\": \"CCSTAGE\",\r\n" + "    \"Tag1\": [\r\n" + "        {\r\n"
				+ "            \"D_BARCODE\": \"005835_BLOT\",\r\n" + "            \"D_DESCRIPTION\": \"\",\r\n"
				+ "            \"D_QTY\": \"3\",\r\n" + "            \"D_STAGEBINNO\": \"STAGEMOVEBIN\",\r\n"
				+ "            \"D_CCSTAGEBINNO\": \"CCSTAGE\",\r\n"
				+ "            \"D_SUGGESTEDZONEID\": \"ZONE02\",\r\n"
				+ "            \"D_SUGGESTEDBINID\": \"KWI01-G009-Y024-03B1\",\r\n"
				+ "            \"D_SUGGESTEDBINID_1\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_2\": \"\",\r\n"
				+ "            \"D_SUGGESTEDBINID_3\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_4\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_1\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_2\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_3\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_4\": \"\"\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"D_BARCODE\": \"761921_LOT00001095\",\r\n"
				+ "            \"D_DESCRIPTION\": \"\",\r\n" + "            \"D_QTY\": \"6\",\r\n"
				+ "            \"D_STAGEBINNO\": \"STAGEMOVEBIN\",\r\n"
				+ "            \"D_CCSTAGEBINNO\": \"CCSTAGE\",\r\n"
				+ "            \"D_SUGGESTEDZONEID\": \"ZONE11\",\r\n"
				+ "            \"D_SUGGESTEDBINID\": \"KWI08-G001-Y006-01A2\",\r\n"
				+ "            \"D_SUGGESTEDBINID_1\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_2\": \"\",\r\n"
				+ "            \"D_SUGGESTEDBINID_3\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_4\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_1\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_2\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_3\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_4\": \"\"\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"D_BARCODE\": \"ORL-00000677_LOT00012346\",\r\n"
				+ "            \"D_DESCRIPTION\": \"\",\r\n" + "            \"D_QTY\": \"5\",\r\n"
				+ "            \"D_STAGEBINNO\": \"STAGEMOVEBIN\",\r\n"
				+ "            \"D_CCSTAGEBINNO\": \"CCSTAGE\",\r\n"
				+ "            \"D_SUGGESTEDZONEID\": \"ZONE11\",\r\n"
				+ "            \"D_SUGGESTEDBINID\": \"KWI08-G001-Y006-01A2\",\r\n"
				+ "            \"D_SUGGESTEDBINID_1\": \"KWI19-F039-R012-03A1\",\r\n"
				+ "            \"D_SUGGESTEDBINID_2\": \"KWI19-F014-L014-03A1\",\r\n"
				+ "            \"D_SUGGESTEDBINID_3\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_4\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_1\": \"KWNZ15\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_2\": \"KWNZ12\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_3\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_4\": \"\"\r\n"
				+ "        }\r\n" + "    ],\r\n" + "    \"Misc\": \"0\",\r\n" + "    \"DOCTYPE\": \"1\",\r\n"
				+ "    \"IsManual\": \"1\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// View List
	@Test(priority = 39)
	public void VIew_list() {
		String RequestBody = "{\r\n" + "    \"Status\": \"3\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"DOCNO\": \"CC/2223/000000028731\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DeviceID\": \"02C01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// UNDO
	@Test(priority = 40)
	public void UNDO() {
		String RequestBody = "{\r\n" + "    \"Status\": \"8\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"DOCNO\": \"CC/2223/000000028732\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"02C01\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// Post
	@Test(priority = 41)
	public void Post() {
		String RequestBody = "{\r\n" + "    \"Status\": \"2\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"DOCNO\": \"CC/2223/000000028736\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"02C02\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// Empty Bin
	@Test(priority = 41)
	public void Empty_Bin() {
		String RequestBody = "{\r\n" + "    \"Status\": \"98\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "\"TokenID\":\"" + TokenID + "\"" + "," + "    \"BinNo\": \"KWI01-G036-R001-04A7\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DOCNO\": \"CCC/2223/000000028794\",\r\n"
				+ "    \"DeviceID\": \"01C11\",\r\n" + "    \"UserNM\": \"BILAL\",\r\n"
				+ "    \"tag\":\"[{\\\"D_BARCODE\\\":\\\"100995_BLOT\\\",\\\"D_DESCRIPTION\\\":\\\"\\\",\\\"D_QTY\\\":\\\"6\\\",\\\"D_STAGEBINNO\\\":\\\"STAGEMOVEBIN\\\",\\\"D_CCSTAGEBINNO\\\":\\\"CCSTAGE\\\",\\\"D_SUGGESTEDZONEID\\\":\\\"G036\\\",\\\"D_SUGGESTEDBINID\\\":\\\"KWI01-G036-R001-04A7\\\",\\\"D_SUGGESTEDBINID_1\\\":\\\"\\\",\\\"D_SUGGESTEDBINID_2\\\":\\\"\\\",\\\"D_SUGGESTEDBINID_3\\\":\\\"\\\",\\\"D_SUGGESTEDBINID_4\\\":\\\"\\\",\\\"D_SUGGESTEDZONEID_1\\\":\\\"\\\",\\\"D_SUGGESTEDZONEID_2\\\":\\\"\\\",\\\"D_SUGGESTEDZONEID_3\\\":\\\"\\\",\\\"D_SUGGESTEDZONEID_4\\\":\\\"\\\"}]\",\r\n"
				+ "    \"Misc\": \"0\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	/*--------------------------Scan Cycle Count Screen-------------------------------*/
	// ONLOAD CHECK ISMANUAL CC
	@Test(priority = 42)
	public void ONLoad_Check() {
		String RequestBody = "{\r\n" + "    \"Status\": \"7\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\",\r\n" + "    \"DOCTYPE\": \"0\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ONLOAD PREV SCANNED DOC
	@Test(priority = 43)
	public void ONLoad_Prev() {
		String RequestBody = "{\r\n" + "    \"Status\": \"5\",\r\n" + "    \"userID\": \"12511\",\r\n"
				+ "    \"DOCNO\": \"CC/2223/000000028724\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ONLOAD DOC GENERATION
	@Test(priority = 44)
	public void ONLoad_Doc_Gen() {
		String RequestBody = "{\r\n" + "    \"Status\": \"0\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"DeviceID\": \"01C01\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ON BINSCAN
	@Test(priority = 45)
	public void ONBIN_SCAN() {
		String RequestBody = "{\r\n" + "    \"DOCNO\": \"CC/2223/000000028737\",\r\n" + "    \"WHNO\": \"KWI01\",\r\n"
				+ "    \"BinNo\": \"KWI01-G009-Y024-03B1\",\r\n" + "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\""
				+ TokenID + "\"" + "," + "    \"NavBaseUrl\": \"https://staging-omsapi.boutiqaat.com\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody)
				.post(Manual_Cycle_Count_OnBinScan);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// ON BARCODE SCAN
	@Test(priority = 46)
	public void OnBarCode_Scan() {
		String RequestBody = "{\r\n" + "\"Status\": \"2\",\r\n" + "\"Barcode\": \"100995_BLOT\",\r\n"
				+ "\"userID\": \"97458870\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + "," + "\"WHNO\": \"KWI01\"\r\n"
				+ "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody)
				.post(Manual_Cycle_OnBarcode_Scan);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}

	// Confirm
	@Test(priority = 47)
	public void COnFIrm() {
		String RequestBody = "{\r\n" + "    \"Status\": \"1\",\r\n" + "    \"DOCQTY\": \"8\",\r\n"
				+ "    \"userID\": \"12511\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
				+ "    \"BinNo\": \"KWI01-G003-R030-01A1\",\r\n" + "    \"Barcode\": \"229120_BLOT\",\r\n"
				+ "    \"ItemNo\": \"229120\",\r\n" + "    \"ItemDesc\": \"\",\r\n" + "    \"LOTNO\": \"BLOT\",\r\n"
				+ "    \"WHNO\": \"KWI01\",\r\n" + "    \"DOCNO\": \"CC/2223/000000028748\",\r\n"
				+ "    \"Qty\": \"1\",\r\n" + "    \"DeviceID\": \"01C09\",\r\n" + "    \"SYSQTY\": \"0\",\r\n"
				+ "    \"SYSZONEID\": \"\",\r\n" + "    \"UserNM\": \"BILAL\",\r\n"
				+ "    \"STAGEBINNO\": \"CCSTAGE\",\r\n" + "    \"Tag\": [\r\n" + "        {\r\n"
				+ "            \"D_BARCODE\": \"\",\r\n" + "            \"D_DESCRIPTION\": \"\",\r\n"
				+ "            \"D_QTY\": \"0\",\r\n" + "            \"D_STAGEBINNO\": \"STAGEMOVEBIN\",\r\n"
				+ "            \"D_CCSTAGEBINNO\": \"CCSTAGE\",\r\n" + "            \"D_SUGGESTEDZONEID\": \"\",\r\n"
				+ "            \"D_SUGGESTEDBINID\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_1\": \"\",\r\n"
				+ "            \"D_SUGGESTEDBINID_2\": \"\",\r\n" + "            \"D_SUGGESTEDBINID_3\": \"\",\r\n"
				+ "            \"D_SUGGESTEDBINID_4\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_1\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_2\": \"\",\r\n" + "            \"D_SUGGESTEDZONEID_3\": \"\",\r\n"
				+ "            \"D_SUGGESTEDZONEID_4\": \"\"\r\n" + "        }\r\n" + "    ],\r\n"
				+ "    \"Misc\": \"1\",\r\n" + "    \"DOCTYPE\": \"0\",\r\n" + "    \"IsManual\": \"0\"\r\n" + "}";
		System.out.println(RequestBody);
		Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
		System.out.println(response2.getBody().asString());
		JsonPath jsonpath=response2.jsonPath();
		assertEquals(jsonpath.get("StatusCode").toString(), "200");
		System.out.println("--------------------------------------------------------------");
	}
	
	// View List
		@Test(priority = 48)
		public void VIewList() {
			String RequestBody = "{\r\n"
					+ "    \"Status\": \"3\",\r\n"
					+ "    \"userID\": \"12511\",\r\n"
					+ "\"TokenID\":\"" + TokenID + "\"" + ","
					+ "    \"DOCNO\": \"CC/2223/000000028737\",\r\n"
					+ "    \"WHNO\": \"KWI01\",\r\n"
					+ "    \"DeviceID\": \"02C02\"\r\n"
					+ "}";
			System.out.println(RequestBody);
			Response response2 = given().contentType("application/json").body(RequestBody)
					.post(Manual_Cycle_OnBarcode_Scan);
			System.out.println(response2.getBody().asString());
			JsonPath jsonpath=response2.jsonPath();
			assertEquals(jsonpath.get("StatusCode").toString(), "200");
			System.out.println("--------------------------------------------------------------");
		}

		// Undo
				@Test(priority = 49)
				public void Undo() {
					String RequestBody = "{\r\n"
							+ "\"Status\": \"8\",\r\n"
							+ "\"userID\": \"12511\",\r\n"
							+ "\"DOCNO\": \"CC/2223/000000028747\",\r\n"
							+ "\"TokenID\":\"" + TokenID + "\"" + ","
							+ "\"DeviceID\": \"02C01\",\r\n"
							+ "\"WHNO\": \"KWI01\"\r\n"
							+ "}";
					System.out.println(RequestBody);
					Response response2 = given().contentType("application/json").body(RequestBody)
							.post(Manual_Cycle_OnBarcode_Scan);
					System.out.println(response2.getBody().asString());
					JsonPath jsonpath=response2.jsonPath();
					assertEquals(jsonpath.get("StatusCode").toString(), "200");
					System.out.println("--------------------------------------------------------------");
				}
				
				// Post
				@Test(priority = 50)
				public void POst() {
					String RequestBody = "{\r\n" + "    \"Status\": \"2\",\r\n" + "    \"userID\": \"12511\",\r\n"
							+ "    \"DOCNO\": \"CC/2223/000000028736\",\r\n" + "\"TokenID\":\"" + TokenID + "\"" + ","
							+ "    \"DeviceID\": \"02C02\",\r\n" + "    \"WHNO\": \"KWI01\"\r\n" + "}";
					System.out.println(RequestBody);
					Response response2 = given().contentType("application/json").body(RequestBody).post(Manual_Cycle_Count);
					System.out.println(response2.getBody().asString());
					JsonPath jsonpath=response2.jsonPath();
					assertEquals(jsonpath.get("StatusCode").toString(), 200);
					System.out.println("--------------------------------------------------------------");
					
				}
}
