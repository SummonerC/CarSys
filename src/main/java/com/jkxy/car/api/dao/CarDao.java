package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CarDao {
    @Select("select * from carMessage")
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName);

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries},carNum=#{carNum} where id = #{id}")
    void updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries,carNum) values(#{carName},#{carType},#{price},#{carSeries},#{carNum})")
    void insertCar(Car car);

    @Update("update carmessage set carNum=carNum-#{carNum} where id = #{id}")
    void buyCar(int id,int carNum);

    @Select("select * from carmessage where carName like '%${likeName}%' limit 0,3")
    List<Car> findByLike(String likeName);
}
