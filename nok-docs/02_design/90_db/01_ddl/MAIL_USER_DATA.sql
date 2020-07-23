-- メールユーザ情報
CREATE TABLE IF NOT EXISTS MAIL_USER_DATA (
  -- ユーザメールID
  SEQ_SER_MAIL_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT 'ユーザメールID',
  -- ログインID
  SEQ_LOGIN_ID INT COMMENT 'ログインID',
  -- メールアドレス
  MAIL_ADDRESS VARBINARY(1024) COMMENT 'メールアドレス',
  -- バージョン情報
  VERSION INT COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE TIMESTAMP COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE TIMESTAMP COMMENT '更新日時'
);