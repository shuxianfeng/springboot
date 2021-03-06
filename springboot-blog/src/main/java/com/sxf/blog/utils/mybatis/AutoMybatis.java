package com.sxf.blog.utils.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shuxf
 * @Date 2018/4/28 17:48
 */
public class AutoMybatis {
    public static final String cp = "classes/mybatis/generatorConfig.xml";

    public static void main(String[] args) throws IOException, XMLParserException {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

//		File configFile = new File(AutoMybatis.class.getResource("/").getFile());
        URL base = AutoMybatis.class.getResource("");	//获取AutoMybatis类所在的包目录
        System.out.println(base);	//file:/E:/maven/movision-1.0.0/target/classes/com/movision/utils/mybatis/
        String path = new File(base.getFile(), "../../../../../../"+cp).getCanonicalPath();
        System.out.println("path>>>"+path);	//path>>>E:\maven\movision-1.0.0\src\main\resources\mybatis\generatorConfig.xml	路径正确

        File configFile = new File(path);

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config;
        try {
            config = cp.parseConfiguration(configFile);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator;
            try {
                myBatisGenerator = new MyBatisGenerator(config, callback,
                        warnings);
                myBatisGenerator.generate(null);
                System.out.println("success generate!");
            } catch (Exception e) {

                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }
}
