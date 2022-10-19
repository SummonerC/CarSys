package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 通过id购买
     *
     * @param id,carNum
     * @return
     */
    @PostMapping("buyCar/{id}/{carNum}")
    public synchronized JSONResult buyCar(@PathVariable int id,@PathVariable int carNum) {
        Car carBefore = carService.findById(id);
        int inventory = carBefore.getCarNum();
        if (inventory-carNum<0) {
            return JSONResult.ok("库存不足！！！当前库存为: " + inventory + " 辆，您购买的数量为: " + carNum + " 辆。");
        }
        carService.buyCar(id,carNum);
        Car carAfter = carService.findById(id);
        return JSONResult.ok(carAfter);
    }

    /**
     * 通过车名查询
     *
     * @param likeName
     * @return
     */
    @GetMapping("findByLike/{likeName}")
    public JSONResult findByLike(@PathVariable String likeName) {
        List<Car> cars = carService.findByLike(likeName);
        return JSONResult.ok(cars);
    }
}
