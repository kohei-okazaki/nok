package jp.co.nok.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 定時情報マスタ Entity<br>
 * TODO 削除
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class OntimeMt extends BaseEntity {

	/** 企業コード */
	@Id
	private String companyCode;
	/** 始業時間(時) */
	private String workStartHour;
	/** 始業時間(分) */
	private String workStartMinute;
	/** 終業時間(時) */
	private String workEndHour;
	/** 終業時間(分) */
	private String workEndMinute;

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setWorkStartHour(String workStartHour) {
		this.workStartHour = workStartHour;
	}

	public String getWorkStartHour() {
		return workStartHour;
	}

	public void setWorkStartMinute(String workStartMinute) {
		this.workStartMinute = workStartMinute;
	}

	public String getWorkStartMinute() {
		return workStartMinute;
	}

	public void setWorkEndHour(String workEndHour) {
		this.workEndHour = workEndHour;
	}

	public String getWorkEndHour() {
		return workEndHour;
	}

	public void setWorkEndMinute(String workEndMinute) {
		this.workEndMinute = workEndMinute;
	}

	public String getWorkEndMinute() {
		return workEndMinute;
	}

}
