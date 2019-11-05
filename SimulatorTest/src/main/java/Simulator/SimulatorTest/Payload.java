package Simulator.SimulatorTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

	@SerializedName("idTagInfo")
	@Expose
	private IdTagInfo idTagInfo;
	@SerializedName("transactionId")
	@Expose
	private Integer transactionId;

	public IdTagInfo getIdTagInfo() {
		return idTagInfo;
	}

	public void setIdTagInfo(IdTagInfo idTagInfo) {
		this.idTagInfo = idTagInfo;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

}
