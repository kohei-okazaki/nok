package jp.co.nok.tool.gen;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import jp.co.nok.common.io.property.reader.PropertyReader;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.common.type.BaseEnum;
import jp.co.nok.common.util.FileUtil.FileSeparator;
import jp.co.nok.tool.excel.DmlExcelReader;
import jp.co.nok.tool.excel.Excel;
import jp.co.nok.tool.excel.ExcelReader;
import jp.co.nok.tool.util.ToolUtil;

/**
 * 自動生成基底クラス<br>
 * 自動生成ツールを作成する場合、本クラスを継承し{@linkplain #generateImpl()}に実際の自動生成処理を記述する
 *
 * @version 1.0.0
 */
public abstract class BaseGenerator {

    /** LOG */
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    /** 自動生成ツール設定ファイル情報 */
    protected ToolProperty prop;
    /** 自動生成ツールのExcel情報 */
    protected Excel excel;

    /**
     * 自動生成を行う
     *
     * @throws Exception
     *             自動生成処理に失敗した場合
     */
    final void generate() throws Exception {

        LOG.debug("自動生成開始");

        try {
            // 自動生成ツール設定ファイルを取得
            this.prop = readProp();

            // Excelファイルを取得
            this.excel = getExcelReader().read(prop);

            // 自動生成個別処理
            List<GenerateFile> genFileList = generateImpl();

            // 自動生成ファイルを作成
            ToolUtil.createGenFileList(genFileList);

        } finally {
            LOG.debug("自動生成終了");
        }

    }

    /**
     * 個別自動生成処理を行う<br>
     * 本クラスを継承して実処理を実装すること
     *
     * @return 自動生成ファイルリスト
     * @throws Exception
     *             自動生成個別処理が失敗した場合
     */
    abstract List<GenerateFile> generateImpl() throws Exception;

    /**
     * ExcelファイルのReaderを返す<br>
     * デフォルトで「TABLE_LIST」シートを読み込むReaderを返している<br>
     * DML作成時は本メソッドをOverrideして個別で{@linkplain DmlExcelReader}を返す
     *
     * @return ExcelReader
     * @throws Exception
     *             Excelファイルの読込に失敗した場合
     */
    protected ExcelReader getExcelReader() throws Exception {
        return new ExcelReader();
    }

    /**
     * 設定ファイルを読込を行う
     *
     * @return 設定ファイル
     * @throws URISyntaxException
     *             パスの指定が不正な場合
     */
    private ToolProperty readProp() throws URISyntaxException {

        Path path = Paths.get(this.getClass().getClassLoader().getResource("").toURI());
        String binDir = path.getParent().toString();

        // 設定ファイルを取得
        StringJoiner sj = new StringJoiner(FileSeparator.SYSTEM.getValue());
        sj.add(binDir);
        sj.add("test");
        sj.add("tool.properties");
        ToolProperty prop = new PropertyReader().read(sj.toString(), ToolProperty.class);
        Stream.of(prop.getTargetTables().split(",")).forEach(e -> prop.addTargetTable(e));
        Stream.of(prop.getDmlTables().split(",")).forEach(e -> prop.addDmlTable(e));

        return prop;

    }

    /**
     * 自動生成区分の列挙
     *
     * @version 1.0.0
     */
    public static enum GenerateType implements BaseEnum {

        /** Entity作成 */
        ENTITY("ENTITY", "nok-app\\src\\main\\java\\jp\\co\\nok\\db\\entity",
                EntityGenerator.class),
        /** DAO作成 */
        DAO("DAO", "nok-app\\src\\main\\java\\jp\\co\\nok\\db\\dao",
                DaoGenerator.class),
        /** DDL作成 */
        DDL("DDL", "nok-docs\\02_design\\90_db\\01_ddl", CreateTableGenerator.class),
        /** DROP作成 */
        DROP("DROP", "nok-docs\\02_design\\90_db\\02_drop", DropSqlGenerator.class),
        /** テーブル定義作成 */
        TABLE_DEFINE("TABLE_DEFINE", "nok-docs\\02_design\\90_db\\99_others",
                TableDefineGenerator.class),
        /** DML作成 */
        DML("DML", "nok-docs\\02_design\\90_db\\03_dml", DmlGenerator.class);

        /** 値 */
        private String value;
        /** 出力先パス */
        private String path;
        /** 自動生成クラス型 */
        private Class<? extends BaseGenerator> genClass;

        /**
         * コンストラクタ
         *
         * @param value
         *            値
         * @param path
         *            出力先パス
         * @param genClass
         *            自動生成クラス型
         */
        private GenerateType(String value, String path,
                Class<? extends BaseGenerator> genClass) {
            this.value = value;
            this.path = path;
            this.genClass = genClass;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        public String getPath() {
            return this.path;
        }

        public Class<? extends BaseGenerator> getGenClass() {
            return this.genClass;
        }

        public static GenerateType of(String value) {
            return BaseEnum.of(GenerateType.class, value);
        }

    }
}
