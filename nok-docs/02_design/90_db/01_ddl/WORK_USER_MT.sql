-- 勤怠ユーザマスタ
CREATE TABLE IF NOT EXISTS WORK_USER_MT (
  -- 勤怠ユーザマスタID
  SEQ_WORK_USER_MT_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '勤怠ユーザマスタID',
  -- ログインID
  SEQ_LOGIN_ID INT NOT NULL COMMENT 'ログインID',
  -- 定時情報マスタID
  SEQ_REGULAR_WORK_MT_ID INT NOT NULL COMMENT '定時情報マスタID',
  -- バージョン情報
  VERSION INT NOT NULL COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE TIMESTAMP NOT NULL COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE TIMESTAMP NOT NULL COMMENT '更新日時'
);