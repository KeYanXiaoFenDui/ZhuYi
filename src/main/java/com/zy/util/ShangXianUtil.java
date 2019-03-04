package com.zy.util;


import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设置工作路径
 * 设置存放qualifiedNames的文本路径
 * 设置生成文件的路径
 * 运行后生成上线文件
 *
 * @author Admininstrator
 */
public class ShangXianUtil {
    //IDE工作路径
    private static final String wordSpace = "E:/workfiel";
    //放qualifiedNames的文本路径
    private static String qualifiedName = "D:/桌面文件/上线文件/qualifiedName.txt";
    //把要上线的文件要创建到的路径
    private static final String createTo = "D:/桌面文件/上线文件";
    //执行生成上线文件操作的年月日
    private static String operationDate = getYMD();
    //成功数
    private static int success = 0;
    //失败数
    private static int fail = 0;

    public static void main(String[] args){

        factory("D:/桌面文件/ssss.txt","D:/桌面文件/test");
//        String str = "a;sdj{213f}},{sdjlk};ald;;125{sd11}";
//        String regEx = "\\{.*?\\}";
//        String regEx2 = "<([^>]*)>";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(str);
//        System.out.println(m.replaceAll("").trim());
    }


    public static double m2(double f) {
         DecimalFormat df = new DecimalFormat("#.00");
        String dd = df.format(f);
         return Double.parseDouble(dd);
     }

//    public static void main(String[] args) {
////        factory("C:/Users/冼世龙/Desktop/ssss.txt","C:/Users/冼世龙/Desktop/test");
////        dllTo_aAsASQL("");
////        domainParamToSetMethod("s");
//        List<String> line = readTxtLines("D:/桌面文件/ssss.txt");
//        int flag = 0;//0找/**  1找中文方法名  2找英文方法名
//        String system = "/api/business";
//        String model = "";
//        String method = "";
//        for(String l : line){
//            if(flag == 0){
//                int i = l.indexOf("/**");
//                if(i != -1){
//                    flag = 1;
//                    continue;
//                }
//            }
//            if(flag == 1){
//                method = l.trim().substring(2);
////                method = l.trim();
////                System.out.println("method:"+method);
//                flag = 2;
//                continue;
//            }
//            if(flag == 2){
//                int i = l.indexOf("@RequestMapping");
//                if(i != -1){
//                    l = l.substring(l.indexOf("\"/")+1,l.lastIndexOf("\""));
//                    if(model.equals("")){model = l.trim();flag = 0;System.out.println("model:"+l);continue;}
//                    System.out.println(system+model+l.trim()+"\t"+method);
//                    flag = 0;
//                    continue;
//                }
//            }
//        }
//    }

    /**
     * 读取txt里的dll(数据库对象数据),,生成对象,,增删查改Sql,,接口和实现类
     * @param inPath 输入文件 D:/xxxx/xxx/xxx.txt
     * @param outPath 输出路径 D:/xxxx/xx
     */
    private static void factory(String inPath,String outPath){
        Map object = getTableMsg(inPath);
        createDomain(object,outPath+"/domain");
        List<Map> methods = createMapper(object,outPath+"/mapper");
        createInterface(methods,object,outPath+"/service");
        createImpl(methods,object,outPath+"/service/impl");
    }
    /**
     * 生成实现类ServiceImpl,,依赖createMapper方法的返回
     * @param methods
     * @param object
     * @param parentPath
     */
    private static void createImpl(List<Map> methods,Map object,String parentPath){
        StringBuffer sb = new StringBuffer();
        String domainName = (String)object.get("domainName");
        List<Map> params = (List<Map>)object.get("params");
        sb.append("@Service\n");
        sb.append("public class "+toUpperCaseFirstOne(domainName)+"ServiceImpl implements I"+toUpperCaseFirstOne(domainName)+"Service{\n");
        sb.append("@Autowired\n");
        sb.append("private "+toUpperCaseFirstOne(domainName)+"Mapper "+domainName+"Mapper;\n");
        for (Map method:methods){
            sb.append("@Override\n");
            String m = getStr(method.get("IMethod"),"");
            sb.append(m.replaceAll(";","{")+"\n");
            sb.append("return "+domainName+"Mapper."+getStr(method.get("methodName"),"")+"("+getStr(method.get("params"),"")+");\n");
            sb.append("}\n");
        }
        sb.append("}");
        System.out.println(sb.toString());
        writeToTxt(toUpperCaseFirstOne(domainName)+"ServiceImpl.java",parentPath,sb.toString());
    }
    /**
     * 生成接口,,依赖createMapper方法的返回
     * @param methods
     * @param object
     * @param parentPath
     */
    private static void createInterface(List<Map> methods,Map object,String parentPath){
        StringBuffer sb = new StringBuffer();
        String domainName = (String)object.get("domainName");
        List<Map> params = (List<Map>)object.get("params");
        sb.append("@Service\n");
        sb.append("public interface I"+toUpperCaseFirstOne(domainName)+"Service {\n");
        for (Map method:methods){
            sb.append(getStr(method.get("IMethod"),"")+"\n");
        }
        sb.append("}");
        System.out.println(sb.toString());
        writeToTxt("I"+toUpperCaseFirstOne(domainName)+"Service.java",parentPath,sb.toString());
    }
    /**
     * 生成增删查改Mapper对象,,,配合getTableMsg使用
     * @param object
     */
    private static List<Map> createMapper(Map object,String parentPath){
        List<Map> methods = new ArrayList<Map>();
        StringBuffer sb = new StringBuffer();
        StringBuffer temp1 = new StringBuffer();
        StringBuffer temp2 = new StringBuffer();
        StringBuffer temp3 = new StringBuffer();
        String tableName = (String)object.get("tableName");
        String domainName = (String)object.get("domainName");
        List<Map> params = (List<Map>)object.get("params");
        sb.append("@Mapper\n");
        sb.append("public interface "+toUpperCaseFirstOne(domainName)+"Mapper {\n");
        sb.append("\n");
        for(Map m:params){//house_id
            if(getStr(m.get("sqlParam"),"").equals("id")){continue;}
            temp1.append(getStr(m.get("sqlParam"),"")+",");
        }
        temp1.deleteCharAt(temp1.length()-1);
        for(Map m:params){//#{houseId}
            if(getStr(m.get("domainParam"),"").equals("id")){continue;}
            temp2.append("#{"+getStr(m.get("domainParam"),"")+"},");
        }
        temp2.deleteCharAt(temp2.length()-1);
        for(Map m:params){//house_id=#{houseId}
            if(getStr(m.get("domainParam"),"").equals("id")){continue;}
            temp3.append(getStr(m.get("sqlParam"),"")+"="+"#{"+getStr(m.get("domainParam"),"")+"},");
        }
        temp3.deleteCharAt(temp3.length()-1);

        sb.append("@Insert(\"INSERT into "+tableName+" ("+temp1.toString()+") values ("+temp2.toString()+")\")\n");
        sb.append("@Options(useGeneratedKeys=true,keyProperty=\"id\")\n");
        sb.append("public int insert"+toUpperCaseFirstOne(domainName)+"("+toUpperCaseFirstOne(domainName)+" "+domainName+");\n");
        sb.append("\n");
        Map insertMap = new HashMap();
        insertMap.put("IMethod","public int insert"+toUpperCaseFirstOne(domainName)+"("+toUpperCaseFirstOne(domainName)+" "+domainName+");\n");
        insertMap.put("methodName","insert"+toUpperCaseFirstOne(domainName));
        insertMap.put("params",domainName);
        insertMap.put("returnType","int");
        methods.add(insertMap);

        sb.append("@Update(\"UPDATE "+tableName+" SET "+temp3.toString()+" where id=#{id}\")\n");
        sb.append("public int update"+toUpperCaseFirstOne(domainName)+"("+toUpperCaseFirstOne(domainName)+" "+domainName+");\n");
        sb.append("\n");
        Map updateMap = new HashMap();
        updateMap.put("IMethod","public int update"+toUpperCaseFirstOne(domainName)+"("+toUpperCaseFirstOne(domainName)+" "+domainName+");\n");
        updateMap.put("methodName","update"+toUpperCaseFirstOne(domainName));
        updateMap.put("params",domainName);
        updateMap.put("returnType","int");
        methods.add(updateMap);

        sb.append("@Delete(\"delete from "+tableName+" where id=#{id}\")\n");
        sb.append("public int delete"+toUpperCaseFirstOne(domainName)+"(int id);\n");
        sb.append("\n");
        Map deleteMap = new HashMap();
        deleteMap.put("IMethod","public int delete"+toUpperCaseFirstOne(domainName)+"(int id);\n");
        deleteMap.put("methodName","delete"+toUpperCaseFirstOne(domainName));
        deleteMap.put("params","id");
        deleteMap.put("returnType","int");
        methods.add(deleteMap);

        sb.append("@Select(\"select * from "+tableName+" where id=#{id}\")\n");
        sb.append("public "+toUpperCaseFirstOne(domainName)+" get"+toUpperCaseFirstOne(domainName)+"(int id);\n");
        sb.append("\n");
        Map selectMap = new HashMap();
        selectMap.put("IMethod","public "+toUpperCaseFirstOne(domainName)+" get"+toUpperCaseFirstOne(domainName)+"(int id);\n");
        selectMap.put("methodName","get"+toUpperCaseFirstOne(domainName));
        selectMap.put("params","id");
        selectMap.put("returnType",toUpperCaseFirstOne(domainName));
        methods.add(selectMap);

        sb.append("}");
        System.out.println(sb.toString());
        writeToTxt(toUpperCaseFirstOne(domainName)+"Mapper.java",parentPath,sb.toString());
        return methods;
    }
    /**
     * 生成domain对象,,,配合getTableMsg使用
     * @param object
     */
    private static void createDomain(Map object,String parentPath){
        StringBuffer sb = new StringBuffer();
        String tableName = (String)object.get("tableName");
        String domainName = (String)object.get("domainName");
        List<Map> params = (List<Map>)object.get("params");
        sb.append("public class "+toUpperCaseFirstOne(domainName)+" implements Serializable {\n");
        for(Map m:params){
            sb.append("private " + getStr(m.get("domainType"),"") + " "+getStr(m.get("domainParam"),"")+";"+"//"+getStr(m.get("comment"),"")+"\n");
        }
        sb.append("\n");
        for(Map m:params){
            String domainType = getStr(m.get("domainType"),"");
            String DomainParam = toUpperCaseFirstOne(getStr(m.get("domainParam"),""));
            String domainParam = getStr(m.get("domainParam"),"");
            sb.append("public "+domainType+" get"+DomainParam+"() { return "+domainParam+"; }\n\n");
            sb.append("public void set"+DomainParam+"("+domainType+" "+domainParam+") { this."+domainParam+" = "+domainParam+"; }\n\n");
        }
        sb.append("}");
        System.out.println(sb.toString());
        writeToTxt(toUpperCaseFirstOne(domainName)+".java",parentPath,sb.toString());
    }
    /**
     * 根据表Dll(对象信息)获取:表名,,表字段,,表字段类型,,字段注释,,实体对象字段,,实体对象类型,,类名
     * @return
     */
    public static Map<String,Object> getTableMsg(String inPath){
        List<String> s = readTxtLines(inPath);
        StringBuffer sb = new StringBuffer();
        String domainName = "",tableName = "",tableNameCN = "",comment;
        List<Map> list = new ArrayList<Map>();
        for (String ss : s) {
            if (ss.indexOf("CREATE TABLE") != -1){
                tableName = ss.substring(ss.indexOf("`")+1,ss.lastIndexOf("`"));
                domainName = _aToA(tableName);
            }else{
                try{
                    ss = ss.trim();
                    if(!ss.substring(0,1).equals("`")){throw new RuntimeException();}
                    Map object = new HashMap();

                    String sqlParam = ss.substring(ss.indexOf("`")+1, ss.lastIndexOf("`"));
                    String domainParam = _aToA(sqlParam);
                    object.put("sqlParam",sqlParam);
                    object.put("domainParam",domainParam);

                    ss = ss.substring(ss.indexOf(" ")+1);
                    String sqlType = ss.substring(0,ss.indexOf(" "));
                    if(sqlType.indexOf("(")!=-1){
                        sqlType = sqlType.substring(0,sqlType.indexOf("("));
                    }
                    String domainType = sqlTypeToDomainType(sqlType);
                    object.put("sqlType",sqlType);
                    object.put("domainType",domainType);
                    if(ss.indexOf("COMMENT '") != -1){
                        comment = ss.substring(ss.indexOf("COMMENT '")+9,ss.lastIndexOf("'"));
                        object.put("comment",comment);
                    }
                    list.add(object);
                }catch(Exception e){
                    if(ss.indexOf("COMMENT") != -1){
                        tableNameCN = ss.substring(ss.indexOf("'")+1,ss.lastIndexOf("'"));
                    }
                    System.out.println("无效行:"+ss);
                    continue;
                }
            }
        }
        Map<String,Object> objects = new HashMap();
        objects.put("tableName",tableName);
        objects.put("domainName",domainName);
        objects.put("tableNameCN",tableNameCN);
        objects.put("params",list);
        System.out.println("tableName:"+tableName);
        System.out.println("domainName:"+domainName);
        for (Map m : list){
            System.out.println(m.get("sqlType"));
            System.out.println(m.get("domainType"));
            System.out.println(m.get("sqlParam"));
            System.out.println(m.get("domainParam"));
            System.out.println(m.get("comment"));
            System.out.println("++++++++++++++++++++++++++++++++");
        }
        return objects;
    }

    //根据数据库对象转成文档数据库表格
    //eg:2		图片	URL		pic_url		varchar(500)		否		无
    private static void dllToTableFileMsg() {
        List<String> s = readTxtLines("D:/桌面文件/ssss.txt");
        StringBuffer sb = new StringBuffer();
        String str;
        int num = 1;
        for (String ss : s) {
            ss = ss.trim();
            //paramName
            System.out.print(num + "\t");
            String china = ss.substring(ss.indexOf("'") + 1, ss.lastIndexOf("'"));
            System.out.print(china + "\t");
            String name = ss.substring(ss.indexOf("`") + 1, ss.lastIndexOf("`"));
            System.out.print(name + "\t");
            String dataType = ss.substring(ss.indexOf(" ") + 1);
            dataType = dataType.substring(0, dataType.indexOf(" "));
            System.out.print(dataType + "\t");
            System.out.print("否" + "\t");
            System.out.println("无");
            num++;
        }
        System.out.println(sb.toString());
    }

    //根据对象参数获取setXXX
    //eg:private int id; --> a.setId(Integer.parseInt(getStr("","0")));
    private static void domainParamToSetMethod(String domainName) {
        List<String> s = readTxtLines("D:/桌面文件/ssss.txt");
        for (String ss : s) {
            ss = ss.trim();
            ss = ss.substring(0, ss.lastIndexOf(";") + 1);
            if (ss.indexOf(" int ") != -1) {
                System.out.println(domainName + ".set" + toUpperCaseFirstOne(ss.substring(ss.lastIndexOf(" ") + 1, ss.lastIndexOf(";"))) + "(Integer.parseInt(getStr(\"\",\"0\")));");
            } else if (ss.indexOf(" String ") != -1) {
                System.out.println(domainName + ".set" + toUpperCaseFirstOne(ss.substring(ss.lastIndexOf(" ") + 1, ss.lastIndexOf(";"))) + "(getStr(\"\",\"\"));");
            } else if (ss.indexOf(" double ") != -1) {
                System.out.println(domainName + ".set" + toUpperCaseFirstOne(ss.substring(ss.lastIndexOf(" ") + 1, ss.lastIndexOf(";"))) + "(Double.parseDouble(getStr(\"\",\"0\")));");
            } else if (ss.indexOf(" Date ") != -1) {
                System.out.println("try {");
                System.out.println("\tSimpleDateFormat format = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
                System.out.println("\tLong time = Long.parseLong(getStr(paramsHm.get(\"\"),\"\"));");
                System.out.println("\tString d = format.format(time);");
                System.out.println("\tDate date=format.parse(d);");
                System.out.println("\t" + domainName + ".set" + toUpperCaseFirstOne(ss.substring(ss.lastIndexOf(" ") + 1, ss.lastIndexOf(";"))) + "(date);");
                System.out.println("} catch (ParseException e) {");
                System.out.println("\te.printStackTrace();");
                System.out.println("}");
            } else {
                System.out.println(domainName + ".set" + toUpperCaseFirstOne(ss.substring(ss.lastIndexOf(" ") + 1, ss.lastIndexOf(";"))) + "();");
            }
        }
    }

    //数据库下杠字段 as 驼峰
    private static void dllTo_aAsASQL(String domainName) {
        List<String> s = readTxtLines("D:/桌面文件/ssss.txt");
        StringBuffer sb = new StringBuffer();
        String str;
        for (String ss : s) {
//            str = ss.substring(ss.indexOf("COMMENT"));
            ss = ss.trim();
            ss = ss.substring(ss.indexOf("`")+1);
            ss = ss.substring(0, ss.indexOf("`"));
            if(!domainName.equals("")){
                sb.append("ifnull("+domainName+"." + ss + ",'')");
                ss = _aToA(ss);
                sb.append(" as " + ss + ", ");
           }else{
                sb.append("ifnull(" + ss + ",'')");
                ss = _aToA(ss);
                sb.append(" as " + ss + ", ");
            }
        }
        System.out.println(sb.toString());
    }

    private static void dllToUpdateSql() {
        List<String> s = readTxtLines("D:/桌面文件/ssss.txt");
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (String ss : s) {
            ss = ss.substring(ss.indexOf("`") + 1, ss.lastIndexOf("`"));
            sb.append(ss + "=#{");
            ss = _aToA(ss);
            sb.append(ss + "},");
        }
        System.out.println(sb.toString());
    }

    private static void dllToDomainOrVo() {
        List<String> s = readTxtLines("D:/桌面文件/ssss.txt");
        StringBuffer sb = new StringBuffer();
        for (String ss : s) {
            ss = ss.trim();
            //参数类型
            String paramType = sqlTypeToDomainType(ss.substring(ss.lastIndexOf("`") + 2, ss.indexOf("(")));
            sb.append("private " + paramType + " ");
            ss = _aToA(ss);
            sb.append(ss.substring(ss.indexOf("`")+1, ss.lastIndexOf("`")) + ";\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * 类型转换,,数据库参数类型转换为java对象参数类型
     * @param str
     * @return
     */
    private static String sqlTypeToDomainType(String str){
        String[] aa = {"int","varchar","datetime","timestamp","decimal","smallint"};
        String[] AA = {"int","String","Date","Date","double","int"};
        for (int i = 0; i < aa.length; i++) {
            str = str.replaceAll(aa[i], AA[i]);
        }
        return str;
    }

    private static void dllToInsertSql() {
        List<String> s = readTxtLines("D:/桌面文件/ssss.txt");
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (String ss : s) {
            sb.append(ss.substring(ss.indexOf("`") + 1, ss.lastIndexOf("`")) + ", ");
        }
        System.out.println(sb.toString());
        for (String ss : s) {
            sb2.append("#{" + ss.substring(ss.indexOf("`") + 1, ss.lastIndexOf("`")) + "}, ");
        }
        System.out.println(sb2.toString());
        String str = sb2.toString();
        str = _aToA(str);
        System.out.println(str);
    }

    /**
     * 字符串全替换,,,下杠小写字母替换为对应大写字母,eg:_a替换为A
     * cert_owner_title变为certOwnerTitle
     *
     * @param str
     * @return
     */
    private static String _aToA(String str) {
        String[] aa = {"_a", "_b", "_c", "_d", "_e", "_f", "_g", "_h", "_i", "_j", "_k", "_l", "_m", "_n", "_o", "_p", "_q", "_r", "_s", "_t", "_u", "_v", "_w", "_x", "_y", "_z"};
        String[] AA = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < aa.length; i++) {
            str = str.replaceAll(aa[i], AA[i]);
        }
        return str;
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 返回一个装有<文件本地路径><ftp路径>和<文件生成路径>的对象的集合
     *
     * @return List<a>
     */
    private static List<a> shangXian() {
        List<String> qns = readTxtLines(qualifiedName);
        List<a> as = new ArrayList<a>();
        String localPath = "";
        String ftpPath = "";
        ShangXianUtil sxu = new ShangXianUtil();
        for (String qn : qns) {
            a ccc = sxu.new a();
            localPath = qn.replaceAll("/src/java/", "/web/WEB-INF/classes/");
            localPath = localPath.replaceAll(".java", ".class");
            ftpPath = qn.replaceAll("/mth3-iagent/", "/mthia/");
            ftpPath = ftpPath.replaceAll("/mth4-home/", "/mthhome/");
            ftpPath = ftpPath.replaceAll("/web/", "/");
            ftpPath = ftpPath.replaceAll("/src/java/", "/WEB-INF/classes/");
            ftpPath = ftpPath.replaceAll("/src/resource/", "/WEB-INF/classes/");
            ftpPath = ftpPath.replaceAll(".java", ".class");
            ccc.setFtpPath(ftpPath);
            ccc.setLocalPath(wordSpace + localPath);
            File file = new File(wordSpace + localPath);
            if (file.exists()) {
                int end = ftpPath.lastIndexOf("/");
                String aimPath = ftpPath.substring(0, end);
                aimPath = operationDate + aimPath;
                ccc.setShangXianPath(createTo + aimPath);
                as.add(ccc);
            } else {
                System.out.println("-----------------不存在!-----------------------");
                System.out.println("localPath:" + wordSpace + localPath);
                System.out.println("ftpPath:" + ftpPath);
                System.out.println("-----------------------------------------------");
                fail++;
            }
        }
        return as;
    }

    /**
     * 给出类型"F"or"D"和路径,创建文件或文件夹
     *
     * @param type "F"=文件,"D"=文件夹
     * @param Path 路径 D:/xxx/xxx/xx.xxx or D:/xxx/xx
     * @return boolean
     */
    public static boolean createFileOrDir(String type, String Path) {
        if (type != null && !type.equals("")) {
            if (type.equalsIgnoreCase("F")) {
                File file = new File(Path).getParentFile();
                if (!file.exists()) {
                    file.mkdirs();
                }
                try {
                    file = new File(Path);
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else if (type.equalsIgnoreCase("D")) {
                File file = new File(Path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 返回"/yyyy.mm.dd上线"字符串
     *
     * @return
     */
    private static String getYMD() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String result = dateFormat.format(now);
        result = "/" + result + "上线";
        return result;
    }

    /**
     * 传入目标文件路径,读出每一行内容
     *
     * @param filePath
     * @return List<String>
     */
    public static List<String> readTxtLines(String filePath) {
        List<String> strs = new ArrayList<String>();
        File file = new File(filePath);
        InputStreamReader read = null;
        BufferedReader br = null;
        try {
            read = new InputStreamReader(new FileInputStream(
                    file), "utf-8");
            br = new BufferedReader(read);// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                strs.add(s);
            }
            br.close();
            return strs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 调用系统copy方法复制文件
     * 若存在,会覆盖原有文件
     * D:/xxx/xxxx/xxx/xx.txt
     *
     * @param fromPath 源文件路径
     * @param toPath   目标路径
     * @return
     */
    public static boolean copy(String fromPath, String toPath) {
        fromPath = fromPath.replaceAll("/", "\\\\");
        toPath = toPath.replaceAll("/", "\\\\");
        try {
            Runtime.getRuntime().exec("cmd.exe /c copy " + fromPath + " " + toPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 把指定的内容写入到指定文件
     *
     * @param fileName   文件名
     * @param parentPath 文件所在目录
     * @param content    内容<String>
     * @return
     */
    public static boolean writeToTxt(String fileName, String parentPath, String content) {
        File fileDir = new File(parentPath);
        BufferedWriter bw = null;
        try {
            fileDir.mkdirs();
            File fileTxt = new File(fileDir.getPath() + "/" + fileName);
            bw = new BufferedWriter(new FileWriter(fileTxt));
            bw.write(content);
            bw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 创建文件
     * @param fileName
     * @return
     */
    public static boolean createFile(File fileName)throws Exception{
        try{
            if(!fileName.exists()){
                fileName.createNewFile();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     *读取TXT内容
     * @param file
     * @return
     */
    public static String readTxtFile(File file){
        String result = "";
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"gbk");
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            while((s=br.readLine())!=null){
                result = result  + s;
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 写入TXT，覆盖原内容
     * @param content
     * @param fileName
     * @return
     * @throws Exception
     */
    public static boolean writeTxtFile(String content,File fileName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 写入TXT，追加写入
     * @param filePath
     * @param content
     */
    public static void fileChaseFW(String filePath, String content) {
        try {
            //构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("文件写入失败！" + e);
        }
    }

    private static String getStr(Object str, String edefaultStrfaultStr) {
        if (str == null || str.toString().trim().equals("")) {
            return edefaultStrfaultStr;
        }
        return str.toString().trim();
    }

    class a {
        private String localPath;
        private String ftpPath;
        private String shangXianPath;

        public a() {
        }

        public a(String localPath, String ftpPath, String shangXianPath) {
            this.localPath = localPath;
            this.ftpPath = ftpPath;
            this.shangXianPath = shangXianPath;
        }

        public String getLocalPath() {
            return localPath;
        }

        public void setLocalPath(String localPath) {
            this.localPath = localPath;
        }

        public String getFtpPath() {
            return ftpPath;
        }

        public void setFtpPath(String ftpPath) {
            this.ftpPath = ftpPath;
        }

        public String getShangXianPath() {
            return shangXianPath;
        }

        public void setShangXianPath(String shangXianPath) {
            this.shangXianPath = shangXianPath;
        }

        @Override
        public String toString() {
            return "a [localPath=" + localPath + ", ftpPath=" + ftpPath
                    + ", shangXianPath=" + shangXianPath + "]";
        }

    }
}