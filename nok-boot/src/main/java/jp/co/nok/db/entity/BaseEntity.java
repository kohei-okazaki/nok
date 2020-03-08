package jp.co.nok.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;

import jp.co.nok.db.listener.AppDaoListener;

/**
 * 基底Entityクラス
 *
 * @version 1.0.0
 */
@Entity(listener = AppDaoListener.class)
public abstract class BaseEntity {

	/** バージョン */
	// @Version
	// private Integer version;
	/** 登録ユーザ */
	// private String regUser;
	/** 登録日時 */
	private LocalDateTime regDate;
	/** 更新ユーザ */
	// private String updateUser;
	/** 更新ユーザ */
	private LocalDateTime updateDate;

	// public String getRegUser() {
	// return regUser;
	// }
	//
	// public void setRegUser(String regUser) {
	// this.regUser = regUser;
	// }

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	// public String getUpdateUser() {
	// return updateUser;
	// }
	//
	// public void setUpdateUser(String updateUser) {
	// this.updateUser = updateUser;
	// }

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

}
