package jp.co.nok.common.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jp.co.nok.common.type.BaseEnum;

/**
 * File操作のUtil
 *
 * @version 1.0.0
 */
public class FileUtil {

    /**
     * プライベートコンストラクタ
     */
    private FileUtil() {
    }

    /**
     * ファイルを作成する<br>
     * ファイルが存在しない場合のみ、作成
     *
     * @param filePath
     *            ファイルパス
     * @return ファイルのパスクラス
     * @throws IOException
     *             ファイル作成処理に失敗した場合
     */
    public static Path createFile(String filePath) throws IOException {

        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            // ファイルが存在する場合
            return path;
        }
        return Files.createFile(path);
    }

    /**
     * ファイル拡張子の列挙
     *
     * @version 1.0.0
     */
    public static enum FileExtension implements BaseEnum {

        /** csv */
        CSV(".csv"),
        /** zip */
        ZIP(".zip"),
        /** java */
        JAVA(".java"),
        /** sql */
        SQL(".sql"),
        /** properties */
        PROPERTY(".properties");

        /** 値 */
        private String value;

        private FileExtension(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        public static FileExtension of(String value) {
            return BaseEnum.of(FileExtension.class, value);
        }
    }

    /**
     * ファイルセパレータの列挙
     *
     * @version 1.0.0
     */
    public static enum FileSeparator implements BaseEnum {

        /** Windows */
        WINDOWS("\\"),
        /** linux */
        LINUX("/"),
        /** system */
        SYSTEM(FileSystems.getDefault().getSeparator());

        /** セパレータ */
        private String value;

        private FileSeparator(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        public static FileSeparator of(String value) {
            return BaseEnum.of(FileSeparator.class, value);
        }
    }

    /**
     * ファイル改行コードの列挙
     *
     * @version 1.0.0
     */
    public static enum LineFeedType implements BaseEnum {

        /** CR */
        CR("\r"),
        /** LF */
        LF("\n"),
        /** CRLF */
        CRLF("\r\n");

        /** 値 */
        private String value;

        private LineFeedType(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        public static LineFeedType of(String value) {
            return BaseEnum.of(LineFeedType.class, value);
        }

    }
}
