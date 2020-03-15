-- ログインユーザ情報
CREATE TABLE IF NOT EXISTS LOGIN_USER_DATA (
  -- ログインID
  SEQ_LOGIN_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT 'ログインID',
  -- メールアドレス
  MAIL_ADDRESS VARBINARY(1024) COMMENT 'メールアドレス',
  -- パスワード
  PASSWORD VARCHAR(64) COMMENT 'パスワード',
  -- パスワード有効期限
  PASSWORD_EXPIRE DATE COMMENT 'パスワード有効期限',
  -- バージョン情報
  VERSION INT COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE TIMESTAMP COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE TIMESTAMP COMMENT '更新日時'
);