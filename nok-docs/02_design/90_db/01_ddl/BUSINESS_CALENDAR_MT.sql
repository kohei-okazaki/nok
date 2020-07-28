-- 営業日マスタ
CREATE TABLE IF NOT EXISTS BUSINESS_CALENDAR_MT (
  -- 営業日マスタID
  SEQ_BUSINESS_CALENDAR_MT_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '営業日マスタID',
  -- 日付
  DATE DATE NOT NULL COMMENT '日付',
  -- 営業日フラグ
  BUSINESS_FLG VARCHAR(1) NOT NULL COMMENT '営業日フラグ',
  -- バージョン情報
  VERSION INT NOT NULL COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE TIMESTAMP NOT NULL COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE TIMESTAMP NOT NULL COMMENT '更新日時'
);