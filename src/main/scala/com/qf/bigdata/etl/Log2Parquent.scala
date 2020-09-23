package com.qf.bigdata.etl

import org.apache.spark.sql.SparkSession

/**
 * 数据加载，格式转换
 */
object Log2Parquent {
  def main(args: Array[String]): Unit = {
    // 参数判断

    // 创建上下文
    val spark = SparkSession.builder().master("local[2]").appName(this.getClass.getName)
      .getOrCreate()
    // 配置参数

  }
}
