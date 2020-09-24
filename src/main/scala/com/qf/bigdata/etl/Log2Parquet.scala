package com.qf.bigdata.etl

import com.qf.bigdata.bean.Logs
import com.qf.bigdata.util.String2Utils
import com.typesafe.config.ConfigFactory
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}


/**
 * 数据加载，格式转换
 */
object Log2Parquet {
  def main(args: Array[String]): Unit = {
    // 参数判断 传入的参数分别为输入路径和输出路径
    if(args.length != 2){
      // 退出
      sys.exit()
    }
      // 把参数存入一个数组中
    val Array(inputPath,outputPath) = args
    // 创建上下文
    val spark = SparkSession.builder().master("local").appName(this.getClass.getName)
      .getOrCreate()
    // 导入隐式转换
    import spark.implicits._

    // 加载配置文件 properties/config/json
    val config = ConfigFactory.load()
    // 方式1
//    val prop = new Properties()
//    prop.setProperty("spark.sql.parquet.compression.codec",config.getString("spark.sql.parquet.compression.codec"))
//    prop.setProperty("spark.serializer",config.getString("spark.serializer"))
//    spark.sqlContext.setConf(prop)
    // 方式2
    spark.sqlContext.setConf("spark.sql.parquet.compression.codec",config.getString("spark.sql.parquet.compression.codec"))
    spark.sqlContext.setConf("spark.serializer",config.getString("spark.serializer"))

    // 读取数据
    val lines = spark.sparkContext.textFile(inputPath)
    val logRDD: RDD[Logs] = lines.map(_.split(",", -1)).filter(_.length >= 85)
      .map(arr => {
        // 封装Bean
        new Logs(
          arr(0),
          String2Utils.str2Int(arr(1)),
          String2Utils.str2Int(arr(2)),
          String2Utils.str2Int(arr(3)),
          String2Utils.str2Int(arr(4)),
          arr(5),
          arr(6),
          String2Utils.str2Int(arr(7)),
          String2Utils.str2Int(arr(8)),
          String2Utils.str2Double(arr(9)),
          String2Utils.str2Double(arr(10)),
          arr(11),
          arr(12),
          arr(13),
          arr(14),
          arr(15),
          arr(16),
          String2Utils.str2Int(arr(17)),
          arr(18),
          arr(19),
          String2Utils.str2Int(arr(20)),
          String2Utils.str2Int(arr(21)),
          arr(22),
          arr(23),
          arr(24),
          arr(25),
          String2Utils.str2Int(arr(26)),
          arr(27),
          String2Utils.str2Int(arr(28)),
          arr(29),
          String2Utils.str2Int(arr(30)),
          String2Utils.str2Int(arr(31)),
          String2Utils.str2Int(arr(32)),
          arr(33),
          String2Utils.str2Int(arr(34)),
          String2Utils.str2Int(arr(35)),
          String2Utils.str2Int(arr(36)),
          arr(37),
          String2Utils.str2Int(arr(38)),
          String2Utils.str2Int(arr(39)),
          String2Utils.str2Double(arr(40)),
          String2Utils.str2Double(arr(41)),
          String2Utils.str2Int(arr(42)),
          arr(43),
          String2Utils.str2Double(arr(44)),
          String2Utils.str2Double(arr(45)),
          arr(46),
          arr(47),
          arr(48),
          arr(49),
          arr(50),
          arr(51),
          arr(52),
          arr(53),
          arr(54),
          arr(55),
          arr(56),
          String2Utils.str2Int(arr(57)),
          String2Utils.str2Double(arr(58)),
          String2Utils.str2Int(arr(59)),
          String2Utils.str2Int(arr(60)),
          arr(61),
          arr(62),
          arr(63),
          arr(64),
          arr(65),
          arr(66),
          arr(67),
          arr(68),
          arr(69),
          arr(70),
          arr(71),
          arr(72),
          String2Utils.str2Int(arr(73)),
          String2Utils.str2Double(arr(74)),
          String2Utils.str2Double(arr(75)),
          String2Utils.str2Double(arr(76)),
          String2Utils.str2Double(arr(77)),
          String2Utils.str2Double(arr(78)),
          arr(79),
          arr(80),
          arr(81),
          arr(82),
          arr(83),
          String2Utils.str2Int(arr(84))
        )
      })
//    spark.createDataset(logsRDD).show()
    val df = spark.createDataFrame(logRDD)
    // 保存数据结果
//    df.write.save(outputPath)



  }
}
