package jp.co.nok.tool;

import jp.co.nok.tool.gen.BaseGenerator.GenerateType;
import jp.co.nok.tool.gen.GenerateInvoker;

/**
 * 自動生成起動クラス
 *
 * @version 1.0.0
 */
public class ToolInvoker {

    /**
     * 起動メソッド<br>
     * {@linkplain GenerateType}を可変引数として指定
     *
     * @param args
     *            使わない
     */
    public static void main(String[] args) {

        // GenerateInvoker.invoke(GenerateType.TABLE_DEFINE);
        // GenerateInvoker.invoke(GenerateType.DDL);
        // GenerateInvoker.invoke(GenerateType.DROP);
        // GenerateInvoker.invoke(GenerateType.ENTITY);
        // GenerateInvoker.invoke(GenerateType.DAO);

        GenerateInvoker.invoke(
                GenerateType.TABLE_DEFINE,
                GenerateType.DDL,
                GenerateType.DROP,
                GenerateType.ENTITY,
                GenerateType.DAO);

    }

}
