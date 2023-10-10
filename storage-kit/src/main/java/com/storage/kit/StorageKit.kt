package com.storage.kit

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Author: 信仰年轻
 * Date: 2020-12-25 16:04
 * Email: hydznsqk@163.com
 * Des:基于Jetpack Room 离线缓存框架
 *
 * Room是轻量级orm数据库，本质上是一个SQLite抽象层，使用更加简单（类似于Retrofit库）
 * 开发阶段通过注解的方式标记相关功能，编译时自动生成响应的impl实现类
 * 创建room数据库，三大件
 * 1.@Entity,表示数据库中的表
 * 2.@Dao（data acces object数据访问对象）,数据操作对象，里面是各种增删改查的方法，编译的时候会扫描这些注解生成相应的类
 * 3.@Databse数据库：定义数据库，必须是RoomDatabase的抽象类，然后添加注解@Databse，
 * 在这个注解中关联上表，然后创建抽象字段关联上上面创建的Dao对象，主要就是关联上表和数据操作实体，在编译的时候都会生成相对于的实现类对象
 *
 * 本组件实现思路:
 * 首先定义表,里面两个字段Key 和Data,Key唯一且自增为主键
 * 然后定义Dao用来增删改查
 * 在之后定义Databse数据库,写成单例的
 * 自己写个使用类,首先是存储,定义两个参数,key和Data,key为String类型,Data是object类型,
 * 把我们的data通过ObjectOutPutStream转成流然后存到数据库中,反正每次都存,覆盖存
 * 取的时候,通过key找到data,并通过ObjectInputStream转成相应的对象返回即可,如果找不到返回空
 * 在提供一个删除的方法,也是通过key去删除data
 *
 */
object StorageKit {

    /**
     * 存储缓存
     */
    fun <T> saveCache(key: String, body: T) {
        val cache = Cache()
        cache.key = key
        cache.data = toByteArray(body)
        CacheDatabase.get().cacheDao.saveCache(cache)
    }

    /**
     * 获取缓存
     */
    fun <T> getCache(key: String): T? {
        val cache = CacheDatabase.get().cacheDao.getCache(key)
        return (if (cache?.data != null) {
            toObject(cache.data)
        } else null) as? T
    }

    /**
     * 删除缓存
     */
    fun deleteCache(key: String) {
        val cache = Cache()
        cache.key = key
        CacheDatabase.get().cacheDao.deleteCache(cache)
    }

    private fun <T> toByteArray(body: T): ByteArray? {
        var baos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(body)
            oos.flush()
            return baos.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            baos?.close()
            oos?.close()
        }
        return ByteArray(0)
    }

    private fun toObject(data: ByteArray?): Any? {
        var bais: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            bais = ByteArrayInputStream(data)
            ois = ObjectInputStream(bais)
            return ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            bais?.close()
            ois?.close()
        }
        return null
    }
}