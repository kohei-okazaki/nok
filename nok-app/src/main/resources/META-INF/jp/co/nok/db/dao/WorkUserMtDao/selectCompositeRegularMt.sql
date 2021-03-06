SELECT
  WUM.SEQ_WORK_USER_MT_ID
  , WUM.SEQ_LOGIN_ID
  , WUM.SEQ_REGULAR_WORK_MT_ID
  , RWM.BEGIN_HOUR
  , RWM.BEGIN_MINUTE
  , RWM.END_HOUR
  , RWM.END_MINUTE
  , WUM.REG_DATE
FROM
  WORK_USER_MT WUM
  , REGULAR_WORK_MT RWM
WHERE
  WUM.SEQ_REGULAR_WORK_MT_ID = RWM.SEQ_REGULAR_WORK_MT_ID
ORDER BY
  WUM.SEQ_LOGIN_ID,
  WUM.SEQ_WORK_USER_MT_ID DESC;