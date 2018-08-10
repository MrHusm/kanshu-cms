package com.yxsd.kanshu.log;

import com.alibaba.fastjson.JSON;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.HttpUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by lenovo on 2018/7/17.
 */
@Controller
@Scope("prototype")
@RequestMapping("log")
public class LogController extends BaseController {

//    @RequestMapping("reportLog")
//    public void reportLog(HttpServletResponse response, HttpServletRequest request){
//        initLog();
//    }

    public static void initLog(){
        System.out.println("开始上报日志");
        //所有图书和章节
        List<Map<String,List<Chapter>>> books = new ArrayList<Map<String,List<Chapter>>>();

        //String[] bookIds = {"805300162","805300161","805300160","805300159","805300158","805300157","805300156"};
        String[] bookIds = {"805300094"};
        for(String bookId : bookIds){
            String result = HttpUtils.getContent("http://cx.ikanshu.cn/cx/itf/getvolume?bookId="+bookId,"UTF-8");
            List<Volume> volumes = JSON.parseArray(JSON.parseObject(result).getString("list"),Volume.class);
            Map<String,List<Chapter>> book = new HashMap<String, List<Chapter>>();
            List<Chapter> chapters = new ArrayList<Chapter>();
            for(Volume volume : volumes){
                chapters.addAll(volume.getBookChapters());
            }
            book.put(bookId,chapters);
            books.add(book);
        }

        //章节日志十个一组存放
        List<List<LogItem>> data = new ArrayList<List<LogItem>>();
        List<LogItem> logs = new ArrayList<LogItem>();
        Map<String,String> ext = new HashMap<String, String>();
        ext.put("isvip","0");
        for(Map<String,List<Chapter>> book : books){
            String bookId = (String) book.keySet().toArray()[0];
            for(int i = 1; i < book.get(bookId).size(); i++){
                Chapter chapter = book.get(bookId).get(i);
                if(i % 10 == 1){
                    logs = new ArrayList<LogItem>();
                }
                LogItem log = new LogItem();
                log.setPfp("1-3");
                log.setPft("2019");
                log.setDid(bookId);
                log.setMsg(chapter.getId());
                log.setVersion("4.0.2");
                log.setUploaddate(String.valueOf(System.currentTimeMillis()));
                log.setExt(JSON.toJSONString(ext));
                logs.add(log);
                if(i % 10 == 0){
                    data.add(logs);
                }
            }
        }

        try {
            Random random = new Random();
            //随机睡眠时间
            int[] sleepTime = {1000,2000,3000};
            for(int i = 0; i < 800; i++){
                System.out.println("开始上报第" + (i+1) * 10 +"条日志");
                int num = random.nextInt(data.size());
                StringBuffer params = new StringBuffer();
                //随机生成
                String cnid = getCnid();
                String imsi = getImsi();
                String uid = getUid();
                String model = getModel();
                String imei = getImei();
                String mac = getMac();
                String brand = getBrand();

                params.append("?cnid=").append(cnid);
                params.append("&umeng=").append("FreeShu_xiaomi");
                params.append("&version=").append("4.0.2");
                params.append("&vercode=").append("67");
                params.append("&imei=").append(imei);
                params.append("&imsi=").append(imsi);
                params.append("&uid=").append(uid);
                params.append("&packname=").append("com.mianfeia.book");
                params.append("&oscode=").append("23");
                params.append("&model=").append(model);
                params.append("&other=a");
                params.append("&vcode=").append("67");
                params.append("&channelId=").append(cnid);
                params.append("&mac=").append(mac);
                params.append("&platform=").append("android").append("&appname=").append("cxb");
                params.append("&brand=").append(brand);

                List<LogItem> list = data.get(num);
                for(LogItem log : list){
                    log.setUid(uid);
                    log.setCnid(cnid);
                    log.setImsi(imsi);
                }
                sendLogs(list,params.toString());
                Thread.sleep(sleepTime[random.nextInt(sleepTime.length)]);
            }
            System.out.println("日志上报结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendLogs(List<LogItem> logs,String params) throws Exception {
        ClientLog.Clientlog.OperLog.Builder operLog;
        ClientLog.Clientlog.Builder builder = ClientLog.Clientlog.newBuilder();
        for (LogItem logitem : logs) {
            operLog = ClientLog.Clientlog.OperLog.newBuilder();
            operLog.initParse(logitem);
            builder.addOperLog(operLog);
        }
//        System.out.println(params);
//        System.out.println(builder.build());
        byte[] bytes = builder.build().toByteArray();
        if (bytes != null && bytes.length > 0) {
            String url = UrlManager.getReportDatasUrl();
            String result = ConnectUtil.postByteData(url, bytes,params);
            System.out.println("上报返回结果："+result);
        }
    }

    public static String getUid(){
        Random random = new Random();
        String uid = String.valueOf(14769730 + random.nextInt(30000000));
        return uid;
    }

    public static String getCnid(){
        String[] cnids = {"1045","1046","1047","1048","1049","103+50","1051","1052","1053","1054","1055","1056","1057","1058","1059","1060","1061",
                "1062","1063","1064","1065","1066","1067","1068","1069","1070","1071","1072","1073","1074","1075","1076","1077","1078","1079","1080","1081","1082","1083","1084",
                "1085","1086","1087","1088","1089","1090","1091","1092","1093","1094","1095","1096","1097","1098","1099","1100","1101","1102","1103","1104","1105","1106",
                "1107","1108","1109","1110","1111","1112","1113","1114","1115","1116","1117","1118","1119","1120","1121","1122","1123","1124","1125","1126","1127","1128",
                "1129","1130","1131","1132","1133","1134","1135","1136","1137","1138","1139","1140","1141","1142","1143","1144","1145","1146","1147","1148","1149","1150",
                "1151","1152","1153","1154","1155","1156","1157","1158","1159","1160","1161","1162","1163","1164","1165","1166","1167","1168","1169","1170","1171","1172","1173","1174",
                "1175","1176","1177","1178","1179","1180","1181","1182","1183","1184","1185","1186","1187","1188","1189","1190","1191","1192","1193","1194","1195","1196","1197","1198",
                "1199","1200","1201","1202","1203","1204","1205","1206","1207","1208","1209","1210","1211","1212","1213","1214","1215","1216","1217","1218","1219","1220","1221","1222",
                "1223","1224","1225","1226","1227","1228","1229","1230","1231","1232","1233","1234","1235","1236","1237","1238","1239","1240","1241","1242","1243","1244","1245","1246",
                "1247","1248","1249","1250","1251","1252","1253","1254","1255","1256","1257","1258","1259","1260","1261","1262","1263","1264","1265","1266","1267","1268","1269","1270",
                "1271","1272","1273","1274","1275","1276","1277","1278","1279","1280","1281","1282","1283","1284","1285","1286","1287","1288","1289","1290","1291","1292","1293","1294",
                "1295","1296","1297","1298","1299","1300","1301","1302","1303","1304","1305","1306","1307","1308","1309","1310","1311","1312","1313","1314","1315","1316","1317","1318",
                "1319","1320","1321","1322","1323","1324","1325","1326","1327","1328","1329","1330","1331","1332","1333","1334","1335","1336","1337","1338","1339","1340","1341","1342",
                "1343","1344","1345","1346","1347","1348","1349","1350","1351","1352","1353","1354","1355","1356","1357","1358","1359","1360","1361","1362","1363","1364","1365","1366",
                "1367","1368","1369","1370","1371","1372","1373","1374","1375","1376","1377","1378","1379","1380","1381","1382","1383","1384","1385","1386","1387","1388","1389",
                "1390","1391","1392","1393","1394","1395","1396","1397","1398","1399","1400","1401","1402","1403","1404","1405","1406","1407","1408","1409","1410","1411","1412",
                "1413","1414","1415","1416","1417","1418","1419","1420","1421","1422","1423","1424","1425","1426","1427","1428","1429","1430","1431","1432","1433","1434","1435",
                "1436","1437","1438","1439","1440","1441","1442","1443","1444","1445","1446","1447","1448","1449","1450","1451","1452","1453","1454","1455","1456","1457","1458",
                "1459","1460","1461","1462","1463","1464","1465","1466","1467","1468","1469","1470","1471","1472","1473","1474","1475","1476","1477","1478","1479","1480","1481",
                "1482","1483","1484","1485","1486","1487","1488","1489","1490","1491","1492","1493","1494","1495","1496","1497","1498","1499","1500","1501","1502","1503","1504",
                "1505","1506","1507","1508","1509","1510","1511","1512","1513","1514","1515","1516","1517","1518","1519","1520","1521","1522","1523","1524","1525","1526","1527",
                "1528","1529","1530","1531","1532","1533","1534","1535","1537","1538","1539","1540","1541","1542","1543","1544","1545","1546","1547","1548","1549","1550","1551",
                "1552","1553","1554","1555","1556","1557","1558","1559","1560","1561","1562","1563","1564","1565","1566","1567","1568","1569","1570","1571","1572","1573","1574",
                "1575","1576","1577","1578","1579","1580","1581","1582","1583","1584","1585","1586","1587","1588","1589","1590","1591","1592","1593"};
        Random random = new Random();
        return cnids[random.nextInt(cnids.length)];
    }

    public static String getImei(){
        StringBuffer imei = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i< 15; i++){
            imei.append(random.nextInt(10));
        }
        return imei.toString();
    }

    public static String getImsi(){
        StringBuffer imsi = new StringBuffer("460");
        Random random = new Random();
        for(int i = 0; i< 12; i++){
            imsi.append(random.nextInt(10));
        }
        return imsi.toString();
    }


    public static String getMac(){
        StringBuffer mac = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 17; i++){
            mac.append(random.nextInt(10));
        }
        String[] str = {"a","b","c","d","e","f","0","1","2","3","4","5","6","7","8","9"};
        for(int i = 0; i< 10; i++){
            mac.append(str[random.nextInt(16)]);
        }
        return mac.toString();
    }

    public static String getBrand(){
        String[] brands = {"Xiaomi","motorola","HUAWEI","samsung","vivo","oppo","Meizu","lenovo","SONY","Moto","htc","ZTE","Hisense"};
        Random random = new Random();
        return brands[random.nextInt(brands.length)];
    }

    public static String getModel(){
        String[] models = {"Ml+5","XT1570","NEX","Find X","z1","Galaxy","R11","R15","nova","mix","Axon","Blade"};
        Random random = new Random();
        return models[random.nextInt(models.length)];
    }

    public static void main(String[] args) {
        LogController controller = new LogController();
        controller.initLog();
    }
}
