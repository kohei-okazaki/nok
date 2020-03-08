package jp.co.nok.common.log;

import jp.co.nok.common.type.BaseEnum;

/**
 * Logger
 *
 * @version 1.0.0
 */
public class Logger {

	/** ロガー */
	private org.slf4j.Logger logger;

	/**
	 * コンストラクタ
	 *
	 * @param logger
	 *            org.slf4j.Logger
	 */
	Logger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	/**
	 * Debugログを出力する
	 *
	 * @param bean
	 *            Bean
	 */
	public void debugRes(Object bean) {
		logger.debug(LogMessageFactory.getLogMessage(bean));
	}

	/**
	 * Debugログを出力する
	 *
	 * @param msg
	 *            ログメッセージ
	 */
	public void debug(String msg) {
		logger.debug(msg);
	}

	/**
	 * Debugログを出力する
	 *
	 * @param format
	 *            フォーマット
	 * @param args
	 *            出力値
	 */
	public void debug(String format, Object... args) {
		logger.debug(format, args);
	}

	/**
	 * Infoログを出力する
	 *
	 * @param bean
	 *            Bean
	 */
	public void infoRes(Object bean) {
		logger.info(LogMessageFactory.getLogMessage(bean));
	}

	/**
	 * Infoログを出力する
	 *
	 * @param msg
	 *            ログメッセージ
	 */
	public void info(String msg) {
		logger.info(msg);
	}

	/**
	 * Infoログを出力する
	 *
	 * @param format
	 *            フォーマット
	 * @param args
	 *            出力値
	 */
	public void info(String format, Object... args) {
		logger.info(format, args);
	}

	/**
	 * Warnログを出力する
	 *
	 * @param bean
	 *            Bean
	 */
	public void warnRes(Object bean) {
		logger.warn(LogMessageFactory.getLogMessage(bean));
	}

	/**
	 * Warnログを出力する
	 *
	 * @param bean
	 *            Bean
	 * @param t
	 *            例外
	 */
	public void warnRes(Object bean, Throwable t) {
		logger.warn(LogMessageFactory.getLogMessage(bean), t);
	}

	/**
	 * Warnログを出力する
	 *
	 * @param msg
	 *            ログメッセージ
	 */
	public void warn(String msg) {
		logger.warn(msg);
	}

	/**
	 * warnログを出力する
	 *
	 * @param format
	 *            フォーマット
	 * @param args
	 *            出力値
	 */
	public void warn(String format, Object... args) {
		logger.warn(format, args);
	}

	/**
	 * Warnログを出力する
	 *
	 * @param msg
	 *            ログメッセージ
	 * @param t
	 *            例外
	 */
	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
	}

	/**
	 * Errorログを出力する
	 *
	 * @param bean
	 *            Bean
	 */
	public void errorRes(Object bean) {
		logger.error(LogMessageFactory.getLogMessage(bean));
	}

	/**
	 * Errorログを出力する
	 *
	 * @param bean
	 *            Bean
	 * @param t
	 *            例外
	 */
	public void errorRes(Object bean, Throwable t) {
		logger.error(LogMessageFactory.getLogMessage(bean), t);
	}

	/**
	 * Errorログを出力する
	 *
	 * @param msg
	 *            ログメッセージ
	 */
	public void error(String msg) {
		logger.error(msg);
	}

	/**
	 * errorログを出力する
	 *
	 * @param format
	 *            フォーマット
	 * @param args
	 *            出力値
	 */
	public void error(String format, Object... args) {
		logger.error(format, args);
	}

	/**
	 * Errorログを出力する
	 *
	 * @param msg
	 *            ログメッセージ
	 * @param t
	 *            例外
	 */
	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	/**
	 * ログレベルの列挙
	 *
	 * @version 1.0.0
	 */
	public static enum LogLevel implements BaseEnum {

		/** DEBUG */
		DEBUG("DEBUG"),
		/** INFO */
		INFO("INFO"),
		/** WARN */
		WARN("WARN"),
		/** ERROR */
		ERROR("ERROR");

		/** 値 */
		private String value;

		/**
		 * コンストラクタ
		 *
		 * @param value
		 *            値
		 */
		private LogLevel(String value) {
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getValue() {
			return this.value;
		}

		/**
		 * @see jp.co.nok.common.type.BaseEnum#of(Class, String)
		 * @param value
		 *            値
		 * @return LogLevel
		 */
		public static LogLevel of(String value) {
			return BaseEnum.of(LogLevel.class, value);
		}

	}

}
