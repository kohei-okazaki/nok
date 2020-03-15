package jp.co.nok.tool.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import jp.co.nok.common.type.Charset;
import jp.co.nok.common.util.FileUtil.FileSeparator;
import jp.co.nok.common.util.StringUtil;
import jp.co.nok.tool.util.ToolUtil;

/**
 * drop.sqlの自動生成クラス
 *
 * @version 1.0.0
 */
public class DropSqlGenerator extends BaseGenerator {

	@Override
	List<GenerateFile> generateImpl() throws Exception {

		StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
		ToolUtil.getTableList(excel.getRowList()).stream().forEach(e -> {
			body.add("-- " + e.getLogicalName());
			body.add("DROP TABLE IF EXISTS " + e.getPhysicalName() + ";");
		});

		// 自動生成ファイルリスト
		List<GenerateFile> genList = new ArrayList<>();
		GenerateFile generateFile = new GenerateFile();
		generateFile.setCharset(Charset.UTF_8);
		generateFile.setFileName("drop.sql");
		generateFile.setData(body.toString());
		generateFile.setOutputPath(prop.getBaseDir() + FileSeparator.SYSTEM.getValue()
				+ GenerateType.DROP.getPath());
		genList.add(generateFile);
		return genList;
	}

}
