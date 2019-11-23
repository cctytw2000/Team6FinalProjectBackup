package ecpay.payment.integration.domain;

/**
 * 當ChoosePayment為ATM時的取號結果通知物件
 * @author mark.chiu
 *
 */
public class ATMRequestObj {
	
	/**
	 * MerchantID
	 * 合作特店編號
	 */
	private String MerchantID;
	
	/**
	 * MerchantTradeNo
	 * 合作特店交易編號
	 */
	private String MerchantTradeNo;
	
	/**
	 * RtnCode
	 * 交易狀態
	 */
	private String RtnCode;
	
	/**
	 * RtnMsg
	 * 交易訊息
	 */
	private String RtnMsg;
	
	/**
	 * TradeNo
	 * 綠界的交易編號
	 */
	private String TradeNo;
	
	/**
	 * TradeAmt
	 * 交易金額
	 */
	private String TradeAmt;

	/**
	 * PaymentType
	 * 合作特店選擇的付款方式
	 */
	private String PaymentType;
	
	/**
	 * TradeDate
	 * 訂單成立時間
	 */
	private String TradeDate;
	
	/**
	 * CheckMacValue
	 * 檢查碼
	 */
	private String CheckMacValue;
	
	/**
	 * BankCode
	 * 繳費銀行代碼
	 */
	private String BankCode;
	
	/**
	 * vAccount
	 * 繳費虛擬帳號
	 */
	private String vAccount;
	
	/**
	 * ExpireDate
	 * 繳費期限
	 */
	private String ExpireDate;
	
	/********************* getters and setters *********************/
	
	/**
	 * 取得MerchantID 合作特店編號
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設定MerchantID 合作特店編號
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * 取得MerchantTradeNo 合作特店交易編號
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * 設定MerchantTradeNo 合作特店交易編號
	 * @param MerchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * 取得RtnCode 交易狀態
	 * @return RtnCode
	 */
	public String getRtnCode() {
		return RtnCode;
	}
	/**
	 * 設定RtnCode 交易狀態
	 * @param RtnCode
	 */
	public void setRtnCode(String rtnCode) {
		RtnCode = rtnCode;
	}
	/**
	 * 取得RtnMsg 交易訊息
	 * @return RtnMsg
	 */
	public String getRtnMsg() {
		return RtnMsg;
	}
	/**
	 * 設定RtnMsg 交易訊息
	 * @param RtnMsg
	 */
	public void setRtnMsg(String rtnMsg) {
		RtnMsg = rtnMsg;
	}
	/**
	 * 取得TradeNo 綠界的交易編號
	 * @return TradeNo
	 */
	public String getTradeNo() {
		return TradeNo;
	}
	/**
	 * 設定TradeNo 綠界的交易編號
	 * @param TradeNo
	 */
	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}
	/**
	 * 取得TradeAmt 交易金額
	 * @return TradeAmt
	 */
	public String getTradeAmt() {
		return TradeAmt;
	}
	/**
	 * 設定TradeAmt 交易金額
	 * @param TradeAmt
	 */
	public void setTradeAmt(String tradeAmt) {
		TradeAmt = tradeAmt;
	}
	/**
	 * 取得PaymentType 合作特店選擇的付款方式
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * 設定PaymentType 合作特店選擇的付款方式
	 * @param PaymentType
	 */
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	/**
	 * 取得TradeDate 訂單成立時間
	 * @return TradeDate
	 */
	public String getTradeDate() {
		return TradeDate;
	}
	/**
	 * 設定TradeDate 訂單成立時間
	 * @param TradeDate
	 */
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	/**
	 * 取得CheckMacValue 檢查碼
	 * @return CheckMacValue
	 */
	public String getCheckMacValue() {
		return CheckMacValue;
	}
	/**
	 * 設定CheckMacValue 檢查碼
	 * @param CheckMacValue
	 */
	public void setCheckMacValue(String checkMacValue) {
		CheckMacValue = checkMacValue;
	}
	/**
	 * 取得BankCode 繳費銀行代碼
	 * @return BankCode
	 */
	public String getBankCode() {
		return BankCode;
	}
	/**
	 * 設定BankCode 繳費銀行代碼
	 * @param BankCode
	 */
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	/**
	 * 取得vAccount 繳費虛擬帳號
	 * @return vAccount
	 */
	public String getvAccount() {
		return vAccount;
	}
	/**
	 * 設定vAccount 繳費虛擬帳號
	 * @param vAccount
	 */
	public void setvAccount(String vAccount) {
		this.vAccount = vAccount;
	}
	/**
	 * 取得ExpireDate 繳費期限
	 * @return ExpireDate
	 */
	public String getExpireDate() {
		return ExpireDate;
	}
	/**
	 * 設定ExpireDate 繳費期限
	 * @param ExpireDate
	 */
	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}
	@Override
	public String toString() {
		return "ATMRequestObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", RtnCode="
				+ RtnCode + ", RtnMsg=" + RtnMsg + ", TradeNo=" + TradeNo + ", TradeAmt=" + TradeAmt + ", PaymentType="
				+ PaymentType + ", TradeDate=" + TradeDate + ", CheckMacValue=" + CheckMacValue + ", BankCode="
				+ BankCode + ", vAccount=" + vAccount + ", ExpireDate=" + ExpireDate + "]";
	}
}
